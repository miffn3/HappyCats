package university.happyCatsSpring.service.iface;

import org.springframework.stereotype.Service;
import university.happyCatsSpring.entity.Disease;

import java.util.List;

@Service
public interface DiseaseService {
    List<Disease> findAll();
}
