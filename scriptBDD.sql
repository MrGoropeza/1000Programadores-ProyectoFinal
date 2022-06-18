# Creacion de la base de datos
create database muelas;
use muelas;

# Creacion de usuario para conectarse a la bdd desde JAVA
create user 'basedatos'@'localhost' identified by 'Contra123#';
grant all privileges on * . * to 'basedatos'@'localhost';
flush privileges;

# Creacion de Tablas
create table personas(
	persona_id int not null auto_increment,
    persona_nombre varchar(50) not null,
    persona_apellido varchar(50) not null ,
    persona_fecha_nac date,
    persona_numero varchar(20),
    persona_correo varchar(100),
    primary key (persona_id)
);