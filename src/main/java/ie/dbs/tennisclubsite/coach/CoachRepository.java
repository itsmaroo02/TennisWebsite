package ie.dbs.tennisclubsite.coach;

import ie.dbs.tennisclubsite.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachRepository
        extends JpaRepository<Coach, Long> {

    List<Coach> findByCoachNameContainingIgnoreCase(
            String coachName
    );

}
