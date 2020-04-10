package hackovid2020.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackovid2020.back.dao.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long>{

}
