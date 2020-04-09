package hackovid2020.back.dto.shop;

import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.Constants;
import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.ShopImage;
import hackovid2020.back.dao.User;
import hackovid2020.back.utils.MD5Util;

public class ShopCreationRequest {
	
	private Long ownerId;
	
	private String coverUrl;
	
	private float latitude;
	
	private float longitude;
	
	private String streetName;
	
	private List<String> shopCategories;
	
	private List<String> shopImages;
	
	private ShopCreationRequest(
			@JsonProperty("ownerId") Long ownerId,
			@JsonProperty("coverUrl") String coverUrl,
			@JsonProperty("latitude") float latitude,
			@JsonProperty("longitude") float longitude,
			@JsonProperty("streetName") String streetName,
			@JsonProperty("shopCategories") List<String> shopCategories,
			@JsonProperty("shopImages") List<String> shopImages) {
		this.ownerId = ownerId;
		this.coverUrl = Constants.GRAVATAR + MD5Util.md5Hex(coverUrl);
		this.latitude = latitude;
		this.longitude = longitude;
		this.streetName = streetName;
		this.shopCategories = shopCategories;
		this.shopImages = shopImages;
	}
	
	public Shop toShop(User owner) {
		return Shop.createShop(coverUrl, owner, Calendar.getInstance().getTime(),
				Calendar.getInstance().getTime());
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public List<String> getShopCategories() {
		return shopCategories;
	}

	public List<String> getShopImages() {
		return shopImages;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public String getStreetName() {
		return streetName;
	}
	
	

}
