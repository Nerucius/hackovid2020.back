package hackovid2020.back.dto.shop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.Location;
import hackovid2020.back.dao.User;
import hackovid2020.back.dto.file.FileRequest;

import java.util.Calendar;
import java.util.List;

public class ShopCreationRequest {
	
	private Long ownerId;
	
	private FileRequest coverImage;
	
	private float latitude;
	
	private float longitude;
	
	private String streetName;
	
	private List<Long> shopCategoryIds;
	
	private List<Long> shopImageIds;
	
	@JsonCreator
	private ShopCreationRequest(
			@JsonProperty("ownerId") Long ownerId,
			@JsonProperty("coverImage") FileRequest coverImage,
			@JsonProperty("latitude") float latitude,
			@JsonProperty("longitude") float longitude,
			@JsonProperty("streetName") String streetName,
			@JsonProperty("shopCategoryIds") List<Long> shopCategoryIds,
			@JsonProperty("shopImageIds") List<Long> shopImages) {
		this.ownerId = ownerId;
		this.coverImage = coverImage;
		this.latitude = latitude;
		this.longitude = longitude;
		this.streetName = streetName;
		this.shopCategoryIds = shopCategoryIds;
		this.shopImageIds = shopImages;
	}
	
	public Shop toShop(User owner, Location location) {
		return Shop.createShop(null, owner, null, null, location, Calendar.getInstance().getTime(),
				Calendar.getInstance().getTime());
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public FileRequest getCoverImage() {
		return coverImage;
	}

	public List<Long> getShopCategoryIds() {
		return shopCategoryIds;
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
