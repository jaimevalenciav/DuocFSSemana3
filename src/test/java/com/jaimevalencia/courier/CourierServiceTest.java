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

    @Test
    public void testGuardarEnvio_nuevo() {
        // Crear datos de prueba
        Courier courierAGuardar = new Courier(3L, 1028, "España", "En tránsito", "Gran Vía 123, Madrid", "40.4167,-3.7033");
        
        // Configurar comportamiento del mock
        when(courierRespository.existsById(3L)).thenReturn(false);
        when(courierRespository.save(ArgumentMatchers.any(Courier.class))).thenReturn(courierAGuardar);
        
        // Ejecutar el método a probar
        Courier resultado = courierService.guardarEnvio(courierAGuardar);
        
        // Verificar resultados
        assertNotNull(resultado);
        assertEquals(3L, resultado.getId());
        assertEquals("España", resultado.getPaisOrigen());
        assertEquals("En tránsito", resultado.getEstado());
        
        // Verificar que el método save del repositorio fue llamado una vez
        verify(courierRespository, times(1)).existsById(3L);
        verify(courierRespository, times(1)).save(ArgumentMatchers.any(Courier.class));
    }

    @Test
    public void testGuardarEnvio_existente() {
        // Crear datos de prueba
        Courier courierAGuardar = new Courier(1L, 1028, "España", "En tránsito", "Gran Vía 123, Madrid", "40.4167,-3.7033");
        
        // Configurar comportamiento del mock para simular que ya existe el ID
        when(courierRespository.existsById(1L)).thenReturn(true);
        
        // Verificar que lanza la excepción esperada
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            courierService.guardarEnvio(courierAGuardar);
        });
        
        // Verificar el mensaje de la excepción
        assertEquals("Ya existe un envío con el id: 1", exception.getMessage());
        
        // Verificar que se llamó a existsById pero no a save
        verify(courierRespository, times(1)).existsById(1L);
        verify(courierRespository, never()).save(ArgumentMatchers.any(Courier.class));
    }

    @Test
    public void testActualizar_existente() {
        // Crear datos de prueba
        Long idExistente = 2L;
        Courier courierExistente = new Courier(idExistente, 1027, "Italia", "En tránsito", "San Peter 773, Monaco", "12.0883700,-71.0478700");
        Courier courierActualizado = new Courier(idExistente, 1030, "Francia", "Entregado", "Rue de Rivoli 1, París", "48.8566,2.3522");
        
        // Configurar comportamiento del mock
        when(courierRespository.findById(idExistente)).thenReturn(Optional.of(courierExistente));
        when(courierRespository.save(ArgumentMatchers.any(Courier.class))).thenReturn(courierActualizado);
        
        // Ejecutar el método a probar
        Courier resultado = courierService.actualizar(idExistente, courierActualizado);
        
        // Verificar resultados
        assertEquals("Entregado", resultado.getEstado());
        assertEquals("Francia", resultado.getPaisOrigen());
        assertEquals(1030, resultado.getNumEnvio());
        assertEquals("Rue de Rivoli 1, París", resultado.getDireccionEnvio());
        
        // Verificar que se llamaron correctamente los métodos del repositorio
        verify(courierRespository, times(1)).findById(idExistente);
        verify(courierRespository, times(1)).save(ArgumentMatchers.any(Courier.class));
    }

    @Test
    public void testDeleted_existente() {
        // Crear datos de prueba
        Long idExistente = 1L;
        Courier courierExistente = new Courier(idExistente, 1026, "Chile", "Pendiente", "Astorga 43334, Rancagua", "-10.0883700,-71.0302700");
        
        // Configurar comportamiento del mock
        when(courierRespository.findById(idExistente)).thenReturn(Optional.of(courierExistente));
        doNothing().when(courierRespository).delete(ArgumentMatchers.any(Courier.class));
        
        // Ejecutar el método a probar
        courierService.deleted(idExistente);
        
        // Verificar que se llamaron los métodos del repositorio correctamente
        verify(courierRespository, times(1)).findById(idExistente);
        verify(courierRespository, times(1)).delete(courierExistente);
    }

    @Test
    public void testDeleted_noExistente() {
        // Configurar comportamiento del mock para ID que no existe
        Long idNoExistente = 99L;
        when(courierRespository.findById(idNoExistente)).thenReturn(Optional.empty());
        
        // Verificar que lanza la excepción esperada
        assertThrows(CourierNotFoundException.class, () -> {
            courierService.deleted(idNoExistente);
        });
        
        // Verificar que se llamó a findById pero no a delete
        verify(courierRespository, times(1)).findById(idNoExistente);
        verify(courierRespository, never()).delete(ArgumentMatchers.any(Courier.class));
    }
}