-- Esquema de Base de Datos — HCE Pabellón de Salud Mental
-- Corresponde al modelo descripto en modelo-datos-hce.md

CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL,           -- MEDICO_GUARDIA, PSIQUIATRA, PSICOLOGO, ENFERMERIA, ADMIN
    descripcion VARCHAR(255)
);

CREATE TABLE profesional (
    id_profesional INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    matricula VARCHAR(50),
    id_rol INT NOT NULL,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

CREATE TABLE paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    fecha_nacimiento DATE,
    sexo VARCHAR(20),
    contacto_familiar VARCHAR(255),
    fecha_alta_registro DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE internacion (
    id_internacion INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_profesional_guardia INT,
    fecha_ingreso_guardia DATETIME,
    motivo_consulta_guardia VARCHAR(255),
    resumen_entrevista_guardia TEXT,
    derivado_a_pabellon BOOLEAN DEFAULT FALSE,
    fecha_ingreso_pabellon DATETIME,
    id_profesional_tratante INT,
    estado VARCHAR(20) DEFAULT 'EN_GUARDIA',   -- EN_GUARDIA, INTERNADO, DE_ALTA
    es_reingreso BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (id_profesional_guardia) REFERENCES profesional(id_profesional),
    FOREIGN KEY (id_profesional_tratante) REFERENCES profesional(id_profesional)
);

CREATE TABLE diagnostico (
    id_diagnostico INT AUTO_INCREMENT PRIMARY KEY,
    id_internacion INT NOT NULL,
    id_profesional INT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    descripcion TEXT NOT NULL,
    vigente BOOLEAN DEFAULT TRUE,
    id_diagnostico_anterior INT,
    FOREIGN KEY (id_internacion) REFERENCES internacion(id_internacion),
    FOREIGN KEY (id_profesional) REFERENCES profesional(id_profesional),
    FOREIGN KEY (id_diagnostico_anterior) REFERENCES diagnostico(id_diagnostico)
);

CREATE TABLE indicacion_medica (
    id_indicacion INT AUTO_INCREMENT PRIMARY KEY,
    id_internacion INT NOT NULL,
    id_profesional INT NOT NULL,
    fecha_indicacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    droga VARCHAR(100) NOT NULL,
    dosis VARCHAR(50),
    frecuencia VARCHAR(50),
    via_administracion VARCHAR(50),
    estado VARCHAR(20) DEFAULT 'ACTIVA',       -- ACTIVA, MODIFICADA, SUSPENDIDA
    id_indicacion_anterior INT,
    origen VARCHAR(20) DEFAULT 'TRATANTE',     -- TRATANTE, CONSULTA_GUARDIA
    FOREIGN KEY (id_internacion) REFERENCES internacion(id_internacion),
    FOREIGN KEY (id_profesional) REFERENCES profesional(id_profesional),
    FOREIGN KEY (id_indicacion_anterior) REFERENCES indicacion_medica(id_indicacion)
);

CREATE TABLE administracion_medicacion (
    id_administracion INT AUTO_INCREMENT PRIMARY KEY,
    id_indicacion INT NOT NULL,
    id_profesional INT NOT NULL,               -- enfermería
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    administrada BOOLEAN DEFAULT TRUE,
    observaciones VARCHAR(255),
    FOREIGN KEY (id_indicacion) REFERENCES indicacion_medica(id_indicacion),
    FOREIGN KEY (id_profesional) REFERENCES profesional(id_profesional)
);

CREATE TABLE evolucion (
    id_evolucion INT AUTO_INCREMENT PRIMARY KEY,
    id_internacion INT NOT NULL,
    id_profesional INT NOT NULL,
    tipo VARCHAR(30) NOT NULL,                 -- MEDICA, ENFERMERIA_COMPORTAMIENTO
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    contenido TEXT NOT NULL,
    id_evolucion_corregida INT,
    FOREIGN KEY (id_internacion) REFERENCES internacion(id_internacion),
    FOREIGN KEY (id_profesional) REFERENCES profesional(id_profesional),
    FOREIGN KEY (id_evolucion_corregida) REFERENCES evolucion(id_evolucion)
);

CREATE TABLE permiso_salida (
    id_permiso INT AUTO_INCREMENT PRIMARY KEY,
    id_internacion INT NOT NULL,
    id_profesional INT NOT NULL,
    fecha_solicitud DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_autorizacion DATETIME,
    condiciones VARCHAR(255),
    estado VARCHAR(20) DEFAULT 'SOLICITADO',   -- SOLICITADO, AUTORIZADO, DENEGADO, USADO
    FOREIGN KEY (id_internacion) REFERENCES internacion(id_internacion),
    FOREIGN KEY (id_profesional) REFERENCES profesional(id_profesional)
);

CREATE TABLE consulta_guardia_excepcion (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    id_internacion INT NOT NULL,
    id_profesional_enfermeria INT NOT NULL,
    id_profesional_guardia INT NOT NULL,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    motivo TEXT,
    decision VARCHAR(255),
    id_indicacion_generada INT,
    FOREIGN KEY (id_internacion) REFERENCES internacion(id_internacion),
    FOREIGN KEY (id_profesional_enfermeria) REFERENCES profesional(id_profesional),
    FOREIGN KEY (id_profesional_guardia) REFERENCES profesional(id_profesional),
    FOREIGN KEY (id_indicacion_generada) REFERENCES indicacion_medica(id_indicacion)
);

CREATE TABLE egreso (
    id_egreso INT AUTO_INCREMENT PRIMARY KEY,
    id_internacion INT NOT NULL UNIQUE,
    id_profesional INT NOT NULL,
    fecha_egreso DATETIME DEFAULT CURRENT_TIMESTAMP,
    motivo_egreso VARCHAR(255),
    indicaciones_post_alta TEXT,
    FOREIGN KEY (id_internacion) REFERENCES internacion(id_internacion),
    FOREIGN KEY (id_profesional) REFERENCES profesional(id_profesional)
);

CREATE TABLE auditoria (
    id_auditoria INT AUTO_INCREMENT PRIMARY KEY,
    id_profesional INT,
    entidad VARCHAR(50) NOT NULL,
    id_entidad INT NOT NULL,
    accion VARCHAR(20) NOT NULL,               -- CREATE, UPDATE_LOGICO, LOGIN, etc.
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    detalle TEXT,
    FOREIGN KEY (id_profesional) REFERENCES profesional(id_profesional)
);
