package hackovid2020.back.dto.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.Product;
import hackovid2020.back.dao.Shop;

import java.util.Calendar;
import java.util.List;

public class ProductCreationRequest {

	private String name;
	
	private String description;
	
	private float price;
	
	private Long shopId;
	
	private List<Long> shopImageIds;
	
	@JsonCreator
	private ProductCreationRequest(
			@JsonProperty("name") String name,
			@JsonProperty("description") String description,
			@JsonProperty("price") float price,
			@JsonProperty("shopId") Long shopId,
			@JsonProperty("productImageIds") List<Long> shopImages) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Product toProduct(Shop shop) {
		return Product.createProduct(name, description, price, shop, null,
				Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}

	public Long getShopId() {
		return shopId;
	}

	public List<Long> getShopImageIds() {
		return shopImageIds;
	}
	
}
