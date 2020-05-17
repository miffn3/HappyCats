//
//  NewsDetailVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit

final class NewsDetailVC: UIViewController {
    
    private var model: NewsDetailVM!

    @IBOutlet weak var newsImage: UIImageView!
    @IBOutlet weak var newsTitle: UILabel!
    @IBOutlet weak var newsDescription: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        buildUI()
    }
    
    func setModel(model: NewsDetailVM) {
        self.model = model
    }
    
    private func buildUI() {
        navigationController?.navigationBar.setBackgroundImage(UIImage(), for: .default)
        navigationController?.navigationBar.shadowImage = UIImage()
        
        newsImage.image = R.image.stub()
    }
}
