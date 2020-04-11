package hackovid2020.back.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.User;

import java.util.Date;

public class UserDetailsResponse {
	
	@JsonProperty
	private Long userId;
	
	@JsonProperty
	private String firstName;
	
	@JsonProperty
	private String lastName;
	
	@JsonProperty
	private String mail;

	@JsonProperty
	private String token;
	
	@JsonProperty
	private String imageUrl;
	
	@JsonProperty
	private boolean isAccountNonExpired;
	
	@JsonProperty
	private boolean isAccountNonLocked;
	
	@JsonProperty
	private boolean isCredentialsNonExpired;
	
	@JsonProperty
	private boolean isEnabled;
	
	@JsonProperty
	private Date createdAt;
	
	@JsonProperty
	private Date modifiedAt;
	
	@JsonCreator
	private UserDetailsResponse(Long userId, String firstName, String lastName, String mail, String token, String imageUrl,
			boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled,
			Date createdAt, Date modifiedAt) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.token = token;
		this.imageUrl = imageUrl;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static UserDetailsResponse ofUser(User user) {
		return new UserDetailsResponse(user.getUserId(), user.getFirstName(), user.getLastName(), user.getMail(), user.getToken(),
				user.getImageId(), user.isAccountNonExpired(), user.isAccountNonLocked(), user.isCredentialsNonExpired(),
				user.isEnabled(), user.getCreatedAt(), user.getModifiedAt());
	}

}
