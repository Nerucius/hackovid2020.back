package hackovid2020.back.dto.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.support.CategoriesEnum;

public class CategoryResponse {
	
	@JsonProperty
	private Long categoryId;
	
	@JsonProperty
	private CategoriesEnum categoryType;
	
	@JsonProperty
	private Category parentCategory;
	
	@JsonCreator
	private CategoryResponse(Long categoryId, CategoriesEnum categoryType, Category parentCategory) {
		this.categoryId = categoryId;
		this.categoryType = categoryType;
		this.parentCategory = parentCategory;
	}
	
	public static CategoryResponse ofCategory(Category category) {
		return new CategoryResponse(category.getCategoryId(), category.getCategory(), category.getParentCategory());
	}

}
