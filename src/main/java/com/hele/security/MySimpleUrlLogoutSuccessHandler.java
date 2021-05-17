package com.hele.security;

import com.hele.controller.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySimpleUrlLogoutSuccessHandler
        implements LogoutSuccessHandler {

    @Autowired
    private IndexController indexController;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication)
            throws IOException, ServletException {

        indexController.setLoggedIn(false);

        indexController.setLoggedUserId(null);

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
    }
}
