package university.happyCatsSpring.service.iface;

import org.springframework.stereotype.Service;
import university.happyCatsSpring.entity.User;

import java.util.Optional;

@Service
public interface UserService {
	void createUser(String username, String password, String name, String email);
	Long getUserIdByUsername(String username);
	Optional<User> getUserByPhone(String phone);
	Optional<User> getUserByUsername(String username);
	boolean existsUserByUsername(String username);
	boolean existsUserByPhone(String phone);
}
