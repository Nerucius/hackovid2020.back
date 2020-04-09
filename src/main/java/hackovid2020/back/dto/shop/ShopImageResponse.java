package hackovid2020.back.dto.shop;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.ShopImage;

public class ShopImageResponse {
	
	@JsonProperty
	private Long shopImageId;
	
	@JsonProperty
	private String shopImageUrl;
	
	@JsonProperty
	private Date createdAt;
	
	@JsonProperty
	private Date modifiedAt;

	@JsonCreator
	private ShopImageResponse(Long shopImageId, String shopImageUrl, Date createdAt, Date modifiedAt) {
		this.shopImageId = shopImageId;
		this.shopImageUrl = shopImageUrl;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static ShopImageResponse ofShopImage(ShopImage shopImage) {
		return new ShopImageResponse(shopImage.getShopImageId(), shopImage.getImageUrl(),
				shopImage.getCreatedAt(), shopImage.getModifiedAt());
	}
}
