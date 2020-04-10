package hackovid2020.back.rest;

import javax.transaction.Transactional;

import hackovid2020.back.dto.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hackovid2020.back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(value = "user", description = "user management", tags = { "User" })
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="Returns a list with all users.")
	@Transactional
	public SimpleUserDetailsListResponse getUsers() {
		return SimpleUserDetailsListResponse.ofUserList(userService.findAll());
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Creates a new user.")
	@Transactional
	public UserDetailsResponse createUser(@RequestBody UserCreationRequest request) {
		return UserDetailsResponse.ofUser(userService.save(request.toUser()));
	}
	
	@PostMapping(value="/login")
	@ResponseBody
	@ApiOperation(value= "Get login token.")
	@Transactional
	public UserTokenResponse login(@RequestBody LoginRequest loginRequest) {
		String mail = loginRequest.getMail();
		String password = loginRequest.getPassword();
		String token = userService.login(mail, password);
		return new UserTokenResponse(mail, token);
	}
	
	@GetMapping(value="/{id}")
	@ResponseBody
	@ApiOperation(value= "Get User.")
	@Transactional
	public UserDetailsResponse getUser(@PathVariable("id") Long id) {
		return UserDetailsResponse.ofUser(userService.findById(id));
	}
	
	@GetMapping(value="simpleDetails/{id}")
	@ResponseBody
	@ApiOperation(value= "Get simple UserDetails with first name and last name.")
	@Transactional
	public SimpleUserDetailsResponse getSimpleUser(@PathVariable("id") Long id) {
		return SimpleUserDetailsResponse.ofUser(userService.findById(id));
	}
	
	@PutMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="Update user.")
	@Transactional
	public UserDetailsResponse updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequest request) {
		return UserDetailsResponse.ofUser(userService.update(id, request.getFirstName(),
				request.getLastName(), request.getMail(), request.getPassword(), request.getImageUrl()));
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Delete user.")
	@Transactional
	public HttpStatus deleteUser(@PathVariable("id") Long id) {
		userService.delete(id);
		return HttpStatus.OK;
	}
	
}
