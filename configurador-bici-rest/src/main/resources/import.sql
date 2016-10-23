-- You can use this file to load seed data into the database using SQL statements
insert into ConfiguracionBicicleta (id,descripcion,emailCreador) values(-1,'Configuracion 1','maria.a@email.com');
insert into PiezaBicicleta (id,tipo,descripcion, idConfiguracion) values(-1,'MARCO','Marco hiper resistente', -1);
insert into PiezaBicicleta (id,tipo,descripcion, idConfiguracion) values(-2,'LLANTA_DELANTERA','Llanta con disco', -1);
insert into PiezaBicicleta (id,tipo,descripcion, idConfiguracion) values(-3,'LLANTA_TRASERA','Llanta aleación', -1);
insert into PiezaBicicleta (id,tipo,descripcion, idConfiguracion) values(-4,'FRENOS','Freno convencional', -1);
insert into PiezaBicicleta (id,tipo,descripcion, idConfiguracion) values(-5,'TRANSMISION','18 pinones', -1);
