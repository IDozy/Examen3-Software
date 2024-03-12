package com.huatay.springclud.msvc.mecanico.controllers;

import com.huatay.springclud.msvc.mecanico.models.entity.Mecanico;
import com.huatay.springclud.msvc.mecanico.services.MecanicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RequestMapping("/api/mecanico")
@RestController
public class MecanicoController {

    @Autowired
    private MecanicoService service;

    @GetMapping
    public List<Mecanico> listar() {
        return service.lista();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Mecanico> mecanicoOptional = service.porId(id);
        if (mecanicoOptional.isPresent()) {
            return ResponseEntity.ok(mecanicoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear( @RequestBody Mecanico mecanico, BindingResult result) {
        if(service.porNombre(mecanico.getNombres()).isPresent()){
            return  ResponseEntity.badRequest().body(Collections.singletonMap("HuatayCasas ","Ups!! ese correo ya esta siendo usado :c"));
        }
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(mecanico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(  @RequestBody Mecanico mecanico, BindingResult result, @PathVariable Long id ) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        Optional<Mecanico> op = service.porId(id);
        if (op.isPresent()) {
            Mecanico mecanicoDB = op.get();
            if(!mecanico.getNombres().equalsIgnoreCase(mecanicoDB.getNombres()) && service.porNombre(mecanico.getNombres()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("HuatayCasas", "Ya Existe el email en otro ususario"));
            }
            mecanicoDB.setNombres(mecanico.getNombres());
            mecanicoDB.setExperiencia(mecanico.getExperiencia());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(mecanicoDB));
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    private ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Huatay Casas " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Mecanico> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

  /*  @GetMapping("/mecanico-por-curso")
    public ResponseEntity<List<Mecanico>>  listarAlumnosporCurso(@RequestParam List<Long> ids){
        return  ResponseEntity.ok(service.listarPorIds(ids));
    }*/
}
