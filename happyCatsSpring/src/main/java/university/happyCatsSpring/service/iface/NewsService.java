package university.happyCatsSpring.service.iface;


import org.springframework.stereotype.Service;
import university.happyCatsSpring.entity.News;

import java.util.List;
import java.util.Optional;

@Service
public interface NewsService {
    List<News> findAll();
    Optional<News> findById(Long id);
}
