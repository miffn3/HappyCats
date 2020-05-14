package university.happyCatsSpring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import university.happyCatsSpring.entity.Cat;

import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, Long> {
    Optional<Cat> findByName(String name);
}
