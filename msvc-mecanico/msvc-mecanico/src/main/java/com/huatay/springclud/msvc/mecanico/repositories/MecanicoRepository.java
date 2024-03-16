package com.huatay.springclud.msvc.mecanico.repositories;

import com.huatay.springclud.msvc.mecanico.models.entity.Mecanico;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MecanicoRepository extends CrudRepository<Mecanico,Long> {

    Optional<Mecanico> findByNombres(String nombre);

    @Modifying
    @Query("delete from Mantenimiento cu where cu.idAuto=?1")
    void eliminarMecanicoAutoPorId(Long id);

}
