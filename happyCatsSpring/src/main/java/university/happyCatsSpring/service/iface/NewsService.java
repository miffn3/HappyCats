package university.happyCatsSpring.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import university.happyCatsSpring.entity.News;

@Service
public interface NewsService {
    Page<News> findAll(Pageable pageable);
}
