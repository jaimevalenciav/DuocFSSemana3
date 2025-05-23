package com.jaimevalencia.courier.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.hateoas.RepresentationModel;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "courier") 

public class Courier extends RepresentationModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="El número del envío no puede ser nulo.")
    @Min(value=1, message="El valor no puede ser menor a uno.")
    @Column(name = "num_envio")
    private int numEnvio;

    @NotBlank(message="El país de origen no puede estar vacío.")
    @NotNull(message="El nombre del país no puede ser nulo.")
    @Size(min=2, max=50, message = "El país de origen debe tener entre 2 y 50 caracteres.")
    @Column(name = "pais_origen")
    private String paisOrigen;

    @NotBlank(message="El estado no puede estar vacío.")
    @NotNull(message="El estado no puede ser nulo.")
    @Size(min=2, max=50, message = "El estado debe tener entre 2 y 50 caracteres.")
    @Column(name = "estado")
    private String estado;
    
    @NotBlank(message="La dirección de envío no puede estar vacía.")
    @NotNull(message="La dirección no puede ser nulo.")
    @Size(min=5, max=150, message = "La dirección de envío debe tener entre 5 y 150 caracteres.")
    @Column(name = "direccion_envio")
    private String direccionEnvio;
    
    @NotBlank(message="La ubicación actual no puede estar vacía.")
    @NotNull(message="La ubicación actual no puede ser nulo.")
    @Size(min=2, max=50, message = "La ubicación actual debe tener entre 2 y 50 caracteres.") 
    @Column(name = "ubicacion_actual")   
    private String ubicacionActual;


}
