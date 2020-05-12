//
//  NewsListVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 07.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxCocoa

final class NewsListVM: Stepper {
    let steps = PublishRelay<Step>()
    var news = [News]()
    
    init() {
        updateNews()
    }
    
    func updateNews() {
        news.removeAll()
        news.append(News(id: 1, title: "News 1"))
        news.append(News(id: 2, title: "News 2"))
    }
    
    public func pick(newsId: Int) {
        self.steps.accept(AppStep.newsDetail(withId: newsId))
    }
}
