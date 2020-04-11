package hackovid2020.back.service;

import hackovid2020.back.dao.Product;
import hackovid2020.back.repository.ProductRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	public ProductService(ProductRepository productRepository) {
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	
	
}
