package course.repository;

import course.entity.CoworkingSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoworkingSpaceRepository extends JpaRepository<CoworkingSpaceEntity, Long> {


}
