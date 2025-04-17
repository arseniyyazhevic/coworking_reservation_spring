package course.repository;

import course.entity.BookingEntity;
import course.entity.BookingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistoryEntity, Long> {
    List<BookingHistoryEntity> findByBookingIdOrderBySavedAtDesc(Long bookingId);
}
