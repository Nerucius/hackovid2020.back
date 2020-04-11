package hackovid2020.back.dto.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.support.CategoriesEnum;

import java.util.Calendar;

public class CategoryRequest {
	
	@JsonProperty
	private CategoriesEnum categoryType;
	
	@JsonProperty
	private Category parentCategory;
	
	@JsonCreator
	private CategoryRequest(CategoriesEnum categoryType, Category parentCategory) {
		this.categoryType = categoryType;
		this.parentCategory = parentCategory;
	}
	
	public Category toCategory() {
		return Category.createCategory(categoryType, parentCategory,
				Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
	}

	public CategoriesEnum getCategoryType() {
		return categoryType;
	}

	public Category getParentCategory() {
		return parentCategory;
	}
	
}
