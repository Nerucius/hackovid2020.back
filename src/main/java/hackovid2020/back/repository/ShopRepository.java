package hackovid2020.back.repository;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.Shop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

	@Query("SELECT c FROM category c INNER JOIN shop_category sc WHERE sc.shop_shop_id :shopId")
	List<Category> findAllShopCategories(Long shopId);
	
}
