//
//  AppDelegate.swift
//  HappyCats
//
//  Created by Яна Преображенская on 07.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit
import RxFlow
import RxSwift

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    var coordinator = FlowCoordinator()
    
    private let disposeBag = DisposeBag()
    private var servicesContainer: ServicesContainer!

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        servicesContainer = buildServicesContainer()
        let appearanceManager = AppearanceManager()
        appearanceManager.setup()
        
        guard let window = self.window else { return false }

        self.coordinator.rx.didNavigate.subscribe(onNext: { (flow, step) in
            print("did navigate to flow=\(flow) and step=\(step)")
        }).disposed(by: self.disposeBag)

        let appFlow = AppFlow(window: window, services: servicesContainer)

        self.coordinator.coordinate(flow: appFlow, with: AppStepper(withServices: servicesContainer))

        return true
    }
    
    func buildServicesContainer() -> ServicesContainer {
        return ServicesContainer()
    }
}

