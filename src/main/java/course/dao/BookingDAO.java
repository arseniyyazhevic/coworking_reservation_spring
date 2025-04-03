package course.dao;

import course.entity.Booking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BookingDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveBooking(Booking booking) {
        entityManager.persist(booking);
    }

    @Transactional
    public Booking getBookingById(Long id) {
        return entityManager.find(Booking.class, id);
    }

    @Transactional
    public List<Booking> getAllBookings() {
        return entityManager.createQuery("from Booking", Booking.class).getResultList();
    }

    @Transactional
    public void updateBooking(Booking booking, Long id) {
        entityManager.createQuery("UPDATE Booking b SET b.customerName = :name, b.date = :date, b.startAndEndOfBookingTime = :timeInterval, b.coworkingSpace = :coworkingId WHERE b.id = :id")
                .setParameter("name", booking.getCustomerName())
                .setParameter("date", booking.getDate())
                .setParameter("timeInterval", booking.getStartAndEndOfBookingTime())
                .setParameter("coworkingId", booking.getCoworkingSpace())
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public void deleteBooking(Long id) {
        Booking booking = entityManager.find(Booking.class, id);
        if (booking != null) {
            entityManager.remove(booking);
            System.out.println("Booking deleted successfully!");
        }
    }
}
