-- Crear tabla de usuarios si no existe
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Crear tabla de perros si no existe
CREATE TABLE IF NOT EXISTS perros (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    raza VARCHAR(255),
    tamano VARCHAR(50),
    ubicacion VARCHAR(255),
    comportamiento VARCHAR(255),
    duenio VARCHAR(255),
    edad INT,
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Insertar datos de ejemplo si la tabla está vacía
INSERT INTO usuarios (email, password) 
SELECT 'admin@municipalidad.com', 'admin123' 
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE email = 'admin@municipalidad.com');

INSERT INTO usuarios (email, password) 
SELECT 'registrador@municipalidad.com', 'registrador123' 
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE email = 'registrador@municipalidad.com');

-- Insertar perros de ejemplo si la tabla está vacía
INSERT INTO perros (nombre, raza, tamano, ubicacion, comportamiento, duenio, edad, usuario_id) 
SELECT 'Luna', 'Labrador', 'Grande', 'Miraflores', 'Amigable', 'Juan Pérez', 3, 1
WHERE NOT EXISTS (SELECT 1 FROM perros WHERE nombre = 'Luna');

INSERT INTO perros (nombre, raza, tamano, ubicacion, comportamiento, duenio, edad, usuario_id) 
SELECT 'Max', 'Golden Retriever', 'Grande', 'San Isidro', 'Juguetón', 'María García', 2, 1
WHERE NOT EXISTS (SELECT 1 FROM perros WHERE nombre = 'Max');

INSERT INTO perros (nombre, raza, tamano, ubicacion, comportamiento, duenio, edad, usuario_id) 
SELECT 'Bella', 'Chihuahua', 'Pequeño', 'Barranco', 'Nervioso', 'Carlos López', 1, 1
WHERE NOT EXISTS (SELECT 1 FROM perros WHERE nombre = 'Bella');

INSERT INTO perros (nombre, raza, tamano, ubicacion, comportamiento, duenio, edad, usuario_id) 
SELECT 'Rocky', 'Pitbull', 'Mediano', 'La Victoria', 'Protector', 'Ana Rodríguez', 4, 1
WHERE NOT EXISTS (SELECT 1 FROM perros WHERE nombre = 'Rocky');

INSERT INTO perros (nombre, raza, tamano, ubicacion, comportamiento, duenio, edad, usuario_id) 
SELECT 'Coco', 'Poodle', 'Mediano', 'San Borja', 'Inteligente', 'Luis Torres', 5, 1
WHERE NOT EXISTS (SELECT 1 FROM perros WHERE nombre = 'Coco');

INSERT INTO perros (nombre, raza, tamano, ubicacion, comportamiento, duenio, edad, usuario_id) 
SELECT 'Thor', 'Pastor Alemán', 'Grande', 'Surco', 'Leal', 'Patricia Silva', 6, 1
WHERE NOT EXISTS (SELECT 1 FROM perros WHERE nombre = 'Thor');

INSERT INTO perros (nombre, raza, tamano, ubicacion, comportamiento, duenio, edad, usuario_id) 
SELECT 'Milo', 'Beagle', 'Mediano', 'Magdalena', 'Curioso', 'Roberto Díaz', 2, 1
WHERE NOT EXISTS (SELECT 1 FROM perros WHERE nombre = 'Milo');

INSERT INTO perros (nombre, raza, tamano, ubicacion, comportamiento, duenio, edad, usuario_id) 
SELECT 'Nina', 'Husky', 'Grande', 'Pueblo Libre', 'Energético', 'Carmen Vega', 3, 1
WHERE NOT EXISTS (SELECT 1 FROM perros WHERE nombre = 'Nina');