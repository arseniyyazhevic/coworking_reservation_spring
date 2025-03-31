package dao;

import entity.Booking;
import entity.CoworkingSpace;
import enums.TypeOfWorkspaces;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.CompositeOwner;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;

public class CoworkingSpaceDao {
    public void saveCoworkingSpace(CoworkingSpace coworkingSpace) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(coworkingSpace);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public CoworkingSpace getCoworkingSpaceById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(CoworkingSpace.class, id);
        }
    }

    // Get All Users
    public List<CoworkingSpace> getAllCoworkingSpaces() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from CoworkingSpace", CoworkingSpace.class).list();
        }
    }

    // Update User
    public void updateCoworkingSpace(CoworkingSpace coworkingSpace, Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "UPDATE CoworkingSpace c SET c.name = :name, c.availabilityStatus = :availabilityStatus, c.priceInDollars = :priceInDollars, c.typeOfWorkspaces = :typeOfWorkspaces WHERE c.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("name", coworkingSpace.getName());
            query.setParameter("availabilityStatus", coworkingSpace.isAvailabilityStatus());
            query.setParameter("priceInDollars", coworkingSpace.getPriceInDollars());
            query.setParameter("typeOfWorkspaces", coworkingSpace.getTypeOfWorkspaces());
            query.setParameter("id", id);

            int result = query.executeUpdate(); // Make sure update executes correctly
            if (result == 0) {
                throw new RuntimeException("Update failed: No coworking space found with ID " + id);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete User
    public void deleteCoworkingSpace(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CoworkingSpace coworkingSpace = session.get(CoworkingSpace.class, id);
            if (coworkingSpace != null) {
                session.remove(coworkingSpace);
                System.out.println("Coworking Space deleted successfully!");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
