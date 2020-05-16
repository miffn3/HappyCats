//
//  ProfileFlow.swift
//  HappyCats
//
//  Created by Яна Преображенская on 14.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import Foundation
import RxFlow

final class ProfileFlow: Flow {
    
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
        case .mainProfile:
            return navigationToMainProfile()
        case .myProfile:
            return navigationToMyProfile()
        default:
            return .none
        }
    }
    
    private func navigationToMainProfile() -> FlowContributors {
        let model = MainProfileVM(userService: services.userService)
        let vc = MainProfileVC()
        vc.title = R.string.localizable.profileMainProfileTitle()
        vc.setModel(model: model)
        self.rootViewController.pushViewController(vc, animated: true)
        return .one(flowContributor: FlowContributor.contribute(withNextPresentable: vc, withNextStepper: model))
    }
    
    private func navigationToMyProfile() -> FlowContributors {
        let model = MyProfileVM(userService: services.userService)
        let vc = MyProfileVC()
        vc.title = R.string.localizable.profileMyProfileTitle()
        vc.setModel(model: model)
        self.rootViewController.pushViewController(vc, animated: true)
        return .one(flowContributor: FlowContributor.contribute(withNextPresentable: vc, withNextStepper: model))
    }
}
