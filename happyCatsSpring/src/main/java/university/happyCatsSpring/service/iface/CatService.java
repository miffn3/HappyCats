package university.happyCatsSpring.service.iface;

import org.springframework.stereotype.Service;
import university.happyCatsSpring.entity.Cat;

import java.util.List;

@Service
public interface CatService {
    List<Cat> findAll();
}
