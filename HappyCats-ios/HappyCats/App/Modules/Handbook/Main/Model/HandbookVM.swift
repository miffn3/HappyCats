//
//  HandbookVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxSwift
import RxCocoa

final class HandbookVM: Stepper {
    let steps = PublishRelay<Step>()
    
    private let disposeBag = DisposeBag()
    private let userService: UserService
    
    struct Input { }
    
    struct Output {
        let breeds: Driver<[Breed]>
        let disease: Driver<[Disease]>
    }
    
    init(userService: UserService) {
        self.userService = userService
    }
    
    func transform(input: Input) -> Output {
        let breeds = BehaviorRelay<[Breed]>(value: [])
        let disease = BehaviorRelay<[Disease]>(value: [])
        
        BreedAPI.getAllBreeds(token: self.userService.getToken().orEmpty)
            .subscribeOn(ConcurrentDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { data in
                breeds.accept(data)
            })
            .disposed(by: self.disposeBag)
        
        DiseaseAPI.getAllDisease(token: self.userService.getToken().orEmpty)
            .subscribeOn(ConcurrentDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { data in
                disease.accept(data)
            })
            .disposed(by: self.disposeBag)
        
        let output = Output(breeds: breeds.asDriver(onErrorJustReturn: []),
                            disease: disease.asDriver(onErrorJustReturn: []))
        return output
    }
}
