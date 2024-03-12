package com.huatay.springclud.msvc.auto.models.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "autos")

public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuto;

    @NotBlank
    @Column(unique = true)
    private String Placa;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy")
    private String aniofab;

    public Long getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(Long idAuto) {
        this.idAuto = idAuto;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getAniofab() {
        return aniofab;
    }

    public void setAniofab(String aniofab) {
        this.aniofab = aniofab;
    }
}
