package hackovid2020.back.dto.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
}
