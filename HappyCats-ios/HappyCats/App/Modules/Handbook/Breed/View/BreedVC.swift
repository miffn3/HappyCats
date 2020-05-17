//
//  BreedVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 17.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class BreedVC: UIViewController {
    
    private var model: BreedVM!
    private let disposeBag = DisposeBag()

    @IBOutlet weak var breedImage: UIImageView!
    @IBOutlet weak var breedTitle: UILabel!
    @IBOutlet weak var breedDescription: UILabel!
    @IBOutlet weak var titleLeftView: UIView!
    @IBOutlet weak var viewTopHeight: NSLayoutConstraint!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        buildUI()
        bindUI()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        navigationController?.navigationBar.setBackgroundImage(nil, for: .default)
        navigationController?.navigationBar.shadowImage = nil
    }
    
    func setModel(model: BreedVM) {
        self.model = model
    }
    
    private func buildUI() {
        viewTopHeight.constant = -((navigationController?.navigationBar.frame.height ?? 0) + UIApplication.shared.statusBarFrame.height)
        buildNavigation()
        buildLabels()
        buildTitleView()
    }
    
    private func buildNavigation() {
        navigationController?.navigationBar.setBackgroundImage(UIImage(), for: .default)
        navigationController?.navigationBar.shadowImage = UIImage()
        navigationController?.hidesBarsOnSwipe = true
    }
    
    private func buildLabels() {
        breedDescription.font = Constants.UI.Main.font
        breedTitle.font = Constants.UI.Main.titleFont
    }
    
    private func buildTitleView() {
        titleLeftView.backgroundColor = Constants.UI.Main.color
        titleLeftView.layer.cornerRadius = 7.5
    }
    
    private func bindUI() {
        let input = BreedVM.Input()
        
        let output = model.transform(input: input)
        output.title.drive(breedTitle.rx.text).disposed(by: disposeBag)
        output.description.drive(breedDescription.rx.text).disposed(by: disposeBag)
        output.image.drive(breedImage.rx.image).disposed(by: disposeBag)
    }
}
