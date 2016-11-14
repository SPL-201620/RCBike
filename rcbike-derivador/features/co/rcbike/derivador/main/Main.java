package co.rcbike.derivador.main;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;

public class Main {

	public static void main(String[] args){
		List<String> modulos = new ArrayList<>();
		/*if[PerfilUsuario]*/
		System.out.print("PerfilUsuario");
		/*end[PerfilUsuario]*/
		
		/*if[AutenticacionLocal]*/
		System.out.print("autenticacion-local");
		/*end[AutenticacionLocal]*/
		/*if[AutenticacionFacebook]*/
		System.out.print("autenticacion-facebook");
		/*end[AutenticacionFacebook]*/
		/*if[AutenticacionTwitter]*/
		System.out.print("autenticacion-twitter");
		/*end[AutenticacionTwitter]*/
		
		
		/*if[AlquilerBicicleta]*/
		System.out.print("alquiler");
		/*end[AlquilerBicicleta]*/

		/*if[ConfiguradorBicicleta]*/
		System.out.print("configurador");
		/*end[ConfiguradorBicicleta]*/
		
		/*if[Desplazamientos]*/
		System.out.print("desplazamientos");
		/*end[Desplazamientos]*/

		/*if[Mensajeria]*/
		System.out.print("mensajeria");
		/*end[Mensajeria]*/
		
		/*if[Reportes]*/
		System.out.print("reportes");
		/*end[Reportes]*/

		/*if[VentaBicicleta]*/
		System.out.print("venta");
		/*end[VentaBicicleta]*/

		
		/*if[CompartirTwitter]*/
		System.out.print("compartir-twitter");
		/*end[CompartirTwitter]*/
		
		/*if[CompartirFacebook]*/
		System.out.print("compartir-facebook");
		/*end[CompartirFacebook]*/
		
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile( new File( "/path/to/pom.xml" ) );
		request.setGoals( Collections.singletonList( "install" ) );

		Invoker invoker = new DefaultInvoker();
		//invoker.execute( request );
	}
}
