package com.jaimevalencia.courier.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class CourierNotFoundException extends RuntimeException{
    public CourierNotFoundException(Long id){
        super("No se ha encontrado en nuestra base de datos los datos del envío:" + id);
    }
}
