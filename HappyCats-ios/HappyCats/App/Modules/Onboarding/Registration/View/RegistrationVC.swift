//
//  RegistrationVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 12.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit
import RxSwift

class RegistrationVC: UIViewController {
    
    private var model: RegistrationVM!

    @IBOutlet weak var registrationView: UIView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var nameField: UITextField!
    @IBOutlet weak var emailLabel: UILabel!
    @IBOutlet weak var emailField: UITextField!
    @IBOutlet weak var loginLabel: UILabel!
    @IBOutlet weak var loginField: UITextField!
    @IBOutlet weak var passwordLabel: UILabel!
    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var registrationButton: UIButton!
    @IBOutlet weak var loginButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        buildUI()
        bindUI()
    }
    
    func setModel(model: RegistrationVM) {
        self.model = model
    }
    
    private func buildUI() {
        navigationController?.navigationBar.isHidden = true
        
        registrationView.layer.cornerRadius = Constants.UI.View.cornerRadius
        
        buildTitleLabel()
        buildLabel(label: nameLabel, withText: R.string.localizable.onboardingUserName())
        buildLabel(label: emailLabel, withText: R.string.localizable.onboardingUserEmail())
        buildLabel(label: loginLabel, withText: R.string.localizable.onboardingUserLogin())
        buildLabel(label: passwordLabel, withText: R.string.localizable.onboardingUserPassword())

        buildLoginButton()
        buildRegistrationButton()
    }
    
    private func buildTitleLabel() {
        titleLabel.text = R.string.localizable.onboardingRegistrationTitle()
        titleLabel.font = Constants.UI.Main.titleFont
    }
    
    private func buildLabel(label: UILabel, withText text: String) {
        label.font = Constants.UI.Main.font
        label.text = text
    }
    
    private func buildRegistrationButton() {
        registrationButton.setTitle(R.string.localizable.onboardingRegistrationReadyButton(), for: .normal)
        registrationButton.titleLabel?.font = Constants.UI.Main.font
        registrationButton.layer.cornerRadius = Constants.UI.Button.cornerRadius
        registrationButton.backgroundColor = Constants.UI.Main.color
        registrationButton.tintColor = Constants.UI.Main.alternativeFontColor
    }
    
    private func buildLoginButton() {
        loginButton.setTitle(R.string.localizable.onboardingRegistrationLoginButton(), for: .normal)
        loginButton.titleLabel?.font = Constants.UI.Main.smallFont
        loginButton.tintColor = Constants.UI.Main.color
    }
    
    private func bindUI() {
        let registrationButtonClick = Observable<Void>.merge(registrationButton.rx.tap.asObservable())
        let loginButtonClick = Observable<Void>.merge(loginButton.rx.tap.asObservable())
        let input = RegistrationVM.Input(registrationButtonClick: registrationButtonClick,
                                         loginButtonClick: loginButtonClick)
        let output = model.transform(input: input)
    }
}
