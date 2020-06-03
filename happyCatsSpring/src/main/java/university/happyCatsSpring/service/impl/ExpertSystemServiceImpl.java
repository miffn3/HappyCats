package university.happyCatsSpring.service.impl;

import org.springframework.data.util.Pair;
import university.happyCatsSpring.dto.AnswerCreateDto;
import university.happyCatsSpring.entity.Answer;
import university.happyCatsSpring.entity.Breed;
import university.happyCatsSpring.entity.Disease;
import university.happyCatsSpring.entity.Question;
import university.happyCatsSpring.repo.AnswerRepository;
import university.happyCatsSpring.repo.BreedRepository;
import university.happyCatsSpring.repo.DiseaseRepository;
import university.happyCatsSpring.repo.QuestionRepository;
import university.happyCatsSpring.service.iface.ExpertSystemService;

import java.util.Optional;

public class ExpertSystemServiceImpl implements ExpertSystemService {

    private AnswerRepository answerRepository;
    private DiseaseRepository diseaseRepository;
    private QuestionRepository questionRepository;

    public ExpertSystemServiceImpl(AnswerRepository answerRepository, DiseaseRepository diseaseRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.diseaseRepository = diseaseRepository;
        this.questionRepository = questionRepository;
    }
    

    @Override
    public Pair<Answer, Question> ask(String useranswer) {
        Optional<Answer> answerOptional = answerRepository.findById(useranswer);
        Optional<Question> questionOptional = questionRepository.findById(useranswer);

        if (!answerOptional.isPresent() && !questionOptional.isPresent()) {
            return Pair.of(new Answer("", "Неверный вопрос, начните заново.", null), new Question());
        }
        return Pair.of(answerOptional.orElse(new Answer()), questionOptional.orElse(new Question()));
    }

    @Override
    public Answer addAnswer(AnswerCreateDto answer) {
        Optional<Disease> diseaseOptional = diseaseRepository.findByName(answer.getBreedName());
        if (!diseaseOptional.isPresent())
            return null;
        
        Answer answerCreated = new Answer(answer.getId(), answer.getValue(), diseaseOptional.get().getId());
        return answerRepository.save(answerCreated);
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public boolean existById(String id) {
        return answerRepository.existsById(id) || questionRepository.existsById(id);
    }

}
