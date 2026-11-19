package ie.dbs.tennisclubsite.member;

import jakarta.validation.constraints.*;

// Holds user info when creating / editing a member

public class MemberForm {

    @NotBlank(message = "Please enter the member's name!")
    private String memberName;

    @NotBlank(message = "Please enter the member's email!")
    @Email(message = "Please enter a valid email address!")
    private String memberEmail;

    public MemberForm() {}

    public MemberForm(String memberName, String memberEmail) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }

    public String getMemberName() {return memberName;}

    public void setMemberName(String memberName) {this.memberName = memberName;}

    public String getMemberEmail() {return memberEmail;}

    public void setMemberEmail(String memberEmail) {this.memberEmail = memberEmail;}
}
