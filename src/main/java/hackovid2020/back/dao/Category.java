package hackovid2020.back.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	
	@OneToOne (fetch = FetchType.LAZY)
	private Category parentCategory;
	
	private Category(Shop shop, CategoriesEnum category, Category parentCategory, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.shop = shop;
		this.category = category;
		this.parentCategory = parentCategory;
	}
	
	public static Category createCategory(Shop shop, CategoriesEnum categoryEnum, Category parentCategory,
			Date createdAt, Date modifiedAt) {
		return new Category(shop, categoryEnum, parentCategory, createdAt, modifiedAt);
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public CategoriesEnum getCategory() {
		return category;
	}

	public void setCategory(CategoriesEnum category) {
		this.category = category;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	
	
	
}
