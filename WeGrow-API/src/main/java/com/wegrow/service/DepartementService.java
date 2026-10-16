package com.wegrow.service;

import com.wegrow.dto.departement.DepartementRequestDTO;
import com.wegrow.dto.departement.DepartementResponseDTO;

import java.util.List;

public interface DepartementService {
    DepartementResponseDTO creerDepartement(DepartementRequestDTO dto);
    List<DepartementResponseDTO> listerDepartements();
    void supprimerDepartement(Long id);
}
