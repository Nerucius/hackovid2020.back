package hackovid2020.back.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.Constants;
import hackovid2020.back.dao.User;
import hackovid2020.back.utils.MD5Util;

import java.util.Calendar;

public class UserCreationRequest {
	
	private String firstName;

	private String lastName;
	
	private String mail;
	
	private String password;
	
	private String imageUrl;

	@JsonCreator
	private UserCreationRequest(
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName,
			@JsonProperty("mail") String mail,
			@JsonProperty("password") String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
		this.imageUrl = Constants.GRAVATAR + MD5Util.md5Hex(mail);
	}
	
	public User toUser() {
		return User.createUser(firstName, lastName, mail, password, imageUrl,
				Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
	}
	
	
	
}
