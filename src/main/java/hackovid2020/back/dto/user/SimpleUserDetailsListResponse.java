package hackovid2020.back.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.User;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleUserDetailsListResponse {
	
	@JsonProperty
	private List<SimpleUserDetailsResponse> userDetails;
	
	@JsonCreator
	private SimpleUserDetailsListResponse(List<SimpleUserDetailsResponse> userDetails) {
		this.userDetails = userDetails;
	}

	public static SimpleUserDetailsListResponse ofUserList(List<User> users) {
		List<SimpleUserDetailsResponse> userDetails = users.stream()
				.map(SimpleUserDetailsResponse::ofUser).collect(Collectors.toList());
		return new SimpleUserDetailsListResponse(userDetails);
	}

}
