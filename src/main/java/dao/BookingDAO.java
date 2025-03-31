package dao;

import entity.Booking;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BookingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void saveBooking(Booking booking) {
       sessionFactory.getCurrentSession().persist(booking);
    }

    @Transactional
    public Booking getBookingById(Long id) {
            return sessionFactory.getCurrentSession().get(Booking.class, id);
    }

    @Transactional
    public List<Booking> getAllBookings() {
            return sessionFactory.getCurrentSession().createQuery("from Booking", Booking.class).list();
    }

    @Transactional
    public void updateBooking(Booking booking, Long id) {
            String hql = "UPDATE Booking b SET b.customerName = :name, b.date = :date, b.startAndEndOfBookingTime = :timeInterval, b.coworkingSpace = :coworkingId WHERE b.id = :id";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter("name", booking.getCustomerName());
            query.setParameter("date", booking.getDate());
            query.setParameter("timeInterval", booking.getStartAndEndOfBookingTime());
            query.setParameter("coworkingId", booking.getCoworkingSpace());
            query.setParameter("id", id);
            query.executeUpdate();
    }

    @Transactional
    public void deleteBooking(Long id) {
        Booking booking = sessionFactory.getCurrentSession().get(Booking.class, id);
        if (booking != null) {
            sessionFactory.getCurrentSession().remove(booking);
            System.out.println("Booking deleted successfully!");
        }
    }
}
