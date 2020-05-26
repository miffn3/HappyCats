package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import university.happyCatsSpring.entity.News;
import university.happyCatsSpring.service.iface.NewsService;


import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/id/{newsid}")
    public ResponseEntity<News> getNewsById(@PathVariable("newsid") String newsId)
    {
        Long id = Long.parseLong(newsId);

        Optional<News> newsOptional = newsService.findById(id);

        if (newsOptional.isPresent()) {
            News news = newsOptional.get();
            return ResponseEntity.ok(news);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
