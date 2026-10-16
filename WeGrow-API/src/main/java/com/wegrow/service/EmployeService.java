package com.wegrow.service;

import com.wegrow.dto.employe.EmployeRequestDTO;
import com.wegrow.dto.employe.EmployeResponseDTO;
import com.wegrow.service.EmployeService;
import java.util.List;

public interface EmployeService {

     EmployeResponseDTO ajouterEmploye(Long departementId, EmployeRequestDTO dto);
     List<EmployeResponseDTO> listerEmployesParDepartement(Long departementId);
}