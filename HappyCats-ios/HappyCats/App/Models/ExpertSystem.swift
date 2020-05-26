//
//  ExpertSystem.swift
//  HappyCats
//
//  Created by Яна Преображенская on 26.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import Foundation

struct ExpertSystem: Codable {
    let first: ExpertSystemFirst?
    let second: ExpertSystemSecond?
}

struct ExpertSystemFirst: Codable {
    
}

struct ExpertSystemSecond: Codable {
    let id: String?
    let newId: String?
    let value: String?
}
