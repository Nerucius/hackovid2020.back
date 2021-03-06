package hackovid2020.back.rest;

import hackovid2020.back.dao.*;
import hackovid2020.back.dto.product.ProductResponseList;
import hackovid2020.back.dto.shop.ShopCreationRequest;
import hackovid2020.back.dto.shop.ShopDetailsResponse;
import hackovid2020.back.dto.shop.ShopDetailsResponseList;
import hackovid2020.back.dto.shop.ShopUpdateRequest;
import hackovid2020.back.dto.user.UserDetailsResponse;
import hackovid2020.back.service.CategoryService;
import hackovid2020.back.service.FileService;
import hackovid2020.back.service.ProductService;
import hackovid2020.back.service.ShopService;
import hackovid2020.back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Creates a new shop.")
	@Transactional
	public ShopDetailsResponse createShop(@RequestBody ShopCreationRequest request) {
		User owner = userService.findById(request.getOwnerId());
		Location location = shopService.saveShopLocation(request.getLatitude(), request.getLongitude(),
				request.getStreetName());

		Shop shop = request.toShop(owner, location);
		shop.setCoverImage(fileService.findFileById(request.getCoverImageId()));
		shop = shopService.saveShop(shop);

		Set<Category> shopCategories = categoryService.findAllShopCategories(request.getShopCategoryIds())
				.stream().collect(Collectors.toSet());
		Set<File> shopImages = fileService.findAllShopImages(request.getShopImageIds())
				.stream().collect(Collectors.toSet());
		fileService.assignShopToFiles(request.getShopImageIds(), shop);
		List<Product> productList = productService.findAllShopProducts(shop.getShopId());
		return ShopDetailsResponse.ofShop(shop, UserDetailsResponse.ofUser(owner),
				shopImages, shopCategories, location, ProductResponseList.ofProductList(productList));
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
				.stream().collect(Collectors.toSet());
		List<Product> productList = productService.findAllShopProducts(shop.getShopId());
		return ShopDetailsResponse.ofShop(shop, UserDetailsResponse.ofUser(shop.getUser()),
				shopImages, shopCategories, shop.getLocation(), ProductResponseList.ofProductList(productList));
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
		List<Product> productList = productService.findAllShopProducts(shop.getShopId());
		return ShopDetailsResponse.ofShop(shop, udr, shop.getShopImages(), shop.getCategories(), shop.getLocation(),
				ProductResponseList.ofProductList(productList));
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Delete a shop")
	@Transactional
	public HttpStatus deleteShop(@PathVariable("id") Long id) {
		shopService.deleteShop(id);
		return HttpStatus.OK;
	}
	
	public void updateShopCategories() {
		/* TODO A partir de un request recibe un Set<Long> (ids)
		 * Se debe obtener el set de images que ahora mismo tiene asignadas la tienda. (TiendaId es clave foranea en image)
		 * Comparar el set de ids asignado a la tienda, con el set de ids que viene por parámetro:
		 *  - Se dejan en el set final las repetidas en ambos sets
		 *  - Se añaden, las que estén en set 2, pero no en set 1
		 *  - Se eliminan las que estén en set1, pero no en set 2
		*/
	}
	
	public void updateShopImages() {
		/* TODO A partir de un request recibe un Set<Long> (ids)
		 * Se debe obtener el set de images que ahora mismo tiene asignadas la tienda. (TiendaId es clave foranea en image)
		 * Comparar el set de ids asignado a la tienda, con el set de ids que viene por parámetro:
		 *  - Se dejan en el set final las repetidas en ambos sets
		 *  - Se añaden, las que estén en set 2, pero no en set 1
		 *  - Se eliminan las que estén en set1, pero no en set 2
		 *  [1, 3, 5] --> [1, 4, 5, 6] ----> [1, 4, 5, 6]
		*/
	}
	
}
