package com.wegrow.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        String path = request.getPathInfo();
        try {
            if (method.equals("GET")) {
                response.setStatus(response.SC_OK);
                response.getWriter().write("test !!");
            }
            if (method.equals("POST")) {
                response.setStatus(response.SC_OK);
                response.getWriter().write("test !!");
            }
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }

        return null;

    }
}
