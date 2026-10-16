package com.wegrow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wegrow.dto.employe.EmployeRequestDTO;
import com.wegrow.dto.employe.EmployeResponseDTO;
import com.wegrow.service.EmployeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.List;

public class EmployeController implements Controller {

    private final EmployeService employeService;
    private final ObjectMapper objectMapper;

    public EmployeController(EmployeService employeService, ObjectMapper objectMapper) {
        this.employeService = employeService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        String path = request.getPathInfo(); 
        if (path == null) path = "";

    
        String[] parts = path.split("/");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
             if (parts.length >= 4 && parts[3].equalsIgnoreCase("employes")) {
                Long departementId = Long.parseLong(parts[2]);

                 if ("GET".equalsIgnoreCase(method)) {
                    List<EmployeResponseDTO> list = employeService.listerEmployesParDepartement(departementId);
                    response.setStatus(HttpServletResponse.SC_OK);
                    objectMapper.writeValue(response.getWriter(), list);
                    return null;
                }
                 if ("POST".equalsIgnoreCase(method)) {
                    EmployeRequestDTO dto = objectMapper.readValue(request.getInputStream(), EmployeRequestDTO.class);
                    EmployeResponseDTO res = employeService.ajouterEmploye(departementId, dto);
                    response.setStatus(HttpServletResponse.SC_CREATED); 
                    objectMapper.writeValue(response.getWriter(), res);
                    return null;
                }
            }

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        } catch (Exception e) {
            if (e.getClass().getSimpleName().equals("ResourceNotFoundException")) {
                response.setStatus(404);
            } else if (e.getClass().getSimpleName().equals("ConflictException")) {
                response.setStatus(409);
            } else {
                response.setStatus(500);
            }
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }

        return null;
    }
}