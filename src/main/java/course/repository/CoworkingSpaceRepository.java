package course.repository;

import course.entity.CoworkingSpace;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoworkingSpaceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveCoworkingSpace(CoworkingSpace coworkingSpace) {
        entityManager.persist(coworkingSpace);
    }

    @Transactional
    public CoworkingSpace getCoworkingSpaceById(Long id) {
        return entityManager.find(CoworkingSpace.class, id);
    }

    @Transactional
    public List<CoworkingSpace> getAllCoworkingSpaces() {
        return entityManager.createQuery("from CoworkingSpace", CoworkingSpace.class).getResultList();
    }

    @Transactional
    public void updateCoworkingSpace(CoworkingSpace coworkingSpace, Long id) {

        entityManager.createQuery("UPDATE CoworkingSpace c SET c.name = :name, c.availabilityStatus = :availabilityStatus, c.priceInDollars = :priceInDollars, c.typeOfWorkspaces = :typeOfWorkspaces WHERE c.id = :id")
                .setParameter("name", coworkingSpace.getName())
                .setParameter("availabilityStatus", coworkingSpace.isAvailabilityStatus())
                .setParameter("priceInDollars", coworkingSpace.getPriceInDollars())
                .setParameter("typeOfWorkspaces", coworkingSpace.getTypeOfWorkspaces())
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public void deleteCoworkingSpace(Long id) {
        CoworkingSpace coworkingSpace = entityManager.find(CoworkingSpace.class, id);
        if (coworkingSpace != null) {
            entityManager.remove(coworkingSpace);
            System.out.println("Coworking Space deleted successfully!");
        }
    }
}
