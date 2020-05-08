//
//  NewsDetailVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit

class NewsDetailVC: UIViewController {
    
    private var model: NewsDetailVM!

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    func setModel(model: NewsDetailVM) {
        self.model = model
    }
}
