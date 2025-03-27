package com.jaimevalencia.courier.service;

import com.jaimevalencia.courier.model.Courier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CourierService {
    private final List<Courier> couriers = new ArrayList<>();

    public CourierService(){
        couriers.add(new Courier(1L, 29283928, "China", "En Tránsito", "Las Parcelas 63637, Vallenar, Chile", "31.230391, 121.473701"));
        couriers.add(new Courier(2L, 38392019, "Estados Unidos", "Entregado", "Av. Los Álamos 1234, Santiago, Chile", "-33.448890, -70.669265"));
        couriers.add(new Courier(3L, 47283920, "España", "En Aduana", "Calle Mayor 56, Madrid, España", "40.416775, -3.703790"));
        couriers.add(new Courier(4L, 58392039, "Alemania", "Despachado", "Hauptstrasse 12, Berlín, Alemania", "52.520008, 13.404954"));
        couriers.add(new Courier(5L, 67482930, "Francia", "En Tránsito", "Rue de Rivoli 25, París, Francia", "48.856613, 2.352222"));
        couriers.add(new Courier(6L, 78920193, "Japón", "Entregado", "Shinjuku 3-15-17, Tokio, Japón", "35.689487, 139.691711"));
        couriers.add(new Courier(7L, 89293010, "Brasil", "En Almacén", "Rua das Flores 45, São Paulo, Brasil", "-23.550520, -46.633308"));
        couriers.add(new Courier(8L, 97281930, "México", "En Tránsito", "Av. Reforma 678, Ciudad de México, México", "19.432608, -99.133209"));
        couriers.add(new Courier(9L, 10382939, "Canadá", "En Aduana", "123 Maple Street, Toronto, Canadá", "43.651070, -79.347015"));
        couriers.add(new Courier(10L, 11483940, "Argentina", "Entregado", "Av. Córdoba 789, Buenos Aires, Argentina", "-34.603722, -58.381592"));
        couriers.add(new Courier(11L, 12583920, "Italia", "En Tránsito", "Via Roma 45, Roma, Italia", "41.902782, 12.496366"));
        couriers.add(new Courier(12L, 13682939, "Reino Unido", "Despachado", "Oxford Street 23, Londres, Reino Unido", "51.507351, -0.127758"));
        couriers.add(new Courier(13L, 14783940, "India", "En Almacén", "MG Road 678, Nueva Delhi, India", "28.613939, 77.209023"));
        couriers.add(new Courier(14L, 15882950, "Australia", "En Tránsito", "George St 12, Sídney, Australia", "-33.868820, 151.209290"));
        couriers.add(new Courier(15L, 16981960, "Rusia", "En Aduana", "Tverskaya 67, Moscú, Rusia", "55.755825, 37.617298"));
        couriers.add(new Courier(16L, 17281970, "China", "Despachado", "Nanjing Road 45, Shanghái, China", "31.230391, 121.473701"));
        couriers.add(new Courier(17L, 18382980, "Colombia", "Entregado", "Cra. 10 #12-45, Bogotá, Colombia", "4.609710, -74.081749"));
        couriers.add(new Courier(18L, 19483990, "Perú", "En Tránsito", "Jr. de la Unión 456, Lima, Perú", "-12.046374, -77.042793"));
        couriers.add(new Courier(19L, 20582900, "Sudáfrica", "En Aduana", "Nelson Mandela Blvd 789, Ciudad del Cabo, Sudáfrica", "-33.924870, 18.424055"));
        couriers.add(new Courier(20L, 21681910, "Corea del Sur", "Despachado", "Gangnam-daero 56, Seúl, Corea del Sur", "37.566536, 126.977966"));
        couriers.add(new Courier(21L, 22782920, "Egipto", "En Almacén", "Tahrir Square, El Cairo, Egipto", "30.044420, 31.235712"));
        couriers.add(new Courier(22L, 23883930, "Turquía", "En Tránsito", "Istiklal Caddesi 67, Estambul, Turquía", "41.008240, 28.978359"));
        couriers.add(new Courier(23L, 24981940, "Chile", "Entregado", "Av. Providencia 678, Santiago, Chile", "-33.448890, -70.669265"));
        couriers.add(new Courier(24L, 26082950, "Venezuela", "En Aduana", "Av. Bolívar 456, Caracas, Venezuela", "10.480594, -66.903606"));
        couriers.add(new Courier(25L, 27181960, "Uruguay", "Despachado", "Bulevar Artigas 123, Montevideo, Uruguay", "-34.901112, -56.164532"));
        couriers.add(new Courier(26L, 28282970, "Paraguay", "En Almacén", "Av. Mariscal López 789, Asunción, Paraguay", "-25.263740, -57.575926"));
        couriers.add(new Courier(27L, 29383980, "Bolivia", "En Tránsito", "Av. Camacho 234, La Paz, Bolivia", "-16.500000, -68.150002")); 
        couriers.add(new Courier(28L, 30481990, "Ecuador", "Entregado", "Av. Amazonas 678, Quito, Ecuador", "-0.180653, -78.467834")); 
        couriers.add(new Courier(29L, 31582900, "Panamá", "En Aduana", "Calle 50, Panamá, Panamá", "8.982379, -79.519870")); 
        couriers.add(new Courier(30L, 32681910, "Costa Rica", "Despachado", "Av. Central, San José, Costa Rica", "9.928069, -84.090725"));

    }

    public List<Courier> todosEnvios(){
        return couriers;
    }

    public Optional<Courier> consultarPorNumEnvio(int numEnvio){
        return couriers.stream()
        .filter(p->p.getNumEnvio() == numEnvio)
        .findFirst();
    }

    public Courier guardarEnvio(Courier courier){
        couriers.add(courier);
        return courier;
    }

    public Courier actualizarEnvio(int numEnvio, Courier courierActualizado){
        Optional<Courier> optionalCourier = consultarPorNumEnvio(numEnvio);

        if (optionalCourier.isPresent()){
            Courier courierExistente = optionalCourier.get();

            courierExistente.setPaisOrigen(courierActualizado.getPaisOrigen());
            courierExistente.setEstado(courierActualizado.getEstado());
            courierExistente.setDireccionEnvio(courierActualizado.getDireccionEnvio());
            courierExistente.setUbicacionActual(courierActualizado.getUbicacionActual());

            return courierExistente;

        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"El número de envío: " + numEnvio + " no existe en la base de datos");
        }
    }

}
