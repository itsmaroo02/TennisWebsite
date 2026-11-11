package ie.dbs.tennisclubsite.booking;

import ie.dbs.tennisclubsite.court.CourtRepository;
import ie.dbs.tennisclubsite.member.Member;
import ie.dbs.tennisclubsite.member.MemberRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//*** Handles requests for viewing, creating, editing, and deleting bookings ***//

// HTTP METHODS
// GET --> Get or view information (show all bookings)
// POST --> Send information (create a booking)
// PUT --> Update existing information (update a booking)
// DELETE --> Delete information (delete a booking)

// Tells spring this class handles website requests
@Controller
@RequestMapping("/bookings") // base URL
public class BookingController {

    private final BookingService bookingService;
    private final CourtRepository courtRepository;
    private final MemberRepository memberRepository;

    public BookingController(
            BookingService bookingService,
            CourtRepository courtRepository,
            MemberRepository memberRepository) {

        this.bookingService = bookingService;
        this.courtRepository = courtRepository;
        this.memberRepository = memberRepository;
    }

    //*** Handles GET requests to /bookings ***//
    @GetMapping
    public String listBookings(Model model) {

        model.addAttribute(
                "bookings",
                bookingService.getBookings()
        );

        return "booking/list"; // returns booking/list.html
    }

    //*** Handles GET requests to /bookings/new ***//
    @GetMapping("/new")
    public String showBookingForm(Model model) {

        model.addAttribute(
                "bookingForm",
                new BookingForm()
        );

        model.addAttribute(
                "members",
                memberRepository.findAll()
        );

        model.addAttribute(
                "courts",
                courtRepository.findAll()
        );

        return "booking/form"; // returns booking/form.html
    }

    //*** Handles POST requests to /bookings ***//
    @PostMapping
    public String createBooking(
            @Valid
            @ModelAttribute("bookingForm")
            BookingForm bookingForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "courts",
                    courtRepository.findAll()
            );

            model.addAttribute(
                    "members",
                    memberRepository.findAll()
            );

            return "booking/form";
        }

        Booking savedBooking =
                bookingService.createBooking(bookingForm);

        if (savedBooking == null) {

            bindingResult.rejectValue(
                    "courtId",
                    "court.invalid",
                    "The selected court does not exist"
            );

            model.addAttribute(
                    "courts",
                    courtRepository.findAll()
            );

            bindingResult.rejectValue(
                    "memberId",
                    "member.invalid",
                    "The selected member does not exist"
            );

            model.addAttribute(
                    "members",
                    memberRepository.findAll()
            );

            return "booking/form";
        }

        return "redirect:/bookings";
    }

    //*** Handles requests to /bookings/{id}/edit ***//
    @GetMapping("/{id}/edit")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Booking booking =
                bookingService.getBookingById(id);

        if (booking == null) {
            return "redirect:/bookings";
        }

        BookingForm bookingForm =
                new BookingForm(
                        booking.getBookingType(),
                        booking.getDate(),
                        booking.getTime(),
                        booking.getCourtID() != null
                                ? booking.getCourtID().getCourtID()
                                : null,
                        booking.getMemberID() != null
                                ? booking.getMemberID().getMemberID()
                                : null
                );

        //  ? : --> checks a condition and chooses one of two values

        model.addAttribute(
                "bookingForm",
                bookingForm
        );

        model.addAttribute("bookingId", id);

        model.addAttribute(
                "courts",
                courtRepository.findAll()
        );

        model.addAttribute(
                "members",
                memberRepository.findAll()
        );

        return "booking/edit"; // opens booking/edit.html
    }

    //*** Handles POST requests to update a specific booking ***//
    @PostMapping("/{id}")
    public String updateBooking(
            @PathVariable Long id,
            @Valid
            @ModelAttribute("bookingForm")
            BookingForm bookingForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("bookingId", id);

            model.addAttribute(
                    "courts",
                    courtRepository.findAll()
            );

            model.addAttribute(
                    "members",
                    memberRepository.findAll()
            );

            return "booking/edit";
        }

        Booking updatedBooking =
                bookingService.updateBooking(
                        id,
                        bookingForm
                );

        if (updatedBooking == null) {

            if (bookingService.getBookingById(id) == null) {
                return "redirect:/bookings";
            }

            bindingResult.rejectValue(
                    "courtId",
                    "court.invalid",
                    "The selected court does not exist!"
            );

            model.addAttribute("bookingId", id);

            model.addAttribute(
                    "courts",
                    courtRepository.findAll()
            );

            bindingResult.rejectValue(
                    "memberId",
                    "member.invalid",
                    "The selected member does not exist!"
            );

            model.addAttribute("bookingId", id);

            model.addAttribute(
                    "members",
                    memberRepository.findAll()
            );

            return "booking/edit";
        }

        return "redirect:/bookings";
    }

    //*** Handles POST requests to delete a specific booking ***//
    @PostMapping("/{id}/delete")
    public String deleteBooking(
            @PathVariable Long id) {

        bookingService.deleteBooking(id);

        return "redirect:/bookings";
    }
}
