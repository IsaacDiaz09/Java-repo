package com.reto5.app_inventario.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto5.app_inventario.modelo.Producto;
import com.reto5.app_inventario.modelo.RepositorioProducto;


/**
 * @author: Isaac Diaz
 * Se implementan los metodos anteriormente existentes de la clase CrudRepository
 */

@Service
public class ControladorProducto implements RepositorioProducto {

	/**
	 * Inyecta la dependencia para poder trabajar con ella
	 */
	@Autowired
	private  RepositorioProducto repositorioProducto;

	public ControladorProducto(RepositorioProducto repo) {
		this.repositorioProducto = repo;
	}

	public ControladorProducto() {
	}

	@Override
	public <S extends Producto> Iterable<S> saveAll(Iterable<S> entities) {
		return repositorioProducto.saveAll(entities);
	}

	@Override
	public Optional<Producto> findById(Integer id) {

		return repositorioProducto.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {

		return repositorioProducto.existsById(id);
	}

	@Override
	public Iterable<Producto> findAll() {

		return repositorioProducto.findAll();
	}

	@Override
	public Iterable<Producto> findAllById(Iterable<Integer> ids) {

		return repositorioProducto.findAllById(ids);
	}

	@Override
	public long count() {
		return repositorioProducto.count();
	}

	@Override
	public void deleteById(Integer id) {
		repositorioProducto.deleteById(id);

	}

	@Override
	public void delete(Producto entity) {
		repositorioProducto.delete(entity);

	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		repositorioProducto.deleteAllById(ids);

	}

	@Override
	public void deleteAll(Iterable<? extends Producto> entities) {
		repositorioProducto.deleteAll(entities);

	}

	@Override
	public void deleteAll() {
		repositorioProducto.deleteAll();
	}

	@Override
	public <S extends Producto> S save(S entity) {
		return repositorioProducto.save(entity);
	}

	
	
}
package com.reto5.app_inventario.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Service;

import com.reto5.app_inventario.modelo.Producto;
import com.reto5.app_inventario.modelo.RepositorioProducto;
import com.reto5.app_inventario.vista.Gui;
import com.reto5.app_inventario.vista.VentanaActualizarProd;

/**
 * @author: Isaac Diaz
 */

@Service
public class ControladorVentana implements ActionListener, MouseListener {
	/**
	 * Inicializacion de la variable que tomara el valor del id seleccionado
	 */
	public static int prodSeleccionado = -1;
	/**
	 * Para dar formato a los calculos de promedio y valor total
	 */
	private final DecimalFormat DF = new DecimalFormat("#.0");
	/**
	 * Se define global porque llevara el nombre del producto seleccionado a la
	 * ventana hija
	 */
	public static String nombreProd = "";
	/**
	 * Inicializacion de la ventana principal de Swing
	 */
	public static Gui ventana;
	/**
	 * Inicializacion del repositorio para ejecutar los metodos crud
	 */
	private static RepositorioProducto repo;

	/**
	 * Constructor vacio necesario para poder crear los objetos
	 */
	public ControladorVentana() {
	}

	/**
	 * Constructor para asignar el repositorio y traer la vista
	 * 
	 * @param repositorio
	 * @param vista
	 */
	public ControladorVentana(RepositorioProducto repositorio, Gui vista) {
		ControladorVentana.repo = repositorio;
		ControladorVentana.ventana = vista;
		repo.deleteAll();
		productosIniciales();
		listarJTable();
		agregaEventos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventana.getAgregaProd()) {
			if (verificaCampos()) {
				agregaProducto();
				listarJTable();

			} else {
				JOptionPane.showMessageDialog(ventana, "Todos los campos son obligatorios", "Advertencia",
						JOptionPane.WARNING_MESSAGE, null);
				limpiaCamposTxt();
			}

		}

		if (e.getSource() == ventana.getActualizaProd()) {
			if (verificaSeleccion()) {
				VentanaActualizarProd ventanaActualizar = new VentanaActualizarProd();
				ControladorVentanaActualizar controlador = new ControladorVentanaActualizar(repo, ventanaActualizar);
				ventanaActualizar.setControlador(controlador);
				ventanaActualizar.setVisible(true);
			}

		}

		if (e.getSource() == ventana.getEliminaProd()) {
			if (verificaSeleccion()) {
				repo.deleteById(prodSeleccionado);
				listarJTable();
				JOptionPane.showMessageDialog(ventana, "Producto eliminado exitosamente", "Informaci?n",
						JOptionPane.INFORMATION_MESSAGE, null);
			}

		}
		if (e.getSource() == ventana.getInformeBtn()) {
			JOptionPane.showMessageDialog(ventana, generarInforme(), "Informe", JOptionPane.INFORMATION_MESSAGE, null);
		}

	}

	public void agregaEventos() {
		ventana.getAgregaProd().addActionListener(this);
		ventana.getActualizaProd().addActionListener(this);
		ventana.getEliminaProd().addActionListener(this);
		ventana.getInformeBtn().addActionListener(this);
		ventana.getTable().addMouseListener(this);
	}

	private void limpiaCamposTxt() {
		ventana.getTxtNombre().setText("");
		ventana.getTxtPrecio().setText("");
		ventana.getTxtInventario().setText("");
		ventana.getTxtNombre().grabFocus();
	}

	private boolean verificaCampos() {
		if (ventana.getTxtNombre().getText().isBlank() || ventana.getTxtPrecio().getText().isBlank()
				|| ventana.getTxtInventario().getText().isBlank()) {
			return false;
		}
		return true;

	}

	private void productosIniciales() {
		repo.save(Producto.crearProducto("Manzanas", 6000.0, 97));
		repo.save(Producto.crearProducto("Limones", 2600.0, 45));
		repo.save(Producto.crearProducto("Peras", 2700.0, 55));
		repo.save(Producto.crearProducto("Arandanos", 7300.0, 44));
		repo.save(Producto.crearProducto("Tomates", 8100.0, 42));
		repo.save(Producto.crearProducto("Fresas", 9100.0, 99));
		repo.save(Producto.crearProducto("Helado", 4500.0, 41));
		repo.save(Producto.crearProducto("Galletas", 600.0, 18));
		repo.save(Producto.crearProducto("Chocolates", 4500.0, 990));
		repo.save(Producto.crearProducto("Jamon", 18000.0, 55));
	}

	private void agregaProducto() {
		try {
			String nombreProd = ventana.getTxtNombre().getText();
			float precioProd = Float.parseFloat(ventana.getTxtPrecio().getText());
			int inventarioProd = Integer.valueOf(ventana.getTxtInventario().getText());

			Producto producto = Producto.crearProducto(nombreProd, precioProd, inventarioProd);
			repo.save(producto);
			limpiaCamposTxt();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(ventana, "Ha introducido valores invalidos\nIntente nuevamente",
					"Advertencia", JOptionPane.WARNING_MESSAGE, null);
			limpiaCamposTxt();
		}
	}

	public static void listarJTable() {
		DefaultTableModel modelo = ventana.getModel();
		modelo.setRowCount(0);
		for (Producto p : (List<Producto>) repo.findAll()) {
			Object[] fila = { p.getName(), p.getPrice(), p.getStock() };
			modelo.addRow(fila);
		}
	}

	/**
	 * Regresa el id del producto seleccionado cuando se hace click sobre su fila
	 * 
	 * @return id -> int
	 */
	private void prodSeleccionado() {
		int fila = ventana.getTable().getSelectedRow();
		nombreProd = ventana.getTable().getValueAt(fila, 0).toString();

		List<Integer> ids = new ArrayList<Integer>();
		List<String> nombres = new ArrayList<String>();

		for (Producto p : (List<Producto>) repo.findAll()) {
			ids.add(p.getId());
			nombres.add(p.getName());
		}
		prodSeleccionado = ids.get(nombres.indexOf(nombreProd));
	}

	/**
	 * Verifica que se haya seleccionado alguna fila en el JTable, muestra un
	 * mensaje de error de ser asi
	 * 
	 * @return -> boolean
	 */
	public boolean verificaSeleccion() {

		if (ventana.getTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Primero debe seleccionar un producto", "Error",
					JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		prodSeleccionado();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Metodo que genera el informe sobre los productos
	 * 
	 * @return -> un string
	 */
	private String generarInforme() {
		return "Producto precio mayor: " + mayorYmenor()[0] + "\n" + "Producto precio menor: " + mayorYmenor()[1] + "\n"
				+ "Promedio de precios:   " + promedio() + "\n" + "Valor del inventario:  " + valTotal();
	}

	/**
	 * Metodo que halla el nombre del producto con el precio mayor y menor
	 * 
	 * @return retorna un array
	 */
	private String[] mayorYmenor() {
		List<Double> preciosProd = new ArrayList<Double>();
		List<String> nombresProd = new ArrayList<String>();
		String[] arr = new String[2];
		// Se rellenan las listas
		for (Producto p : (List<Producto>) repo.findAll()) {
			preciosProd.add(p.getPrice());
			nombresProd.add(p.getName());
		}
		// mayor
		arr[0] = nombresProd.get(preciosProd.indexOf(Collections.max(preciosProd)));
		// menor
		arr[1] = nombresProd.get(preciosProd.indexOf(Collections.min(preciosProd)));

		return arr;
	}

	/**
	 * Metodo que haya el promedio entre los precios de los productos actuales en la
	 * tabla
	 * 
	 * @return retorna el promedio de los productos como string
	 */
	private String promedio() {
		double suma = 0;
		for (Producto p : repo.findAll()) {
			suma += p.getPrice();
		}

		return DF.format(suma / repo.count());
	}

	/**
	 * Metodo que haya el valor total de los productos actuales en la tabla
	 * 
	 * @return retorna el valor total en un string
	 */
	private String valTotal() {
		double suma = 0;
		for (Producto p : repo.findAll()) {
			suma += p.getPrice() * p.getStock();
		}

		return DF.format(suma / repo.count());
	}

}
package com.reto5.app_inventario.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import com.reto5.app_inventario.modelo.Producto;
import com.reto5.app_inventario.modelo.RepositorioProducto;
import com.reto5.app_inventario.vista.VentanaActualizarProd;

/**
 * @author: Isaac Diaz
 */

@Service
public class ControladorVentanaActualizar implements ActionListener {

	private VentanaActualizarProd ventanaHija;

	private RepositorioProducto repo;

	/**
	 * Constructor de clase
	 * 
	 * @param repositorio
	 * @param vntActualizar
	 */
	public ControladorVentanaActualizar(RepositorioProducto repositorio, VentanaActualizarProd vntActualizar) {
		this.repo = repositorio;
		this.ventanaHija = vntActualizar;
		agregaEventos();
	}
	/**
	 * Constructor vacio
	 */
	public ControladorVentanaActualizar() {}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaHija.getBtnActualizaProd()) {
			actualizarProducto();
			ControladorVentana.listarJTable();
		}
	}

	private void agregaEventos() {
		ventanaHija.getBtnActualizaProd().addActionListener(this);
	}

	/**
	 * actualiza un producto
	 */
	private void actualizarProducto() {

		if (verificaCampos()) {
			try {
				String nuevoNombre = ventanaHija.getTxtNuevoNombre().getText();
				float nuevoPrecio = Float.parseFloat(ventanaHija.getTxtNuevoInventario().getText());
				int nuevoInventario = Integer.valueOf(ventanaHija.getTxtNuevoPrecio().getText());

				Producto producto = Producto.crearProducto(ControladorVentana.prodSeleccionado, nuevoNombre,
						nuevoPrecio, nuevoInventario);
				repo.save(producto);
				limpiaCamposTxt();
				ventanaHija.dispose();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(ventanaHija, "Ha introducido valores invalidos\nIntente nuevamente",
						"Advertencia", JOptionPane.WARNING_MESSAGE, null);
				limpiaCamposTxt();
			}
		}
	}

	/**
	 * Limpia los JTextField
	 */
	private void limpiaCamposTxt() {
		ventanaHija.getTxtNuevoNombre().setText("");
		ventanaHija.getTxtNuevoInventario().setText("");
		ventanaHija.getTxtNuevoPrecio().setText("");
		ventanaHija.getTxtNuevoNombre().grabFocus();
	}

	/**
	 * verifica si los campos fueron dejados en blanco
	 * 
	 * @return retorna un boolean (true o false)
	 */
	private boolean verificaCampos() {
		if (ventanaHija.getTxtNuevoNombre().getText().isBlank()
				|| ventanaHija.getTxtNuevoInventario().getText().isBlank()
				|| ventanaHija.getTxtNuevoPrecio().getText().isBlank()) {
			return false;
		}
		return true;

	}

}
