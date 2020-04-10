package hackovid2020.back.service;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.support.CategoriesEnum;
import hackovid2020.back.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category getCategory(Long id) {
		return categoryRepository.getOne(id);
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category updateCategory(Long id, CategoriesEnum categoryType, Category parentCategory) {
		Category category = categoryRepository.getOne(id);
		category.setCategory(categoryType);
		category.setParentCategory(parentCategory);
		category.setModifiedAt(Calendar.getInstance().getTime());
		return categoryRepository.save(category);
	}

	public List<Category> findAllShopCategories(List<Long> shopCategoryIds) {
		return categoryRepository.findAllById(shopCategoryIds);
	}

}
