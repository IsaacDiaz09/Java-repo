package com.reto5.app_inventario.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.reto5.app_inventario.modelo.Producto;
import com.reto5.app_inventario.modelo.RepositorioProducto;
import com.reto5.app_inventario.vista.VentanaActualizarProd;
import com.reto5.app_inventario.vista.Gui;

import org.springframework.stereotype.Service;

/**
 * @author: Isaac Diaz
 */

@Service
public class ControladorVentanaActualizar implements ActionListener {

	private VentanaActualizarProd ventanaHija;

	private RepositorioProducto repo;

	public ControladorVentanaActualizar(RepositorioProducto repositorio, VentanaActualizarProd vntActualizar) {

		this.repo = repositorio;
		this.ventanaHija = vntActualizar;
		agregaEventos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaHija.getBtnActualizaProd()) {
			actualizarProducto();
			ControladorVentana.listarJTable();
		}
	}

	public ControladorVentanaActualizar() {
	}

	public void agregaEventos() {
		ventanaHija.getBtnActualizaProd().addActionListener(this);
	}

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

	private void limpiaCamposTxt() {
		ventanaHija.getTxtNuevoNombre().setText("");
		ventanaHija.getTxtNuevoInventario().setText("");
		ventanaHija.getTxtNuevoPrecio().setText("");
		ventanaHija.getTxtNuevoNombre().grabFocus();
	}

	private boolean verificaCampos() {
		if (ventanaHija.getTxtNuevoNombre().getText().isBlank()
				|| ventanaHija.getTxtNuevoInventario().getText().isBlank()
				|| ventanaHija.getTxtNuevoPrecio().getText().isBlank()) {
			return false;
		}
		return true;

	}

}
