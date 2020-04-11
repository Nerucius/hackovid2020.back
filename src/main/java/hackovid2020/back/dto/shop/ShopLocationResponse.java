package hackovid2020.back.dto.shop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.Location;

import java.util.Date;

public class ShopLocationResponse {
	
	@JsonProperty
	private Long shopLocationId;
	
	@JsonProperty
	private float latitude;
	
	@JsonProperty
	private float longitued;
	
	@JsonProperty
	private String streetName;
	
	@JsonProperty
	private Date createdAt;
	
	@JsonProperty
	private Date modifiedAt;
	
	@JsonCreator
	private ShopLocationResponse(Long shopLocationId, float latitude, float longitude, String streetName,
                                 Date createdAt, Date modifiedAt) {
		this.shopLocationId = shopLocationId;
		this.latitude = latitude;
		this.longitued = longitude;
		this.streetName = streetName;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static ShopLocationResponse ofShopLocation(Location location) {
		return new ShopLocationResponse(location.getLocationId(), location.getLatitude(), location.getLongitude(),
				location.getStreetName(), location.getCreatedAt(), location.getModifiedAt());
	}

}
