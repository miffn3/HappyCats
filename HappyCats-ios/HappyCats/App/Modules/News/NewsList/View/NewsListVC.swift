//
//  NewsListVC.swift
//  HappyCats
//
//  Created by Яна Преображенская on 07.05.2020.
//  Copyright © 2020 Яна Преображенская. All rights reserved.
//

import UIKit

class NewsListVC: UIViewController {
    
    private var model: NewsListVM!

    @IBOutlet private weak var newsTable: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        model.updateNews()
        configNewsTable()
    }
    
    func setModel(model: NewsListVM) {
        self.model = model
    }
}

extension NewsListVC: UITableViewDelegate, UITableViewDataSource {
    func configNewsTable() {
        newsTable.delegate = self
        newsTable.dataSource = self
        
        newsTable.register(UINib(nibName: Constants.Cells.News.name, bundle: nil),
                           forCellReuseIdentifier: Constants.Cells.News.name)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return model.news.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let defaultCell = UITableViewCell()
        guard let newsCell = newsTable.dequeueReusableCell(withIdentifier: Constants.Cells.News.name, for: indexPath) as? NewsTVC else { return defaultCell }
        if let cellData = model.news[safe: indexPath.row] {
            newsCell.config(data: cellData)
        } else {
            newsCell.configEmpty()
        }
        return newsCell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        guard let newsId = model.news[safe: indexPath.item]?.id else { return }
        self.model.pick(newsId: newsId)
    }
}
