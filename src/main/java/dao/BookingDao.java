package dao;

import entity.Booking;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;

public class BookingDao {
    public void saveBooking(Booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(booking);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Booking getBookingById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Booking.class, id);
        }
    }

    // Get All Users
    public List<Booking> getAllBookings() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Booking", Booking.class).list();
        }
    }

    // Update User
    public void updateBooking(Booking booking, Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "UPDATE Booking b SET b.customerName = :name, b.date = :date, b.startAndEndOfBookingTime = :timeInterval, b.coworkingSpace = :coworkingId WHERE b.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("name", booking.getCustomerName());
            query.setParameter("date", booking.getDate());
            query.setParameter("timeInterval", booking.getStartAndEndOfBookingTime());
            query.setParameter("coworkingId", booking.getCoworkingSpace());
            query.setParameter("id", id);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete User
    public void deleteBooking(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Booking booking = session.get(Booking.class, id);
            if (booking != null) {
                session.remove(booking);
                System.out.println("Booking deleted successfully!");
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
