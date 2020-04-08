package hackovid2020.back.rest;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hackovid2020.back.dao.User;
import hackovid2020.back.dto.user.UserDetailsResponse;
import hackovid2020.back.dtop.shop.ShopCreationRequest;
import hackovid2020.back.dtop.shop.ShopDetailsResponse;
import hackovid2020.back.service.ShopService;
import hackovid2020.back.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Creates a new shop.")
	@Transactional
	public ShopDetailsResponse createShop(@RequestBody ShopCreationRequest request) {
		User owner = userService.findById(request.getOwnerId());
		return ShopDetailsResponse.ofShop(shopService.save(request.toShop(owner)),
				UserDetailsResponse.ofUser(owner));
	}
	
}
