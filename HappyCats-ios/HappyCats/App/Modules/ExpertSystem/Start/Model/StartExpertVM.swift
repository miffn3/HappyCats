//
//  StartExpertVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 26.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxSwift
import RxCocoa

final class StartExpertVM: Stepper {
    let steps = PublishRelay<Step>()
    
    private let disposeBag = DisposeBag()
    private let userService: UserService
    
    struct Input {
        let startButtonClick: Observable<Void>
    }
    
    struct Output {
    }
    
    init(userService: UserService) {
        self.userService = userService
    }
    
    func transform(input: Input) -> Output {
        input.startButtonClick
            .throttle(.seconds(1), scheduler: MainScheduler.instance)
            .observeOn(ConcurrentDispatchQueueScheduler.init(qos: .background))
            .flatMap { _ -> Observable<ExpertSystem> in
                return ExpertSystemAPI.ask(withId: Constants.API.ExpertSystem.startID,
                                           token: self.userService.getToken().orEmpty)
            }
            .subscribe(onNext: { result in
                self.steps.accept(AppStep.question(withId: (result.second?.newId).orEmpty,
                                                   question: (result.second?.value).orEmpty))
            }).disposed(by: disposeBag)
        
        let output = Output()
        return output
    }
}
