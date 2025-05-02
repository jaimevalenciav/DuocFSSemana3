package com.jaimevalencia.courier.hateoas;

//Se cargan las funciones que generan los links
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.jaimevalencia.courier.controllers.CourierController;
import com.jaimevalencia.courier.model.Courier;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CourierModelAssembler implements RepresentationModelAssembler<Courier, EntityModel<Courier>>{
    //Metodo para transformar Courier en EntityModel con enlaces a sus distintos métodos
    @Override
    public EntityModel<Courier> toModel(Courier courier)
    {
        return EntityModel.of(
            courier,

            //Enlace para buscar Encomienda por Id
            linkTo(methodOn(CourierController.class)
                .buscarPorId(courier.getId()))
                .withSelfRel(),
            
            //Enlace para buscar eliminar encomienda
            linkTo(methodOn(CourierController.class)
                .eliminarCourier(courier.getId()))
                .withRel("delete"),
            
            //Enlace para buscar actualizar los datos de una encomienda
            linkTo(methodOn(CourierController.class)
                .actualizar(courier.getId(), new Courier()))
                .withRel("update"),

            //Enlace para desplegar todas las encomiendas
            linkTo(methodOn(CourierController.class)
                .todosEnvios())
                .withRel("all")

        );
    }
}
