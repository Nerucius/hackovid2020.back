package hackovid2020.back.dao;

import hackovid2020.back.dao.support.CategoriesEnum;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity(name="category")
public class Category extends EntityObject {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long categoryId;
	
	private CategoriesEnum category;
	
	@OneToOne (fetch = FetchType.LAZY)
	private Category parentCategory;
	
	private Category(CategoriesEnum category, Category parentCategory, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.category = category;
		this.parentCategory = parentCategory;
	}
	
	public static Category createCategory(CategoriesEnum categoryEnum, Category parentCategory,
                                          Date createdAt, Date modifiedAt) {
		return new Category(categoryEnum, parentCategory, createdAt, modifiedAt);
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
