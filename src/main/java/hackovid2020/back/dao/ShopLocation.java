package hackovid2020.back.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name="shop_location")
public class ShopLocation extends EntityObject {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long shopLocationId;
	
	private float latitude;
	
	private float longitude;
	
	private String streetName;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Shop shop;
	
	private ShopLocation(float latitude, float longitude, String streetName, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.latitude = latitude;
		this.longitude = longitude;
		this.streetName = streetName;
	}
	
	public static ShopLocation createShopLocation(float latitude, float longitude, String streetName,
			Date createdAt, Date modifiedAt) {
		return new ShopLocation(latitude, longitude, streetName, createdAt, modifiedAt);
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

	public Long getShopLocationId() {
		return shopLocationId;
	}
	
}
