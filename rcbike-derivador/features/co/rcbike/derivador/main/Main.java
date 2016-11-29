package co.rcbike.derivador.main;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import co.rcbike.derivador.maven.MavenRunner;

public class Main {

	public static void main(String[] args){
		Set<String> modulos = new HashSet<>();
		Properties funciones = new Properties();
		modulos.add("base");
		
		/*if[AutenticacionLocal]*/
		modulos.add("autenticacion-local");
		/*end[AutenticacionLocal]*/
		/*if[AutenticacionFacebook]*/
		funciones.put("autenticacion-facebook","true");
		/*end[AutenticacionFacebook]*/
		/*if[AutenticacionTwitter]*/
		funciones.put("autenticacion-twitter","true");
		/*end[AutenticacionTwitter]*/
		
		/*if[AlquilerBicicleta]*/
		modulos.add("alquiler");
		/*end[AlquilerBicicleta]*/
		/*if[ConfiguradorBicicleta]*/
		modulos.add("configurador");
		/*end[ConfiguradorBicicleta]*/
		/*if[Desplazamientos]*/
		modulos.add("desplazamientos");
		/*end[Desplazamientos]*/
		/*if[Mensajeria]*/
		modulos.add("mensajeria");
		/*end[Mensajeria]*/
		/*if[Reportes]*/
		modulos.add("reportes");
		/*end[Reportes]*/
		/*if[VentaBicicleta]*/
		modulos.add("ventas");
		/*end[VentaBicicleta]*/

		/*if[TwitterRest]*/
		modulos.add("sinc-redes");
		funciones.put("twitter-rest","true");
		/*end[TwitterRest]*/
		/*if[TwitterWeb]*/
		funciones.put("twitter-web","true");
		/*end[TwitterWeb]*/

		/*if[FacebookRest]*/
		modulos.add("sinc-redes");
		funciones.put("facebook-rest","true");
		/*end[FacebookRest]*/
		/*if[FacebookWeb]*/
		funciones.put("facebook-web","true");
		/*end[FacebookWeb]*/

		
			
		MavenRunner mavenRunner = new MavenRunner();
		mavenRunner.inkoveRcbikeParent(modulos,funciones);
	}
}
