package Reto2.src;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * @author: Isaac Diaz G15
 * Reto 2: Inventario de una tienda
 */
class Reto2 {

	/**
	 * variables de clase
	 */
	static String[] respuesta = new String[4];

	private final static Scanner SCANNER = new Scanner(System.in);
	static String[] datos = new String[4];

	/**
	 *
	 * @return String del scanner
	 */
	public static String read() {
		// Objeto de tipo scanner
		return SCANNER.nextLine();
	}

	protected static class Globals {

		/**
		 * Listas para separar los datos
		 */
		public static List<String> nombres = new ArrayList<String>();
		public static List<Double> precios = new ArrayList<Double>();
		public static List<Integer> inventario = new ArrayList<Integer>();
		public static List<Integer> ids = new ArrayList<Integer>();
	}

	/**
	 * solucion propuesta
	 */
	public static void run() {
		// Se agregan los productos al HashMap

		Reto2.listaProductos.put(1, new Producto(1, "Manzanas", 6000.0, 97));
		Reto2.listaProductos.put(2, new Producto(2, "Limones", 2600.0, 45));
		Reto2.listaProductos.put(3, new Producto(3, "Peras", 2700.0, 55));
		Reto2.listaProductos.put(4, new Producto(4, "Arandanos", 7300.0, 44));
		Reto2.listaProductos.put(5, new Producto(5, "Tomates", 8100.0, 42));
		Reto2.listaProductos.put(6, new Producto(6, "Fresas", 9100.0, 99));
		Reto2.listaProductos.put(7, new Producto(7, "Helado", 4500.0, 41));
		Reto2.listaProductos.put(8, new Producto(8, "Galletas", 600.0, 18));
		Reto2.listaProductos.put(9, new Producto(9, "Chocolates", 4500.0, 990));
		Reto2.listaProductos.put(10, new Producto(10, "Jamon", 18000.0, 55));

		// Lectura de datos de entrada para realizar la accion pertinente
		String query = read();
		datos = read().split(" ");
		int id = Integer.parseInt(datos[0]);
		String prod = datos[1];
		double precio_prod = Double.parseDouble(datos[2]);
		int stock = Integer.parseInt(datos[3]);

		switch (query) {
		case "AGREGAR":
			if (!BaseDatosProductos.verificarExistencia(id)) {
				agregarProducto(id, prod, precio_prod, stock);
				BaseDatosProductos.generarInforme();
			} else {
				System.out.println("ERROR");
			}

			break;
		case "ACTUALIZAR":
			if (BaseDatosProductos.verificarExistencia(id)) {
				actualizarProducto(id, prod, precio_prod, id);
				BaseDatosProductos.generarInforme();
			} else {
				System.out.println("ERROR");
			}

			break;
		case "BORRAR":

			if (BaseDatosProductos.verificarExistencia(id)) {
				eliminarProducto(id);
				BaseDatosProductos.generarInforme();
			} else {
				System.out.println("ERROR");
			}

			break;

		}

	}

	/**
	 * Funcion que haya el nombre del producto con el precio menor
	 *
	 * @param precio_prods
	 * @return
	 */
	protected static String menor() {
		double menor = Globals.precios.get(0);
		for (double n : Globals.precios) {
			if (n < menor) {
				menor = n;
			}
		}
		return Globals.nombres.get(Globals.precios.indexOf(menor));
	}

	/**
	 * Funcion que haya el nombre del producto con el precio mayor
	 *
	 * @param precio_prods
	 * @return
	 */
	protected static String mayor() {
		double mayor = Globals.precios.get(0);
		for (double n : Globals.precios) {
			if (n > mayor) {
				mayor = n;
			}
		}
		return Globals.nombres.get(Globals.precios.indexOf(mayor));
	}

	/**
	 * Funcion que haya el promedio de precio entre los producto
	 *
	 * @param precio_prods
	 * @return
	 */
	protected static String promedio() {
		double suma = 0;
		for (double s : Globals.precios) {
			suma += s;
		}
		String promedio = String.format("%.01f", (suma / Globals.precios.size()));
		return promedio;
	}

	/**
	 * Funcion que haya el valor total del inventario
	 *
	 * @param precio_prods
	 * @param cantidad
	 * @return
	 */
	protected static String valTotal() {
		double suma = 0;
		for (int i = 0; i < Globals.precios.size(); i++) {
			suma += Globals.precios.get(i) * Globals.inventario.get(i);
		}

		String valTotal = String.format("%.01f", suma);
		return valTotal;
	}

	/**
	 * metodo que agrega un producto
	 *
	 * @param cod
	 * @param prd
	 * @param val
	 * @param inv
	 */
	private static void agregarProducto(int cod, String prd, double val, int inv) {
		Reto2.listaProductos.put(cod, new Producto(cod, prd, val, inv));
	}

	/**
	 * metodo que actualiza un producto
	 *
	 * @param cod
	 * @param prd
	 * @param val
	 * @param inv
	 */
	private static void actualizarProducto(int cod, String prd, double val, int inv) {
		Reto2.listaProductos.put(cod, new Producto(cod, prd, val, inv));
	}

	/**
	 * metodo que elimina un producto
	 *
	 * @param cod
	 */
	private static void eliminarProducto(int cod) {
		Reto2.listaProductos.remove(cod);
	}

	/**
	 * metodo main
	 */
	public static void main(String[] args) {

		run();

	}

	/**
	 * HashMap con los productos de la tienda
	 */
	protected static Map<Integer, Producto> listaProductos = new LinkedHashMap<Integer, Producto>();

}
