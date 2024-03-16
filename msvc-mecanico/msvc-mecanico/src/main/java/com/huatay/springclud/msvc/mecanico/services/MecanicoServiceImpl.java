package com.huatay.springclud.msvc.mecanico.services;

import com.huatay.springclud.msvc.mecanico.clients.AutoClientRest;
import com.huatay.springclud.msvc.mecanico.models.Auto;
import com.huatay.springclud.msvc.mecanico.models.entity.Mantenimiento;
import com.huatay.springclud.msvc.mecanico.models.entity.Mecanico;
import com.huatay.springclud.msvc.mecanico.repositories.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MecanicoServiceImpl implements MecanicoService{

    @Autowired // inyecta la dependencia de una clase que tiene metodos a otra clase
    private MecanicoRepository repository;

    @Autowired
    private AutoClientRest client;

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






//metodos remotos

    @Override
    @Transactional
    public Optional<Auto> asignarAuto(Auto auto, Long mecanicoId) {
        Optional<Mecanico> o = repository.findById(mecanicoId);
        if(o.isPresent()){
            Auto autoMsvc = client.detalle(auto.getIdAuto());
            Mecanico curso = o.get();
            Mantenimiento mantenimiento = new Mantenimiento();
            mantenimiento.setIdAuto(autoMsvc.getIdAuto());
            curso.addMantenimiento((mantenimiento));
            repository.save(curso);
            return Optional.of(autoMsvc);
        }
        return Optional.empty();
    }
    @Override
    @Transactional
    public Optional<Auto> crearAuto(Auto auto, Long mecanicoId) {
        Optional<Mecanico> o = repository.findById(mecanicoId);
        if(o.isPresent()){
            Auto autoMsvc = client.crear(auto);
            Mecanico mecanico = o.get();
            Mantenimiento mantenimiento = new Mantenimiento();
            mantenimiento.setIdAuto(autoMsvc.getIdAuto());
            mecanico.addMantenimiento((mantenimiento));
            repository.save(mecanico);
            return Optional.of(autoMsvc);
        }
        return Optional.empty();
    }


    @Override
    @Transactional
    public Optional<Auto> eliminarAuto(Auto auto, Long mecanicoId){
        Optional<Mecanico> o = repository.findById(mecanicoId);
        if(o.isPresent()){
            Auto autoMsvc = client.detalle(auto.getIdAuto());
            Mecanico mecanico = o.get();
            Mantenimiento mantenimiento = new Mantenimiento();
            mantenimiento.setIdAuto(autoMsvc.getIdAuto());

            mecanico.removeMantenimiento((mantenimiento));
            repository.save(mecanico);
            return Optional.of(autoMsvc);
        }
        return Optional.empty();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Mecanico> porIdConAutos(Long id) {
        Optional<Mecanico> o = repository.findById(id);
        if(o.isPresent()){
            Mecanico mecanico = o.get();
            if(!mecanico.getMantenimientoList().isEmpty()){
                List<Long> ids = mecanico.getMantenimientoList().stream().map(cu -> cu.getIdAuto())
                        .collect(Collectors.toList());
                List<Auto> autos = client.obtenerAutosPorMecanico(ids);
                mecanico.setAutoList(autos);
            }
            return Optional.of(mecanico);
        }
        return Optional.empty();
    }


    @Override
    @Transactional
    public void eliminarMecanicoAutoPorId(Long id) {
        repository.eliminarMecanicoAutoPorId(id);
    }





}
