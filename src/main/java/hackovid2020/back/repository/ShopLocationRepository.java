package hackovid2020.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackovid2020.back.dao.ShopLocation;

@Repository
public interface ShopLocationRepository extends JpaRepository<ShopLocation, Long>{
	
	ShopLocation findByShopShopId(Long shopId);

}
