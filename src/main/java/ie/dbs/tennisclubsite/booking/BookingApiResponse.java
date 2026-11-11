package ie.dbs.tennisclubsite.booking;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

//*************** Stores the booking info returned by the API ***************//

public class BookingApiResponse {

    private String bookingType;
    private LocalDate date;
    private LocalTime time;
    private int courtNum;
    private String memberName;

    public BookingApiResponse() {}

    public BookingApiResponse(String bookingType, LocalDate date, LocalTime time, int courtNum, String memberName) {
        this.bookingType = bookingType;
        this.date = date;
        this.time = time;
        this.courtNum = courtNum;
        this.memberName = memberName;
    }

    public String getBookingType() { return bookingType; }

    public void setBookingType(String bookingType) { this.bookingType = bookingType; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }

    public void setTime(LocalTime time) { this.time = time; }

    public int getCourtNum() { return courtNum; }

    public void setCourtNum(int courtNum) { this.courtNum = courtNum; }

    public String getMemberName() { return memberName; }

    public void setMemberName(String memberName) { this.memberName = memberName; }
}
