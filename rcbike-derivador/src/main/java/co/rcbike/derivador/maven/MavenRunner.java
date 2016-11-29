package co.rcbike.derivador.maven;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class MavenRunner {

	private String banner = "derivador rcbike";

	public MavenRunner() {
		URL url = Resources.getResource("banner.txt");
		try {
			banner = Resources.toString(url, Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(banner);
	}

	private Properties buildProperties(Properties propiedades) {
		if (propiedades == null) {
			propiedades = new Properties();
		}
		propiedades.put("rcbike-derivator", "true");
		return propiedades;
	}

	public void inkoveRcbikeParent(Collection<String> modulos, Properties propiedades) {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File("../rcbike-parent/pom.xml"));
		request.setGoals(Arrays.asList("clean", "package"));
		request.setProfiles(new ArrayList<String>(modulos));
		request.setThreads("6");
		request.setProperties(buildProperties(propiedades));
		Invoker invoker = new DefaultInvoker();
		try {
			invoker.execute(request);
		} catch (MavenInvocationException e) {
			e.printStackTrace();
		}
	}
}
