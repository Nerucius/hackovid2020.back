package hackovid2020.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackovid2020.back.dao.CatalogImage;

@Repository
public interface CatalogImageRepository extends JpaRepository<CatalogImage, Long> {

}
