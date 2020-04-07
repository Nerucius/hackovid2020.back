package hackovid2020.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hackovid2020.back.dao.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findUserByMailAndPassword(String mail, String password);
	
	Optional<User> findByToken(String token);
	
}
