package university.happyCatsSpring.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import university.happyCatsSpring.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAll(Pageable pageable);
}
