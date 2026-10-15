package com.wegrow.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wegrow.entity.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    boolean existsByMatricule(String matricule);

    List<Employe> findByDepartementId(Long departementId);

    long countByDepartementId(Long departementId);
}
