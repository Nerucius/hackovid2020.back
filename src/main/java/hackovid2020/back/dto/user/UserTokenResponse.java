package hackovid2020.back.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTokenResponse {
	
	@JsonProperty
	private String mail;
	
	@JsonProperty
	private String token;
	
	@JsonCreator
	public UserTokenResponse(String mail, String token) {
		this.mail = mail;
		this.token = token;
	}

}
