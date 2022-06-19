# Creacion de la base de datos
create database muelas;
use muelas;

# Creacion de usuario para conectarse a la bdd desde JAVA
create user 'basedatos'@'localhost' identified by 'Contra123#';
grant all privileges on * . * to 'basedatos'@'localhost';
flush privileges;

# Creacion de Tablas
create table personas(
	persona_dni int not null,
    persona_nombre varchar(50) not null,
    persona_apellido varchar(50) not null ,
    persona_fecha_nac date not null,
    persona_numero varchar(20) null,
    persona_correo varchar(100) not null,
    primary key (persona_dni)
);

create table administrativos(
	administrativo_id int not null auto_increment,
    is_super_admin bool default false,
    persona_dni int not null,
    primary key (administrativo_id),
    foreign key fk_admin_persona (persona_dni) references personas(persona_dni)
);

create table sectores(
	sector_id int not null auto_increment,
    sector_nombre varchar(100) not null,
    primary key(sector_id)
);

create table doctores(
	doctor_id int not null auto_increment,
    doctor_especialidad varchar(100) null,
    sector_id int null,
    persona_dni int not null,
    primary key (doctor_id),
    foreign key fk_doctor_persona (persona_dni) references personas(persona_dni),
    foreign key fk_doctor_sector (sector_id) references sectores(sector_id)
);

create table pacientes(
	paciente_id int not null auto_increment,
    paciente_has_ficha_medica bool default false,
    persona_dni int not null,
    primary key (paciente_id),
    foreign key fk_paciente_persona (persona_dni) references personas(persona_dni)
);

create table tratamientos(
	tratamiento_id int not null auto_increment,
    tratamiento_descripcion varchar(500) not null,
    tratamiento_fecha date not null,
    tratamiento_is_emergencia bool default false,
    paciente_id int not null,
    doctor_id int not null,
    primary key (tratamiento_id),
    foreign key fk_tratamientos_paciente (paciente_id) references pacientes(paciente_id),
    foreign key fk_tratamientos_doctor (doctor_id) references doctores(doctor_id)
);

create table turnos(
	turno_id int not null auto_increment,
    turno_fecha datetime not null,
    turno_duracion_minutos int not null,
    paciente_id int not null,
    doctor_id int not null,
    primary key (turno_id),
    foreign key fk_turnos_paciente (paciente_id) references pacientes(paciente_id),
    foreign key fk_turnos_doctor (doctor_id) references doctores(doctor_id)
);

# Inserción de datos iniciales
	# Sectores
	insert into muelas.sectores (sector_nombre)
    values('menores'),('mayores'),('emergencias');
    
    # Doctores
    insert into muelas.personas
		(persona_dni,persona_nombre,persona_apellido,persona_fecha_nac,persona_numero,persona_correo)
    values
		(38983235,'Pedro','Ramirez','1982-06-04','3874047363','pramirez@gmail.com'),
		(37298351,'Jose','Fernandez','1980-03-16','3874045222','jfernandez@gmail.com'),
        (36192857,'Guillermo','Mogro','1992-05-25','3874028621','gmogro@gmail.com');
        
	insert into muelas.doctores
    (doctor_especialidad,sector_id,persona_dni)
    values
		('Odontólogo',1,38983235),
        ('Cirujano dentista',2,37298351),
        ('Cirujano dentista',3,36192857);
	
	# Administrativo
    insert into muelas.personas
		(persona_dni,persona_nombre,persona_apellido,persona_fecha_nac,persona_numero,persona_correo)
    values
		(40297421,'Gonzalo','Sánchez','1982-06-04','3874325253','gsanchez@gmail.com');
	
    insert into muelas.administrativos
		(persona_dni,is_super_admin)
	value (40297421,true);


drop database muelas;