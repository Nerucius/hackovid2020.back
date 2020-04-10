package hackovid2020.back.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.User;

public class SimpleUserDetailsResponse {
	
	@JsonProperty
	private String firstName;
	
	@JsonProperty
	private String lastName;
	
	@JsonProperty
	private String imageUrl;
	
	@JsonCreator
	private SimpleUserDetailsResponse(String firstName, String lastName, String imageUrl) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageUrl = imageUrl;
	}

	public static SimpleUserDetailsResponse ofUser(User user) {
		return new SimpleUserDetailsResponse(user.getFirstName(), user.getLastName(), user.getImageUrl());
	}
	
}
