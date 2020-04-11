package hackovid2020.back.repository;

import hackovid2020.back.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findUserByMailAndPassword(String mail, String password);
	
	Optional<User> findByToken(String token);

	boolean existsByMail(String mail);
	
}
