//
//  HandbookTVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit

class HandbookTVC: UITableViewCell {

    @IBOutlet weak var cellImage: UIImageView!
    @IBOutlet weak var cellLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func configCatCell(cat: Cat) {
        cellLabel.text = cat.name
        cellImage.image = R.image.stub()
    }
    
    func configDiseaseCell(disease: Disease) {
        cellLabel.text = disease.title
        cellImage.image = R.image.stub()
    }
    
    func configEmpty() {
        cellLabel.text = R.string.localizable.emptyTitle()
        cellImage.image = R.image.stub()
    }
}
