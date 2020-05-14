package university.happyCatsSpring.service.iface;

import org.springframework.stereotype.Service;
import university.happyCatsSpring.entity.Breed;

import java.util.List;

@Service
public interface BreedService {
    List<Breed> findAll();
}
