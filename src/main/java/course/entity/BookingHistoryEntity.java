package course.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking_history")
@Data
public class BookingHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private String customerName;
    private String startAndEndOfBookingTime;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "coworking_space_id")
    private CoworkingSpaceEntity coworkingSpaceEntity;

    private LocalDateTime savedAt;
}
