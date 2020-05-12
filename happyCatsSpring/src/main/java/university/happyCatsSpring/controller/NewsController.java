package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import university.happyCatsSpring.entity.News;
import university.happyCatsSpring.service.iface.NewsService;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/news")
public class NewsController {
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;

    }

    @GetMapping(value = "/")
    public Page<News> listNews( @NotNull Pageable pageable) {
        return newsService.findAll(pageable);
    }
}
