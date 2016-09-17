# Bienvenidos a RCBike wiki!

![logo](http://i67.tinypic.com/20f6lps.jpg)

## Integrantes
* Armando Perea - _Líder del Grupo_
* Juan Carlos Cifuentes Murcia - _Líder de la Arquitectura_
* Raúl Gómez - _Líder del desarrollo de productos_
* Felipe Martínez - _Líder del desarrollo de activos y administración de la configuración_

## Desarrollo
### Recursos
* Base de datos h2: http://www.h2database.com/html/features.html
* Eclipse + Git : 
https://www.youtube.com/watch?v=rblGZRWqFVI
https://www.youtube.com/watch?v=KfeqnernMmE
### Compilacion y ejecucion
Requisitos:
1. instalacion de maven (ultima version disponible)
2. instalacion de Wildlfy

RCBike consta de un proyecto parent y multiples proyectos web, descritos de la siguiente manera:
1. *-rest*: modulos que exponen los servicios rest de la logica de negocio
2. *-web*: modulo de capa web que integra las funcionalidades de backend
3. *-parent*: modulo que controla el manejo de versiones, y el ciclo de compilacion y despliegue

La compilacion e instalacion en el repositorio de maven se puede hacer desde el parent o desde cada proyecto individual

		mvn clean install
        
El despliegue de los modulos tambien se puede hacer a traves de maven, *para esto se requiere que una instancia de Wildfly se encuentre iniciada en el sistema*. Si el comando se ejecuta desde el parent, desplegara todos los modulos, si se realiza individualmente despliega el modulo correspondiente: 

		mvn clean wildfly:deploy -T 5
        
Para desinstalar la aplicacion del servidor:

		mvn wildfly:undeploy -T 5
        
Cada uno de los modulos se desplegara en:

		http://localhost:8080/<nombre-proyecto>-rest/
	
Para facilitar la ejecucion y pruebas sugiero instalar una herramienta para enviar peticiones http, para firefox pueden usar [HttpRequester](https://addons.mozilla.org/en-US/firefox/addon/httprequester/)

Adicional a los proyectos de RCBike se requiere desplegar el directorio de servicios que se ejecuta desde el parent con el comando:

		mvn install -Pdeploy-dir