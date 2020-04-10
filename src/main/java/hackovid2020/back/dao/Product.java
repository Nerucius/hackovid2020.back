package hackovid2020.back.dao;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name="product")
public class Product extends EntityObject {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long productId;
	
	private String name;
	
	private String description;
	
	private float price;
	
	private Shop shop;
	
	@OneToMany(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<File> productImages;
	
	private Product(String name, String description, float price, Shop shop, Set<File> productImages, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.name = name;
		this.description = description;
		this.price = price;
		this.shop = shop;
		this.productImages = productImages;
	}
	
	public Product createProduct(String name, String description, float price, Shop shop, Set<File> productImages,
			Date createdAt, Date modifiedAt) {
		return new Product(name, description, price, shop, productImages, createdAt, modifiedAt);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Set<File> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<File> productImages) {
		this.productImages = productImages;
	}

	public Long getProductId() {
		return productId;
	}
	
	
	
}
