package com.huatay.springclud.msvc.mecanico.services;

import com.huatay.springclud.msvc.mecanico.models.entity.Mecanico;

import java.util.List;
import java.util.Optional;

public interface MecanicoService {

    List<Mecanico> lista();
    Optional<Mecanico> porId(Long id);
    Mecanico guardar(Mecanico mecanico);
    void eliminar(Long id);

    Optional<Mecanico> porNombre(String nombre);

}
