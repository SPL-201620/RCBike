package co.rcbike.derivador.main;

import java.util.HashSet;
import java.util.Set;


import co.rcbike.derivador.maven.MavenRunner;

public class Main {

	public static void main(String[] args){
		Set<String> modulos = new HashSet<>();
		modulos.add("base");
		
		/*if[AutenticacionLocal]*/
		modulos.add("autenticacion-local");
		/*end[AutenticacionLocal]*/
		/*if[AutenticacionFacebook]*/
		modulos.add("autenticacion-facebook");
		/*end[AutenticacionFacebook]*/
		/*if[AutenticacionTwitter]*/
		modulos.add("autenticacion-twitter");
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
		/*if[CompartirTwitter]*/
		modulos.add("sinc-redes");
		modulos.add("sinc-twitter");
		/*end[CompartirTwitter]*/
		
		/*if[CompartirFacebook]*/
		modulos.add("sinc-redes");
		modulos.add("sinc-facebook");
		/*end[CompartirFacebook]*/
		
		MavenRunner mavenRunner = new MavenRunner();
		mavenRunner.inkoveRcbikeParent(modulos);
	}
}
