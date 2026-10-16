package com.wegrow.service.impl;

import com.wegrow.dto.departement.DepartementRequestDTO;
import com.wegrow.dto.departement.DepartementResponseDTO;
import com.wegrow.entity.Departement;
import com.wegrow.exception.ConflictException;
import com.wegrow.exception.ResourceNotFoundException;
import com.wegrow.repository.DepartementRepository;
import com.wegrow.repository.EmployeRepository;
import com.wegrow.service.DepartementService;

import java.util.List;
import java.util.stream.Collectors;

public class DepartementServiceImpl implements DepartementService {

    private final DepartementRepository departementRepository;
    private final EmployeRepository employeRepository;

    public DepartementServiceImpl(DepartementRepository departementRepository, EmployeRepository employeRepository) {
        this.departementRepository = departementRepository;
        this.employeRepository = employeRepository;
    }

    @Override
    public DepartementResponseDTO creerDepartement(DepartementRequestDTO dto) {
        if (departementRepository.existsByNom(dto.getNom())) {
            throw new ConflictException("Un département avec le nom '" + dto.getNom() + "' existe déjà.");
        }

        Departement departement = new Departement(dto.getNom(), dto.getDescription());
        Departement saved = departementRepository.save(departement);

        return new DepartementResponseDTO(saved.getId(), saved.getNom(), saved.getDescription(), 0);
    }

    @Override
    public List<DepartementResponseDTO> listerDepartements() {
        return departementRepository.findAll().stream().map(dept -> {
            long count = employeRepository.countByDepartementId(dept.getId());
            return new DepartementResponseDTO(dept.getId(), dept.getNom(), dept.getDescription(), count);
        }).collect(Collectors.toList());
    }

    @Override
    public void supprimerDepartement(Long id) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Département introuvable avec l'ID : " + id));

        long count = employeRepository.countByDepartementId(id);
        if (count > 0) {
            throw new ConflictException("Impossible de supprimer le département : il contient encore " + count + " employé(s).");
        }

        departementRepository.delete(departement);
    }
}