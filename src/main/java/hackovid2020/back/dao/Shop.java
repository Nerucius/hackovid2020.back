package hackovid2020.back.dao;

import java.awt.geom.Point2D;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name="shop")
public class Shop extends EntityObject {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long shopId;
	
	@OneToOne (fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	private String coverUrl;
	
	private Shop(String coverId, User owner, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.coverUrl = coverId;
		this.user = owner;
	}
	
	public static Shop createShop(String coverId, User owner, Date createdAt, Date modifiedAt) {
		return new Shop(coverId,  owner, createdAt, modifiedAt);
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User owner) {
		this.user = owner;
	}

	public String getCoverId() {
		return coverUrl;
	}

	
	
}
