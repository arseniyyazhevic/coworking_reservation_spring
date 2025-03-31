package dao;

import entity.CoworkingSpace;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import util.HibernateUtils;

import java.util.List;

public class CoworkingSpaceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveCoworkingSpace(CoworkingSpace coworkingSpace) {
        sessionFactory.getCurrentSession().persist(coworkingSpace);
    }

    @Transactional
    public CoworkingSpace getCoworkingSpaceById(Long id) {
        return sessionFactory.getCurrentSession().get(CoworkingSpace.class, id);
    }

    @Transactional
    public List<CoworkingSpace> getAllCoworkingSpaces() {
        return sessionFactory.getCurrentSession().createQuery("from CoworkingSpace", CoworkingSpace.class).list();
    }

    @Transactional
    public void updateCoworkingSpace(CoworkingSpace coworkingSpace, Long id) {

        String hql = "UPDATE CoworkingSpace c SET c.name = :name, c.availabilityStatus = :availabilityStatus, c.priceInDollars = :priceInDollars, c.typeOfWorkspaces = :typeOfWorkspaces WHERE c.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("name", coworkingSpace.getName());
        query.setParameter("availabilityStatus", coworkingSpace.isAvailabilityStatus());
        query.setParameter("priceInDollars", coworkingSpace.getPriceInDollars());
        query.setParameter("typeOfWorkspaces", coworkingSpace.getTypeOfWorkspaces());
        query.setParameter("id", id);
        int result = query.executeUpdate();
        if (result == 0) {
            throw new RuntimeException("Update failed: No coworking space found with ID " + id);
        }
    }

    @Transactional
    public void deleteCoworkingSpace(Long id) {
        CoworkingSpace coworkingSpace = sessionFactory.getCurrentSession().get(CoworkingSpace.class, id);
        if (coworkingSpace != null) {
            sessionFactory.getCurrentSession().remove(coworkingSpace);
            System.out.println("Coworking Space deleted successfully!");
        }
    }
}
