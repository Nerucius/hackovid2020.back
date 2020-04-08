package hackovid2020.back.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdateRequest {
	
	private String firstName;
	
	private String lastName;
	
	private String mail;
	
	private String password;
	
	private String imageUrl;
	
	@JsonCreator
	private UserUpdateRequest(
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName,
			@JsonProperty("mail") String mail,
			@JsonProperty("password") String password,
			@JsonProperty("imageUrl") String imageUrl) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
		this.imageUrl = imageUrl;
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
	
	public String getImageUrl() {
		return imageUrl;
	}

}
