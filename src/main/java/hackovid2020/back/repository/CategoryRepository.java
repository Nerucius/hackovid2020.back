package hackovid2020.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackovid2020.back.dao.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
