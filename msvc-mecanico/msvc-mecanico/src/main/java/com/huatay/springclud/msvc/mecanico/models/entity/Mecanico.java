package com.huatay.springclud.msvc.mecanico.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "mecanicos")
public class Mecanico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMecanico;

    @NotBlank
    @Column(unique = true)
    private String nombres;

    @NotBlank
    private String experiencia;

    public Long getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(Long idMecanico) {
        this.idMecanico = idMecanico;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}
