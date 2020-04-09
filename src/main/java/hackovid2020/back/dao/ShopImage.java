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

@Entity(name="shop_image")
public class ShopImage extends EntityObject {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long shopImageId;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Shop shop;
	
	private String imageUrl;
	
	private ShopImage(Shop shop, String imageUrl, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.shop = shop;
		this.imageUrl = imageUrl;
	}

	public static ShopImage createCatalogImage(Shop shop, String imageUrl, Date createdAt, Date modifiedAt) {
		return new ShopImage(shop, imageUrl, createdAt, modifiedAt);
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getShopImageId() {
		return shopImageId;
	}
	
}