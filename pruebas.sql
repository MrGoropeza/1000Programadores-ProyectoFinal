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
		(43337890,'Gonzalo','Oropeza','2001-03-08','3874047262','goropeza8@gmail.com');
        
insert into muelas.pacientes
	(persona_dni)
    value (43337890);
    
    
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


