package university.happyCatsSpring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import university.happyCatsSpring.entity.Answer;

import java.util.Optional;

public interface AnswerRepository  extends JpaRepository<Answer, String> {
    Optional<Answer> findById(String id);
}
