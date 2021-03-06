package university.happyCatsSpring.service.iface;

import org.springframework.stereotype.Service;
import university.happyCatsSpring.dto.CreateCatDto;
import university.happyCatsSpring.entity.Cat;
import university.happyCatsSpring.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public interface CatService {
    List<Cat> findAll();
    Optional<Cat> findById(Long id);
    Optional<Cat> findByName(String name);
    Cat addCat(String username, CreateCatDto catDto) throws Exception;
    Cat updateCat(Cat cat, CreateCatDto catDto) throws Exception;
    User deleteCat(Cat cat, String username) throws Exception;
}
