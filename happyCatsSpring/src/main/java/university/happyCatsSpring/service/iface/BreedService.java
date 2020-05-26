package university.happyCatsSpring.service.iface;

import org.springframework.stereotype.Service;
import university.happyCatsSpring.entity.Breed;

import java.util.List;
import java.util.Optional;

@Service
public interface BreedService {
    List<Breed> findAll();
    Optional<Breed> findById(Long id);
}
