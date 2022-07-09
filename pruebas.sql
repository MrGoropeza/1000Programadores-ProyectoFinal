use muelas;

select * from administrativos t1
inner join personas t2
where t2.persona_dni = 40297421;

select * from administrativos t1
inner join personas t2
where t1.persona_dni = t2.persona_dni;

select * from doctores t1
inner join personas t2, sectores t3
where t1.persona_dni = t2.persona_dni
and t1.sector_id = t3.sector_id;

select * from muelas.sectores;


insert into muelas.personas
		(persona_dni,persona_nombre,persona_apellido,persona_fecha_nac,persona_numero,persona_correo)
    values
		(43337890,'Gonzalo','Oropeza','2001-03-08','3874047262','goropeza8@gmail.com'),
        (43337891,'Gonzalo','Fernandez','2001-03-08','3874047262','goropeza8@gmail.com');
        
insert into muelas.pacientes
	(persona_dni)
    values 
		(43337890),
        (43337891);

insert into muelas.personas
		(persona_dni,persona_nombre,persona_apellido,persona_fecha_nac,persona_numero,persona_correo)
    values
        (43337891,'Gonzalo','Fernandez','2001-03-08','3874047262','goropeza8@gmail.com');
        
insert into muelas.pacientes
	(persona_dni)
    values 
        (43337891);

    
select * from muelas.turnos;

select * from muelas.administrativos;

delete from muelas.turnos
where turno_id = 4;


select * from turnos t1
inner join doctores t2, personas t3
where t1.doctor_id = t2.doctor_id
and t2.persona_dni = t3.persona_dni
and t2.persona_dni = '37298351';


select * from muelas.administrativos t1
inner join muelas.personas t2
where t1.persona_dni = t2.persona_dni
and t2.persona_dni = 43337890;


alter table muelas.turnos
add column isEmergencia bool default false;

use muelas;
select * from turnos t1
inner join doctores t2, personas t3, pacientes t4
where t1.doctor_id = t2.doctor_id
and t1.paciente_id = t4.paciente_id
and t3.persona_dni = t4.persona_dni
and t2.doctor_id = 2;

select * from doctores;

select 
	t1.persona_dni, t2.persona_nombre,
    t2.persona_apellido, t2.persona_fecha_nac,
    t2.persona_numero, t2.persona_correo
from pacientes t1
inner join personas t2, turnos t3, doctores t4
where t1.persona_dni = t2.persona_dni
and t3.paciente_id = t1.paciente_id
and t4.doctor_id = 2
group by t1.paciente_id;

select * from muelas.tratamientos;

delete from muelas.tratamientos
where tratamiento_id = 2;

