//
//  ResultExpertVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 26.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class ResultExpertVC: UIViewController {
    
    private var model: ResultExpertVM!
    private let disposeBag = DisposeBag()

    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var againButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        buildUI()
        bindUI()
    }
    
    func setModel(model: ResultExpertVM) {
        self.model = model
    }
    
    private func buildUI() {
        buildDescription()
        buildAgainButton()
    }
    
    private func buildDescription() {
        descriptionLabel.font = Constants.UI.Main.titleFont
    }
    
    private func buildAgainButton() {
        againButton.setTitle(R.string.localizable.expertsystemButtonAgain(), for: .normal)
        againButton.titleLabel?.font = Constants.UI.Main.mainFont
        againButton.layer.cornerRadius = Constants.UI.Button.cornerRadius
        againButton.backgroundColor = Constants.UI.Main.mainColor
        againButton.tintColor = Constants.UI.Main.alternativeFontColor
    }
    
    private func bindUI() {
        let againButtonClick = Observable<Void>.merge(againButton.rx.tap.asObservable())
        let input = ResultExpertVM.Input(againButtonClick: againButtonClick)
        let output = model.transform(input: input)
        output.result.drive(descriptionLabel.rx.text).disposed(by: disposeBag)
    }
}
