-- You can use this file to load seed data into the database using SQL statements
--Piezas
insert into Pieza (id,tipo,descripcion) values(-1,'MARCO','Montana');
insert into Pieza (id,tipo,descripcion) values(-2,'MARCO','BMX');
insert into Pieza (id,tipo,descripcion) values(-3,'LLANTA_DELANTERA','Clicher');
insert into Pieza (id,tipo,descripcion) values(-4,'LLANTA_DELANTERA','Tubular');
insert into Pieza (id,tipo,descripcion) values(-5,'LLANTA_TRASERA','Sprint');
insert into Pieza (id,tipo,descripcion) values(-6,'LLANTA_TRASERA','Endrick');
insert into Pieza (id,tipo,descripcion) values(-7,'FRENOS','Tiro Central');
insert into Pieza (id,tipo,descripcion) values(-8,'FRENOS','Disco');
insert into Pieza (id,tipo,descripcion) values(-9,'CAMBIOS','Externos');
insert into Pieza (id,tipo,descripcion) values(-10,'CAMBIOS','Internos');
insert into Pieza (id,tipo,descripcion) values(-11,'LUZ','LED');
insert into Pieza (id,tipo,descripcion) values(-12,'LUZ','Candela');
insert into Pieza (id,tipo,descripcion) values(-13,'PITO','Tipo moto');
insert into Pieza (id,tipo,descripcion) values(-14,'PITO','Bocina');

--Colores
insert into Color (id,descripcion) values(-1,'NO APLICA');
insert into Color (id,descripcion) values(-2,'AMARILLO POLLITO');
insert into Color (id,descripcion) values(-3,'NEGRO ZAPOTE');
insert into Color (id,descripcion) values(-4,'ROJO FERRARI');

--Configuraciones y Piezas

insert into Configuracion (id,descripcion,emailCreador) values(-1,'Configuracion 1','rcb1@m.co');
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-1,'ROJO FERRARI',-1, -1);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-2,'NEGRO ZAPOTE',-2, -1);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-3,'AMARILLO POLLITO', -3, -1);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-4,'NO APLICA',-4, -1);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-5,'NO APLICA',-5, -1);

insert into Configuracion (id,descripcion,emailCreador) values(-2,'Configuracion 2','rcb1@m.co');
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-10,'AMARILLO POLLITO',-10, -2);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-11,'AMARILLO POLLITO',-11, -2);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-12,'NO APLICA',-12, -2);
insert into PiezaConfiguracion (id,color,idPieza,idConfiguracion) values(-13,'NO APLICA',-13, -2);
