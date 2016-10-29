package co.rcbike.desplazamientos.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Transformador {

	public static void asignarSuperAtributosPorNombre(Object origen, Object destino) {
		try {
			Class<?> claseOrigen = origen.getClass().getSuperclass();
			Class<?> claseDestino = destino.getClass().getSuperclass();

				Method[] metodosClaseDestino = claseDestino.getMethods();
				for (int i = 0; i < metodosClaseDestino.length; i++) {
					Method metodoClaseDestino = metodosClaseDestino[i];
					String nombreMetodoClaseDestino = metodoClaseDestino.getName();
					String tipoMetodoClaseDestino = nombreMetodoClaseDestino.substring(0, 3);
					String parteMetodoClaseDestino = nombreMetodoClaseDestino.substring(3);
					if (("set".equals(tipoMetodoClaseDestino)) && (metodoClaseDestino.getParameterTypes().length == 1)) {
						Class<?> parameterTypes[] = new Class[] {};
						Method metodoGetClaseDestino = null;
						try {
							metodoGetClaseDestino = claseOrigen.getMethod("get" + parteMetodoClaseDestino, parameterTypes);
						} catch (NoSuchMethodException e) {
							metodoGetClaseDestino = claseOrigen.getMethod("is" + parteMetodoClaseDestino, parameterTypes);
						}
						if (metodoGetClaseDestino != null) {
							Object parametrosGetObjeto[] = new Object[0];
							Object value = metodoGetClaseDestino.invoke(origen, parametrosGetObjeto);
							if (value != null) {
								if (metodoGetClaseDestino.getReturnType().isAssignableFrom(value.getClass())) {
									Object parametrosSetObjeto[] = new Object[1];
									parametrosSetObjeto[0] = value;
									metodosClaseDestino[i].invoke(destino, parametrosSetObjeto);
								}
							}
						}

					}
				}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//	public static <A, B> List<B> crearListObjetosDestino(List<A> listaObjetosOrigen, Class<B> claseDestino) {
//		List<B> result = null;
//		try {
//			if (listaObjetosOrigen != null) {
//				result = new ArrayList<>();
//				for (A objetoOrigen : listaObjetosOrigen) {
//					B objetoDestino = claseDestino.newInstance();
//					asignarSuperAtributosPorNombre(objetoOrigen, objetoDestino);
//					result.add(objetoDestino);
//				}
//			}
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return result;
//	}
//
//	public static <A, B> Set<B> crearSetObjetosDestino(Set<A> setObjetosOrigen, Class<B> claseDestino) {
//		Set<B> result = null;
//		try {
//			if (setObjetosOrigen != null) {
//				result = new HashSet<>();
//				for (A objetoOrigen : setObjetosOrigen) {
//					result.add(crearObjetoDestino(objetoOrigen, claseDestino));
//				}
//			}
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return result;
//	}

	public static <A, B> B crearObjetoDestino(A objetoOrigen, Class<B> claseDestino) {
		B result = null;
		try {
			result = claseDestino.newInstance();
			asignarSuperAtributosPorNombre(objetoOrigen, result);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
