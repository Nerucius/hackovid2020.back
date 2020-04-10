package hackovid2020.back.dto.shop;

import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.ShopLocation;
import hackovid2020.back.dao.User;
import hackovid2020.back.dto.file.FileRequest;

public class ShopCreationRequest {
	
	private Long ownerId;
	
	private FileRequest coverImage;
	
	private float latitude;
	
	private float longitude;
	
	private String streetName;
	
	private List<String> shopCategories;
	
	private List<Long> shopImageIds;
	
	private ShopCreationRequest(
			@JsonProperty("ownerId") Long ownerId,
			@JsonProperty("coverImage") FileRequest coverImage,
			@JsonProperty("latitude") float latitude,
			@JsonProperty("longitude") float longitude,
			@JsonProperty("streetName") String streetName,
			@JsonProperty("shopCategories") List<String> shopCategories,
			@JsonProperty("shopImageIds") List<Long> shopImages) {
		this.ownerId = ownerId;
		this.coverImage = coverImage;
		this.latitude = latitude;
		this.longitude = longitude;
		this.streetName = streetName;
		this.shopCategories = shopCategories;
		this.shopImageIds = shopImages;
	}
	
	public Shop toShop(User owner, ShopLocation location) {
		return Shop.createShop(null, owner, null, null, location, Calendar.getInstance().getTime(),
				Calendar.getInstance().getTime());
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public FileRequest getCoverImage() {
		return coverImage;
	}

	public List<String> getShopCategories() {
		return shopCategories;
	}

	public List<Long> getShopImageIds() {
		return shopImageIds;
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
