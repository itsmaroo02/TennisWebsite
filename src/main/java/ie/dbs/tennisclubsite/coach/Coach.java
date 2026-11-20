package ie.dbs.tennisclubsite.coach;

import ie.dbs.tennisclubsite.court.Court;
import ie.dbs.tennisclubsite.member.Member;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

// Instructs JPA this class represents something stored in a database.
@Entity
@Table(name = "coach")

public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coachID;

    @Column(nullable = false)
    private String coachName;

    @Column(nullable = false)
    private String coachEmail;

    public Coach(){

    }

    public Coach(String coachName, String coachEmail) {
        this.coachName = coachName;
        this.coachEmail = coachEmail;
    }

    public Long getCoachID() { return coachID; }

    public void setCoachID(Long coachID) { this.coachID = coachID; }

    public String getCoachName() { return coachName; }

    public void setCoachName(String coachName) { this.coachName = coachName; }

    public String getCoachEmail() { return coachEmail; }

    public void setCoachEmail(String coachEmail) { this.coachEmail = coachEmail; }

}
