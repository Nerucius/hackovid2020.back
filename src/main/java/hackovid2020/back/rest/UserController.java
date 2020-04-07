package hackovid2020.back.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hackovid2020.back.dto.UserCreationRequest;
import hackovid2020.back.dto.UserDetailsResponse;
import hackovid2020.back.dto.UserTokenResponse;
import hackovid2020.back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(value = "user", description = "user management", tags = { "User" })
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	@ResponseBody
	@ApiOperation(value = "Creates a new user.")
	@Transactional
	public UserDetailsResponse createUser(@RequestBody UserCreationRequest request) {
		return UserDetailsResponse.ofUser(userService.save(request.toUser()));
	}
	
	@GetMapping
	@ResponseBody
	@ApiOperation(value= "Get login token.")
	@Transactional
	public UserTokenResponse login(@RequestParam("mail") String mail, @RequestParam("password") String password) {
		String token = userService.login(mail, password);
		return new UserTokenResponse(mail, password, token);
	}
	
}
