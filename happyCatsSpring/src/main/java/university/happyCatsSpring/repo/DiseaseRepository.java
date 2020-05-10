package university.happyCatsSpring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import university.happyCatsSpring.entity.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
