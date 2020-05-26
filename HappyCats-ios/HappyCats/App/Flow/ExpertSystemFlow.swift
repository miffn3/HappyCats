//
//  ExpertSystemFlow.swift
//  HappyCats
//
//  Created by Яна Преображенская on 26.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import Foundation
import RxFlow

final class ExpertSystemFlow: Flow {
    
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
        case .start:
            return navigationToStartExpertScreen()
        case .question(let id, let question):
            return navigationToQuestionScreen(withId: id, question: question)
        default:
            return .none
        }
    }
    
    private func navigationToStartExpertScreen() -> FlowContributors {
        let model = StartExpertVM(userService: services.userService)
        let vc = StartExpertVC()
        vc.title = R.string.localizable.expertsystemNavigationTitle()
        vc.setModel(model: model)
        rootViewController.pushViewController(vc, animated: true)
        return .one(flowContributor: .contribute(withNextPresentable: vc, withNextStepper: model))
    }
    
    private func navigationToQuestionScreen(withId id: String, question: String) -> FlowContributors {
        let model = QuestionExpertVM(userService: services.userService, questionID: id, question: question)
        let vc = QuestionExpertVC()
        vc.title = R.string.localizable.expertsystemQuestionTitle()
        vc.setModel(model: model)
        rootViewController.pushViewController(vc, animated: true)
        return .one(flowContributor: .contribute(withNextPresentable: vc, withNextStepper: model))
    }
}