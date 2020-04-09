package hackovid2020.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hackovid2020.back.dao.File;

public interface FileRepository extends JpaRepository<File, Long>{

	List<File> findByShopShopId(Long shopId);

}
