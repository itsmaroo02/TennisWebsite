package ie.dbs.tennisclubsite.booking;

import ie.dbs.tennisclubsite.court.Court;
import ie.dbs.tennisclubsite.court.CourtRepository;
import ie.dbs.tennisclubsite.member.Member;
import ie.dbs.tennisclubsite.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CourtRepository courtRepository;
    private final MemberRepository memberRepository;

    public BookingService(BookingRepository bookingRepository, CourtRepository courseRepository, MemberRepository memberRepository) {
        this.bookingRepository = bookingRepository;
        this.courtRepository = courseRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Booking getBookingById(Long id) {
        return bookingRepository
                .findById(id)
                .orElse(null);
    }

    @Transactional
    public Booking createBooking(BookingForm form) {
        Court court = courtRepository
                .findById(form.getCourtId())
                .orElse(null);

        if (court == null) {
            return null;
        }

        Member member = memberRepository
                .findById(form.getMemberId())
                .orElse(null);

        if (member == null) {
            return null;
        }

        Booking booking = new Booking(
                form.getBookingType(), form.getDate(), form.getTime(), court, member
        );

        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking updateBooking(Long id, BookingForm form) {
        Booking booking = bookingRepository.findById(id).orElse(null);

        if (booking == null) {
            return null;
        }

        Court court = courtRepository.findById(form.getCourtId()).orElse(null);

        if (court == null) {
            return null;
        }

        Member member = memberRepository.findById(form.getMemberId()).orElse(null);

        if (member == null) {
            return null;
        }

        booking.setBookingType(form.getBookingType());
        booking.setDate(form.getDate());
        booking.setTime(form.getTime());
        booking.setCourtID(court);
        booking.setMemberID(member);

        return bookingRepository.save(booking);
    }

    @Transactional
    public boolean deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            return false;
        }

        bookingRepository.deleteById(id);

        return true;
    }

    public List<BookingApiResponse> getBookingApiList() {

        List<Booking> bookings =
                bookingRepository.findAll();

        List<BookingApiResponse> responses =
                new ArrayList<>();

        for (Booking booking : bookings) {
            responses.add(toApiResponse(booking));
        }

        return responses;
    }

    public BookingApiResponse getBookingApiById(Long id) {

        Booking booking =
                bookingRepository.findById(id).orElse(null);

        if (booking == null) {
            return null;
        }

        return toApiResponse(booking);
    }

    public BookingApiResponse createBookingApi(
            BookingApiRequest request) {

        Member member =
                memberRepository
                        .findById(request.getMemberId())
                        .orElse(null);

        if (member == null) {
            return null;
        }

        Court court =
                courtRepository
                        .findById(request.getCourtId())
                        .orElse(null);

        if (court == null) {
            return null;
        }

        Booking booking = new Booking(
                request.getBookingType(),
                request.getDate(),
                request.getTime(),
                court,
                member
        );

        Booking savedBooking =
                bookingRepository.save(booking);

        return toApiResponse(savedBooking);
    }

    public BookingApiResponse updateBookingApi(
            Long id,
            BookingApiRequest request) {

        Booking booking =
                bookingRepository.findById(id).orElse(null);

        if (booking == null) {
            return null;
        }

        Member member =
                memberRepository
                        .findById(request.getMemberId())
                        .orElse(null);

        if (member == null) {
            return null;
        }

        Court court =
                courtRepository
                        .findById(request.getCourtId())
                        .orElse(null);

        if (court == null) {
            return null;
        }

        booking.setBookingType(request.getBookingType());
        booking.setDate(request.getDate());
        booking.setTime(request.getTime());
        booking.setCourtID(court);
        booking.setMemberID(member);

        Booking savedBooking =
                bookingRepository.save(booking);

        return toApiResponse(savedBooking);
    }

    private BookingApiResponse toApiResponse(
            Booking booking) {

        return new BookingApiResponse(
                booking.getBookingType(),
                booking.getDate(),
                booking.getTime(),
                booking.getCourtID().getCourtNum(),
                booking.getMemberID().getMemberName()
        );
    }
}
