package com.huatay.springclud.msvc.auto.services;

import com.huatay.springclud.msvc.auto.models.entity.Auto;
import com.huatay.springclud.msvc.auto.repositories.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutoServiceImpl implements AutoService{

    @Autowired // inyecta la dependencia de una clase que tiene metodos a otra clase
    private AutoRepository repository;

    @Override
    @Transactional
    public List<Auto> lista() {
        return (List<Auto>) repository.findAll() ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Auto> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Auto guardar(Auto auto) {
        return repository.save(auto);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Auto> porPlaca(String placa)  { return repository.findByPlaca(placa);}
}
