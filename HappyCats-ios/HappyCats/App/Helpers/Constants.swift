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
    enum API {
        enum URL {
            static let mainURL = "http://localhost:8080"
            static let loginURL = "/auth/sigin"
            static let registrationURL = "/auth/signup"
            static let user = "/users/me"
            static let allNews = "/news/all"
            static let newsById = "/news/id"
            static let allBreeds = "/breeds/allbreeds"
            static let allDisease = "/diseases/all"
        }
        
        enum Headers {
            static let auth = "Authorization"
        }
    }
    
    enum UI {
        enum Main {
            static let color = UIColor(red: 0.13, green: 0.59, blue: 0.95, alpha: 1.00)
            static let font = R.font.notoSans(size: 17)
            static let smallFont = R.font.notoSans(size: 14)
            static let titleFont = R.font.notoSansBold(size: 24)
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
        
        enum View {
            static let cornerRadius: CGFloat = 10
        }
    }
    
    enum Cells {
        static let news = "NewsTVC"
        static let handbook = "HandbookTVC"
        static let mainProfile = "MainProfileTVC"
        
        enum MainProfileCellType {
            case myProfile
            case myCats
        }
    }
    
    enum SelectedButton {
        case cats
        case disease
    }
}
