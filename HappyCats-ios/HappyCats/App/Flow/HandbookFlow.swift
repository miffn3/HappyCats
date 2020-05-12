//
//  HandbookFlow.swift
//  HappyCats
//
//  Created by Яна Преображенская on 08.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import Foundation
import RxFlow

class HandbookFlow: Flow {
    
    private let rootViewController = UINavigationController()
    private let services: ServicesContainer
    
    var root: Presentable {
        return self.rootViewController
    }
    
    init(withServices services: ServicesContainer) {
        self.services = services
    }

    deinit {
        print("\(type(of: self)): \(#function)")
    }

    func navigate(to step: Step) -> FlowContributors {

        guard let step = step as? AppStep else { return .none }

        switch step {
        case .handbook:
            return navigationToCatsScreen()
        default:
            return .none
        }
    }
    
    private func navigationToCatsScreen() -> FlowContributors {
        let model = HandbookVM()
        let vc = HandbookVC()
        vc.title = R.string.localizable.handbookTitle()
        vc.setModel(model: model)
        rootViewController.pushViewController(vc, animated: true)
        return .one(flowContributor: .contribute(withNextPresentable: vc, withNextStepper: model))
    }
}
