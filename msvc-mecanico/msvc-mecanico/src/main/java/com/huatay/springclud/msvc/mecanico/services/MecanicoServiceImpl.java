package com.huatay.springclud.msvc.mecanico.services;

import com.huatay.springclud.msvc.mecanico.models.entity.Mecanico;
import com.huatay.springclud.msvc.mecanico.repositories.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MecanicoServiceImpl implements MecanicoService{

    @Autowired // inyecta la dependencia de una clase que tiene metodos a otra clase
    private MecanicoRepository repository;

    @Override
    @Transactional
    public List<Mecanico> lista() {
        return (List<Mecanico>) repository.findAll() ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mecanico> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Mecanico guardar(Mecanico mecanico) {
        return repository.save(mecanico);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Mecanico> porNombre(String nombres)  { return repository.findByNombres(nombres);}
}
