//
//  LoginVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 11.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit
import RxSwift

class LoginVC: UIViewController {
    
    private var model: LoginVM!
    private var disposeBag = DisposeBag()

    @IBOutlet weak var loginView: UIView!
    @IBOutlet weak var viewTitle: UILabel!
    @IBOutlet weak var loginLabel: UILabel!
    @IBOutlet weak var passwordLabel: UILabel!
    @IBOutlet weak var loginField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var loginButton: UIButton!
    @IBOutlet weak var registrationButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        buildUI()
        bindUI()
    }
    
    func setModel(model: LoginVM) {
        self.model = model
    }
    
    private func buildUI() {
        navigationController?.navigationBar.isHidden = true
        
        loginView.layer.cornerRadius = Constants.UI.View.cornerRadius
        
        buildTitleLabel()
        buildLabel(label: loginLabel, withText: R.string.localizable.onboardingUserLogin())
        buildLabel(label: passwordLabel, withText: R.string.localizable.onboardingUserPassword())

        buildLoginButton()
        buildRegistrationButton()
    }
    
    private func buildTitleLabel() {
        viewTitle.text = R.string.localizable.onboardingLoginTitle()
        viewTitle.font = Constants.UI.Main.titleFont
    }
    
    private func buildLabel(label: UILabel, withText text: String) {
        label.font = Constants.UI.Main.font
        label.text = text
    }
    
    private func buildLoginButton() {
        loginButton.setTitle(R.string.localizable.onboardingLoginReadyButton(), for: .normal)
        loginButton.titleLabel?.font = Constants.UI.Main.font
        loginButton.layer.cornerRadius = Constants.UI.Button.cornerRadius
        loginButton.backgroundColor = Constants.UI.Main.color
        loginButton.tintColor = Constants.UI.Main.alternativeFontColor
    }
    
    private func buildRegistrationButton() {
        registrationButton.setTitle(R.string.localizable.onboardingLoginRegistrationButton(), for: .normal)
        registrationButton.titleLabel?.font = Constants.UI.Main.smallFont
        registrationButton.tintColor = Constants.UI.Main.color
    }
    
    private func bindUI() {
        let loginButtonClick = Observable<Void>.merge(loginButton.rx.tap.asObservable())
        let registrationButtonClick = Observable<Void>.merge(registrationButton.rx.tap.asObservable())
        let input = LoginVM.Input(loginButtonClick: loginButtonClick,
                                  registrationButtonClick: registrationButtonClick)
        let output = model.transform(input: input)
    }
}
