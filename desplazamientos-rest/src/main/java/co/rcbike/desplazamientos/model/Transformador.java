package co.rcbike.desplazamientos.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Transformador {

	public static void asignarSuperAtributosPorNombre(Object origen, Object destino) {
		try {
			Class<?> superClaseOrigen = origen.getClass().getSuperclass();
			Class<?> superClaseDestino = destino.getClass().getSuperclass();

			if (superClaseOrigen.isAssignableFrom(superClaseDestino)) {
				Method[] methods = superClaseDestino.getMethods();
				for (int i = 0; i < methods.length; i++) {
					Method method = methods[i];
					String methodName = method.getName();
					String methodType = methodName.substring(0, 3);
					String methodPart = methodName.substring(3);
					if (("set".equals(methodType)) && (method.getParameterTypes().length == 1)) {
						Class<?> parameterTypes[] = new Class[] {};
						Method getMethod = null;
						try {
							getMethod = superClaseOrigen.getMethod("get" + methodPart, parameterTypes);
						} catch (NoSuchMethodException e) {
							getMethod = superClaseOrigen.getMethod("is" + methodPart, parameterTypes);
						}
						if (getMethod != null) {
							Object parametrosGetObjeto[] = new Object[0];
							Object value = getMethod.invoke(origen, parametrosGetObjeto);

							Object parametrosSetObjeto[] = new Object[1];
							parametrosSetObjeto[0] = value;
							methods[i].invoke(destino, parametrosSetObjeto);
						}

					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <A, B> List<B> crearListObjetosDestino(List<A> listaObjetosOrigen, Class<B> claseDestino) {
		List<B> result = null;
		try {
			if (listaObjetosOrigen != null) {
				result = new ArrayList<>();
				for (A objetoOrigen : listaObjetosOrigen) {
					B objetoDestino = claseDestino.newInstance();
					asignarSuperAtributosPorNombre(objetoOrigen, objetoDestino);
					result.add(objetoDestino);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public static <A, B> Set<B> crearSetObjetosDestino(Set<A> setObjetosOrigen, Class<B> claseDestino) {
		Set<B> result = null;
		try {
			if (setObjetosOrigen != null) {
				result = new HashSet<>();
				for (A objetoOrigen : setObjetosOrigen) {
					B objetoDestino = claseDestino.newInstance();
					asignarSuperAtributosPorNombre(objetoOrigen, objetoDestino);
					result.add(objetoDestino);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

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
