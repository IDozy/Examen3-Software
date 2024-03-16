package com.huatay.springclud.msvc.mecanico.models.entity;

import com.huatay.springclud.msvc.mecanico.models.Auto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;


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


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="auto_id")
    private List<Mantenimiento> mantenimientoList;

    @Transient
    private List<Auto> autoList;


    public Mecanico() {
        mantenimientoList = new ArrayList<>();
        autoList = new ArrayList<>();
    }



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


    public void addMantenimiento(Mantenimiento mantenimiento){
        mantenimientoList.add(mantenimiento);
    }
    public void removeMantenimiento(Mantenimiento mantenimiento){
        mantenimientoList.remove(mantenimiento);
    }

    public List<Mantenimiento> getMantenimiento() {
        return mantenimientoList;
    }
    public void setMantenimiento(List<Mantenimiento> mantenimiento) {
        this.mantenimientoList = mantenimiento;
    }


    public List<Mantenimiento> getMantenimientoList() {
        return mantenimientoList;
    }

    public void setMantenimientoList(List<Mantenimiento> mantenimientoList) {
        this.mantenimientoList = mantenimientoList;
    }

    public List<Auto> getAutoList() {
        return autoList;
    }

    public void setAutoList(List<Auto> autoList) {
        this.autoList = autoList;
    }
}
