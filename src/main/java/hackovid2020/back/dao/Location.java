package hackovid2020.back.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name="location")
public class Location extends EntityObject {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long locationId;
	
	private float latitude;
	
	private float longitude;
	
	private String streetName;
	
	private Location(float latitude, float longitude, String streetName, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.latitude = latitude;
		this.longitude = longitude;
		this.streetName = streetName;
	}
	
	public static Location createLocation(float latitude, float longitude, String streetName,
                                                  Date createdAt, Date modifiedAt) {
		return new Location(latitude, longitude, streetName, createdAt, modifiedAt);
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Long getLocationId() {
		return locationId;
	}
	
}
