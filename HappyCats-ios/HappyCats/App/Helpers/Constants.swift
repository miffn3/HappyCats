//
//  Constants.swift
//  HappyCats
//
//  Created by Яна Преображенская on 07.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import Foundation
import UIKit

enum Constants {
    enum UI {
        // Main
        static let mainColor = UIColor(red: 0.50, green: 0.70, blue: 1.00, alpha: 1.00)
        static let mainFont = R.font.notoSans(size: 17)
        
        // Navigation
        static let navigationTitleFont = R.font.notoSans(size: 20)
        static let systemNavigationTitleFont = UIFont.systemFont(ofSize: 20)
    }
    
    enum Cells {
        static let news = "NewsTVC"
        static let handbook = "HandbookTVC"
    }
    
    enum SelectedButton {
        case cats
        case disease
    }
}
