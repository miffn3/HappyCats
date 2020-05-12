package university.happyCatsSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import university.happyCatsSpring.repo.NewsRepository;
import university.happyCatsSpring.repo.UserRepository;
import university.happyCatsSpring.service.iface.NewsService;
import university.happyCatsSpring.service.iface.UserService;
import university.happyCatsSpring.service.impl.NewsServiceImpl;
import university.happyCatsSpring.service.impl.UserServiceImpl;

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
}