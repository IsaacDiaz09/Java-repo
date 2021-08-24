package Reto3.Reto3Parte1.src;

import java.util.LinkedHashMap;
import java.util.Map;

class BaseDatosProductos {
	/**
	 * HashMap con los productos de la tiendaReto3.
	 */
	protected static Map<Integer, Producto> listaProductos = new LinkedHashMap<Integer, Producto>();

	/**
	 * Aqui se produce el informe a mostrar en pantalla -> El nombre de los tres
	 * productos con los precios mas altos, de mayor a menor
	 */
	public static void generarInforme() {
		// Se rellenan las listas estrictamente necesarias
		for (Producto p : listaProductos.values()) {
			Reto3.Globals.nombres.add(p.getName());
			Reto3.Globals.precios.add(p.getPrice());
		}

		
		String prodsMasCaros = "" ;
		int contador = 0;
		// Se ejecutara 3 veces porque queremos los 3 mas altos
		while (contador < 3) {
			// Se obtiene el producto actualmente mas caro y se añade a un String
			String prod = Reto3.mayor();
			// Se elimina dicho precio, para que el siguiente que se obtenga
			// sea el que esta por debajo de el
			Reto3.Globals.precios.remove(Reto3.Globals.nombres.indexOf(prod));
			Reto3.Globals.nombres.remove(prod);
			// Se aumenta la variable para no caer en un bucle infinito
			prodsMasCaros += prod + " ";
			contador++;
		}
		// Se muestran los tres productos, eliminando el ultimo espacio
		System.out.println(prodsMasCaros.strip());
	}

	/**
	 * 
	 * @param id
	 * @return -> boolean, verifica si un producto se escuentra ya en el HashMap
	 */
	protected static boolean verificarExistencia(int id) {
		return (listaProductos.containsKey(id));
	}
}
