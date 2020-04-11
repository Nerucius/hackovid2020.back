package hackovid2020.back.dao;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<File> shopImages;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(
			name="shop_category",
			joinColumns = @JoinColumn(name="category_id"),
			inverseJoinColumns = @JoinColumn(name="shop_id"))
	private Set<Category> categories;
	
	@OneToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Location location;
	
	private Shop(File coverImage, User user, Set<File> shopImages, Set<Category> categories,
                 Location location, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.coverImage = coverImage;
		this.user = user;
		this.shopImages = shopImages;
		this.location = location;
		this.categories = categories;
	}
	
	public static Shop createShop(File coverImage, User user, Set<File> shopImages, Set<Category> categories,
                                  Location location, Date createdAt, Date modifiedAt) {
		return new Shop(coverImage,  user, shopImages, categories, location, createdAt, modifiedAt);
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
}
