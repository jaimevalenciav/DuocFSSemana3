package com.jaimevalencia.courier.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courier {

    private Long id;
    private int numEnvio;
    private String paisOrigen;
    private String estado;
    private String direccionEnvio;
    private String ubicacionActual;


}
