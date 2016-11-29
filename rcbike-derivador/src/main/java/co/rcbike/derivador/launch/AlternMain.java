package co.rcbike.derivador.launch;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import co.rcbike.derivador.maven.MavenRunner;
import co.rcbike.derivador.maven.RcbikeWebPomProcessor;

public class AlternMain {

	public static void main(String[] args) throws IOException {
		Set<String> modulos = new HashSet<>();
		Properties funciones = new Properties();
		modulos.add("rcbike-derivado");

		funciones.put("autenticacion-facebook", "true");

		funciones.put("autenticacion-twitter", "true");

		modulos.add("alquiler");

		modulos.add("configurador");

		modulos.add("desplazamientos");

		modulos.add("mensajeria");

		modulos.add("reportes");

		modulos.add("ventas");

		modulos.add("sinc-redes");
		funciones.put("twitter-rest", "true");

		funciones.put("twitter-web", "true");

		modulos.add("sinc-redes");
		funciones.put("facebook-rest", "true");

		funciones.put("facebook-web", "true");

		MavenRunner mavenRunner = new MavenRunner();
		RcbikeWebPomProcessor.parsePom(modulos);
		mavenRunner.inkoveRcbikeParent(modulos, funciones);
	}
}
