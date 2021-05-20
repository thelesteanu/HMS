package com.hele.controller;

import com.hele.dto.HotelDto;
import com.hele.dto.UserDto;
import com.hele.model.mappers.FrontRoomMapper;
import com.hele.model.filters.RoomFilter;
import com.hele.model.frontObjects.RoomData;
import com.hele.security.MyUserPrincipal;
import com.hele.service.HotelService;
import com.hele.service.RoomService;
import com.hele.utils.Pagination;
import com.hele.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.hele.model.util.Utils.generateMockHotels;


/**
 * Created by thelesteanu on 27.04.2021.
 */
@Controller
public class HotelController {

    private HotelService hotelService;
    private RoomService roomService;

    @Autowired
    public HotelController(final HotelService hotelService,
                           final RoomService roomService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    /**
     * Method used to redirect to hotel management page and retrieve a hotel list.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/hotelManagement", method = RequestMethod.GET)
    public String hotelManagement(Model model,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("pageSize") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        final Page<HotelDto> hotelsData = hotelService.getPageableHotel(new Pagination(currentPage - 1, pageSize));

        model.addAttribute("hotels", hotelsData);

        List<Integer> pageNumbers = IntStream.rangeClosed(1, hotelsData.getTotalPages() - 1)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);

        return "hotelManagement/hotelManagement";
    }


    /**
     * Method used view the hotel information
     *
     * @param model
     * @param id    : the id of the hotel that should be viewed
     * @return
     */
    @RequestMapping(value = "/hotelManagement/hotelInfo/{id}", method = RequestMethod.GET)
    public String hotelInformation(@PathVariable Long id, Model model) {
        HotelDto hotelData = hotelService.getHotelById(id);

        model.addAttribute("hotelData", hotelData);

        return "hotelManagement/hotelInformation";
    }


    /**
     * Method used to update the hotel information.
     *
     * @param hotelData
     * @param model
     * @return
     */
    @RequestMapping(value = "/hotelManagement/hotelInfo", method = RequestMethod.POST)
    public String saveHotel(@ModelAttribute(value = "userData") HotelDto hotelData, Model model) {
        hotelService.createOrUpdate(hotelData);

        return "redirect:/hotelManagement";
    }


    /**
     * Method used to view the room management screen.
     *
     * @param roomFilter
     * @param model
     * @return
     */
    @RequestMapping(value = "/hotelManagement/roomManagement", method = RequestMethod.GET)
    public String roomManagement(@ModelAttribute(value = "roomFilter") RoomFilter roomFilter,
                                 Model model,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("pageSize") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        final Page<RoomData> roomsData = roomService
                .getPageableRoom(new Pagination(currentPage - 1, pageSize), roomFilter)
                .map(FrontRoomMapper::toRoomData);

        model.addAttribute("rooms", roomsData);

        List<Integer> pageNumbers = IntStream.rangeClosed(1, roomsData.getTotalPages() - 1)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);

        return "hotelManagement/roomManagement";
    }

    /**
     * Method used to redirect to add account.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/hotelManagement/addHotel", method = RequestMethod.GET)
    public String addHotel(@ModelAttribute(value = "userData") UserDto userData, Model model) {

        HotelDto hotelData = new HotelDto();

        model.addAttribute("hotelData", hotelData);

        return "hotelManagement/hotelInformation";
    }

    /**
     * Method used to filter rooms
     *
     * @param roomFilter
     * @param model
     * @return
     */
    @RequestMapping(value = "/hotelManagement/roomManagement", method = RequestMethod.POST)
    public String filterRooms(@ModelAttribute(value = "roomFilter") RoomFilter roomFilter,
                              Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("pageSzie") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        final Page<RoomData> roomsData = roomService
                .getPageableRoom(new Pagination(currentPage - 1, pageSize), roomFilter)
                .map(FrontRoomMapper::toRoomData);

        model.addAttribute("rooms", roomsData);

        List<Integer> pageNumbers = IntStream.rangeClosed(1, roomsData.getTotalPages() - 1)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);

        return "hotelManagement/roomManagement";
    }


    /**
     * Method used to redirect to add room screen.
     *
     * @param roomData
     * @param model
     * @return
     */
    @RequestMapping(value = "/hotelManagement/addRoom", method = RequestMethod.GET)
    public String addRoomScreen(@ModelAttribute(value = "roomData") RoomData roomData, Model model) {
        final List<HotelDto> hotels = generateMockHotels(); //This should be the hotel list
        model.addAttribute("hotels", hotels);
        return "hotelManagement/addRoom";
    }

    /**
     * Method used to add a new room.
     *
     * @param roomData
     * @param model
     * @return
     */
    @RequestMapping(value = "/hotelManagement/addRoom", method = RequestMethod.POST)
    public String addRoom(@ModelAttribute(value = "roomData") RoomData roomData, Model model) {

        //TODO Add new room using room data.
        return "redirect:/hotelManagement/roomManagement";
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
