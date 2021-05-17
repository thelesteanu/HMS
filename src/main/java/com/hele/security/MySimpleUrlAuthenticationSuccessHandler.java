package com.hele.security;


import com.hele.controller.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySimpleUrlAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    @Autowired
    private IndexController indexController;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {
        indexController.setLoggedIn(true);

        MyUserPrincipal userDetails = (MyUserPrincipal) authentication.getPrincipal();

        indexController.setLoggedUserId(userDetails.getUserId());

        redirectStrategy.sendRedirect(request, response, "/");
    }
}
