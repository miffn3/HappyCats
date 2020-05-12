//
//  RegistrationVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 12.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxSwift
import RxCocoa

final class RegistrationVM: Stepper {
    let steps = PublishRelay<Step>()
    
    private let disposeBag = DisposeBag()
    
    struct Input {
        let registrationButtonClick: Observable<Void>
        let loginButtonClick: Observable<Void>
    }
    
    struct Output {
        
    }
    
    init() { }
    
    func transform(input: Input) -> Output {
        let output = Output()
        
        input.registrationButtonClick
            .throttle(.seconds(1), scheduler: MainScheduler.instance)
            .subscribe(onNext: { _ in
                self.steps.accept(AppStep.registrationIsComplete)
            }).disposed(by: disposeBag)
        
        input.loginButtonClick
            .throttle(.seconds(1), scheduler: MainScheduler.instance)
            .subscribe(onNext: { _ in
                self.steps.accept(AppStep.login)
            }).disposed(by: disposeBag)
        
        return output
    }
}
