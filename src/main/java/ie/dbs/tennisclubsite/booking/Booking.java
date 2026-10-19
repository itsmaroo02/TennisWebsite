package ie.dbs.tennisclubsite.booking;

import ie.dbs.tennisclubsite.court.Court;
import ie.dbs.tennisclubsite.member.Member;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "booking")

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingID;

    @Column(nullable = false)
    private String bookingType;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "court_courtID", nullable = false)
    private Court court;

    @ManyToOne
    @JoinColumn(name = "member_memberID", nullable = false)
    private Member member;

    public Booking(){

    }

    public Booking(String bookingType, LocalDate date, LocalTime time, Court court, Member member) {
        this.bookingType = bookingType;
        this.date = date;
        this.time = time;
        this.court = court;
        this.member = member;
    }

    public Long getBookingID() { return bookingID; }

    public void setBookingID(Long bookingID) { this.bookingID = bookingID; }

    public String getBookingType() { return bookingType; }

    public void setBookingType(String bookingType) { this.bookingType = bookingType; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }

    public void setTime(LocalTime time) { this.time = time; }

    public Court getCourtID() { return court; }

    public void setCourtID(Court court) { this.court = court; }

    public Member getMemberID() {
        return member;
    }

    public void setMemberID(Member memberID) {
        this.member = memberID;
    }

}
