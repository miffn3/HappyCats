package university.happyCatsSpring.service.iface;


import org.springframework.stereotype.Service;
import university.happyCatsSpring.entity.News;

import java.util.List;

@Service
public interface NewsService {
    List<News> findAll();
}
