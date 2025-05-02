package com.jaimevalencia.courier;
import com.jaimevalencia.courier.exception.CourierNotFoundException;
import com.jaimevalencia.courier.model.Courier;
import com.jaimevalencia.courier.repository.CourierRespository;
import com.jaimevalencia.courier.service.CourierService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.data.domain.Sort;


public class CourierServiceTest {

    private CourierRespository courierRespository;
    private CourierService courierService;

    @BeforeEach
    public void setUp(){
        courierRespository = mock(CourierRespository.class);
        courierService = new CourierService(courierRespository);
    }

    @Test
    public void testObtenerTodos(){
        // Crear datos de prueba
        Courier c1 = new Courier(1L, 1026, "Chile", "Pendiente", "Astorga 43334, Rancagua", "-10.0883700,-71.0302700");
        Courier c2 = new Courier(2L, 1027, "Italia", "Entregado", "San Peter 773, Monaco", "12.0883700,-71.0478700");
        
        // Configurar comportamiento del mock para cualquier tipo de Sort
        when(courierRespository.findAll(ArgumentMatchers.any(Sort.class))).thenReturn(Arrays.asList(c1, c2));
        
        // También configurar para findAll() sin argumentos por si acaso
        when(courierRespository.findAll()).thenReturn(Arrays.asList(c1, c2));

        // Ejecutar el método a probar
        List<Courier> resultado = courierService.todosEnvios();

        // Verificar resultados
        assertEquals(2, resultado.size());
        assertEquals("Chile", resultado.get(0).getPaisOrigen());
    }

    @Test
    public void testObtenerPorId_existente(){
        Courier courier = new Courier(26L, 1027, "Italia", "Entregado", "San Peter 773, Monaco", "12.0883700,-71.0478700");
        when(courierRespository.findById(26L)).thenReturn(Optional.of(courier));

        Courier resultado = courierService.buscarPorId(26L);
        assertEquals("Italia", resultado.getPaisOrigen());
        assertEquals("Entregado", resultado.getEstado());
    }

    @Test
    public void testObtenerPorId_noExistente(){
        when(courierRespository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CourierNotFoundException.class, () -> {
            courierService.buscarPorId(99L);
        });
    }
}