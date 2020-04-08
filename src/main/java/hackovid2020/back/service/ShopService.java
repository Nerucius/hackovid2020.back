package hackovid2020.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hackovid2020.back.repository.CatalogImageRepository;
import hackovid2020.back.repository.CategoryRepository;
import hackovid2020.back.repository.ShopRepository;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CatalogImageRepository catalogImageRepository;
	
	

}
