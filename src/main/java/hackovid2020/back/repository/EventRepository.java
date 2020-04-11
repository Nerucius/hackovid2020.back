package hackovid2020.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackovid2020.back.dao.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
