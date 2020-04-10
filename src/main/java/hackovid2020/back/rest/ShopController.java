package hackovid2020.back.rest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.File;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.ShopLocation;
import hackovid2020.back.dao.User;
import hackovid2020.back.dto.shop.ShopCreationRequest;
import hackovid2020.back.dto.shop.ShopDetailsResponse;
import hackovid2020.back.dto.shop.ShopDetailsResponseList;
import hackovid2020.back.dto.shop.ShopUpdateRequest;
import hackovid2020.back.dto.user.UserDetailsResponse;
import hackovid2020.back.service.CategoryService;
import hackovid2020.back.service.FileService;
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
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Creates a new shop.")
	@Transactional
	public ShopDetailsResponse createShop(@RequestBody ShopCreationRequest request) {
		User owner = userService.findById(request.getOwnerId());
		ShopLocation location = shopService.saveShopLocation(request.getLatitude(), request.getLongitude(),
				request.getStreetName());
		Shop shop = shopService.saveShop(request.toShop(owner, location));
		Set<Category> shopCategories = categoryService.findAllShopCategories(request.getShopCategoryIds())
				.stream().collect(Collectors.toSet());
		Set<File> shopImages = fileService.findAllShopImages(request.getShopImageIds())
				.stream().collect(Collectors.toSet());
		fileService.assignShopToFiles(request.getShopImageIds(), shop);
		return ShopDetailsResponse.ofShop(shop, UserDetailsResponse.ofUser(owner),
				shopImages, shopCategories, location);
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Get a shop.")
	@Transactional
	public ShopDetailsResponse getShop(@PathVariable("id") Long id) {
		Shop shop = shopService.findById(id);
		Set<File> shopImages = fileService.findAllShopImages(shop.getShopId())
				.stream().collect(Collectors.toSet());;
		Set<Category> shopCategories = shopService.findAllShopCategories(shop)
				.stream().collect(Collectors.toSet());;
		ShopLocation location = shopService.findShopLocation(shop);
		return ShopDetailsResponse.ofShop(shop, UserDetailsResponse.ofUser(shop.getUser()),
				shopImages, shopCategories, location);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Get all shop.")
	@Transactional
	public ShopDetailsResponseList getShops() {
		List<Shop> shops = shopService.findAllShops();
		return ShopDetailsResponseList.ofShopsList(shops);
	}
	
	@PostMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Update a shop.")
	@Transactional
	public ShopDetailsResponse updateShop(@PathVariable("id") Long id, ShopUpdateRequest request) {
		Shop shop = shopService.updateShop(id, request.getCoverImageId(), request.getLatitude(),
				request.getLongitude(), request.getStreetName());
		UserDetailsResponse udr = UserDetailsResponse.ofUser(shop.getUser());
		return ShopDetailsResponse.ofShop(shop, udr, shop.getShopImages(), shop.getCategories(), shop.getLocation());
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Delete a shop")
	@Transactional
	public HttpStatus deleteShop(@PathVariable("id") Long id) {
		shopService.deleteShop(id);
		return HttpStatus.OK;
	}
	
	public void updateShopCategories() {
		
	}
	
	public void updateShopImages() {
		
	}
	
}
