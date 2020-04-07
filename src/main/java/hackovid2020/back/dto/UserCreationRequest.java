package hackovid2020.back.dto;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.User;

public class UserCreationRequest {
	
	@JsonProperty
	private String firstName;
	
	@JsonProperty
	private String lastName;
	
	@JsonProperty
	private String mail;
	
	@JsonProperty
	private String password;

	@JsonCreator
	private UserCreationRequest(String firstName, String lastName, String mail, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
	}
	
	public User toUser() {
		return User.createUser(firstName, lastName, mail, password,
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
