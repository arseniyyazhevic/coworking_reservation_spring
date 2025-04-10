package course.repository;

import course.entity.CoworkingSpace;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CoworkingSpaceRepository extends JpaRepository<CoworkingSpace, Long> {


}
