package hackovid2020.back.dto.shop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.support.CategoriesEnum;

public class ShopCategoryResponse {
	
	@JsonProperty
	private Long shopCategoryId;
	
	@JsonProperty
	private CategoriesEnum shopCategory;
	
	@JsonCreator
	private ShopCategoryResponse(Long shopCategoryId, CategoriesEnum shopCategory) {
		this.shopCategoryId = shopCategoryId;
		this.shopCategory = shopCategory;
	}
	
	public static ShopCategoryResponse ofCategory(Category category) {
		return new ShopCategoryResponse(category.getCategoryId(), category.getCategory());
	}

}
