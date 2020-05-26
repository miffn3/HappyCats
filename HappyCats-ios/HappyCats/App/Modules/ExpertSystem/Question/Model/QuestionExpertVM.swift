//
//  QuestionExpertVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 26.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxSwift
import RxCocoa

final class QuestionExpertVM: Stepper {
    let steps = PublishRelay<Step>()
    
    private let disposeBag = DisposeBag()
    private let userService: UserService
    private let questionID: String
    private let question: String
    
    struct Input {
        let yesButtonClick: Observable<Void>
        let noButtonClick: Observable<Void>
    }
    
    struct Output {
        let question: Driver<String>
    }
    
    init(userService: UserService, questionID: String, question: String) {
        self.userService = userService
        self.questionID = questionID
        self.question = question
    }
    
    func transform(input: Input) -> Output {
        input.yesButtonClick
            .throttle(.seconds(1), scheduler: MainScheduler.instance)
            .observeOn(ConcurrentDispatchQueueScheduler.init(qos: .background))
            .flatMap { _ -> Observable<ExpertSystem> in
                return ExpertSystemAPI.ask(withId: self.questionID + Constants.API.ExpertSystem.yesID,
                                           token: self.userService.getToken().orEmpty)
            }
            .subscribe(onNext: { result in
                self.steps.accept(AppStep.question(withId: (result.second?.newId).orEmpty,
                                                   question: (result.second?.value).orEmpty))
            }).disposed(by: disposeBag)
        
        input.noButtonClick
            .throttle(.seconds(1), scheduler: MainScheduler.instance)
            .observeOn(ConcurrentDispatchQueueScheduler.init(qos: .background))
            .flatMap { _ -> Observable<ExpertSystem> in
                return ExpertSystemAPI.ask(withId: self.questionID + Constants.API.ExpertSystem.noID,
                                           token: self.userService.getToken().orEmpty)
            }
            .subscribe(onNext: { result in
                self.steps.accept(AppStep.question(withId: (result.second?.newId).orEmpty,
                                                   question: (result.second?.value).orEmpty))
            }).disposed(by: disposeBag)
        
        let output = Output(question: BehaviorRelay<String>(value: question).asDriver(onErrorDriveWith: .never()))
        return output
    }
}
