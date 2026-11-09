package ie.dbs.tennisclubsite.booking;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingApiController {

    private final BookingService bookingService;

    public BookingApiController(
            BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingApiResponse>>
    getBookings() {

        List<BookingApiResponse> bookings =
                bookingService.getBookingApiList();

        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingApiResponse>
    getBookingById(@PathVariable Long id) {

        BookingApiResponse booking =
                bookingService.getBookingApiById(id);

        if (booking == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(booking);
    }

    @PostMapping
    public ResponseEntity<BookingApiResponse>
    createBooking(
            @Valid @RequestBody BookingApiRequest request) {

        BookingApiResponse createdBooking =
                bookingService.createBookingApi(request);

        if (createdBooking == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingApiResponse>
    updateBooking(
            @PathVariable Long id,
            @Valid @RequestBody BookingApiRequest request) {

        if (bookingService.getBookingById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        BookingApiResponse updatedBooking =
                bookingService.updateBookingApi(id, request);

        if (updatedBooking == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteBooking(@PathVariable Long id) {

        boolean deleted =
                bookingService.deleteBooking(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}



