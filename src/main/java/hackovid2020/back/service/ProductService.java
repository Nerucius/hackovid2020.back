package hackovid2020.back.service;

import hackovid2020.back.dao.Product;
import hackovid2020.back.repository.ProductRepository;

import java.util.List;

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

	public Product findProduct(Long id) {
		return productRepository.getOne(id);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public List<Product> findAllShopProducts(Long shopId) {
		return productRepository.findByShopShopId(shopId);
	}
	
	
	
}
