package university.happyCatsSpring.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import university.happyCatsSpring.entity.News;
import university.happyCatsSpring.repo.NewsRepository;
import university.happyCatsSpring.service.iface.NewsService;

public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }
}
