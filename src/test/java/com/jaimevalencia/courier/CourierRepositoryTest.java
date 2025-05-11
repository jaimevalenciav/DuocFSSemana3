package com.jaimevalencia.courier;

import com.jaimevalencia.courier.model.Courier;
import com.jaimevalencia.courier.repository.CourierRespository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CourierRepositoryTest {
    
    @Autowired
    private CourierRespository courierRespository;
    
    @Test
    public void testGuardarYBuscar() {
        // Verificar si existe antes de intentar eliminar
        if (courierRespository.existsById(26L)) {
            courierRespository.deleteById(26L);
        }
        
        Courier courier = new Courier(
            null, 
            1026, 
            "Chile", 
            "Pendiente",
            "Astorga 43334, Rancagua", 
            "-10.0883700,-71.0302700"
        );
        
        Courier guardado = courierRespository.save(courier);
        
        Optional<Courier> encontrado = courierRespository.findById(guardado.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("Pendiente", encontrado.get().getEstado());
    }
    
    @Test
    public void testActualizarCourier() {
        // Crear y guardar un courier
        Courier courier = new Courier(
            null,
            1027,
            "Argentina",
            "En tránsito",
            "Av. Corrientes 1234, Buenos Aires",
            "-34.6037232,-58.3815931"
        );
        
        Courier guardado = courierRespository.save(courier);
        Long id = guardado.getId();
        
        // Actualizar el courier
        guardado.setEstado("Entregado");
        guardado.setDireccionEnvio("Av. Corrientes 1240, Buenos Aires");
        courierRespository.save(guardado);
        
        // Verificar que se actualizó correctamente
        Optional<Courier> actualizado = courierRespository.findById(id);
        assertTrue(actualizado.isPresent());
        assertEquals("Entregado", actualizado.get().getEstado());
        assertEquals("Av. Corrientes 1240, Buenos Aires", actualizado.get().getDireccionEnvio());
    }
    
    @Test
    public void testEliminarCourier() {
        // Crear y guardar un courier
        Courier courier = new Courier(
            null,
            1028,
            "México",
            "Pendiente",
            "Paseo de la Reforma 222, CDMX",
            "19.4326077,-99.167683"
        );
        
        Courier guardado = courierRespository.save(courier);
        Long id = guardado.getId();
        
        // Verificar que se guardó correctamente
        assertTrue(courierRespository.findById(id).isPresent());
        
        // Eliminar el courier
        courierRespository.deleteById(id);
        
        // Verificar que ya no existe
        assertFalse(courierRespository.existsById(id));
    }
    
    @Test
    public void testBuscarPorNumeroEnvio() {
        // Crear y guardar varios couriers
        Courier courier1 = new Courier(
            null,
            2001,
            "Colombia",
            "Pendiente",
            "Carrera 7 #71-21, Bogotá",
            "4.6097100,-74.0817500"
        );
        
        Courier courier2 = new Courier(
            null,
            2002,
            "Perú",
            "En tránsito",
            "Av. Javier Prado Este 2465, Lima",
            "-12.0864,-77.0363"
        );
        
        courierRespository.save(courier1);
        courierRespository.save(courier2);
        
        // Buscar por número de envío
        Courier encontrado = courierRespository.findByNumEnvio(2001);
        
        // Verificar que se encontró el courier correcto
        assertNotNull(encontrado);
        assertEquals("Colombia", encontrado.getPaisOrigen());
        assertEquals("Carrera 7 #71-21, Bogotá", encontrado.getDireccionEnvio());
    }
}