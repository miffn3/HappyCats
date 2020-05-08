//
//  AppearanceManager.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import Foundation
import UIKit

final class AppearanceManager {
    
    func setup() {
        setNavigation()
        setTabBar()
    }
    
    private func setNavigation() {
        let navigationBar = UINavigationBar.appearance()
        navigationBar.barTintColor = Constants.UI.mainColor
        navigationBar.titleTextAttributes = [
            .foregroundColor: UIColor.white,
            .font: Constants.UI.navigationTitleFont ?? Constants.UI.systemNavigationTitleFont
        ]
        navigationBar.tintColor = .white
    }
    
    private func setTabBar() {
        let tabBar = UITabBar.appearance()
        tabBar.barTintColor = Constants.UI.mainColor
        tabBar.tintColor = UIColor.white
        tabBar.unselectedItemTintColor = UIColor.white
    }
}
