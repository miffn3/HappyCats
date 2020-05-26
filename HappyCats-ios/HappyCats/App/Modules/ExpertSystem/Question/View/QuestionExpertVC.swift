//
//  QuestionExpertVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 26.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class QuestionExpertVC: UIViewController {
    
    private var model: QuestionExpertVM!
    private let disposeBag = DisposeBag()

    @IBOutlet weak var questionLabel: UILabel!
    @IBOutlet weak var yesButton: UIButton!
    @IBOutlet weak var noButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        buildUI()
        bindUI()
    }
    
    func setModel(model: QuestionExpertVM) {
        self.model = model
    }
    
    private func buildUI() {
        buildQuestion()
        buildYesButton()
        buildNoButton()
    }
    
    private func buildQuestion() {
        questionLabel.font = Constants.UI.Main.titleFont
    }
    
    private func buildYesButton() {
        yesButton.setTitle(R.string.localizable.expertsystemButtonYes(), for: .normal)
        yesButton.titleLabel?.font = Constants.UI.Main.mainFont
        yesButton.layer.cornerRadius = Constants.UI.Button.cornerRadius
        yesButton.backgroundColor = Constants.UI.Button.YesButton.backgroundColor
        yesButton.tintColor = Constants.UI.Main.alternativeFontColor
    }
    
    private func buildNoButton() {
        noButton.setTitle(R.string.localizable.expertsystemButtonNo(), for: .normal)
        noButton.titleLabel?.font = Constants.UI.Main.mainFont
        noButton.layer.cornerRadius = Constants.UI.Button.cornerRadius
        noButton.backgroundColor = Constants.UI.Button.NoButton.backgroundColor
        noButton.tintColor = Constants.UI.Main.alternativeFontColor
    }
    
    private func bindUI() {
        let yesButtonClick = Observable<Void>.merge(yesButton.rx.tap.asObservable())
        let noButtonClick = Observable<Void>.merge(noButton.rx.tap.asObservable())
        let input = QuestionExpertVM.Input(yesButtonClick: yesButtonClick, noButtonClick: noButtonClick)
        let output = model.transform(input: input)
        output.question.drive(questionLabel.rx.text).disposed(by: disposeBag)
    }
}
