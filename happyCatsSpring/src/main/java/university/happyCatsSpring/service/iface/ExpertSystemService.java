package university.happyCatsSpring.service.iface;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import university.happyCatsSpring.dto.AnswerCreateDto;
import university.happyCatsSpring.entity.Answer;
import university.happyCatsSpring.entity.Question;

@Service
public interface ExpertSystemService {
    Pair<Answer, Question> ask(String useranswer);
    Answer addAnswer(AnswerCreateDto answer);
    Question addQuestion(Question question);
    boolean existById(String id);
}
