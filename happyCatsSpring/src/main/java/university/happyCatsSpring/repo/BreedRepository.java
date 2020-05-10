package university.happyCatsSpring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import university.happyCatsSpring.entity.Breed;

public interface BreedRepository extends JpaRepository<Breed, Long> {
}
