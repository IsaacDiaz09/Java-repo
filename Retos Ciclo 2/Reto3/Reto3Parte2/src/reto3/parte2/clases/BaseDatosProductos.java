package clases;

import java.util.LinkedHashMap;
import java.util.Map;

import clases.Reto3.Globals;

/**
 *
 * @author Isaac
 */
public class BaseDatosProductos {
	/**
	 * HashMap con los productos de la tienda
	 */
	public static Map<Integer, Producto> listaProductos = new LinkedHashMap<Integer, Producto>();

	/**
	 * Carga los datos en las listas para mantener actualizado el informe
	 */
	public static void cargarDatos() {
		limpiaDatos();
		for (Producto p : listaProductos.values()) {
			Globals.ids.add(p.getId());
			Globals.nombres.add(p.getName());
			Globals.precios.add(p.getPrice());
			Globals.inventario.add(p.getStock());
		}
	}

	/**
	 * Aqui se produce el informe a mostrar en un cuadro de JOptionPane -> prod
	 * precio menor, mayor, promedio y valor total.
	 */
	public static String generarInforme() {

		return "Producto precio mayor: " + Reto3.mayor() + "\n" 
		+ "Producto precio menor: " + Reto3.menor() + "\n"
		+ "Promedio de precios:   " + Reto3.promedio() + "\n"
		+ "Valor del inventario:  " + Reto3.valTotal();
	}

	/**
	 * Vacia las listas
	 */
	private static void limpiaDatos() {
		Globals.ids.clear();
		Globals.nombres.clear();
		Globals.precios.clear();
		Globals.inventario.clear();

	}

	/**
	 * Trae el id del producto de la fila seleccionada, para poder ejecutar las
	 * operaciones
	 * 
	 * @param prodSeleccionado
	 * @return -> int
	 */
	public static int getKey(Object prodSeleccionado) {
		return Globals.ids.get(Globals.nombres.indexOf(prodSeleccionado));
	}
}
