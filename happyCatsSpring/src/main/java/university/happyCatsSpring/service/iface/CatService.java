package university.happyCatsSpring.service.iface;

import org.springframework.stereotype.Service;
import university.happyCatsSpring.entity.Cat;

import java.util.List;
import java.util.Optional;

@Service
public interface CatService {
    List<Cat> findAll();
    Optional<Cat> findById(Long id);
    Optional<Cat> findByName(String name);
}
