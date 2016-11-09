-- You can use this file to load seed data into the database using SQL statements
--Piezas
insert into Pieza (id,tipo,descripcion) values(-1,'MARCO','Marco hiper resistente');
insert into Pieza (id,tipo,descripcion) values(-2,'LLANTA_DELANTERA','Llanta con disco');
insert into Pieza (id,tipo,descripcion) values(-3,'LLANTA_TRASERA','Llanta aleacion');
insert into Pieza (id,tipo,descripcion) values(-4,'FRENOS','Freno convencional');
insert into Pieza (id,tipo,descripcion) values(-5,'CAMBIOS','18 pinones');
insert into Pieza (id,tipo,descripcion) values(-10,'MARCO','Marco elastico');
insert into Pieza (id,tipo,descripcion) values(-11,'LLANTA_DELANTERA','Llanta subliminal');
insert into Pieza (id,tipo,descripcion) values(-12,'FRENOS','Freno disco');
insert into Pieza (id,tipo,descripcion) values(-13,'CAMBIOS','9 pinones');
insert into Pieza (id,tipo,descripcion) values(-14,'LUZ','LED');
insert into Pieza (id,tipo,descripcion) values(-15,'LUZ','Encandecente');
insert into Pieza (id,tipo,descripcion) values(-16,'PITO','Tipo moto');
insert into Pieza (id,tipo,descripcion) values(-17,'PITO','Bocina');

--Colores
insert into Color (id,descripcion) values(-1,'NO APLICA');
insert into Color (id,descripcion) values(-2,'AMARILLO POLLITO');
insert into Color (id,descripcion) values(-3,'NEGRO ZAPOTE');
insert into Color (id,descripcion) values(-4,'ROJO FERRARI');

--Configuraciones y Piezas

insert into Configuracion (id,descripcion,emailCreador) values(-1,'Configuracion 1','maria.a@email.com');
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-1,'ROJO FERRARI',-1, -1);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-2,'NEGRO ZAPOTE',-2, -1);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-3,'AMARILLO POLLITO', -3, -1);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-4,'NO APLICA',-4, -1);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-5,'NO APLICA',-5, -1);

insert into Configuracion (id,descripcion,emailCreador) values(-2,'Configuracion 2','ja.velandia@correo.com');
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-10,'AMARILLO POLLITO',-10, -2);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-11,'AMARILLO POLLITO',-11, -2);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-12,'NO APLICA',-12, -2);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-13,'NO APLICA',-13, -2);
