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
        navigationBar.barTintColor = Constants.UI.navigationBackgroundColor
        navigationBar.titleTextAttributes = [
            .foregroundColor: Constants.UI.navigationTitleTextColor,
            .font: Constants.UI.navigationTitleFont ?? Constants.UI.systemNavigationTitleFont
        ]
    }
    
    private func setTabBar() {
        let tabBar = UITabBar.appearance()
        tabBar.barTintColor = Constants.UI.navigationBackgroundColor
        tabBar.tintColor = UIColor.white
        tabBar.unselectedItemTintColor = UIColor.white
    }
}
