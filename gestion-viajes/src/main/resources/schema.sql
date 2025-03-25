CREATE DATABASE IF NOT EXISTS gestion_viajes;
USE gestion_viajes;

-- Tabla Cliente
CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    telefono VARCHAR(15)
);

-- Tabla Viaje
CREATE TABLE viaje (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    destino VARCHAR(150) NOT NULL,
    descripcion TEXT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    fecha_disponible DATE NOT NULL,
    duracion INT NOT NULL
);

-- Tabla Reserva
CREATE TABLE reserva (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_reserva DATETIME NOT NULL,
    estado ENUM('confirmada', 'cancelada') NOT NULL,
    cliente_id BIGINT NOT NULL,
    viaje_id BIGINT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE,
    FOREIGN KEY (viaje_id) REFERENCES viaje(id) ON DELETE CASCADE
);

-- Tabla Pago
CREATE TABLE pago (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    monto DECIMAL(10,2) NOT NULL,
    metodo_pago ENUM('tarjeta', 'transferencia', 'efectivo') NOT NULL,
    fecha_pago DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    reserva_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (reserva_id) REFERENCES reserva(id) ON DELETE CASCADE
);