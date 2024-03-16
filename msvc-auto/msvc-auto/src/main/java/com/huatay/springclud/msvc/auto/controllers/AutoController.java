package com.huatay.springclud.msvc.auto.controllers;

import com.huatay.springclud.msvc.auto.models.entity.Auto;
import com.huatay.springclud.msvc.auto.services.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/auto")
@RestController
public class AutoController {

    @Autowired
    private AutoService service;

    @GetMapping
    public List<Auto> listar() {
        return service.lista();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Auto> autoOptional = service.porId(id);
        if (autoOptional.isPresent()) {
            return ResponseEntity.ok(autoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear( @RequestBody Auto auto, BindingResult result) {
        if(service.porPlaca(auto.getPlaca()).isPresent()){
            return  ResponseEntity.badRequest().body(Collections.singletonMap("HuatayCasas ","Ups!! ese correo ya esta siendo usado :c"));
        }
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(auto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(  @RequestBody Auto auto, BindingResult result, @PathVariable Long id ) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        Optional<Auto> op = service.porId(id);
        if (op.isPresent()) {
            Auto autoDB = op.get();
            if(!auto.getPlaca().equalsIgnoreCase(autoDB.getPlaca()) && service.porPlaca(auto.getPlaca()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("HuatayCasas", "Ya Existe el email en otro ususario"));
            }
            autoDB.setPlaca(auto.getPlaca());
            autoDB.setAniofab(auto.getAniofab());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(autoDB));
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
        Optional<Auto> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/autos-por-mecanico")
    public ResponseEntity<?> obtenerAutosPorMecanico(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.listarPorIds(ids));
    }

}

