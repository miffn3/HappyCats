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
        enum Main {
            static let color = UIColor(red: 0.13, green: 0.59, blue: 0.95, alpha: 1.00)
            static let font = R.font.notoSans(size: 17)
            static let fontColor = UIColor.black
            static let alternativeFontColor = UIColor.white
        }
        
        enum Navigation {
            static let titleFont = R.font.notoSans(size: 20)
            static let systemTitleFont = UIFont.systemFont(ofSize: 20)
        }
        
        enum Button {
            static let cornerRadius: CGFloat = 5
            static let borderWidth: CGFloat = 1
        }
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
