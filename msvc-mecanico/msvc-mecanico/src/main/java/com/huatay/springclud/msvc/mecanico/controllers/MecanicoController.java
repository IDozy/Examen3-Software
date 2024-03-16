package com.huatay.springclud.msvc.mecanico.controllers;

import com.huatay.springclud.msvc.mecanico.models.Auto;
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


    //metodos remotos



    @PutMapping("/asignar-auto/{mecanicoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Auto auto, @PathVariable Long mecanicoId) {
        return manejarDatos(service.asignarAuto(auto, mecanicoId), "asignar");
    }

    @PostMapping("/crear-auto/{mecanicoId}")
    public ResponseEntity<?> crearUsuario(@RequestBody Auto auto, @PathVariable Long mecanicoId) {
        return manejarDatos(service.crearAuto(auto, mecanicoId), "crear");
    }

    @DeleteMapping("/desasignar/{mecanicoId}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Auto auto, @PathVariable Long mecanicoId) {
        return  manejarDatos(service.eliminarAuto(auto, mecanicoId), "eliminar");
    }


    private ResponseEntity<?> manejarDatos(Optional<Auto> optionalUsuario, String operation) {
        if (optionalUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUsuario.get());
        } else {
            return manejarException(operation);
        }
    }

    private ResponseEntity<?> manejarException(String operation) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Collections.singletonMap("Mensaje: ", String.format("No se pudo %s el auto o error en la comunicación", operation)));
    }




    @GetMapping("/mecAutos/{id}")
    public ResponseEntity<?> detalleCurUsers(@PathVariable Long id){
        Optional<Mecanico> op = service.porIdConAutos(id);
        if(op.isPresent()){
            return ResponseEntity.ok(op.get());
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/eliminar-mecAutos/{id}")
    public ResponseEntity<?> eliminarMecanicoAutoPorId(@PathVariable Long id){
        service.eliminarMecanicoAutoPorId(id);
        return ResponseEntity.noContent().build();
    }



}
