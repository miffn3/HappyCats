package university.happyCatsSpring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import university.happyCatsSpring.entity.*;
import university.happyCatsSpring.repo.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class DemoData implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiseaseRepository diseaseRepository;

    @Autowired
    BreedRepository breedRepository;

    @Autowired
    CatRepository catRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {
        Disease disease1 = new Disease("Отит","https://avatars.mds.yandex.net/get-zen_doc/195447/pub_5d2090c482066500ad8d1ec1_5d2090d36a438100ae585d4a/scale_1200",
                "Ушной канал питомца выглядит гиперемированным, питомец старается почесать больное ухо лапами.");
        diseaseRepository.save(disease1);

        Disease disease2 = new Disease("Лишай", "https://avatars.mds.yandex.net/get-zen_doc/1545908/pub_5cf776b023e30800b09e572d_5cf776b85bac9100b0d40d01/scale_1200",
                "Лишай у кошек представляет собой грибковую инфекцию, которую вызывают грибы дерматофиты.");
        diseaseRepository.save(disease2);

        log.debug("printing all diseases...");
        this.diseaseRepository.findAll().forEach(v -> log.debug(" Disease :{}", v));

        Set<Disease> diseaseSet = new HashSet<>(Arrays.asList(disease1, disease2));
        Breed breed1 = new Breed("Манул","https://avatars.mds.yandex.net/get-zen_doc/52326/pub_5eb0969a931b1f7814a018cd_5eb0a9d134cbba0565c6c81a/scale_1200",
                "Хищное млекопитающее семейства кошачьих, единственный вид монотипического рода Octolobus.",
                diseaseSet);
        breedRepository.save(breed1);

        log.debug("printing all breeds...");
        this.breedRepository.findAll().forEach(v -> log.debug(" Breed :{}", v));


        Cat cat1 = new Cat("Маня","https://avatars.mds.yandex.net/get-zen_doc/2804475/pub_5eb0969a931b1f7814a018cd_5eb0aa44b13b286689784554/scale_1200",
                "20/04/1999","Любит спать", breed1);
        catRepository.save(cat1);

        log.debug("printing all cats...");
        this.catRepository.findAll().forEach(v -> log.debug(" Cat :{}", v));

        Set<Cat> catSet = new HashSet<>(Collections.singletonList(cat1));

        String encodedPassword = new BCryptPasswordEncoder().encode("password");
        User user1 = new User("user1", encodedPassword, "name", "https://sun9-58.userapi.com/c857032/v857032865/14223b/-T7d9p_PZqw.jpg",
                "19/04/1998", "+7-999-888-77-66", "user1@mail.com", "note", catSet);
        userRepository.save(user1);

        User user2 = new User("user2", encodedPassword, "name", "user2@mail.com");
        userRepository.save(user2);

        log.debug("printing all users...");
        this.userRepository.findAll().forEach(v -> log.debug(" User :{}", v));

        News news1 = new News("Интересные факты о манулах", "https://avatars.mds.yandex.net/get-zen_doc/52326/pub_5eb0969a931b1f7814a018cd_5eb0a9d134cbba0565c6c81a/scale_1200",
                "Манул чуть крупнее домашней кошки: 52-65 сантиметров туловище и 23—31 сантиметровый хвост, при весе в 2-5 килограмм\n" +
                        "По-другому манула зовут еще палласовым котом в честь парня по фамилии Паллас, который первым и описал эту зверюгу\n" +
                        "Он крепкий малый с пухлым телом и короткими лапками, а еще мега-пушист - аж 9000 шерстинок на квадратный сантиметр кота\n" +
                        "Если посветить ему в глаза фонариком (что мы настоятельно не рекомендуем делать), то его глаза не сузятся в линию, как у обычных кошек, а останутся круглыми, но сам манул будет очень недоволен вашим таким поведением\n" +
                        "Манул достаточно ленив, охотится ночью и ранним утром, а все остальное время спит в своей норе (чаще всего отобранной у других животных) или где-то между скалами. Быстро бегать он не очень любит, и сказать честно, не особо хорошо умеет\n" +
                        "Ест он все, что пробегает мимо его норы или места, где он затаился. Обычно это разного рода мыши, но иногда и крупные насекомые\n" +
                        "Живет манул достаточно долго, 11-12 лет\n" +
                        "Зверюга он, достаточно редкая, поэтому его всячески охраняют, но несмотря на это всякие нехорошие люди продолжают на него охотится, потому что у него очень красивая шкура\n" +
                        "Несмотря на то, что сам манул, кажется, понимает, что их не сильно много осталось в дикой природе, размножаться часто он не очень-то и хочет. Делает он это раз в год и в среднем у пары манулов получается 2-6 котят\n" +
                        "У котят манула все происходит, как у обычных кошек: сначала котята слепые, глухие и всячески миловидные, а уже к 10 месяцам они способны заиметь собственных котят\n" +
                        "Манулы очень агрессивны к человеку. Не стоит с ними дружить\n" +
                        "Приручению манулы не поддаются, а всякие такие новости в интернете скорее всего фейк, но мы хотим верить в вероятность существования домашнего манула");
        newsRepository.save(news1);

        log.debug("printing all news...");
        this.newsRepository.findAll().forEach(v -> log.debug(" News :{}", v));
        Question question1 = new Question("start", "c", "Это кошка?");
        questionRepository.save(question1);
        Answer answer1 = new Answer("cn", "Это не кошка. Система даёт ответы только про болезни кошек.");
        answerRepository.save(answer1);
        Question question2 = new Question("cy", "cyds", "У неё длинная шерсть?");
        questionRepository.save(question2);
        Answer answer2 = new Answer("cydsy", "Это лишай. Опасно для человека.");
        answerRepository.save(answer2);
    }
}