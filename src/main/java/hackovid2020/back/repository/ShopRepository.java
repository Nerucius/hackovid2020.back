package hackovid2020.back.repository;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.Shop;

import java.util.List;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query(
            value = "SELECT c FROM category c " +
                    "INNER JOIN shop_category sc ON c.category_id = sc.shop_category_id " +
                    "INNER JOIN shop s ON sc.shop_shop_id = s.shop_id " +
                    "WHERE s.shop_id = :shopId",
            nativeQuery = true
    )
    List<Category> findAllShopCategories(@Param("shopId") Long shopId);

}
