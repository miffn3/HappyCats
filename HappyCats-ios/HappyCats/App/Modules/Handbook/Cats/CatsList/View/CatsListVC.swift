//
//  CatsListVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit

class CatsListVC: UIViewController {
    
    private var model: CatsListVM!

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    func setModel(model: CatsListVM) {
        self.model = model
    }
}
