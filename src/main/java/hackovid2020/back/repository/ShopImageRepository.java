package hackovid2020.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackovid2020.back.dao.ShopImage;

@Repository
public interface ShopImageRepository extends JpaRepository<ShopImage, Long> {

	List<ShopImage> findByShopShopId(Long shopId);

}
