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
	private User owner;
	
	private String coverUrl;
	
	private Point2D location;
	
	private Shop(String coverId, Point2D location, User owner, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.coverUrl = coverId;
		this.location = location;
		this.owner = owner;
	}
	
	public static Shop createShop(String coverId, Point2D location, User owner, Date createdAt, Date modifiedAt) {
		return new Shop(coverId, location, owner, createdAt, modifiedAt);
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public User getOwner() {
		return owner;
	}

	public void serOwner(User owner) {
		this.owner = owner;
	}

	public Point2D getLocation() {
		return location;
	}

	public void setLocation(Point2D location) {
		this.location = location;
	}

	public String getCoverId() {
		return coverUrl;
	}

	
	
}
