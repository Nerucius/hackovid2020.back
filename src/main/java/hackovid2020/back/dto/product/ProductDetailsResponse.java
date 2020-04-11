package hackovid2020.back.dto.product;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.Product;
import hackovid2020.back.dto.file.FileResponse;

public class ProductDetailsResponse {
	
	@JsonProperty
	private Long productId;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String description;
	
	@JsonProperty
	private float price;
	
	@JsonProperty
	private Long shopId;
	
	@JsonProperty
	private Date createdAt;
	
	@JsonProperty
	private Date modifiedAt;
	
	@JsonProperty
	private Set<FileResponse> shopImages;
	
	@JsonCreator
	private ProductDetailsResponse(Long productId, String name, String description, float price, Long shopId,
			Set<FileResponse> shopImages, Date createdAt, Date modifiedAt) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.shopId = shopId;
		this.shopImages = shopImages;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static ProductDetailsResponse ofProduct(Product product) {
		Set<FileResponse> productImages = product.getProductImages().stream().map(FileResponse::ofFile).collect(Collectors.toSet());
		return new ProductDetailsResponse(product.getProductId(), product.getName(), product.getDescription(), product.getPrice(),
				product.getShop().getShopId(), productImages, product.getCreatedAt(), product.getModifiedAt());
	}

}
