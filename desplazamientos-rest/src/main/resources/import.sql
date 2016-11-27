
-- You can use this file to load seed data into the database using SQL statements
-- Rutas
--insert into Ruta (id,emailCreador,tipo,latitudInicio,longitudInicio,latitudFinal,longitudFinal,distancia,tiempoEstimado,calorias,clima,fecha,frecuente) values(0,'rcb1@m.co','INDIVIDUAL','4.679566','-74.038578','4.673995','-74.042376',100.0,29,33,'21ºC',{ts '2017-09-17 18:47:52.69'},false);
--insert into Ruta (id,emailCreador,tipo,latitudInicio,longitudInicio,latitudFinal,longitudFinal,distancia,tiempoEstimado,calorias,clima,fecha,frecuente) values(1,'rcb2@m.co','GRUPAL','4.679566','-74.038578','4.673995','-74.042376',100.0,29,33,'16ºC',{ts '2017-09-17 18:47:52.69'},false);

--insert into Ruta (id,nombre,descripcion,emailCreador,tipo,latitudInicio,longitudInicio,latitudFinal,longitudFinal,distancia,tiempoEstimado,calorias,clima,fecha,frecuente) values(-1,'Ruta 1', 'Ruta 1','maria.a@email.com','INDIVIDUAL',4.614499,-74.091609,4.633060,-74.070538,100.0,29,33,'12.040009 ºC, nubes dispersas, CO.',{ts '2016-10-24 18:47:52.69'},'N');
insert into Ruta (id,nombre,descripcion,emailCreador,tipo,latitudInicio,longitudInicio,latitudFinal,longitudFinal,distancia,tiempoEstimado,calorias,clima,fecha,frecuente) values(-1,'Ruta 1', 'Ruta 1','rcb1@m.co','INDIVIDUAL',4.614499,-74.091609,4.633060,-74.070538,100.0,29,33,'12.040009 ºC, nubes dispersas, CO.',{ts '2016-10-26 18:47:52.69'},'N');
insert into Ruta (id,nombre,descripcion,emailCreador,tipo,latitudInicio,longitudInicio,latitudFinal,longitudFinal,distancia,tiempoEstimado,calorias,clima,fecha,frecuente) values(-2,'Ruta 2', 'Ruta 2','rcb1@m.co','GRUPAL',4.603195,-74.065271,4.594787,-74.077964,100.0,29,33,'12.040009 ºC, nubes dispersas, CO.',{ts '2016-10-25 18:47:52.69'},'S');
insert into Ruta (id,nombre,descripcion,emailCreador,tipo,latitudInicio,longitudInicio,latitudFinal,longitudFinal,distancia,tiempoEstimado,calorias,clima,fecha,frecuente) values(-3,'Ruta 3', 'Ruta 3','rcb1@m.co','INDIVIDUAL',4.614499,-74.091609,4.633060,-74.070538,100.0,71,77,'12.040009 ºC, nubes dispersas, CO.',{ts '2016-10-26 18:47:52.69'},'N');

-- WayPoints
insert into Waypoint (id,idRuta,latitud,longitud) values (-1,-1,4.610689,-74.090426);
insert into Waypoint (id,idRuta,latitud,longitud) values (-2,-1,4.619574,-74.087576);
insert into Waypoint (id,idRuta,latitud,longitud) values (-3,-1,4.624982,-74.083328);
insert into Waypoint (id,idRuta,latitud,longitud) values (-4,-1,4.628634,-74.080409);
insert into Waypoint (id,idRuta,latitud,longitud) values (-5,-1,4.632171,-74.079961);
insert into Waypoint (id,idRuta,latitud,longitud) values (-6,-1,4.634213,-74.079774);
insert into Waypoint (id,idRuta,latitud,longitud) values (-7,-1,4.634312,-74.077397);

--Participantes
insert into Participante (id, idRuta, email) values (-1, -2, 'ja.velandia@correo.com');
insert into Participante (id, idRuta, email) values (-2, -2, 'maria.a@email.com');
insert into Participante (id, idRuta, email) values (-3, -2, 'miguel.espitia@messages.co');