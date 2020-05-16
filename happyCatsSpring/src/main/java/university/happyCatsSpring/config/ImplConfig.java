package university.happyCatsSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import university.happyCatsSpring.entity.Disease;
import university.happyCatsSpring.repo.*;
import university.happyCatsSpring.service.iface.*;
import university.happyCatsSpring.service.impl.*;

@Configuration
public class ImplConfig {
	@Bean
	public UserService userService(UserRepository userRepository) {
		return new UserServiceImpl(userRepository);
	}

	@Bean
	public NewsService newsService(NewsRepository newsRepository) {
		return new NewsServiceImpl(newsRepository);
	}

	@Bean
	public CatService catService(CatRepository catRepository, UserRepository userRepository,
								 BreedRepository breedRepository) {
		return new CatServiceImpl(catRepository, userRepository, breedRepository);
	}

	@Bean
	public BreedService breedService(BreedRepository breedRepository) {
		return new BreedServiceImpl(breedRepository);
	}

	@Bean
	public DiseaseService diseaseService(DiseaseRepository diseaseRepository) {
		return new DiseaseServiceImpl(diseaseRepository);
	}
}