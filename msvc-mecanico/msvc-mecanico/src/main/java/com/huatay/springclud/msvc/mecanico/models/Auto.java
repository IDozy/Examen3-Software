package com.huatay.springclud.msvc.mecanico.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class Auto {


    private Long idAuto;
    private String placa;
    private String aniofab;

    public Long getIdAuto() {return idAuto;}

    public void setIdAuto(Long idAuto) {this.idAuto = idAuto;}

    public String getPlaca() {return placa;}

    public void setPlaca(String placa) {placa = placa;}

    public String getAniofab() {return aniofab;}

    public void setAniofab(String aniofab) {this.aniofab = aniofab;}
}
