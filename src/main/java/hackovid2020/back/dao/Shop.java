package hackovid2020.back.dao;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@OneToOne (fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private File coverImage;
	
	@OneToMany
	private Set<File> shopImages;
	
	private Shop(File coverImage, User user, Set<File> shopImages, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.coverImage = coverImage;
		this.user = user;
		this.shopImages = shopImages;
	}
	
	public static Shop createShop(File coverImage, User user, Set<File> shopImages, Date createdAt, Date modifiedAt) {
		return new Shop(coverImage,  user, shopImages, createdAt, modifiedAt);
	}

	public Long getShopId() {
		return shopId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(File coverImage) {
		this.coverImage = coverImage;
	}

	public Set<File> getShopImages() {
		return shopImages;
	}

	public void setShopImages(Set<File> shopImages) {
		this.shopImages = shopImages;
	}
	
}
