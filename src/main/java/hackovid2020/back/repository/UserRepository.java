package hackovid2020.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hackovid2020.back.dao.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "SELECT * FROM user u WHERE u.mail = ?1 and u.password = ?2")
	Optional<User> login(String mail, String password);
	
	Optional<User> findByToken(String token);
	
}
