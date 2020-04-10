package hackovid2020.back.repository;

import hackovid2020.back.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	List<Category> findByShopShopId(Long shopId);

}
