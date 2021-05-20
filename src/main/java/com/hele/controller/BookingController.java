package com.hele.controller;

import com.hele.dto.HotelDto;
import com.hele.dto.ReservationDto;
import com.hele.model.mappers.FrontBookingMapper;
import com.hele.model.frontObjects.BookingData;
import com.hele.security.MyUserPrincipal;
import com.hele.service.HotelService;
import com.hele.service.ReservationService;
import com.hele.utils.Pagination;
import com.hele.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.hele.model.util.Utils.*;

/**
 * Created by thelesteanu on 27.04.2021.
 */
@Controller
public class BookingController {

    private IndexController indexController;
    private ReservationService reservationService;
    private HotelService hotelService;

    @Autowired
    public BookingController(final IndexController indexController,
                             final ReservationService reservationService,
                             final HotelService hotelService) {
        this.indexController = indexController;
        this.reservationService = reservationService;
        this.hotelService = hotelService;
    }

    /**
     * Method used to redirect to booking management page.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/hotelManagement/bookingsManagement", method = RequestMethod.GET)
    public String hotelManagement(Model model,
                                  @ModelAttribute("hotelSearch") HotelDto hotel,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Long loggedUserId = indexController.getLoggedUserId();

        List<HotelDto> hotelData = hotelService.getHotelsByOwnerId(loggedUserId);

        Long hotelId = hotel.getId();

        List<Long> hotelIds = hotelId != null ? Collections.singletonList(hotel.getId()) : hotelData.stream()
                .distinct()
                .map(HotelDto::getId)
                .collect(Collectors.toCollection(ArrayList::new));

        Page<BookingData> bookings = reservationService
                .getReservationsByHotelIds(new Pagination(currentPage - 1, pageSize), hotelIds)
                .map(FrontBookingMapper::toBooking);

        model.addAttribute("bookings", bookings);
        model.addAttribute("hotels", hotelData);


        List<Integer> pageNumbers = IntStream.rangeClosed(1, bookings.getTotalPages() - 1)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);

        return "hotelManagement/bookingsManagement";
    }


    @RequestMapping(value = "/hotelManagement/bookingsManagement", method = RequestMethod.POST)
    public String HotelManagementHotelId(Model model,
                                         @ModelAttribute(value = "hotelSearch") HotelDto hotel,
                                         @RequestParam("page") Optional<Integer> page,
                                         @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Long loggedUserId = indexController.getLoggedUserId();

        List<HotelDto> hotelData = hotelService.getHotelsByOwnerId(loggedUserId);

        Long hotelId = hotel.getId();

        List<Long> hotelIds = hotelId != null ? Collections.singletonList(hotel.getId()) : hotelData.stream()
                .distinct()
                .map(HotelDto::getId)
                .collect(Collectors.toList());

        Page<BookingData> bookings = reservationService
                .getReservationsByHotelIds(new Pagination(currentPage - 1, pageSize), hotelIds)
                .map(FrontBookingMapper::toBooking);

        model.addAttribute("bookings", bookings);
        model.addAttribute("hotels", hotelData);


        List<Integer> pageNumbers = IntStream.rangeClosed(1, bookings.getTotalPages() - 1)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);

        return "hotelManagement/bookingsManagement";
    }


    @RequestMapping(value = "/hotelManagement/bookingsManagement/delete/{id}", method = RequestMethod.POST)
    public String deleteBooking(@PathVariable Long id, Model model) {
        //Delete booking by id
        return "redirect:/hotelManagement/bookingsManagement";
    }


    @RequestMapping(value = "/bookings/newBooking", method = RequestMethod.GET)
    public String newBooking(@ModelAttribute(value = "bookingData") BookingData bookingData, Model model) {

        List<HotelDto> hotelData = hotelService.getAllHotels();

        model.addAttribute("hotels", hotelData);


        model.addAttribute("rooms", generateRooms());

        return "bookings/newBooking";
    }

    @RequestMapping(value = "/bookings/newBooking", method = RequestMethod.POST)
    public String newBookingCreation(@ModelAttribute(value = "bookingData") BookingData bookingData, Model model) {

        model.addAttribute("booking", generateBooking());
        //Save the booking.
        return "bookings/bookingSuccess";
    }

    /**
     * Method used to load current user bookings and display them.
     *
     * @param bookingData
     * @param model
     * @return
     */
    @RequestMapping(value = "/bookings/myBookings", method = RequestMethod.GET)
    public String myBookings(@ModelAttribute(value = "bookingData") BookingData bookingData,
                             Model model,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("pageSize") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Long loggedUserId = indexController.getLoggedUserId();

        final Page<ReservationDto> reservationDtoList = reservationService.getReservationsByUserId(new Pagination(currentPage - 1, pageSize), loggedUserId);

        final List<BookingData> bookings = reservationDtoList.getContent()
                .stream()
                .map(FrontBookingMapper::toBooking)
                .collect(Collectors.toList());

        model.addAttribute("bookings", bookings);

        List<Integer> pageNumbers = IntStream.rangeClosed(1, reservationDtoList.getTotalPages() - 1)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);


        return "bookings/myBookings";
    }

    /**
     * Delete my booking method.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/bookings/myBookings/delete/{id}", method = RequestMethod.POST)
    public String deleteMyBooking(@PathVariable Long id, Model model) {


        return "redirect:/bookings/myBookings";
    }

    /**
     * Method used to show the booking that needs to be edited.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/bookings/myBookings/edit/{id}", method = RequestMethod.GET)
    public String editBooking(@PathVariable Long id, Model model) {

        final BookingData myBooking = FrontBookingMapper.toBooking(reservationService.getReservationById(id));
        model.addAttribute("bookingData", myBooking);

        model.addAttribute("hotels", generateMockHotels());
        model.addAttribute("rooms", generateRooms());


        return "bookings/editBooking";
    }

    /**
     * Method used to save booking.
     *
     * @param bookingData
     * @param model
     * @return
     */
    @RequestMapping(value = "/bookings/myBookings/save", method = RequestMethod.POST)
    public String saveBooking(@ModelAttribute(value = "bookingData") BookingData bookingData, Model model) {
        //save booking
        return "redirect:/bookings/myBookings";
    }

    /**
     * Method called automatically from thymeleaf when the loggedIn parameter is checked. We no longer need to add the
     * loggerIn object as a model attribute. Only a logged
     *
     * @return
     */
    @ModelAttribute("loggedIn")
    private Boolean isLoggedIn() {
        return true;
    }


    @ModelAttribute("manager")
    private Boolean isManager() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getPrincipal().equals("anonymousUser")) {
            MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
            return principal.getAuthorities().contains(Role.OWNER);
        } else return false;
    }


}
