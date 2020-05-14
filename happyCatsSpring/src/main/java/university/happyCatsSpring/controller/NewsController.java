package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import university.happyCatsSpring.entity.News;
import university.happyCatsSpring.service.iface.NewsService;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;

    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<News>> listNews() {
        return ResponseEntity.ok(newsService.findAll());
    }
}
