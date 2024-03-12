package com.huatay.springclud.msvc.auto.services;

import com.huatay.springclud.msvc.auto.models.entity.Auto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AutoService {
    List<Auto> lista();
    Optional<Auto> porId(Long id);
    Auto guardar(Auto auto);
    void eliminar(Long id);
    Optional<Auto> porPlaca(String placa);
}
