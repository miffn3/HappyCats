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
import RxGesture

class ResultExpertVC: UIViewController {
    
    private var model: ResultExpertVM!
    private let disposeBag = DisposeBag()

    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var againButton: UIButton!
    @IBOutlet weak var learnMoreLabel: UILabel!
    @IBOutlet weak var diseaseView: UIView!
    @IBOutlet weak var diseaseImage: UIImageView!
    @IBOutlet weak var diseaseLabel: UILabel!
    @IBOutlet weak var nextImage: UIImageView!
    @IBOutlet weak var leftDiseaseView: UIView!
    
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
        buildLeftDiseaseView()
        buildMoreLabel()
        buildNextButton()
        buildAgainButton()
    }
    
    private func buildDescription() {
        descriptionLabel.font = Constants.UI.Main.titleFont
    }
    
    private func buildLeftDiseaseView() {
        leftDiseaseView.backgroundColor = Constants.UI.Main.mainColor
        leftDiseaseView.layer.cornerRadius = 7.5
    }
    
    private func buildMoreLabel() {
        learnMoreLabel.font = Constants.UI.Main.titleFont
        learnMoreLabel.text = R.string.localizable.expertsystemMore()
    }
    
    private func buildNextButton() {
        nextImage.image = R.image.nextIcon()
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
        let moreViewClick = Observable<UITapGestureRecognizer>.merge(diseaseView.rx.tapGesture().asObservable())
        let input = ResultExpertVM.Input(againButtonClick: againButtonClick, moreViewClick: moreViewClick)
        let output = model.transform(input: input)
        output.result.drive(descriptionLabel.rx.text).disposed(by: disposeBag)
        output.diseaseImage.drive(diseaseImage.rx.image).disposed(by: disposeBag)
        output.diseaseTitle.drive(diseaseLabel.rx.text).disposed(by: disposeBag)
    }
}
