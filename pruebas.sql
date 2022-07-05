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