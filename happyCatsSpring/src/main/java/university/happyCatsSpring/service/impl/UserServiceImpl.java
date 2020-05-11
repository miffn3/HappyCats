package university.happyCatsSpring.service.impl;

import university.happyCatsSpring.entity.User;
import university.happyCatsSpring.repo.UserRepository;
import university.happyCatsSpring.service.iface.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void createUser(String username, String password, String name, String email) {
		User user = new User(username, password, name, email);
		userRepository.save(user);
	}

	@Override
	public Long getUserIdByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user.map(User::getId).orElse(null);
	}

	@Override
	public Optional<User> getUserByPhone(String phone) {
		return userRepository.findByPhone(phone);
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean existsUserByPhone(String phone) {
		return userRepository.existsUserByPhone(phone);
	}

	@Override
	public boolean existsUserByUsername(String username) {
		return userRepository.existsUserByUsername(username);
	}
}
