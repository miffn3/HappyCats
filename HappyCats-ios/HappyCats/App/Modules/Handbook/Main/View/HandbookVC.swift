//
//  HandbookVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit

final class HandbookVC: UIViewController {

    private var model: HandbookVM!
    private var buttonWidth: CGFloat = 100
    private var selectedButton = Constants.SelectedButton.cats

    @IBOutlet private weak var searchBar: UISearchBar!
    @IBOutlet private weak var catsButton: UIButton!
    @IBOutlet private weak var diseaseButton: UIButton!
    @IBOutlet private weak var tableView: UITableView!
    @IBOutlet private weak var catsButtonWidth: NSLayoutConstraint!
    @IBOutlet private weak var diseaseButtonWidth: NSLayoutConstraint!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        buttonWidth = CGFloat(UIScreen.main.bounds.width / 2 - 15)
        buildUI()
    }
    
    func setModel(model: HandbookVM) {
        self.model = model
    }
    
    private func buildUI() {
        configButtons()
        configTable()
    }
    
    private func configButtons() {
        configCatButton()
        configDiseaseButton()
        selectButton(button: catsButton)
        unselectButton(button: diseaseButton)
    }
    
    private func configCatButton() {
        catsButtonWidth.constant = buttonWidth
        configButton(button: catsButton, label: R.string.localizable.catsTitle())
    }
    
    private func configDiseaseButton() {
        diseaseButtonWidth.constant = buttonWidth
        configButton(button: diseaseButton, label: R.string.localizable.diseaseTitle())
    }
    
    private func configButton(button: UIButton, label: String) {
        button.setTitle(label, for: .normal)
        button.titleLabel?.font = Constants.UI.Main.font
        button.layer.borderWidth = Constants.UI.Button.borderWidth
        button.layer.cornerRadius = Constants.UI.Button.cornerRadius
    }
    
    private func selectButton(button: UIButton) {
        button.layer.borderColor = Constants.UI.Main.color.cgColor
    }
    
    private func unselectButton(button: UIButton) {
        button.layer.borderColor = UIColor.clear.cgColor
    }
    
    @IBAction func clickCatsButton(_ sender: Any) {
        unselectButton(button: diseaseButton)
        selectButton(button: catsButton)
        selectedButton = .cats
        tableView.reloadData()
    }
    
    @IBAction func clickDiseaseButton(_ sender: Any) {
        unselectButton(button: catsButton)
        selectButton(button: diseaseButton)
        selectedButton = .disease
        tableView.reloadData()
    }
}

extension HandbookVC: UITableViewDelegate, UITableViewDataSource {
    func configTable() {
        tableView.delegate = self
        tableView.dataSource = self
        
        tableView.register(UINib(nibName: Constants.Cells.handbook, bundle: nil),
                           forCellReuseIdentifier: Constants.Cells.handbook)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch selectedButton {
        case .cats:
            return model.cats.count
        case .disease:
            return model.disease.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let defaultCell = UITableViewCell()
        guard let cell = tableView.dequeueReusableCell(withIdentifier: Constants.Cells.handbook, for: indexPath) as? HandbookTVC else { return defaultCell }
        switch selectedButton {
        case .cats:
            if let data = model.cats[safe: indexPath.row] {
                cell.configCatCell(cat: data)
            } else {
                cell.configEmpty()
            }
        case .disease:
            if let data = model.disease[safe: indexPath.row] {
                cell.configDiseaseCell(disease: data)
            } else {
                cell.configEmpty()
            }
        }
        return cell
    }
}
