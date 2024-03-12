package com.huatay.springclud.msvc.mecanico.repositories;

import com.huatay.springclud.msvc.mecanico.models.entity.Mecanico;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MecanicoRepository extends CrudRepository<Mecanico,Long> {

    Optional<Mecanico> findByNombres(String nombre);
}
