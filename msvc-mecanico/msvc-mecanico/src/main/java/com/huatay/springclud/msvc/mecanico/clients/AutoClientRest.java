package com.huatay.springclud.msvc.mecanico.clients;

import com.huatay.springclud.msvc.mecanico.models.Auto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-auto", url="localhost:8002/api/auto")
public interface AutoClientRest {
    @GetMapping("/{id}")
    Auto detalle(@PathVariable Long id);
    @PostMapping()
    Auto crear(@RequestBody Auto auto);


    @GetMapping("/autos-por-mecanico")
    List<Auto> obtenerAutosPorMecanico(@RequestParam Iterable<Long> ids);
}