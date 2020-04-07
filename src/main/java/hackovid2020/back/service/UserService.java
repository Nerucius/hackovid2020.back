package hackovid2020.back.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hackovid2020.back.dao.User;
import hackovid2020.back.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public String login(String mail, String password) {
		Optional<User> oUser = userRepository.findUserByMailAndPassword(mail, password);
		if (oUser.isPresent()) {
			String token = UUID.randomUUID().toString();
			User user = oUser.get();
			user.setToken(token);
			userRepository.save(user);
			return token;
		}
		return "";
	}
	
	public Optional<User> findByToken(String token) {
		Optional<User> oUser = userRepository.findByToken(token);
		if (oUser.isPresent()) {
			User user = oUser.get();
			return Optional.of(user);
		}
		return Optional.empty();
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}

}
