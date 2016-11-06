package co.rcbike.web.util;

import java.util.List;

import javax.ws.rs.core.GenericType;

import co.rcbike.configurador_bici.model.ColorWeb;
import co.rcbike.configurador_bici.model.ConfiguracionWeb;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.usuarios.model.Usuario;

public final class UtilRest {

	private UtilRest() {
	}

	public static final GenericType<List<String>> TYPE_LIST_STRING = new GenericType<List<String>>() {
	};

	public static final GenericType<List<Usuario>> TYPE_LIST_USUARIO = new GenericType<List<Usuario>>() {
	};

	public static final GenericType<List<PiezaWeb>> TYPE_LIST_PIEZAS = new GenericType<List<PiezaWeb>>() {
	};

	public static final GenericType<PiezaWeb> TYPE_PIEZA = new GenericType<PiezaWeb>() {
	};

	public static final GenericType<List<ColorWeb>> TYPE_LIST_COLOR = new GenericType<List<ColorWeb>>() {
	};

	public static final GenericType<List<ConfiguracionWeb>> TYPE_LIST_CONFIGURACIONES = new GenericType<List<ConfiguracionWeb>>() {
	};
}
