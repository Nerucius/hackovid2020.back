package hackovid2020.back.repository;

import hackovid2020.back.dao.ShopLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopLocationRepository extends JpaRepository<ShopLocation, Long>{
	
	ShopLocation findByShopShopId(Long shopId);

}
