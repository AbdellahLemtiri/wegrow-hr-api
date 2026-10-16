package com.wegrow.dto.departement;

public class DepartementResponseDTO {

    private Long id;
    private String nom;
    private String description;
    private long nombreEmployes;

    public DepartementResponseDTO() {
    }

    public DepartementResponseDTO(Long id, String nom, String description, long nombreEmployes) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.nombreEmployes = nombreEmployes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getNombreEmployes() {
        return nombreEmployes;
    }

    public void setNombreEmployes(long nombreEmployes) {
        this.nombreEmployes = nombreEmployes;
    }
}