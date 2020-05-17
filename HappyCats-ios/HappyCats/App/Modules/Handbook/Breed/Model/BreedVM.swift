//
//  BreedVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 17.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxSwift
import RxCocoa
import Kingfisher

final class BreedVM: Stepper {
    let steps = PublishRelay<Step>()
    
    private let id: Int
    private let disposeBag = DisposeBag()
    private let userService: UserService

    var news = News()
    
    struct Input { }
    
    struct Output {
        let image: Driver<UIImage>
        let title: Driver<String>
        let description: Driver<String>
    }
    
    init(withId id: Int, userService: UserService) {
        self.id = id
        self.userService = userService
    }
    
    func transform(input: Input) -> Output {
        let title = BehaviorRelay<String>(value: "")
        let description = BehaviorRelay<String>(value: "")
        let breedImage = BehaviorRelay<UIImage>(value: R.image.emptyPhoto() ?? UIImage())
        
        BreedAPI.getBreed(withId: id, token: userService.getToken().orEmpty)
            .subscribeOn(ConcurrentDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { breed in
                title.accept(breed.name.orEmpty)
                description.accept(breed.description.orEmpty)
                if let img = breed.photo, let imgUrl = URL(string: img) {
                    KingfisherManager.shared
                        .retrieveImage(with: ImageResource(downloadURL: imgUrl, cacheKey: nil)) { result in
                            switch result {
                            case .success(let image):
                                breedImage.accept(image.image)
                            case .failure(let error):
                                print(error.localizedDescription)
                            }
                    }
                }
            })
            .disposed(by: disposeBag)
        
        return Output(image: breedImage.asDriver(onErrorDriveWith: .never()),
                      title: title.asDriver(onErrorDriveWith: .never()),
                      description: description.asDriver(onErrorDriveWith: .never()))
    }
}
