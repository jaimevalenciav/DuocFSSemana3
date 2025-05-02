CREATE TABLE IF NOT EXISTS PUBLIC.courier (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    num_envio INTEGER NOT NULL,
    pais_origen VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    direccion_envio VARCHAR(255) NOT NULL,
    ubicacion_actual VARCHAR(255) NOT NULL
);