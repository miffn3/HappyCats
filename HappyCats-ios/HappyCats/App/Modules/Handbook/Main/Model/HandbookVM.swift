//
//  HandbookVM.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import RxFlow
import RxCocoa

final class HandbookVM: Stepper {
    let steps = PublishRelay<Step>()
    var cats = [Cat]()
    var disease = [Disease]()
    
    init() {
        updateCats()
        updateDisease()
    }
    
    func updateCats() {
        cats.removeAll()
        cats.append(Cat(name: "Cat 1"))
        cats.append(Cat(name: "Cat 2"))
    }
    
    func updateDisease() {
        disease.removeAll()
        disease.append(Disease(title: "Disease 1"))
        disease.append(Disease(title: "Disease 2"))
    }
}
