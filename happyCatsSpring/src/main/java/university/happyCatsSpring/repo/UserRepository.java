package university.happyCatsSpring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import university.happyCatsSpring.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User findByPhone(String phone);
	boolean existsUserByUsername(String username);
	boolean existsUserByPhone(String phone);
}
