package com.jaimevalencia.courier.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestParam;

import com.jaimevalencia.courier.model.Courier;
import com.jaimevalencia.courier.service.CourierService;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/encomiendas")
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService){
        this.courierService = courierService;
    }

    @GetMapping
    public List<Courier> todosEnvios(){
        return courierService.todosEnvios();
    }
    
    @GetMapping("/{numEnvio}")
    public Courier envioPorNumEnvio(@PathVariable int numEnvio){
        return courierService.consultarPorNumEnvio(numEnvio)
        .orElseThrow(()-> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "El numero de envío: " + numEnvio + "  no existe en la base de datos."
        ));
    }

    @PostMapping
    public Courier nuevoEnvio(@RequestBody Courier courier){
        return courierService.guardarEnvio(courier);
    }
    

    @PutMapping("/numenvio/{numEnvio}")
    public Courier actualizaEnvio(@PathVariable int numEnvio, @RequestBody Courier courier ){
        return courierService.actualizarEnvio(numEnvio, courier);
    }

    
    
}
