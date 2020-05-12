//
//  LoginVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 11.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxSwift
import RxCocoa

final class LoginVM: Stepper {
    let steps = PublishRelay<Step>()
    
    private let disposeBag = DisposeBag()
    
    struct Input {
        let loginButtonClick: Observable<Void>
        let registrationButtonClick: Observable<Void>
    }
    
    struct Output {
        
    }
    
    init() { }
    
    func transform(input: Input) -> Output {
        let output = Output()
        
        input.loginButtonClick
            .throttle(.seconds(1), scheduler: MainScheduler.instance)
            .subscribe(onNext: { _ in
                self.steps.accept(AppStep.loginIsComplete)
            }).disposed(by: disposeBag)
        
        input.registrationButtonClick
            .throttle(.seconds(1), scheduler: MainScheduler.instance)
            .subscribe(onNext: { _ in
                self.steps.accept(AppStep.registration)
            }).disposed(by: disposeBag)
        
        return output
    }
}
