package hackovid2020.back.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTokenResponse {
	
	@JsonProperty
	private String mail;
	
	@JsonProperty
	private String password;
	
	@JsonProperty
	private String token;
	
	@JsonCreator
	public UserTokenResponse(String mail, String password, String token) {
		this.mail = mail;
		this.password = password;
		this.token = token;
	}

}
