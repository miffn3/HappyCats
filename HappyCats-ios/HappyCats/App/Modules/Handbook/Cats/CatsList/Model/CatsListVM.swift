//
//  CatsListVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxCocoa

class CatsListVM: Stepper {
    let steps = PublishRelay<Step>()
    var cats = [Cat]()
    
    init() {
        
    }
    
    func updateNews() {
        cats.append(Cat(name: "Cat 1"))
    }
}
