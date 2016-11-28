package co.rcbike.configurador_bici.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ValidadorConfiguracion {

	private static final String CONFIGURACIONES_VALIDAS_TXT = "ConfiguracionesValidas.txt";
	private List<List<String>> configuracionesValidas;

	public boolean validar(String configuracion) {
		return validar(getListFromString(configuracion));
	}

	public boolean validar(List<String> configuracion) {
		boolean result = false;
		if (configuracion != null) {
			for (List<String> configuracionValida : getConfiguracionesValidas()) {
				boolean existenTodasCaracteristicasValidas = true;
				for (String caracteristicaValida : configuracionValida) {
					boolean existeCaracteristicaValida = false;
					for (String caracteristica : configuracion) {
						if (caracteristicaValida.equalsIgnoreCase(caracteristica)) {
							existeCaracteristicaValida = true;
							break;
						}
					}
					if (!existeCaracteristicaValida) {
						existenTodasCaracteristicasValidas = false;
						break;
					}
				}
				if (existenTodasCaracteristicasValidas && (configuracion.size() == configuracionValida.size())) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		ValidadorConfiguracion validador = new ValidadorConfiguracion();
		validador.getConfiguracionesValidas();
		boolean resultado = validador
				.validar(validador.getListFromString("Candela, TiroCentral, Bmx, Clicher, Externos, Sprint"));
		System.out.println(resultado);
	}

	public List<String> getListFromString(String str) {
		ArrayList<String> result = new ArrayList<String>();
		String[] split = str.split(",");
		for (String string : split) {
			result.add(string.trim());
		}
		return result;
	}

	public List<List<String>> getConfiguracionesValidas() {
		if (configuracionesValidas == null) {
			configuracionesValidas = new ArrayList<List<String>>();
			try {
				ClassLoader classLoader = getClass().getClassLoader();
				File file = new File(classLoader.getResource(CONFIGURACIONES_VALIDAS_TXT).getFile());
				FileReader fileReader = new FileReader(file);
				BufferedReader br = new BufferedReader(fileReader);
				try {
					String line;
					while ((line = br.readLine()) != null) {
						configuracionesValidas.add(getListFromString(line));
					}

				} finally {
					br.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return configuracionesValidas;
	}

	public void setConfiguracionesValidas(List<List<String>> configuracionesPosibles) {
		this.configuracionesValidas = configuracionesPosibles;
	}

}
