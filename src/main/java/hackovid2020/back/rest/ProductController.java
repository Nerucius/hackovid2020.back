package hackovid2020.back.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hackovid2020.back.dao.Product;
import hackovid2020.back.dto.product.ProductCreationRequest;
import hackovid2020.back.dto.product.ProductDetailsResponse;
import hackovid2020.back.service.FileService;
import hackovid2020.back.service.ProductService;
import hackovid2020.back.service.ShopService;

@RestController
@RequestMapping("/api/product")
@Api(value = "product", description = "Product management", tags = { "Product" })
public class ProductController {
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Creates a new product.")
	@Transactional
	public ProductDetailsResponse createProduct(@RequestBody ProductCreationRequest request) {
		Product product = productService.saveProduct(request.toProduct(shopService.findById(request.getShopId())));
		fileService.assignProductToFiles(request.getShopImageIds(), product);
		return ProductDetailsResponse.ofProduct(product);
	}
	
	
	public void getProduct() {
		
	}
	
	public void getShopProducts() {
		
	}
	
	public void updateProduct() {
		
	}
	
	public void updateProductImages() {
		
	}
	
	public void deleteProduct() {
		
	}

}
