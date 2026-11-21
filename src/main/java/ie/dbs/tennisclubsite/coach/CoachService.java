package ie.dbs.tennisclubsite.coach;

import ie.dbs.tennisclubsite.court.Court;
import ie.dbs.tennisclubsite.court.CourtRepository;
import ie.dbs.tennisclubsite.booking.Booking;
import ie.dbs.tennisclubsite.booking.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CoachService {

    private final CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Transactional(readOnly = true)
    public List<Coach> getCoaches() {
        return coachRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Coach getCoachById(Long id) {
        return coachRepository
                .findById(id)
                .orElse(null);
    }

    //*** Handles the creation of a new coach ***//
    @Transactional
    public Coach createCoach(CoachForm form) {

        Coach coach = new Coach(
                form.getCoachName(),
                form.getCoachEmail()
        );

        return coachRepository.save(coach);

    }

    //*** Handles updating an existing coach ***//
    @Transactional
    public Coach updateCoach(Long id, CoachForm form) {

        Coach coach = coachRepository
                .findById(id)
                .orElse(null);

        if (coach == null) {
            return null;
        }

        coach.setCoachName(form.getCoachName());
        coach.setCoachEmail(form.getCoachEmail());

        return coachRepository.save(coach);

    }

    //*** Handles the deletion of a coach ***//
    @Transactional
    public boolean deleteCoach(Long id) {

        if (!coachRepository.existsById(id)) {
            return false;
        }

        coachRepository.deleteById(id);

        return true;
    }

}

