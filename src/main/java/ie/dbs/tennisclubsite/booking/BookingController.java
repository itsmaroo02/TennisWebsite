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

@Controller
@RequestMapping("/bookings")
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

    @GetMapping
    public String listBookings(Model model) {

        model.addAttribute(
                "bookings",
                bookingService.getBookings()
        );

        return "booking/list";
    }

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

        return "booking/form";
    }

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

        return "booking/edit";
    }

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

    @PostMapping("/{id}/delete")
    public String deleteBooking(
            @PathVariable Long id) {

        bookingService.deleteBooking(id);

        return "redirect:/bookings";
    }
}
