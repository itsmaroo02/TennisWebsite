package ie.dbs.tennisclubsite.member;

import ie.dbs.tennisclubsite.court.Court;
import ie.dbs.tennisclubsite.court.CourtRepository;
import ie.dbs.tennisclubsite.booking.Booking;
import ie.dbs.tennisclubsite.booking.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        return memberRepository
                .findById(id)
                .orElse(null);
    }

    //*** Handles the creation of a new member ***//
    @Transactional
    public Member createMember(MemberForm form) {

        Member member = new Member(
                form.getMemberName(),
                form.getMemberEmail()
        );

        return memberRepository.save(member);

    }

    //*** Handles updating an existing booking ***//
    @Transactional
    public Member updateMember(Long id, MemberForm form) {

        Member member = memberRepository
                .findById(id)
                .orElse(null);

        if (member == null) {
            return null;
        }

        member.setMemberName(form.getMemberName());
        member.setMemberEmail(form.getMemberEmail());

        return memberRepository.save(member);

    }

    //*** Handles the deletion of a booking ***//
    @Transactional
    public boolean deleteMember(Long id) {

        if (!memberRepository.existsById(id)) {
            return false;
        }

        memberRepository.deleteById(id);

        return true;
    }

}

