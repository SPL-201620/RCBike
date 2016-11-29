package co.rcbike.derivador.maven;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.regex.Pattern;

public class RcbikeWebPomProcessor {

	public String template = "<!-- if %1$s -->(?s).*<!-- fi %1$s -->";

	public void parsePom(Collection<String> modulos) throws IOException {
		System.out.println("Procesando POM rcbike-web");
		Path path = Paths.get("../rcbike-web/pom.xml");
		final String pomContent = readFile(path, StandardCharsets.UTF_8);
		String derivated = pomContent;
		for (String modulo : modulos) {
			Pattern p = Pattern.compile(String.format(template, modulo));
			derivated = p.matcher(derivated).replaceAll("");
		}
		Files.write(Paths.get("../rcbike-web/pom-derivado.xml"), derivated.getBytes(StandardCharsets.UTF_8));
		System.out.println("Generado POM de rcbike-web pom-derivado.xml");
	}

	String readFile(Path path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(path);
		return new String(encoded, encoding);
	}

}
