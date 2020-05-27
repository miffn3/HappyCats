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
import Kingfisher

final class ResultExpertVM: Stepper {
    let steps = PublishRelay<Step>()
    
    private let disposeBag = DisposeBag()
    private let userService: UserService
    private let result: String
    private let id: Int
    
    struct Input {
        let againButtonClick: Observable<Void>
        let moreViewClick: Observable<UITapGestureRecognizer>
    }
    
    struct Output {
        let result: Driver<String>
        let diseaseImage: Driver<UIImage>
        let diseaseTitle: Driver<String>
    }
    
    init(userService: UserService, result: String, id: Int) {
        self.userService = userService
        self.result = result
        self.id = id
    }
    
    func transform(input: Input) -> Output {
        let diseaseTitle = BehaviorRelay<String>(value: "")
        let diseaseImage = BehaviorRelay<UIImage>(value: R.image.emptyPhoto() ?? UIImage())
        
        DiseaseAPI.getDisease(withId: id, token: userService.getToken().orEmpty)
            .subscribeOn(ConcurrentDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { disease in
                diseaseTitle.accept(disease.name.orEmpty)
                if let img = disease.photo, let imgUrl = URL(string: img) {
                    KingfisherManager.shared
                        .retrieveImage(with: ImageResource(downloadURL: imgUrl, cacheKey: nil)) { result in
                            switch result {
                            case .success(let image):
                                diseaseImage.accept(image.image)
                            case .failure(let error):
                                print(error.localizedDescription)
                            }
                    }
                }
            })
            .disposed(by: disposeBag)
        
        input.againButtonClick
            .throttle(.seconds(1), scheduler: MainScheduler.instance)
            .subscribe(onNext: { _ in
                self.steps.accept(AppStep.again)
            }).disposed(by: disposeBag)
        
        input.moreViewClick
            .throttle(.seconds(1), scheduler: MainScheduler.instance)
            .subscribe(onNext: {_ in
                self.steps.accept(AppStep.disease(withId: self.id))
            }).disposed(by: disposeBag)
        
        let output = Output(result: BehaviorRelay<String>(value: result).asDriver(onErrorDriveWith: .never()),
                            diseaseImage: diseaseImage.asDriver(onErrorDriveWith: .never()),
                            diseaseTitle: diseaseTitle.asDriver(onErrorDriveWith: .never()))
        return output
    }
}
