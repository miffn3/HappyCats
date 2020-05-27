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
        case .result(let result, let id):
            return navigationToResultScreen(withResult: result, id: id)
        case .disease(let id):
            return navigationToDiseaseScreen(withId: id)
        case .again:
            return returnToStartScreen()
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
    
    private func navigationToResultScreen(withResult result: String, id: Int?) -> FlowContributors {
        let model = ResultExpertVM(userService: services.userService, result: result, id: id)
        let vc = ResultExpertVC()
        vc.title = R.string.localizable.expertsystemResult()
        vc.setModel(model: model)
        rootViewController.pushViewController(vc, animated: true)
        return .one(flowContributor: .contribute(withNextPresentable: vc, withNextStepper: model))
    }
    
    private func navigationToDiseaseScreen(withId id: Int) -> FlowContributors {
        let model = DiseaseVM(withId: id, userService: services.userService)
        let vc = DiseaseVC()
        vc.setModel(model: model)
        rootViewController.pushViewController(vc, animated: true)
        return .one(flowContributor: .contribute(withNextPresentable: vc, withNextStepper: model))
    }
    
    private func returnToStartScreen() -> FlowContributors {
        rootViewController.popToRootViewController(animated: true)
        return .one(flowContributor: .contribute(withNextPresentable: rootViewController,
                                                 withNextStepper: OneStepper(withSingleStep: AppStep.start)))
    }
}
