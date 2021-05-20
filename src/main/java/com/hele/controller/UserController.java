package com.hele.controller;

import com.hele.dto.HotelDto;
import com.hele.dto.UserDto;
import com.hele.security.MyUserPrincipal;
import com.hele.service.UserService;
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
 * Created by thelesteanu on 26.04.2021.
 */
@Controller
public class UserController {

    private final IndexController indexController;
    private final UserService userService;

    @Autowired
    public UserController(final IndexController indexController,
                          final UserService userService) {
        this.indexController = indexController;
        this.userService = userService;
    }

    /**
     * Method used to display logged user information.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/myAccount", method = RequestMethod.GET)
    public String myAccount(Model model) {
        Long loggedUserId = indexController.getLoggedUserId();

        UserDto loggedUserData = userService.getUserById(loggedUserId);

        model.addAttribute("userData", loggedUserData);
        return "common/myAccount";
    }

    /**
     * Method used to update the user information.
     *
     * @param userData
     * @param model
     * @return
     */
    @RequestMapping(value = "/myAccount", method = RequestMethod.POST)
    public String updateAccount(@ModelAttribute(value = "userData") UserDto userData, Model model) {
        Long loggedUserId = indexController.getLoggedUserId();

        UserDto loggedUserData = userService.getUserById(loggedUserId);

        userData.setId(loggedUserData.getId());
        userData.setRegistrationDate(loggedUserData.getRegistrationDate());
        userData.setPassword(loggedUserData.getPassword());
        userData.setGender(loggedUserData.getGender());
        userData.setBirthDate(loggedUserData.getBirthDate());
        userData.setRole(loggedUserData.getRole());

        userService.updateUser(userData);

        model.addAttribute("userData", userData);
        return "common/myAccount";
    }

    /**
     * Method used to redirect to user management page and retrieve a user list.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/userManagement", method = RequestMethod.GET)
    public String userManagement(Model model,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("pageSize") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        final Page<UserDto> usersData = userService
                .getPageableUser(new Pagination(currentPage - 1, pageSize));


        model.addAttribute("users", usersData);

        List<Integer> pageNumbers = IntStream.rangeClosed(1, usersData.getTotalPages() - 1)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);

        return "userManagement/userManagement";
    }

    /**
     * Method used to redirect to user management page and retrieve a user list.
     *
     * @param model
     * @param id    : the id of the user that should be deleted.
     * @return
     */
    @RequestMapping(value = "/userManagement/delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable Long id, Model model) {
        //Delete user by id
        return "redirect:/userManagement";
    }


    /**
     * Method used to redirect to add account.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/userManagement/addAccount", method = RequestMethod.GET)
    public String addAccount(@ModelAttribute(value = "userData") UserDto userData, Model model) {
        //Add account
        final List<HotelDto> hotels = generateMockHotels(); //This should be the hotel list of the owner/manager
        model.addAttribute("hotels", hotels);
        return "userManagement/addAccount";
    }

    /**
     * Method used to register a new account.
     *
     * @param userData
     * @param model
     * @return
     */
    @RequestMapping(value = "/userManagement/addAccount", method = RequestMethod.POST)
    public String registerAccount(@ModelAttribute(value = "userData") UserDto userData, Model model) {

        //Save this new account
        return "redirect:/userManagement";
    }

    @RequestMapping(value = "/userManagement/edit/{id}", method = RequestMethod.GET)
    public String editAccount(@PathVariable Long id, Model model) {
        UserDto userData = userService.getUserById(id);

        model.addAttribute("userData", userData);

        return "userManagement/editAccount";
    }

    @RequestMapping(value = "/userManagement/edit", method = RequestMethod.POST)
    public String editAccount(@ModelAttribute(value = "userData") UserDto userData, Model model) {

        userService.updateUsernameAndRole(userData);

        return "redirect:/userManagement";
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
