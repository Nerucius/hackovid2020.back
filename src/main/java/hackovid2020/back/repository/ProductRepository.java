package hackovid2020.back.repository;

import hackovid2020.back.dao.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByShopShopId(Long shopId);

}
