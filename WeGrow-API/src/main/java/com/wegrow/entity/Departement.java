package com.wegrow.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departements", uniqueConstraints = {
    @UniqueConstraint(name = "uk_departement_nom", columnNames = "nom")
})

public class Departement
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    @Column(name = "description")
    private String description;
    


    

}