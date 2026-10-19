package ie.dbs.tennisclubsite.member;

import ie.dbs.tennisclubsite.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository
        extends JpaRepository<Member, Long> {

}
