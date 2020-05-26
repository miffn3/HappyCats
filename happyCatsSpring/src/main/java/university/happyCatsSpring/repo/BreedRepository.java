package university.happyCatsSpring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import university.happyCatsSpring.entity.Breed;

import java.util.Optional;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    Optional<Breed> findByName(String name);
}
