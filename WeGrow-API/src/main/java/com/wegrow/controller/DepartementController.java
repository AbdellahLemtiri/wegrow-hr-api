package com.wegrow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wegrow.dto.departement.DepartementRequestDTO;
import com.wegrow.dto.departement.DepartementResponseDTO;
import com.wegrow.service.DepartementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.List;

public class DepartementController implements Controller {

    private final DepartementService departementService;
    private final ObjectMapper objectMapper;

    public DepartementController(DepartementService departementService, ObjectMapper objectMapper) {
        this.departementService = departementService;
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
            
            if ("GET".equalsIgnoreCase(method) && parts.length <= 2) {
                List<DepartementResponseDTO> list = departementService.listerDepartements();
                response.setStatus(HttpServletResponse.SC_OK);
                objectMapper.writeValue(response.getWriter(), list);
                return null;    
            }

            if ("POST".equalsIgnoreCase(method) && parts.length <= 2) {
                DepartementRequestDTO dto = objectMapper.readValue(request.getInputStream(), DepartementRequestDTO.class);
                DepartementResponseDTO res = departementService.creerDepartement(dto);
                response.setStatus(HttpServletResponse.SC_CREATED); 
                objectMapper.writeValue(response.getWriter(), res);
                return null;
            }

            if ("DELETE".equalsIgnoreCase(method) && parts.length == 3) {
                Long id = Long.parseLong(parts[2]);
                departementService.supprimerDepartement(id);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return null;
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