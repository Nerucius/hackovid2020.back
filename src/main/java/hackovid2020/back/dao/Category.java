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

import hackovid2020.back.dao.support.CategoriesEnum;

@Entity(name="shop_category")
public class Category extends EntityObject {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long categoryId;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Shop shop;
	
	private CategoriesEnum category;
	
	private Category(Shop shop, CategoriesEnum category, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.shop = shop;
		this.category = category;
	}
	
	public static Category createCategory(Shop shop, CategoriesEnum categoryEnum, Date createdAt, Date modifiedAt) {
		return new Category(shop, categoryEnum, createdAt, modifiedAt);
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public CategoriesEnum getCategoryEnum() {
		return category;
	}

	public void setCategoryEnum(CategoriesEnum category) {
		this.category = category;
	}

	public Long getCategoryId() {
		return categoryId;
	}
	
}
