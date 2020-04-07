package hackovid2020.back.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.User;

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
	private String password;
	
	@JsonProperty
	private boolean isAcountNonExpired;
	
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
	private UserDetailsResponse(String firstName, String lastName, String mail, String password, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, Date createdAt, Date modifiedAt) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
		this.isAcountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static UserDetailsResponse ofUser(User user) {
		return new UserDetailsResponse(user.getFirstName(), user.getLastName(), user.getMail(), user.getPassword(), 
				user.isAccountNonExpired(), user.isAccountNonLocked(), user.isCredentialsNonExpired(),
				user.isEnabled(), user.getCreatedAt(), user.getModifiedAt());
	}

}
