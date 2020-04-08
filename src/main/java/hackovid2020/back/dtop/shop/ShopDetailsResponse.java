package hackovid2020.back.dtop.shop;

import java.awt.geom.Point2D;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.Shop;
import hackovid2020.back.dto.user.UserDetailsResponse;

public class ShopDetailsResponse {
	
	@JsonProperty
	private Long shopId;
	
	@JsonProperty
	private String coverUrl;
	
	@JsonProperty
	private UserDetailsResponse userDetailsResponse;
	
	@JsonProperty
	private Point2D location;
	
	@JsonProperty
	private Date createdAt;
	
	@JsonProperty
	private Date modifiedAt;
	
	@JsonCreator
	private ShopDetailsResponse(Long shopId, String coverUrl, UserDetailsResponse userDetailsResponse, Point2D location,
			Date createdAt, Date modifiedAt) {
		this.shopId = shopId;
		this.coverUrl = coverUrl;
		this.userDetailsResponse = userDetailsResponse;
		this.location = location;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static ShopDetailsResponse ofShop(Shop shop, UserDetailsResponse userDetailsResponse) {
		return new ShopDetailsResponse(shop.getShopId(), shop.getCoverId(), userDetailsResponse, shop.getLocation(),
				shop.getCreatedAt(), shop.getModifiedAt());
	}

}
