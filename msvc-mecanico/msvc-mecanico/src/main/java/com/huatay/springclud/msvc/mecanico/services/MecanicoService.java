package com.huatay.springclud.msvc.mecanico.services;

import com.huatay.springclud.msvc.mecanico.models.Auto;
import com.huatay.springclud.msvc.mecanico.models.entity.Mecanico;

import java.util.List;
import java.util.Optional;

public interface MecanicoService {

    List<Mecanico> lista();
    Optional<Mecanico> porId(Long id);
    Mecanico guardar(Mecanico mecanico);
    void eliminar(Long id);
    Optional<Mecanico> porNombre(String nombre);



    Optional<Auto> asignarAuto(Auto auto, Long MecanicoId);
    Optional<Auto> crearAuto(Auto auto, Long MecanicoId);
    Optional<Auto> eliminarAuto(Auto auto, Long MecanicoId);

    Optional<Mecanico> porIdConAutos(Long id);

    void eliminarMecanicoAutoPorId(Long id);



}
