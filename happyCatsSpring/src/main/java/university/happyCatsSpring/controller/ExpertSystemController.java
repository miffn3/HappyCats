package university.happyCatsSpring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import university.happyCatsSpring.entity.Answer;
import university.happyCatsSpring.entity.Question;
import university.happyCatsSpring.service.iface.ExpertSystemService;

@RestController
@RequestMapping("/expert")
public class ExpertSystemController {
    private ExpertSystemService expertSystemService;

    @Autowired
    public ExpertSystemController(ExpertSystemService expertSystemService) {
        this.expertSystemService = expertSystemService;

    }

    @GetMapping(value = "/ask/{useranswer}")
    public ResponseEntity<Pair<Answer, Question>> ask(@PathVariable("useranswer") String useranswer)
    {
        return ResponseEntity.ok(expertSystemService.ask(useranswer));
    }

//    @PostMapping(value = "/addanswer")
//    public ResponseEntity<Answer> addAnswer(@RequestBody Answer answer)
//    {
//        if (expertSystemService.existById(answer.getId())) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(expertSystemService.addAnswer(answer));
//    }
//
//    @PostMapping(value = "/addquestion")
//    public ResponseEntity<Question> addQuestion(@RequestBody Question question)
//    {
//        if (expertSystemService.existById(question.getId())) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        if (expertSystemService.existById(question.getNewId())) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        return ResponseEntity.ok(expertSystemService.addQuestion(question));
//    }
}
