DROP DATABASE alumno;
CREATE DATABASE alumno;
USE alumno;

CREATE TABLE grupo(
	id_alumno MEDIUMINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	apellidos VARCHAR(80) NOT NULL,
	fecha_nacimiento DATE NOT NULL
);


Probar en JUnit lo que esta en principal