package ie.dbs.tennisclubsite.member;

import ie.dbs.tennisclubsite.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository
        extends JpaRepository<Member, Long> {

    List<Member> findByMemberNameContainingIgnoreCase(
            String memberName
    );

}
