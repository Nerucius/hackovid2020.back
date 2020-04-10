package hackovid2020.back.dto.category;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryResponseList {

	@JsonProperty
	private List<CategoryResponse> categories;
	
	@JsonCreator
	private CategoryResponseList(List<CategoryResponse> categories) {
		this.categories = categories;
	}
	
	public static CategoryResponseList ofCategoriesList(List<Category> categoriesList) {
		return new CategoryResponseList(categoriesList.stream()
				.map(CategoryResponse::ofCategory).collect(Collectors.toList()));
	}
	
}
