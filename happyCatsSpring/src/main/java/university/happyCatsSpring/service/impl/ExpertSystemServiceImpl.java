package university.happyCatsSpring.service.impl;

import javafx.util.Pair;
import university.happyCatsSpring.entity.Answer;
import university.happyCatsSpring.entity.Question;
import university.happyCatsSpring.repo.AnswerRepository;
import university.happyCatsSpring.repo.QuestionRepository;
import university.happyCatsSpring.service.iface.ExpertSystemService;

import java.util.Optional;

public class ExpertSystemServiceImpl implements ExpertSystemService {

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    public ExpertSystemServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Pair<Answer, Question> ask(String useranswer) {
        Optional<Answer> answerOptional = answerRepository.findById(useranswer);
        Optional<Question> questionOptional = questionRepository.findById(useranswer);

        if (!answerOptional.isPresent() && !questionOptional.isPresent()) {
            return new Pair<Answer, Question>(new Answer("", "Неверный вопрос, начните заново."), new Question());
        }
        return new Pair<Answer, Question>(answerOptional.orElse(new Answer()), questionOptional.orElse(new Question()));
    }

    @Override
    public Answer addAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

//    @Override
//    public boolean existById(String id) {
//        return answerRepository.existAnswerById(id) || questionRepository.existQuestionById(id);
//    }

}
