package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import clases.Reto3;

/**
 * 
 * @author Isaac
 *
 */
public class VentanaActualizarProd extends JFrame {
	// Ventana secundaria de actualizar producto
	/**
	 * Definicion de variables de clase
	 */
	public final static JTextField txtNuevoNombre = new JTextField();
	public final static JTextField txtNuevoInventario = new JTextField();
	public final static JTextField txtNuevoPrecio = new JTextField();
	static final JPanel panelActualizar = new JPanel();

	/**
	 * Constructor ventana actualizar producto
	 */
	VentanaActualizarProd() {
		super("Actualizar producto");
		// Titulo
		ImageIcon ico = new ImageIcon(getClass().getResource("icono.png"));
		// Se crea el icono personalizado en la ventana
		this.setIconImage(ico.getImage());
		// Borde
		Border bordeBase = BorderFactory.createLineBorder(new Color(204, 169, 221), 2, true);
		panelActualizar.setBorder(BorderFactory.createTitledBorder(
				bordeBase,
				" Actualizar '" + prodSeleccionado() + "' ", 
				TitledBorder.LEFT, 
				TitledBorder.TOP, 
				Gui.fuenteBorde)
				);

		this.add(panelActualizar);
		// Dimensiones del panel
		panelActualizar.setPreferredSize(new Dimension(370, 190));
		panelActualizar.setBackground(Color.white);
		panelActualizar.setLayout(null);

		// Se definen las etiquetas y se añaden
		JLabel nuevoNombre = new JLabel("Nuevo nombre");
		nuevoNombre.setFont(Gui.etiquetas);
		nuevoNombre.setBounds(20, 30, 130, 30);
		panelActualizar.add(nuevoNombre);

		JLabel precio = new JLabel("Nuevo precio");
		precio.setFont(Gui.etiquetas);
		precio.setBounds(20, 70, 130, 30);
		panelActualizar.add(precio);

		JLabel prod = new JLabel("Nuevo inventario");
		prod.setFont(Gui.etiquetas);
		prod.setBounds(20, 110, 130, 30);
		panelActualizar.add(prod);

		// Se definen los campos de texto y se añaden
		txtNuevoNombre.setFont(Gui.Jtxts);
		txtNuevoNombre.setHorizontalAlignment(JTextField.CENTER);
		txtNuevoNombre.setBounds(200, 30, 150, 30);
		panelActualizar.add(txtNuevoNombre);

		txtNuevoPrecio.setFont(Gui.Jtxts);
		txtNuevoPrecio.setHorizontalAlignment(JTextField.CENTER);
		txtNuevoPrecio.setBounds(200, 70, 150, 30);
		panelActualizar.add(txtNuevoPrecio);

		txtNuevoInventario.setFont(Gui.Jtxts);
		txtNuevoInventario.setHorizontalAlignment(JTextField.CENTER);
		txtNuevoInventario.setBounds(200, 110, 150, 30);
		panelActualizar.add(txtNuevoInventario);

		// Boton de actualizar producto y sus propiedades
		JButton actualizaProducto = new JButton("Actualizar Producto");
		actualizaProducto.setFont(Gui.botones);
		actualizaProducto.setFocusable(false);
		actualizaProducto.setBounds(110, 150, 170, 30);

		// Cuando se hace click en el boton llama al metodo de actualizar producto
		actualizaProducto.addActionListener(e -> Reto3.actualizarProducto(
				Gui.seleccionFila, 
				txtNuevoNombre.getText(),
				txtNuevoPrecio.getText(), 
				txtNuevoInventario.getText())
				);
		
		// Se añade al panel
		panelActualizar.add(actualizaProducto);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);

	}

	/**
	 * Verifica que no hayan quedado campos en blanco, de ser asi muestra un mensaje
	 * de error
	 * 
	 * @return -> boolean
	 */
	public static boolean verificaCamposActualizar() {
		if (txtNuevoNombre.getText().isBlank()  || txtNuevoPrecio.getText().isBlank()
				|| txtNuevoInventario.getText().isBlank()) {
			JOptionPane.showMessageDialog(panelActualizar, "Todos los campos son obligatorios", "Advertencia",
					JOptionPane.WARNING_MESSAGE, null);

			return false;
		}
		return true;
	}

	/**
	 * Regresa el producto seleccionado para personalizar el titulo del borde del
	 * JPanel
	 * 
	 * @return -> String
	 */
	private String prodSeleccionado() {
		int fila = Gui.productos.getSelectedRow();
		int columna = 0;
		return Gui.productos.getValueAt(fila, columna).toString();

	}
}
