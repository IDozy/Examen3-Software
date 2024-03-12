package com.huatay.springclud.msvc.auto.repositories;

import com.huatay.springclud.msvc.auto.models.entity.Auto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AutoRepository extends CrudRepository<Auto,Long> {
    Optional<Auto> findByPlaca(String placa);
}
