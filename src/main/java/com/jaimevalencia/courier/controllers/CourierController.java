package com.jaimevalencia.courier.controllers;

import com.jaimevalencia.courier.hateoas.CourierModelAssembler;
import com.jaimevalencia.courier.model.Courier;
import com.jaimevalencia.courier.model.ResponseWrapper;
import com.jaimevalencia.courier.service.CourierService;
import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/encomiendas")
public class CourierController {

    private final CourierService courierService;
    private final CourierModelAssembler courierAssembler; // ⬅️ Añadir esta línea

    public CourierController(CourierService courierService, CourierModelAssembler courierAssembler) {
        this.courierService = courierService;
        this.courierAssembler = courierAssembler; // ⬅️ Asignar en el constructor
    }

    @GetMapping
    public ResponseEntity<?> todosEnvios(){
        List<Courier> couriers = courierService.todosEnvios();
        if(couriers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("En este mometo no hay registros registrados en el sistema.");
        }

        // Agrega todos los links HATEOAS a cada evento
        couriers.forEach(courier -> {
            Long id = courier.getId();
            // Self link
            Link selfLink = linkTo(methodOn(CourierController.class).buscarPorId(id)).withSelfRel();
            // All eventos link
            Link allEventosLink = linkTo(methodOn(CourierController.class).todosEnvios()).withRel("todos-envios");
            // Update link
            Link updateLink = linkTo(methodOn(CourierController.class).actualizar(id, null)).withRel("actualizar");
            // Delete link
            Link deleteLink = linkTo(methodOn(CourierController.class).eliminarCourier(id)).withRel("eliminar");
            
            courier.add(selfLink);
            courier.add(allEventosLink);
            courier.add(updateLink);
            courier.add(deleteLink);

        });

        ResponseWrapper<Courier> respuesta = new ResponseWrapper<>(
            "OK",
            couriers.size(),
            couriers);
            
        // Agrega links al wrapper
        respuesta.add(linkTo(methodOn(CourierController.class).guardarCourier(null)).withRel("crear-nuevo"));
        
        return ResponseEntity.ok(respuesta);  
    }
    
    @GetMapping("/{id}")
    public EntityModel<Courier> buscarPorId(@PathVariable Long id) {
        Courier courier = courierService.buscarPorId(id);
        return courierAssembler.toModel(courier);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Courier>> guardarCourier(@Valid @RequestBody Courier courier){
        Courier insertada = courierService.guardarEnvio(courier);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>("Registro de encomienda guardado satisfactoriamente", 1, List.of(insertada)));
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Courier>> actualizar(@PathVariable Long id,
        @Valid @RequestBody Courier courierUpdated){
            Courier updated = courierService.actualizar(id, courierUpdated);
            return ResponseEntity.ok(
                new ResponseWrapper<>("Registro de encomienda se ha actualizado satisfactoriamente.", 1, List.of(updated)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Courier>> eliminarCourier(@PathVariable Long id){
        courierService.deleted(id);
        return ResponseEntity.ok(
            new ResponseWrapper<>("Registro de encomienda eliminado satisfactoriamente.", 1, null)
        );
    }   
    
}
