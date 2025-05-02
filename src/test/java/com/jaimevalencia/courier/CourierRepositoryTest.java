package com.jaimevalencia.courier;

import com.jaimevalencia.courier.model.Courier;
import com.jaimevalencia.courier.repository.CourierRespository;

import jakarta.persistence.EntityManager;

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
        courierRespository.deleteById(26L); 
        
        Courier courier = new Courier( null, 1026, "Chile", "Pendiente", 
                                    "Astorga 43334, Rancagua", "-10.0883700,-71.0302700");

        Courier guardado = courierRespository.save(courier);
        
        Optional<Courier> encontrado = courierRespository.findById(guardado.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("Pendiente", encontrado.get().getEstado());
    }
}