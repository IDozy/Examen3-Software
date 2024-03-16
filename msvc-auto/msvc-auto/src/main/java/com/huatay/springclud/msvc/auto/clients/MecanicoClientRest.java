package com.huatay.springclud.msvc.auto.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-mecanico", url="localhost:8001/api/mecanico")
public interface MecanicoClientRest {
    @DeleteMapping("/eliminar-mecAutos/{id}")
    void eliminarMecanicoAutoPorId(@PathVariable Long id);
}

