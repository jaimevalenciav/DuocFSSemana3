package com.jaimevalencia.courier.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.jaimevalencia.courier.model.Courier;

public interface CourierRespository extends JpaRepository<Courier, Long>{

   

}
