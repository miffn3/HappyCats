package university.happyCatsSpring.service.iface;

import org.springframework.stereotype.Service;
import university.happyCatsSpring.dto.UpdateUserDto;
import university.happyCatsSpring.entity.User;

import java.util.Optional;

@Service
public interface UserService {
	void createUser(String username, String password, String name, String email);
	User updateUser(String username, UpdateUserDto updateUserDto);
	Long getUserIdByUsername(String username);
	Optional<User> getUserByPhone(String phone);
	Optional<User> getUserByUsername(String username);
	Optional<User> getUserById(Long id);
	boolean existsUserByUsername(String username);
	boolean existsUserByPhone(String phone);
}
