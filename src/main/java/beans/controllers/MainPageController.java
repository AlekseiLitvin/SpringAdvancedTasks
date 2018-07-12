package beans.controllers;

import beans.daos.BookingDAO;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.populator.BookingServicePopulator;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Controller
public class MainPageController {


    private static final String DELIMITER = ",";
    private static final int EVENT_NAME_INDEX = 0;
    private static final int EVENT_DATE_INDEX = 1;
    private static final int SEATS_INDEX = 2;
    private static final String SEATS_DELIMITER = ";";
    private static final int PRICE_INDEX = 3;

    @Autowired
    @Qualifier("bookingServiceImpl")
    private BookingService bookingService;

    @Autowired
    @Qualifier("bookingServicePopulator")
    private BookingServicePopulator bookingServicePopulator;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Qualifier("bookingDAO")
    @Autowired
    private BookingDAO bookingDAO;

    @Qualifier("eventServiceImpl")
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        bookingServicePopulator.populate();
        return "mainPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        bookingServicePopulator.populate();
        return "loginPage";
    }

    @RequestMapping(value = "/bm/ticketsForEvent", method = RequestMethod.GET)
    public String purchasedTicketsForEvent(HttpServletRequest request, Model model) {
        String eventName = request.getParameter("eventName");
        String eventDate = request.getParameter("eventDate");
        String auditoriumName = request.getParameter("auditoriumName");

        List<Ticket> purchasedTicketsForEvent = bookingService.getTicketsForEvent(eventName, auditoriumName, LocalDateTime.parse(eventDate));
        model.addAttribute("purchasedTicketsForEvent", purchasedTicketsForEvent);

        return "resultPage";
    }

    @RequestMapping(value = "/auth/bookTickets", method = RequestMethod.POST)
    public String bookTickets(@RequestParam("ticketsCsv") MultipartFile ticketsCsv,
                              @RequestParam("userId") long userId,
                              Model model) throws IOException {
        Scanner scanner = new Scanner(new ByteArrayInputStream(ticketsCsv.getBytes()));
        User user = userService.getById(userId);
        while (scanner.hasNextLine()) {
            String[] ticketBooking = scanner.nextLine().split(DELIMITER);
            Event event = eventService.getByName(ticketBooking[EVENT_NAME_INDEX]).get(0);
            LocalDateTime eventDate = LocalDateTime.parse(ticketBooking[EVENT_DATE_INDEX]);
            List<Integer> seats = Arrays.stream(ticketBooking[SEATS_INDEX].split(SEATS_DELIMITER))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int eventPrice = Integer.parseInt(ticketBooking[PRICE_INDEX]);
            Ticket ticket = new Ticket(event, eventDate, seats, user, eventPrice);
            bookingService.bookTicket(user, ticket);
        }

        model.addAttribute("bookingMessage", "Booking successful");
        return "mainPage";
    }

    @RequestMapping(value = "/bm/ticketspdf", headers = "Accept=application/pdf",  produces = "application/pdf", method = RequestMethod.GET)
    public String ticketsPdf(HttpServletRequest request, HttpServletResponse response, Model model) {
        String eventName = request.getParameter("eventName");
        String eventDate = request.getParameter("eventDate");
        String auditoriumName = request.getParameter("auditoriumName");

        List<Ticket> purchasedTicketsForEvent = bookingService.getTicketsForEvent(eventName, auditoriumName, LocalDateTime.parse(eventDate));
        model.addAttribute("purchasedTicketsForEvent", purchasedTicketsForEvent);

        return "bookedTicketsPdfView";
    }

    @RequestMapping(value = "/accessDenied")
    public String accessDeniedPage() {
        return "accessDenied";
    }

}
