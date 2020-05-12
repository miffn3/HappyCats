//
//  NewsTVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 07.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit

final class NewsTVC: UITableViewCell {

    @IBOutlet private weak var newsImage: UIImageView!
    @IBOutlet private weak var newsTitle: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func config(data: News) {
        newsTitle.text = data.title
        newsImage.image = R.image.stub()
    }
    
    func configEmpty() {
        newsTitle.text = R.string.localizable.emptyTitle()
        newsImage.image = R.image.stub()
    }
}
