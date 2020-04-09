package hackovid2020.back.rest;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.ShopImage;
import hackovid2020.back.dao.ShopLocation;
import hackovid2020.back.dao.User;
import hackovid2020.back.dto.shop.ShopCreationRequest;
import hackovid2020.back.dto.shop.ShopDetailsResponse;
import hackovid2020.back.dto.user.UserDetailsResponse;
import hackovid2020.back.service.ShopService;
import hackovid2020.back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/shop")
@Api(value = "shop", description = "shop management", tags = { "Shop" })
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
		Shop shop = shopService.saveShop(request.toShop(owner));
		List<Category> shopCategories = shopService.saveShopCategories(request.getShopCategories(), shop);
		List<ShopImage> shopImages = shopService.saveShopImages(request.getShopImages(), shop);
		ShopLocation location = shopService.saveShopLocation(request.getLatitude(), request.getLongitude(),
				request.getStreetName());
		return ShopDetailsResponse.ofShop(shop, UserDetailsResponse.ofUser(owner),
				shopImages, shopCategories, location);
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Get a shop.")
	@Transactional
	public ShopDetailsResponse getShop(@PathVariable("id") Long id) {
		Shop shop = shopService.findById(id);
		List<ShopImage> shopImages = shopService.findAllShopImages(shop);
		List<Category> shopCategories = shopService.findAllShopCategories(shop);
		ShopLocation location = shopService.findShopLocation(shop);
		return ShopDetailsResponse.ofShop(shop, UserDetailsResponse.ofUser(shop.getUser()),
				shopImages, shopCategories, location);
	}
	
}
