//
//  StartExpertVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 26.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class StartExpertVC: UIViewController {
    
    private var model: StartExpertVM!
    private let disposeBag = DisposeBag()

    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var startButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        buildUI()
        bindUI()
    }
    
    func setModel(model: StartExpertVM) {
        self.model = model
    }
    
    private func buildUI() {
        buildTitle()
        buildDescription()
        buildStartButton()
    }
    
    private func buildTitle() {
        titleLabel.text = R.string.localizable.expertsystemWelcome()
        titleLabel.font = Constants.UI.Main.titleFont
    }
    
    private func buildDescription() {
        descriptionLabel.text = R.string.localizable.expertsystemDescription()
        descriptionLabel.font = Constants.UI.Main.mainFont
    }
    
    private func buildStartButton() {
        startButton.setTitle(R.string.localizable.expertsystemButtonStart(), for: .normal)
        startButton.titleLabel?.font = Constants.UI.Main.mainFont
        startButton.layer.cornerRadius = Constants.UI.Button.cornerRadius
        startButton.backgroundColor = Constants.UI.Main.mainColor
        startButton.tintColor = Constants.UI.Main.alternativeFontColor
    }
    
    private func bindUI() {
        let startButtonClick = Observable<Void>.merge(startButton.rx.tap.asObservable())
        let input = StartExpertVM.Input(startButtonClick: startButtonClick)
        let output = model.transform(input: input)
    }
}
