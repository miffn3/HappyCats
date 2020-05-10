package university.happyCatsSpring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import university.happyCatsSpring.entity.Cat;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
