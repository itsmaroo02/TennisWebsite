package ie.dbs.tennisclubsite.coach;

import jakarta.validation.constraints.*;

// Holds user info when creating / editing a member

public class CoachForm {

    @NotBlank(message = "Please enter the coach's name!")
    private String coachName;

    @NotBlank(message = "Please enter the coach's email!")
    @Email(message = "Please enter a valid email address!")
    private String coachEmail;

    public CoachForm() {}

    public CoachForm(String coachName, String coachEmail) {
        this.coachName = coachName;
        this.coachEmail = coachEmail;
    }

    public String getCoachName() {return coachName;}

    public void setCoachName(String coachName) {this.coachName = coachName;}

    public String getCoachEmail() {return coachEmail;}

    public void setCoachEmail(String coachEmail) {this.coachEmail = coachEmail;}
}
