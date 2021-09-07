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
		ventanaHija.getBtnActualizaProd().addActionListener(this);
	}

	/**
	 * Constructor vacio
	 */
	public ControladorVentanaActualizar() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaHija.getBtnActualizaProd()) {
			actualizarProducto();
		}
	}

	/**
	 * actualiza un producto
	 */
	private void actualizarProducto() {

		if (verificaCampos()) {
			try {
				String nuevoNombre = ventanaHija.getTxtNuevoNombre().getText();
				double nuevoPrecio = Double.parseDouble(ventanaHija.getTxtNuevoPrecio().getText());
				int nuevoInventario = Integer.valueOf(ventanaHija.getTxtNuevoInventario().getText());

				Producto producto = Producto.crearProducto(ControladorVentana.prodSeleccionado, nuevoNombre,
						nuevoPrecio, nuevoInventario);
				repo.save(producto);
				ventanaHija.setVisible(false);
				limpiaCamposTxt();
				ControladorVentana.listarJTable();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(ventanaHija, "Ha introducido valores invalidos\nIntente nuevamente",
						"Advertencia", JOptionPane.WARNING_MESSAGE, null);
				limpiaCamposTxt();

			}

		} else {
			JOptionPane.showMessageDialog(ventanaHija, "Todos los campos son obligatorios", "Advertencia",
					JOptionPane.WARNING_MESSAGE, null);
			limpiaCamposTxt();
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
