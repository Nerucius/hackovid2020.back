package hackovid2020.back.repository;

import hackovid2020.back.dao.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long>{

	List<File> findByShopShopId(Long shopId);

}
