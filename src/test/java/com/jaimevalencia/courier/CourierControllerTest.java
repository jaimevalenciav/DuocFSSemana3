// package com.jaimevalencia.courier;

// import com.jaimevalencia.courier.controllers.CourierController;
// import com.jaimevalencia.courier.hateoas.CourierModelAssembler;
// import com.jaimevalencia.courier.model.Courier;
// import com.jaimevalencia.courier.service.CourierService;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.context.annotation.Import;
// import org.springframework.hateoas.EntityModel;
// import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


// @WebMvcTest(CourierController.class)
// public class CourierControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private CourierService courierService;
    
   
//     @Test
//     @WithMockUser(username = "jvalencia", roles = {"ADMIN"})
//     public void testObtenerPorId() throws Exception {
//         // Datos de prueba
//         Courier courier = new Courier(1L, 1001, "México", "En Tránsito", 
//                                    "Av. Reforma 222, Ciudad de México", 
//                                    "19.4284700,-99.1375800");

//         // Configuración de HATEOAS
//         EntityModel<Courier> courierModel = EntityModel.of(courier,
//             linkTo(methodOn(CourierController.class).buscarPorId(1L)).withSelfRel(),
//             linkTo(methodOn(CourierController.class).eliminarCourier(1L)).withRel("delete"),
//             linkTo(methodOn(CourierController.class).actualizar(1L, null)).withRel("update"),
//             linkTo(methodOn(CourierController.class).todosEnvios()).withRel("all"));

//         // Configuración de mocks
//         when(courierService.buscarPorId(1L)).thenReturn(courier);

//         // Ejecución y verificación
//         mockMvc.perform(get("/encomiendas/1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.numEnvio").value(1001))
//                .andExpect(jsonPath("$.paisOrigen").value("México"))
//                .andExpect(jsonPath("$.estado").value("En Tránsito"))
//                .andExpect(jsonPath("$.direccionEnvio").value("Av. Reforma 222, Ciudad de México"))
//                .andExpect(jsonPath("$.ubicacionActual").value("19.4284700,-99.1375800"));
        
//         Mockito.when(courierService.buscarPorId(1L)).thenReturn(courier);

//         mockMvc.perform(get("/encomienda/1") // Ajusta la ruta según tu Controller
//                 .accept(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.paisOrigen").value("México"));
//     }
// }