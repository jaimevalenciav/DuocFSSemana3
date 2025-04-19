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
    
    private CourierRespository couriers;

    public List<Courier> todosEnvios(){
        return couriers.findAll();
    }

    public Courier buscarPorId(Long id){
        return couriers.findById(id)
            .orElseThrow(() -> new CourierNotFoundException(id));
    }

    public Courier guardarEnvio(Courier courier){
        //valida si existe el pedido
        if(couriers.existsById(courier.getId())){
            throw new IllegalArgumentException("Ya existe un envío con el id: " + courier.getId());
        }
        return couriers.save(courier);
    }  

    public Courier actualizar(Long id, Courier courierUpdated){
        Courier existente = couriers.findById(id).orElseThrow(() -> new CourierNotFoundException(id));

        existente.setNumEnvio(courierUpdated.getNumEnvio());
        existente.setPaisOrigen(courierUpdated.getPaisOrigen());
        existente.setDireccionEnvio(courierUpdated.getDireccionEnvio());
        existente.setEstado(courierUpdated.getEstado());
        existente.setUbicacionActual(courierUpdated.getUbicacionActual());

        return couriers.save(existente);
    }

    public void deleted(Long id){
        Courier existente = couriers.findById(id).orElseThrow(() -> new CourierNotFoundException(id));
        couriers.delete(existente);
    }

}
