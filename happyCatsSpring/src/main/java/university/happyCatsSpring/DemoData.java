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
        Disease disease1 = new Disease("Отит",
                "https://avatars.mds.yandex.net/get-zen_doc/195447/pub_5d2090c482066500ad8d1ec1_5d2090d36a438100ae585d4a/scale_1200",
                "Ушной канал питомца выглядит гиперемированным, питомец старается почесать больное ухо лапами.");
        Disease diseaseOtit = diseaseRepository.save(disease1);

        Disease disease2 = new Disease("Лишай",
                "https://avatars.mds.yandex.net/get-zen_doc/1545908/pub_5cf776b023e30800b09e572d_5cf776b85bac9100b0d40d01/scale_1200",
                "Лишай у кошек представляет собой грибковую инфекцию, которую вызывают грибы дерматофиты.");
        Disease diseaseLishay = diseaseRepository.save(disease2);

        Disease disease3 = new Disease("Мочекаменная болезнь",
                "https://avatars.mds.yandex.net/get-zen_doc/125920/pub_5d454b0c31878200ad83eda9_5d454b11ecfb8000ad78a08b/scale_1200",
                "Мочекаменная болезнь кошек — заболевание нижних мочевыводящих путей, сопровождаемое " +
                        "гематурией (кровь в моче), дизурией (болезненное мочеиспускание), нарушением мочеиспускания, " +
                        "странгурией (позывы к мочеиспусканию), поллакиурией (частые позывы к мочеиспусканию), а иногда " +
                        "и уретральной обструкцией (которая может стать фатальной). Этой болезни подвержены около 12% " +
                        "всех кошек.");
        Disease diseaseKam = diseaseRepository.save(disease3);

        Disease disease4 = new Disease("Стоматит",
                "https://avatars.mds.yandex.net/get-zen_doc/248942/pub_5d086c6608c7e200af4d952f_5d086c88aa04b100ae0a2e2a/scale_1200",
                "У кошек и котов зачастую может возникать воспаление в полости рта, которое со временем " +
                        "переходит в состояние стоматита. При этом воспаление может затронуть не только десна, но и " +
                        "слизистую щек, языка, неба. У представителей кошачьего семейства в отличие от людей в полости " +
                        "рта имеется огромное количество болезнетворных бактерий, инфекций. Достаточно только " +
                        "появиться небольшой ранки на слизистой десен, неба и инфекция вместе со слюной быстро " +
                        "проникнет вглубь тканей. И это впоследствии провоцирует обширный стоматит у кошек.");
        Disease diseaseStomatit = diseaseRepository.save(disease4);

        Disease disease5 = new Disease("Ожирение",
                "https://moja-koshka.ru/images/aimages/bolezni/ogirenie-u-koshek2.jpg",
                "Избыточный вес и ожирение могут приводить к развитию серьезных сопутствующих патологий: " +
                        "от сердечно-сосудистых заболеваний до хронического стрессового состояния. Ожирение у кошек — " +
                        "такая же актуальная прогрессирующая проблема, как и у людей. Ожирение может в течение всей " +
                        "жизни кошки оказывать негативное воздействие на ее здоровье, качество жизни и работу органов.");
        Disease diseaseOjir = diseaseRepository.save(disease5);

        log.debug("printing all diseases...");
        this.diseaseRepository.findAll().forEach(v -> log.debug(" Disease :{}", v));

        Set<Disease> diseaseSet = new HashSet<>(Arrays.asList(disease1, disease2));
        Breed breed1 = new Breed("Манул",
                "https://avatars.mds.yandex.net/get-zen_doc/52326/pub_5eb0969a931b1f7814a018cd_5eb0a9d134cbba0565c6c81a/scale_1200",
                "Хищное млекопитающее семейства кошачьих, единственный вид монотипического рода Octolobus.",
                diseaseSet);
        breedRepository.save(breed1);

        Breed breed2 = new Breed("Шотландская вислоухая","https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Adult_Scottish_Fold.jpg/548px-Adult_Scottish_Fold.jpg",
                "Порода кошек с характерным строением ушных раковин, которые загнуты вперёд и вниз. " +
                        "Причиной необычной внешности этих кошек является генная мутация. Если у котят породы " +
                        "шотландская вислоухая к определённому возрасту уши остаются прямыми, то они получают " +
                        "название шотландские прямоухие (скоттиш-страйт). В некоторых фелинологических федерациях " +
                        "обе эти разновидности кошек считаются одной породой и выставляются в одном ринге.",
                diseaseSet);
        breedRepository.save(breed2);

        Breed breed3 = new Breed("Манчкин",
                "https://i.pinimg.com/originals/2f/3e/9b/2f3e9b3615f85ebcea4e8af8b0c884d9.jpg",
                "Современная порода кошек. При средней длине тела их лапки короче, чем у обычных кошек в " +
                        "2-3 раза. В отличие от обычных кошек, которые, чтобы осмотреться, встают на задние лапки, " +
                        "манчкин садится на седалище и крепко упирается хвостом. В таком положении кошки могут " +
                        "находиться в течение довольно продолжительного времени.",
                diseaseSet);
        breedRepository.save(breed3);

        Breed breed4 = new Breed("Экзотическая короткошёрстная",
                "https://lh3.googleusercontent.com/proxy/BFmrE2lJta6VUVpROZenQpshI__yefkMC9hz280heA9huBDBCoyRctJaPbxWTzOTcFwaCeFamqCWz4bP-esApkl7LPCw6_MBxQGBSwpbb6M",
                "Искусственно выведенная порода короткошёрстных кошек.Экзоты внешне схожи с кошками " +
                        "персидской породы. \n\nЭкзот — коренастая крепкая и, в то же время, компактная кошка с большой " +
                        "круглой головой, большими круглыми выразительными глазами, выраженными щечками, направленными " +
                        "вперёд небольшими ушами и коротким курносым носом с ярко выраженным стопом. Экзотов иногда " +
                        "называют «персидской кошкой для ленивых» из-за того, что они очень похожи на персов, но не " +
                        "требуют тщательного ухода за шерстью, поскольку, в отличие от перса, шерсть у экзота короткая, " +
                        "около двух сантиметров длиной, очень густая и плюшевая на ощупь. На сегодняшний день " +
                        "экзотическая короткошерстная кошка, благодаря своему оригинальному внешнему виду и спокойному " +
                        "характеру, является очень популярной в мировом масштабе породой. Окрас у экзотов может быть " +
                        "самым разнообразным: от белого до сине-черного. Встречаются как однотонные расцветки, так и " +
                        "биколоры, часто можно встретить даже трехцветные окрасы: красный табби, мраморный, черепаховый " +
                        "и т. д.",
                diseaseSet);
        breedRepository.save(breed4);

        Breed breed5 = new Breed("Бирманская кошка",
                "https://upload.wikimedia.org/wikipedia/commons/f/f8/Birmakatze_Seal-Point.jpg",
                "Порода полудлинношёрстных кошек колор-пойнтового окраса, которая по одной из легенд ведёт " +
                        "своё происхождение из Бирмы. Её не следует путать с бурманской кошкой, которая является " +
                        "отдельной породой короткошёрстных кошек. Бирманская кошка узнаваема благодаря необычному " +
                        "окрасу, который по названию породы получил название бирманского окраса. Для кошек с этим " +
                        "окрасом характерно наличие белых «перчаток» при пойнтовом окрасе длинной шерсти. Порода также " +
                        "известна под названием «Священная бирманская кошка» (хотя в англоязычном произношении названия " +
                        "страны — «Священная бурма»). Порода признана всеми фелинологическими организациями мира.",
                diseaseSet);
        breedRepository.save(breed5);

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

        News news1 = new News("Интересные факты о манулах",
                "https://avatars.mds.yandex.net/get-zen_doc/52326/pub_5eb0969a931b1f7814a018cd_5eb0a9d134cbba0565c6c81a/scale_1200",
                "Манул чуть крупнее домашней кошки: 52-65 сантиметров туловище и 23—31 сантиметровый хвост, " +
                        "при весе в 2-5 килограмм. \n\nПо-другому манула зовут еще палласовым котом в честь парня по " +
                        "фамилии Паллас, который первым и описал эту зверюгу. \n\nОн крепкий малый с пухлым телом и " +
                        "короткими лапками, а еще мега-пушист - аж 9000 шерстинок на квадратный сантиметр кота. Если " +
                        "посветить ему в глаза фонариком (что мы настоятельно не рекомендуем делать), то его глаза не " +
                        "сузятся в линию, как у обычных кошек, а останутся круглыми, но сам манул будет очень недоволен " +
                        "вашим таким поведением. \n\nМанул достаточно ленив, охотится ночью и ранним утром, а все остальное " +
                        "время спит в своей норе (чаще всего отобранной у других животных) или где-то между скалами. " +
                        "Быстро бегать он не очень любит, и сказать честно, не особо хорошо умеет. \n\nЕст он все, что " +
                        "пробегает мимо его норы или места, где он затаился. Обычно это разного рода мыши, но иногда и " +
                        "крупные насекомые. \n\nЖивет манул достаточно долго, 11-12 лет. \n\nЗверюга он, достаточно редкая, " +
                        "поэтому его всячески охраняют, но несмотря на это всякие нехорошие люди продолжают на него " +
                        "охотится, потому что у него очень красивая шкура. \n\nНесмотря на то, что сам манул, кажется, " +
                        "понимает, что их не сильно много осталось в дикой природе, размножаться часто он не очень-то и " +
                        "хочет. Делает он это раз в год и в среднем у пары манулов получается 2-6 котят. У котят манула " +
                        "все происходит, как у обычных кошек: сначала котята слепые, глухие и всячески миловидные, а " +
                        "уже к 10 месяцам они способны заиметь собственных котят. \n\nМанулы очень агрессивны к человеку. " +
                        "Не стоит с ними дружить. Приручению манулы не поддаются, а всякие такие новости в интернете " +
                        "скорее всего фейк, но мы хотим верить в вероятность существования домашнего манула.");
        newsRepository.save(news1);

        News news2 = new News("Одомашнивание кошек",
                "https://avatars.mds.yandex.net/get-zen_doc/1856150/pub_5d2758d3ae56cc00aee1df3e_5d275ccd23371c00ada8f173/scale_1200",
                "Одомашнивание кошки произошло примерно 9500 лет назад на Ближнем Востоке в районе Плодородного " +
                        "полумесяца (условное название региона с повышенным количество осадков в зимние месяцы), где " +
                        "зародились и развивались древнейшие человеческие цивилизации.");
        newsRepository.save(news2);

        News news3 = new News("Кошачий язык", "https://cs8.pikabu.ru/post_img/2018/05/13/10/1526228275151841235.jpg",
                "Язык кошки при лакании жидкости вытягивается со скоростью 1 метр в секунду. В отличие от собак, " +
                        "язык кошки только касается поверхности жидкости, а не проникает в неё. Затем язык устремляется " +
                        "вверх и увлекает за собой столбик жидкости.");
        newsRepository.save(news3);

        News news4 = new News("Важность кошачьих усов",
                "https://zooclub.ru/attach/21000/21446.jpg",
                "Вибриссы (в обиходе — усы) у кошек являются специализированными органами чувств, выполняющими "
                        + "тактильную функцию. Вибриссы кошке ни в коем случае нельзя удалять, так как этим она " +
                        "фактически будет лишена своей «системы ориентации и навигации» в пространстве.");
        newsRepository.save(news4);

        News news5 = new News("Главный мышелов",
                "https://zagge.ru/wp-content/uploads/2017/04/Larry_Chief_Mouser.jpg",
                "В Великобритании два кота — Хамфри (1988-2006) и Ларри (2007-) были удостоены официального " +
                        "звания. Коты, проживающие в резиденции премьер-министра Великобритании, получили звание " +
                        "«Главный мышелов правительственной резиденции».");
        newsRepository.save(news5);

        log.debug("printing all news...");
        this.newsRepository.findAll().forEach(v -> log.debug(" News :{}", v));
        Question question1 = new Question("start", "c", "Это кошка?");
        questionRepository.save(question1);
        Answer answer1 = new Answer("cn", "Система даёт ответы только про болезни кошек.", null);
        answerRepository.save(answer1);
        Question question2 = new Question("cy", "cybs", "Ваша кошка беспокойна?");
        questionRepository.save(question2);
        Question question3 = new Question("cybsy", "cybsyap", "У кошки снижен аппетит?");
        questionRepository.save(question3);
        Question question4 = new Question("cybsyapy", "cybsyapytmp", "У кошки повышена температура?");
        questionRepository.save(question4);
        Question question5 = new Question("cybsyapytmpy", "cybsyapytmpypast", "Кошка часто умывает пасть," +
                " будто ей там что-то мешает?");
        questionRepository.save(question5);
        Answer answer2 = new Answer("cybsyapytmpypasty", "Это стоматит. Стоматит может вызвать выпадение" +
                " зубов и ваша кошка не сможет нормально переживывать пищу. Как можно скорее обратитесь к врачу!",
                diseaseStomatit.getId());
        answerRepository.save(answer2);
        Question question6 = new Question("cybsyapytmpypastn", "cybsyapytmpypastnuho", "Ухо вашей кошки" +
                " покраснело и видны выделения?");
        questionRepository.save(question6);
        Answer answer3 = new Answer("cybsyapytmpypastnuhon", "Невозможно точно определить заболевание. Однако кошка" +
                " может быть больна. Обратитесь к врачу.",
                null);
        answerRepository.save(answer3);
        Answer answer4 = new Answer("cybsyapytmpypastnuhoy", "Это отит. Отит может вызвать различные факторы." +
                " Для лечения и устранения этих факторов обратитесь к врачу",
                diseaseOtit.getId());
        answerRepository.save(answer4);
        Answer answer5 = new Answer("cybsyapn", "Ваша кошка здорова. Но возможно, что она чем-то расстроена.",
                null);
        answerRepository.save(answer5);
        Question question7 = new Question("cybsyapytmpn", "cybsyapytmpnmi", "Кошка испытывает искомфорт" +
                " при мочеиспускании?");
        questionRepository.save(question7);
        Answer answer6 = new Answer("cybsyapytmpnmiy", "Это мочекаменная болезнь. Эта болезнь очень опасна для" +
                " вашего питомца. Обязательно обратитесь к врачу!",
                diseaseKam.getId());
        answerRepository.save(answer6);
        Answer answer7 = new Answer("cybsyapytmpnmin", "Невозможно точно определить заболевание. Однако кошка" +
                " может быть больна. Обратитесь к врачу.",
                null);
        answerRepository.save(answer7);
        Question question8 = new Question("cybsn", "cybsnob", "У кошки есть облысевшие участки?");
        questionRepository.save(question8);
        Answer answer8 = new Answer("cybsnoby", "Это лишай. Осторожно! Опасно для человека. Обратитесь к врачу.",
                diseaseLishay.getId());
        answerRepository.save(answer8);
        Question question9 = new Question("cybsnobn", "cybsnobnreb", "У кошки прощупываются ребра?");
        questionRepository.save(question9);
        Answer answer9 = new Answer("cybsnobnreby", "С вашей кошкой всё в порядке!",
                null);
        answerRepository.save(answer9);
        Question question10 = new Question("cybsnobnrebn", "cybsnobnrebnd", "Видно как кошка дышит," +
                " когда лежит на боку?");
        questionRepository.save(question10);
        Answer answer10 = new Answer("cybsnobnrebndn", "Это ожирение. Оно влечет за собой и другие последствия" +
                " и опасно для жизни котика. Обязательно обратитесь к врачу!",
                diseaseOjir.getId());
        answerRepository.save(answer10);
        Answer answer11 = new Answer("cybsnobnrebndy", "Это ожирение в начальной стадии. Большой и круглый котик" +
                " это конечно хорошо, но может привести к плохим последствиям. Обратитесь к врачу, чтобы у вашего котика не было" +
                " проблем, возможно стоит пересмотреть питание.",
                null);
        answerRepository.save(answer11);
    }
}