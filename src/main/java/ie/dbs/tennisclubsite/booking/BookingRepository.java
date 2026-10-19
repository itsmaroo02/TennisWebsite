package ie.dbs.tennisclubsite.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface BookingRepository
 extends JpaRepository<Booking, Long> {

    List<Booking> findByBookingTypeContainingIgnoreCase(
            String bookingType
    );

    }

