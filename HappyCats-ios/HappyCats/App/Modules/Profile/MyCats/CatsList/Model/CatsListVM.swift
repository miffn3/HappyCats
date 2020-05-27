//
//  CatsListVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 18.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxSwift
import RxCocoa
import Kingfisher

final class CatsListVM: Stepper {
    let steps = PublishRelay<Step>()
    
    private let disposeBag = DisposeBag()
    private let userService: UserService
    
    struct Input {
        let selectedCat: Observable<Int>
        let deletedCat: Observable<Int>
        let addCatButton: Observable<Void>
    }
    
    struct Output {
        let cats: Driver<[Cat]>
    }
    
    init(userService: UserService) {
        self.userService = userService
    }
    
    func transform(input: Input) -> Output {
        let cats = BehaviorRelay<[Cat]>(value: [])
        
        fetchCats(cats: cats)
        
        input.selectedCat
            .subscribe(onNext: { index in
                guard let id = cats.value[safe: index]?.id else { return }
                self.steps.accept(AppStep.cat(withId: id))
            }).disposed(by: disposeBag)
        
        input.deletedCat
            .subscribe(onNext: { index in
                guard let id = cats.value[safe: index]?.id else { return }
                CatAPI.deleteCat(token: self.userService.getToken().orEmpty, id: id)
                    .subscribe(onNext: { data in
                        self.fetchCats(cats: cats)
                    }).disposed(by: self.disposeBag)
            }).disposed(by: disposeBag)
        
        input.addCatButton
            .subscribe(onNext: { _ in
                self.steps.accept(AppStep.addCat)
            }).disposed(by: disposeBag)
        
        return Output(cats: cats.asDriver(onErrorJustReturn: []))
    }
    
    private func fetchCats(cats: BehaviorRelay<[Cat]>) {
        UserAPI.getUser(token: userService.getToken().orEmpty)
            .subscribeOn(ConcurrentDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { data in
                cats.accept(data.cats ?? [])
            })
            .disposed(by: self.disposeBag)
    }
}

