package ie.dbs.tennisclubsite.member;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberID;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String memberEmail;

    public Member() {
    }

    public Member(String memberName, String memberEmail) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }

    public Member(
            Long memberID,
            String memberName,
            String memberEmail) {

        this.memberID = memberID;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }

    public Long getMemberID() {
        return memberID;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() { return memberEmail; }

    public void setMemberEmail(String memberEmail) { this.memberEmail = memberEmail; }

}
