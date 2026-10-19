package ie.dbs.tennisclubsite.court;

import ie.dbs.tennisclubsite.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository
        extends JpaRepository<Court, Long> {

}
