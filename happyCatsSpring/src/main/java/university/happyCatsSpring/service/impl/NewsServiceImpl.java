package university.happyCatsSpring.service.impl;

import university.happyCatsSpring.entity.News;
import university.happyCatsSpring.repo.NewsRepository;
import university.happyCatsSpring.service.iface.NewsService;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }
}
