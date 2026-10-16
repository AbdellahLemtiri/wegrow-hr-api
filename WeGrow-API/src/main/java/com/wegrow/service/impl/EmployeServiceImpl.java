package com.wegrow.service.impl;

import com.wegrow.dto.employe.EmployeRequestDTO;
import com.wegrow.dto.employe.EmployeResponseDTO;
import com.wegrow.entity.Departement;
import com.wegrow.entity.Employe;
import com.wegrow.exception.ConflictException;
import com.wegrow.exception.ResourceNotFoundException;
import com.wegrow.repository.DepartementRepository;
import com.wegrow.repository.EmployeRepository;
import com.wegrow.service.EmployeService;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;
    private final DepartementRepository departementRepository;

     public EmployeServiceImpl(EmployeRepository employeRepository, DepartementRepository departementRepository) {
        this.employeRepository = employeRepository;
        this.departementRepository = departementRepository;
    }

    @Override
    public EmployeResponseDTO ajouterEmploye(Long departementId, EmployeRequestDTO dto) {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new ResourceNotFoundException("Département introuvable avec l'ID : " + departementId));

        if (employeRepository.existsByMatricule(dto.getMatricule())) {
            throw new ConflictException("Un employé avec le matricule '" + dto.getMatricule() + "' existe déjà.");
        }

        Employe employe = new Employe(
                dto.getMatricule(),
                dto.getNom(),
                dto.getPrenom(),
                dto.getPoste(),
                dto.getContrat(),
                departement
        );

        Employe saved = employeRepository.save(employe);

        return new EmployeResponseDTO(
                saved.getId(),
                saved.getMatricule(),
                saved.getNom(),
                saved.getPrenom(),
                saved.getPoste(),
                saved.getContrat()
        );
    }

    @Override
    public List<EmployeResponseDTO> listerEmployesParDepartement(Long departementId) {
        if (!departementRepository.existsById(departementId)) {
            throw new ResourceNotFoundException("Département introuvable avec l'ID : " + departementId);
        }
        return employeRepository.findByDepartementId(departementId).stream().map(emp ->
                new EmployeResponseDTO(
                        emp.getId(),
                        emp.getMatricule(),
                        emp.getNom(),
                        emp.getPrenom(),
                        emp.getPoste(),
                        emp.getContrat()
                )
        ).collect(Collectors.toList());
    }
}