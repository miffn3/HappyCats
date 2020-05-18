//
//  MainProfileVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 14.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit
import RxSwift

final class MainProfileVC: UIViewController {
    
    private var model: MainProfileVM!
    private var disposeBag = DisposeBag()
    
    @IBOutlet private weak var userImage: UIImageView!
    @IBOutlet private weak var profileTable: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configProfileTable()
        buildUI()
        bindUI()
    }
    
    func setModel(model: MainProfileVM) {
        self.model = model
    }
    
    private func buildUI() {
        userImage.image = R.image.emptyPhoto()
        userImage.layer.cornerRadius = userImage.frame.width / 2
    }
    
    private func bindUI() {
        let input = MainProfileVM.Input()
        let output = model.transform(input: input)
        output.userImage.drive(userImage.rx.image).disposed(by: disposeBag)
    }
}

extension MainProfileVC: UITableViewDelegate, UITableViewDataSource {
    func configProfileTable() {
        profileTable.delegate = self
        profileTable.dataSource = self
        
        profileTable.register(UINib(nibName: Constants.Cells.mainProfile, bundle: nil),
                              forCellReuseIdentifier: Constants.Cells.mainProfile)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let defaultCell = UITableViewCell()
        guard let profileCell = profileTable.dequeueReusableCell(withIdentifier: Constants.Cells.mainProfile, for: indexPath) as? MainProfileTVC else { return defaultCell }
        switch indexPath.row {
        case 0:
            profileCell.config(type: .myProfile)
        case 1:
            profileCell.config(type: .myCats)
        default:
            return defaultCell
        }
        return profileCell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        switch indexPath.row {
        case 0:
            model.goToMyProfile()
        case 1:
            model.goToMyCats()
        default:
            return
        }
    }
}
