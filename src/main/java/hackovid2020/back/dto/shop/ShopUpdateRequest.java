package hackovid2020.back.dto.shop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopUpdateRequest {
	
	private Long coverImageId;
	
	private float latitude;
	
	private float longitude;
	
	private String streetName;
	
	@JsonCreator
	private ShopUpdateRequest(
			@JsonProperty("coverImageId") Long coverImageId,
			@JsonProperty("latitude") float latitude,
			@JsonProperty("longitude") float longitude,
			@JsonProperty("streetName") String streetName) {
		this.coverImageId = coverImageId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.streetName = streetName;
	}

	public Long getCoverImageId() {
		return coverImageId;
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
