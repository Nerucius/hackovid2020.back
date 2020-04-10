package hackovid2020.back.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import hackovid2020.back.Constants;
import hackovid2020.back.dao.User;
import hackovid2020.back.repository.UserRepository;
import hackovid2020.back.utils.MD5Util;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public String login(String mail, String password) throws ResponseStatusException {
		Optional<User> oUser = userRepository.findUserByMailAndPassword(mail, password);
		if (oUser.isPresent()) {
			String token = UUID.randomUUID().toString();
			User user = oUser.get();
			user.setToken(token);
			save(user);
			return token;
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN);
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
		return userRepository.saveAndFlush(user);
	}
	
	public User update(Long id, String name, String lastName, String mail, String password, String imageUrl) {
		User user = userRepository.findById(id).get();
		user.setFirstName(name);
		user.setLastName(lastName);
		// TODO check that the new mail does not exist
		user.setMail(mail);
		user.setPassword(password);
		user.setImageUrl(Constants.GRAVATAR + MD5Util.md5Hex(imageUrl));
		user.setModifiedAt(Calendar.getInstance().getTime());
		save(user);
		return user;
	}

	public void delete(Long id) {
		userRepository.delete(userRepository.findById(id).get());
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

}
