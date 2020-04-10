package hackovid2020.back.rest;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hackovid2020.back.dto.product.CategoryRequest;
import hackovid2020.back.dto.product.CategoryResponse;
import hackovid2020.back.dto.product.CategoryResponseList;
import hackovid2020.back.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/category")
@Api(value = "category", description = "Category management", tags = { "Category" })
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Creates a new category.")
	@Transactional
	public CategoryResponse createCategory(CategoryRequest request) {
		return CategoryResponse.ofCategory(categoryService.saveCategory(request.toCategory()));
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Get a category.")
	@Transactional
	public CategoryResponse getCategory(@PathVariable("id") Long id) {
		return CategoryResponse.ofCategory(categoryService.getCategory(id));
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Get all categories.")
	@Transactional
	public CategoryResponseList getCategories() {
		return CategoryResponseList.ofCategoriesList(categoryService.getAllCategories());
	}
	
	@PutMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Update a file.")
	@Transactional
	public CategoryResponse updateCategory(CategoryRequest request, @PathVariable("id") Long id) {
		return CategoryResponse.ofCategory(categoryService.updateCategory(id, request.getCategoryType(),
				request.getParentCategory()));
	}

}
