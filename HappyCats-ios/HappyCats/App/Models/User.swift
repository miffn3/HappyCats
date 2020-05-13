//
//  User.swift
//  HappyCats
//
//  Created by Яна Преображенская on 12.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import Foundation

struct User: Codable {
    var id: Int?
    var login: String?
    var name: String?
    var email: String?
}
