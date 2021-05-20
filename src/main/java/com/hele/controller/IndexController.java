package com.hele.controller;

import com.hele.dto.UserDto;
import com.hele.service.UserServiceImpl;
import com.hele.utils.Role;
import com.hele.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.ConstraintViolationException;

/**
 * Created by thelesteanu on 25.04.2021.
 */

@Controller
public class IndexController {

    private UserServiceImpl userService;

    @Autowired
    public IndexController(final UserServiceImpl userService) {
        this.userService = userService;
    }

    private Boolean isLoggedIn = false;
    private Long loggedUserId;

    /**
     * Method used to redirect to the index page.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    /**
     * Method used to show the register page.
     *
     * @param userDto
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(@ModelAttribute(value = "userData") UserDto userDto, Model model) {
        return "common/register";
    }

    /**
     * Method used to register the user.
     *
     * @param userDto
     * @param model
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute(value = "userData") UserDto userDto, Model model) {
        try {
            userService.registerUser(userDto);
            return "common/userRegistered";
        } catch (ConstraintViolationException ex) {
            String errorMessage = ex.getConstraintViolations().iterator().next().getPropertyPath() + " - " + ex.getConstraintViolations().iterator().next().getMessage();
            model.addAttribute("errorMessage", errorMessage);
        }

        return "common/register";
    }

    /**
     * Method used to logout the user
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(Model model) {
        this.isLoggedIn = false;

        //Add logic
        return "redirect:/";
    }


    /**
     * Method called automatically from thymeleaf when the loggedIn parameter is checked. We no longer need to add the
     * loggerIn object as a model attribute
     *
     * @return
     */
    @ModelAttribute("loggedIn")
    private Boolean isLoggedIn() {
        return isLoggedIn;
    }

    @ModelAttribute("manager")
    private Boolean isManager() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.getPrincipal().equals("anonymousUser")) {
            MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
            return principal.getAuthorities().contains(Role.OWNER);
        }
        else return false;
    }

    public void setLoggedUserId(Long loggedUserId) {
        this.loggedUserId = loggedUserId;
    }

    public Long getLoggedUserId() {
        return this.loggedUserId;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
