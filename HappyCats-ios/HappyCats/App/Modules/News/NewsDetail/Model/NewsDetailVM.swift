//
//  NewsDetailVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxCocoa

final class NewsDetailVM: Stepper {
    
    private var id: Int
    
    let steps = PublishRelay<Step>()
    var news = News()
    
    init(withId id: Int) {
        self.id = id
        updateNews()
    }
    
    func updateNews() {
        news = News(id: 1, title: "News 1")
    }
}

