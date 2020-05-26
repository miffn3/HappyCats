//
//  ResultExpertVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 26.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxSwift
import RxCocoa

final class ResultExpertVM: Stepper {
    let steps = PublishRelay<Step>()
    
    private let disposeBag = DisposeBag()
    private let userService: UserService
    private let result: String
    
    struct Input {
        let againButtonClick: Observable<Void>
    }
    
    struct Output {
        let result: Driver<String>
    }
    
    init(userService: UserService, result: String) {
        self.userService = userService
        self.result = result
    }
    
    func transform(input: Input) -> Output {
        input.againButtonClick
            .throttle(.seconds(1), scheduler: MainScheduler.instance)
            .subscribe(onNext: { _ in
                self.steps.accept(AppStep.again)
            }).disposed(by: disposeBag)
        
        let output = Output(result: BehaviorRelay<String>(value: result).asDriver(onErrorDriveWith: .never()))
        return output
    }
}
