// // package com.jaimevalencia.courier;

// // //Controller a probar
// // import com.jaimevalencia.courier.controllers.CourierController;

// // // Entidad Courier a evaluar
// // import com.jaimevalencia.courier.model.Courier;

// // //Servicio simulado por MockBean
// // import com.jaimevalencia.courier.service.CourierService;

// // //Assembler de hateoas
// // import com.jaimevalencia.courier.hateoas.CourierModelAssembler;

// // import org.junit.jupiter.api.Test;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// // import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// // import org.springframework.boot.test.context.SpringBootTest;
// // import org.springframework.boot.test.mock.mockito.MockBean;
// // import org.springframework.context.annotation.Import;
// // import org.springframework.hateoas.EntityModel;
// // import org.springframework.http.MediaType;
// // import org.springframework.security.test.context.support.WithMockUser;
// // import org.springframework.test.context.ActiveProfiles;
// // import org.springframework.test.context.TestPropertySource;
// // import org.springframework.test.web.servlet.MockMvc;

// // //se importan librerias para crear mocks
// // import static org.mockito.Mockito.when;
// // import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// // import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// // //Librerias para generar los enlaces de hateoas
// // import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

// // //Testear la capa del Controller
// // @WebMvcTest(CourierController.class)
// // @ActiveProfiles("test")
// // @AutoConfigureMockMvc(addFilters = false)
// // @Import({CourierModelAssembler.class})

// // public class CourierControllerTest {
// //     @Autowired
// //     private MockMvc mockMvc;

// //     @MockBean
// //     private CourierService courierService;
    
// //     @MockBean
// //     private CourierModelAssembler courierAssembler;

// //     @Test
// //     //Simulación de autenticación
// //     @WithMockUser(username = "jvalencia", password = "Valencia2020", roles = {"ADMIN"})
// //     public void testObtenerPorId() throws Exception{
// //         // Asegúrate de que los IDs coincidan (1L)
// //         Courier courier = new Courier(1L, 1001, "México", "En Tránsito", 
// //                                      "Av. Reforma 222, Ciudad de México", "19.4284700,-99.1375800");

// //         // Usar el mismo ID (1L) en los enlaces HATEOAS
// //         EntityModel<Courier> courierModel = EntityModel.of(courier,
// //                 linkTo(methodOn(CourierController.class).buscarPorId(1L)).withSelfRel(),
// //                 linkTo(methodOn(CourierController.class).eliminarCourier(1L)).withRel("delete"),
// //                 linkTo(methodOn(CourierController.class).actualizar(1L, null)).withRel("update"),
// //                 linkTo(methodOn(CourierController.class).todosEnvios()).withRel("all"));
        
// //         // Configurar los mocks para el ID 1L
// //         when(courierService.buscarPorId(1L)).thenReturn(courier);
// //         when(courierAssembler.toModel(courier)).thenReturn(courierModel);

// //         // Realizar la solicitud GET al endpoint /encomiendas/1
// //         mockMvc.perform(get("/encomiendas/1")
// //             .accept(MediaType.APPLICATION_JSON))
// //             .andExpect(status().isOk())
// //             .andExpect(jsonPath("$.id").value(1))
// //             .andExpect(jsonPath("$.numEnvio").value(1001))
// //             .andExpect(jsonPath("$.paisOrigen").value("México"))
// //             .andExpect(jsonPath("$.estado").value("En Tránsito"))
// //             .andExpect(jsonPath("$.direccionEnvio").value("Av. Reforma 222, Ciudad de México"))
// //             .andExpect(jsonPath("$.ubicacionActual").value("19.4284700,-99.1375800"))
// //             .andExpect(jsonPath("$._links.self.href").exists())
// //             .andExpect(jsonPath("$._links.delete.href").exists())
// //             .andExpect(jsonPath("$._links.update.href").exists())
// //             .andExpect(jsonPath("$._links.all.href").exists());
// //     }
// // }

// package com.jaimevalencia.courier;

// import com.jaimevalencia.courier.controllers.CourierController;
// import com.jaimevalencia.courier.model.Courier;
// import com.jaimevalencia.courier.service.CourierService;
// import com.jaimevalencia.courier.hateoas.CourierModelAssembler;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.Import;
// import org.springframework.hateoas.EntityModel;
// import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.web.servlet.MockMvc;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


// @WebMvcTest(
//     controllers = CourierController.class,
//     excludeAutoConfiguration = {
//         org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
//         org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class
//     }
// )
// @ActiveProfiles("test")
// @Import({CourierService.class, CourierModelAssembler.class})
// public class CourierControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private CourierService courierService;
    
//     @MockBean
//     private CourierModelAssembler courierAssembler;

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
//         when(courierAssembler.toModel(courier)).thenReturn(courierModel);

//         // Ejecución y verificación
//         mockMvc.perform(get("/encomiendas/1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.numEnvio").value(1001))
//                .andExpect(jsonPath("$.paisOrigen").value("México"))
//                .andExpect(jsonPath("$.estado").value("En Tránsito"))
//                .andExpect(jsonPath("$.direccionEnvio").value("Av. Reforma 222, Ciudad de México"))
//                .andExpect(jsonPath("$.ubicacionActual").value("19.4284700,-99.1375800"))
//                .andExpect(jsonPath("$._links.self.href").exists())
//                .andExpect(jsonPath("$._links.delete.href").exists())
//                .andExpect(jsonPath("$._links.update.href").exists())
//                .andExpect(jsonPath("$._links.all.href").exists());
//     }
// }