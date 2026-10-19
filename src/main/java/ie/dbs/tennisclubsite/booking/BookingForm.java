package ie.dbs.tennisclubsite.booking;

import jakarta.validation.constraints.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

public class BookingForm {

        @NotBlank(
                message = "Please choose whether this is a match or practice!"
        )
        private String bookingType;

        @NotNull(message = "Please choose a date!")
        @FutureOrPresent(message = "Date cannot be in the past!")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate date;

        @NotNull(message = "Please choose a time!")
        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        private LocalTime time;

        @NotNull(
                message = "A court must be selected!"
        )
        private Long courtId;

        @NotNull(
                message = "A member must be selected for the booking receipt!"
        )
        private Long memberId;

        public BookingForm() {}

        public BookingForm(String bookingType, LocalDate date, LocalTime time, Long courtId, Long memberId) {
            this.bookingType = bookingType;
            this.date = date;
            this.time = time;
            this.courtId = courtId;
            this.memberId = memberId;
        }

        public String getBookingType() { return bookingType; }

        public void setBookingType(String bookingType) { this.bookingType = bookingType; }

        public LocalDate getDate() { return date; }

        public void setDate(LocalDate date) { this.date = date; }

        public LocalTime getTime() { return time; }

        public void setTime(LocalTime time) { this.time = time; }

        public Long getCourtId() { return courtId; }

        public void setCourtId(Long courtId) { this.courtId = courtId; }

        public Long getMemberId() { return memberId; }

        public void setMemberId(Long memberId) { this.memberId = memberId; }
}
