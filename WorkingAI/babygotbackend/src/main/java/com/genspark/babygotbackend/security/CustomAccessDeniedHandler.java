package com.genspark.babygotbackend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException, ServletException {

        //similar to @AuthenticationPrincipal UserDetailsImpl that you can access anywhere
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {

            LOGGER.warn("User: " + auth.getName()
                    + " attempted to access the protected URL: "
                    + request.getRequestURI());

            //TODO: log security event
        }

        response.sendError(HttpStatus.FORBIDDEN.value(), "Access Denied!");
    }
}