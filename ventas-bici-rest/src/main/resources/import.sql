-- You can use this file to load seed data into the database using SQL statements
insert into VentaBicicleta (id,emailCreador,valor,vendida,marca,ciudadVenta,estado,accesorios,observaciones) values(-1,'maria.a@email.com', 100000,'N','Benotto','Bogota','BUENO','Luz delantera','Perfecto estado');
insert into PiezaVentaBicicleta (id,tipo,descripcion, idVenta) values(-1,'MARCO','Marco hiper resistente', -1);
insert into PiezaVentaBicicleta (id,tipo,descripcion, idVenta) values(-2,'LLANTA_DELANTERA','Llanta con disco', -1);
insert into PiezaVentaBicicleta (id,tipo,descripcion, idVenta) values(-3,'LLANTA_TRASERA','Llanta aleacion', -1);
insert into PiezaVentaBicicleta (id,tipo,descripcion, idVenta) values(-4,'FRENOS','Freno convencional', -1);
insert into PiezaVentaBicicleta (id,tipo,descripcion, idVenta) values(-5,'TRANSMISION','18 pinones', -1);
