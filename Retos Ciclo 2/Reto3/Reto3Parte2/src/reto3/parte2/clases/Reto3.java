package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import interfaz.Gui;
import interfaz.VentanaActualizarProd;

/**
 * 
 * @author Isaac
 * Reto 3 Ciclo de Java (2) misionTic2022
 */
public class Reto3 {
	
	/**
	 * Separe los datos de los productos en listas para trabajarlos a manera mas
	 * grafica, los cuales se van actualizando despues de que se realiza alguna
	 * operacion.
	 */

	public static class Globals {

		public static List<String> nombres = new ArrayList<String>();
		public static List<Double> precios = new ArrayList<Double>();
		public static List<Integer> inventario = new ArrayList<Integer>();
		public static List<Integer> ids = new ArrayList<Integer>();
	}

	/**
	 * solucion propuesta
	 */
	public static void run() {
		BaseDatosProductos.listaProductos.put(1, new Producto(1, "Manzanas", 6000.0, 97));
		BaseDatosProductos.listaProductos.put(2, new Producto(2, "Limones", 2600.0, 45));
		BaseDatosProductos.listaProductos.put(3, new Producto(3, "Peras", 2700.0, 55));
		BaseDatosProductos.listaProductos.put(4, new Producto(4, "Arandanos", 7300.0, 44));
		BaseDatosProductos.listaProductos.put(5, new Producto(5, "Tomates", 8100.0, 42));
		BaseDatosProductos.listaProductos.put(6, new Producto(6, "Fresas", 9100.0, 99));
		BaseDatosProductos.listaProductos.put(7, new Producto(7, "Helado", 4500.0, 41));
		BaseDatosProductos.listaProductos.put(8, new Producto(8, "Galletas", 600.0, 18));
		BaseDatosProductos.listaProductos.put(9, new Producto(9, "Chocolates", 4500.0, 990));
		BaseDatosProductos.listaProductos.put(10, new Producto(10, "Jamon", 18000.0, 55));
		BaseDatosProductos.cargarDatos();
		Gui ventanaReto3 = new Gui();
		ventanaReto3.setVisible(true);
	}

	/**
	 * Funcion que haya el nombre del producto con el menor precio
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
	 * Funcion que haya el nombre del producto con el mayor precio
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
	 * Funcion que haya el promedio de precio entre los productos
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
	public static void agregarProducto(String prd, String val, String inv) {
		// Se verifica que se hayan llenado todos los campos
		if (Gui.verificaCampos()) {
			// Id auto incremental
			int id = Collections.max(Globals.ids) + 1;
			double valor = Double.parseDouble(val);
			int stock = Integer.parseInt(inv);
			// Si ya existe un producto que se llame igual no lo agrega
			if (!(Globals.nombres.contains(prd))) {
				BaseDatosProductos.listaProductos.put(id, new Producto(id, prd, valor, stock));
				// Despues de agregar el Producto al HashMap se actualiza el JTable
				Gui.vaciarJTable();
				BaseDatosProductos.cargarDatos();
				Gui.cargarJTable();
				// Se limpian los campos despues que agregar fue exitoso
				limpiaCampos();
			} else {
				JOptionPane.showMessageDialog(null, "El producto '"+prd+"' ya existe", "Error",
				JOptionPane.ERROR_MESSAGE, null);
				limpiaCampos();
			}

		} else {
			// Se limpian los campos si la verificacion arrojo false
			limpiaCampos();
		}

	}
	/**
	 * Vacia los campos de texto
	 */
	private static void limpiaCampos() {
		Gui.txtNombre.setText("");
		Gui.txtInventario.setText("");
		Gui.txtPrecio.setText("");
		Gui.txtNombre.grabFocus();
	}
	/**
	 * metodo que actualiza un producto
	 *
	 * @param cod
	 * @param prd
	 * @param val
	 * @param inv
	 */
	public static void actualizarProducto(int cod, String prd, String val, String inv) {
		// Verificacion de los campos
			if (VentanaActualizarProd.verificaCamposActualizar()) {
				double valor = Double.parseDouble(val);
				int inventario = Integer.parseInt(inv);
				BaseDatosProductos.listaProductos.put(cod, new Producto(cod, prd, valor, inventario));
				// Actualiza el JTable despues de realizar la operacion
				Gui.vaciarJTable();
				BaseDatosProductos.cargarDatos();
				Gui.cargarJTable();
				Gui.ventanaActualizar.dispose();
				// Limpiar los campos de texto ya que en realidad no cierra la ventana, la oculta
				VentanaActualizarProd.txtNuevoNombre.setText("");
				VentanaActualizarProd.txtNuevoInventario.setText("");
				VentanaActualizarProd.txtNuevoPrecio.setText("");
			} else {
				// Limpia los campos si falto alguno por llenar
				VentanaActualizarProd.txtNuevoNombre.setText("");
				VentanaActualizarProd.txtNuevoInventario.setText("");
				VentanaActualizarProd.txtNuevoPrecio.setText("");
			
		}
	}

	/**
	 * metodo que elimina un producto
	 *
	 * @param cod
	 */
	public static void eliminarProducto(int cod) {
		// Se verifica que se haya seleccionado alguna fila en el JTable
		if (Gui.verificaSeleccion()) {
			BaseDatosProductos.listaProductos.remove(cod);
			// Si es asi, realiza la operacion y actualiza
			Gui.vaciarJTable();
			BaseDatosProductos.cargarDatos();
			Gui.cargarJTable();
			// Muestra un mensaje de aviso de operacion exitosa
			JOptionPane.showMessageDialog(null, "El producto fue borrado exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE, null);

		}
	}

	/**
	 * metodo main
	 */
	public static void main(String[] args) {
		run();

	}

}
