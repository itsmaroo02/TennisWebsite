package ie.dbs.tennisclubsite.booking;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//*************** Handles REST API requests for bookings ***************//

// Tells spring this class handles REST API requests
@RestController
@RequestMapping("/api/bookings") // base URL
public class BookingApiController {

    private final BookingService bookingService;

    public BookingApiController(
            BookingService bookingService) {

        this.bookingService = bookingService;
    }

    // handles GET requests -- returns a list of bookings
    @GetMapping
    public ResponseEntity<List<BookingApiResponse>>
    getBookings() {

        List<BookingApiResponse> bookings =
                bookingService.getBookingApiList();

        return ResponseEntity.ok(bookings);
    }

    // handles the requests for a specific booking
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

    // handles POST requests to create a booking
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

    // Handles PUT requests to update a booking
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

    // Handles DELETE requests for a booking
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



