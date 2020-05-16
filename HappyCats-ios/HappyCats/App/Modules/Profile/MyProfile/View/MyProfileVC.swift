//
//  MyProfileVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 14.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class MyProfileVC: UIViewController {
    
    @IBOutlet private weak var userImage: UIImageView!
    @IBOutlet private weak var nameLabel: UILabel!
    @IBOutlet private weak var loginLabel: UILabel!
    @IBOutlet private weak var emailLabel: UILabel!
    @IBOutlet private weak var phoneLabel: UILabel!
    @IBOutlet private weak var birthdayLabel: UILabel!
    @IBOutlet private weak var nameField: UITextField!
    @IBOutlet private weak var loginField: UITextField!
    @IBOutlet private weak var emailField: UITextField!
    @IBOutlet private weak var phoneField: UITextField!
    @IBOutlet private weak var birthdayField: UITextField!
    @IBOutlet private weak var saveButton: UIButton!
    
    private var model: MyProfileVM!
    private var disposeBag = DisposeBag()

    override func viewDidLoad() {
        super.viewDidLoad()
        buildUI()
        bindUI()
    }
    
    func setModel(model: MyProfileVM) {
        self.model = model
    }
    
    private func buildUI() {
        buildLabel(label: nameLabel, text: R.string.localizable.profileMyProfileName())
        buildLabel(label: loginLabel, text: R.string.localizable.profileMyProfileLogin())
        buildLabel(label: emailLabel, text: R.string.localizable.profileMyProfileEmail())
        buildLabel(label: phoneLabel, text: R.string.localizable.profileMyProfilePhone())
        buildLabel(label: birthdayLabel, text: R.string.localizable.profileMyProfileBirthday())
        
        buildButton()
        buildImage()
    }
    
    private func buildLabel(label: UILabel, text: String) {
        label.text = text + ":"
        label.font = Constants.UI.Main.font
    }
    
    private func buildButton() {
        saveButton.layer.cornerRadius = Constants.UI.Button.cornerRadius
        saveButton.backgroundColor = Constants.UI.Main.color
        saveButton.tintColor = Constants.UI.Main.alternativeFontColor
        saveButton.titleLabel?.font = Constants.UI.Main.font
        saveButton.setTitle(R.string.localizable.buttonSave(), for: .normal)
    }
    
    private func buildImage() {
        userImage.image = R.image.emptyPhoto()
        userImage.layer.cornerRadius = userImage.frame.width / 2
    }
    
    private func bindUI() {
        let saveButtonTap = Observable<Void>.merge(saveButton.rx.tap.asObservable())
        let input = MyProfileVM.Input(name: nameField.rx.text.asObservable(),
                                      login: loginField.rx.text.asObservable(),
                                      email: emailField.rx.text.asObservable(),
                                      phone: phoneField.rx.text.asObservable(),
                                      birthday: birthdayField.rx.text.asObservable(),
                                      saveButton: saveButtonTap)
        let output = model.transform(input: input)
        output.name.drive(nameField.rx.text).disposed(by: disposeBag)
        output.login.drive(loginField.rx.text).disposed(by: disposeBag)
        output.email.drive(emailField.rx.text).disposed(by: disposeBag)
        output.phone.drive(phoneField.rx.text).disposed(by: disposeBag)
        output.birthday.drive(birthdayField.rx.text).disposed(by: disposeBag)
        output.userImage.drive(userImage.rx.image).disposed(by: disposeBag)
    }
}
