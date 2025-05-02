package com.jaimevalencia.courier.service;
import com.jaimevalencia.courier.model.Courier;
import com.jaimevalencia.courier.repository.CourierRespository;
import com.jaimevalencia.courier.exception.CourierNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierService {
    @Autowired
    
    private final CourierRespository courierRespository;

    public CourierService(CourierRespository courierRespository) {
        this.courierRespository = courierRespository;
    }

    public List<Courier> todosEnvios(){
        return courierRespository.findAll();
    }

    public Courier buscarPorId(Long id){
        return courierRespository.findById(id)
            .orElseThrow(() -> new CourierNotFoundException(id));
    }

    public Courier guardarEnvio(Courier courier){
        //valida si existe el pedido
        if(courierRespository.existsById(courier.getId())){
            throw new IllegalArgumentException("Ya existe un envío con el id: " + courier.getId());
        }
        return courierRespository.save(courier);
    }  

    public Courier actualizar(Long id, Courier courierUpdated){
        Courier existente = courierRespository.findById(id).orElseThrow(() -> new CourierNotFoundException(id));

        existente.setNumEnvio(courierUpdated.getNumEnvio());
        existente.setPaisOrigen(courierUpdated.getPaisOrigen());
        existente.setDireccionEnvio(courierUpdated.getDireccionEnvio());
        existente.setEstado(courierUpdated.getEstado());
        existente.setUbicacionActual(courierUpdated.getUbicacionActual());

        return courierRespository.save(existente);
    }

    public void deleted(Long id){
        Courier existente = courierRespository.findById(id).orElseThrow(() -> new CourierNotFoundException(id));
        courierRespository.delete(existente);
    }

}
