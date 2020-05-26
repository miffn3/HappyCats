package university.happyCatsSpring.service.impl;

import university.happyCatsSpring.dto.UpdateUserDto;
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
	public User updateUser(String username, UpdateUserDto updateUserDto) {
		Optional<User> userOptional = getUserByUsername(username);
		User user;
		if (userOptional.isPresent()) {
			user = userOptional.get();
		} else return null;

		String newUsername = updateUserDto.getUsername();
		String newPassword = updateUserDto.getPassword();
		String newBirthday = updateUserDto.getBirthday();
		String newName = updateUserDto.getName();
		String newPhoto = updateUserDto.getPhoto();
		String newPhone = updateUserDto.getPhone();
		String newEmail = updateUserDto.getEmail();
		String newNote = updateUserDto.getNote();

		if( newUsername != null && !newUsername.trim().isEmpty())
			user.setUsername(newUsername);

		if(newPassword != null && !newPassword.trim().isEmpty())
			user.setPassword(newPassword);

		if(newName != null && !newName.trim().isEmpty())
			user.setName(newName);

		if(newBirthday != null && !newBirthday.trim().isEmpty())
			user.setBirthday(newBirthday);

		if(newPhoto != null && !newPhoto.trim().isEmpty())
			user.setPhoto(newPhoto);

		if(newPhone != null && !newPhone.trim().isEmpty())
			user.setPhone(newPhone);

		if(newEmail != null && !newEmail.trim().isEmpty())
			user.setEmail(newEmail);

		if(newNote != null && !newNote.trim().isEmpty())
			user.setNote(newNote);

		return userRepository.save(user);
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
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
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
