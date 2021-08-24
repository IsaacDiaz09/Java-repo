package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import clases.BaseDatosProductos;
import clases.Producto;
import clases.Reto3;

/**
 * @author Isaac
 * 
 *         Nota: Esta clase necesita implementar de MouseListener para llamar un
 *         metodo cuando se seleccione una fila
 */
public class Gui extends JFrame implements MouseListener {
	/**
	 * Variables y fuentes a usar en la clase
	 */
	static final Font fuenteTitulo = new Font("Century Gothic", Font.BOLD, 22);
	static final Font fuenteBorde = new Font("Century Gothic", Font.ITALIC, 18);
	static final Font etiquetas = new Font("Century Gothic", Font.BOLD, 14);
	static final Font botones = new Font("Century Gothic", Font.ITALIC, 14);
	static final Font Jtxts = new Font("Century Gothic", Font.PLAIN, 16);
	static JTable productos;
	static DefaultTableModel model;
	static int seleccionFila;
	public static VentanaActualizarProd ventanaActualizar;
	public static final JTextField txtNombre = new JTextField();
	public static final JTextField txtInventario = new JTextField();
	public static final JTextField txtPrecio = new JTextField();

	/**
	 * Constructor
	 */
	public Gui() {
		/**
		 * JFrame principal
		 */
		// Se invoca al constructor y se le pasa el titulo de la ventana
		super("Reto 3: Inventario de productos");

		// Dimensiones x,y
		this.setSize(700, 550);

		// Ahora si se cierra al presionar la 'x'
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Evita que se redimensione la ventana
		this.setResizable(false);
		ImageIcon ico = new ImageIcon(getClass().getResource("icono.png"));
		// Se crea el icono personalizado en la ventana
		this.setIconImage(ico.getImage());

		this.setLocationRelativeTo(null);
		// Lo centra en la mitad de la pantalla al momento de abrir

		// JPanel principal

		/**
		 * Este sera el JPanel contendedor de la mayoria de componentes, se le define un
		 * borde con un titulo de bienvenida y se le agrega al JFrame
		 */
		JPanel panel = new JPanel();
		Border bordeBase = BorderFactory.createLineBorder(new Color(204, 169, 221), 2, true);
		panel.setBorder(BorderFactory.createTitledBorder(bordeBase, " Bienvenido a la aplicación de Inventario ",
				TitledBorder.CENTER, TitledBorder.TOP, fuenteTitulo));
		this.add(panel);

		/**
		 * ---- Primer JPanel --- JPanel contenedor de las opciones de agregar producto
		 */
		// Borde con titulo
		JPanel panelAgregar = new JPanel();
		panelAgregar.setBorder(BorderFactory.createTitledBorder(bordeBase, " Agregar nuevo Producto ",
				TitledBorder.LEFT, TitledBorder.TOP, fuenteBorde));
		// Se añade al panel principal
		panel.add(panelAgregar);
		panelAgregar.setPreferredSize(new Dimension(350, 190));
		panelAgregar.setBackground(Color.white);
		panelAgregar.setLayout(null);

		// Se declaran las etiquetas
		JLabel nombre = new JLabel("Nombre");
		nombre.setFont(etiquetas);
		nombre.setBounds(30, 30, 80, 30);
		panelAgregar.add(nombre);

		JLabel precio = new JLabel("Precio");
		precio.setFont(etiquetas);
		precio.setBounds(30, 70, 80, 30);
		panelAgregar.add(precio);

		JLabel prod = new JLabel("Inventario");
		prod.setFont(etiquetas);
		prod.setBounds(30, 110, 80, 30);
		panelAgregar.add(prod);

		// Se definen los campos de texto
		txtNombre.setFont(Jtxts);
		txtNombre.setHorizontalAlignment(JTextField.CENTER);
		txtNombre.setBounds(160, 30, 150, 30);
		panelAgregar.add(txtNombre);

		txtPrecio.setFont(Jtxts);
		txtPrecio.setHorizontalAlignment(JTextField.CENTER);
		txtPrecio.setBounds(160, 70, 150, 30);
		panelAgregar.add(txtPrecio);
		
		txtInventario.setFont(Jtxts);
		txtInventario.setHorizontalAlignment(JTextField.CENTER);
		txtInventario.setBounds(160, 110, 150, 30);
		panelAgregar.add(txtInventario);

		// Se declara el boton que agregara el producto
		JButton agregaProducto = new JButton("Agregar Producto");
		agregaProducto.setFont(botones);
		agregaProducto.setFocusable(false);
		agregaProducto.setBounds(100, 150, 170, 30);
		panelAgregar.add(agregaProducto);

		/**
		 * JPanel contenedor del JTable
		 */
		JPanel panelTable = new JPanel();
		panel.add(panelTable);
		panelTable.setPreferredSize(new Dimension(600, 250));
		panelTable.setLayout(new BorderLayout(0, 20));

		/**
		 * Creacion del JTable para representar los datos
		 */

		// Panel con barras de scroll automaticas
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane);
		// Son las columnas que contendra el jTable
		final String[] columnas = { "Nombre", "Precio", "Inventario" };
		model = new DefaultTableModel(null, columnas) {
			/**
			 * Se sobre-escribe el método para evitar que las celdas sean editables
			 */
			@Override
			public boolean isCellEditable(int filas, int columnas) {
				return false;
			}
		};
		// Este metodo rellenara el JTable con los datos del HashMap
		cargarJTable();
		// Ahora si el JTable
		productos = new JTable(model);
		productos.addMouseListener(this);
		// Evita que las celdas se puedan modificar con doble click
		productos.getTableHeader().setReorderingAllowed(false);
		panelTable.add(productos, BorderLayout.NORTH);
		// Posicionar la tabla en el cuadro scroll
		productos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Se le asigna la barra de scroll y se establece que solo se puede seleccionar
		// una fila al tiempo
		scrollPane.setViewportView(productos);

		/**
		 * Panel inferior con Layout de grilla para posicionar los 3 botones de opciones
		 */
		JPanel opciones = new JPanel();
		opciones.setBorder(BorderFactory.createEmptyBorder());

		opciones.setPreferredSize(new Dimension(585, 30));
		panelTable.add(opciones, BorderLayout.SOUTH);
		opciones.setLayout(new GridLayout(1, 3, 30, 0));

		// Se definen y se añaden los 3 botones a su JPanel padre
		JButton btnElimina = new JButton("Eliminar");
		opciones.add(btnElimina);
		btnElimina.setFont(botones);
		btnElimina.setFocusable(false);

		JButton btnActualiza = new JButton("Actualizar producto");
		opciones.add(btnActualiza);
		btnActualiza.setFont(botones);
		btnActualiza.setFocusable(false);

		JButton btnInforme = new JButton("Informe");
		btnInforme.setFont(botones);
		opciones.add(btnInforme);
		btnInforme.setFocusable(false);

		/**
		 * --- Llamado a las acciones de botones --- Cuando se presiona el boton se
		 * ejecuta una expresion lambda o anonima
		 */

		// Llama al metodo que muestra el informe en un JOptionPane
		btnInforme.addActionListener(e -> muestraInforme());

		// Llama al metodo de eliminar un producto
		btnElimina.addActionListener(e -> Reto3.eliminarProducto(seleccionFila));

		// Cuando se presiona invoca o no una segunda ventana con las opciones de
		// actualizar producto
		btnActualiza.addActionListener(e -> ventanaActualizaProd());

		// Llama al metodo de agregar producto
		agregaProducto.addActionListener(
				e -> Reto3.agregarProducto(txtNombre.getText(), txtPrecio.getText(), txtInventario.getText()));

	}

	/**
	 * Se rellena el JTable llamando al metodo cargarProductos que rellena el
	 * HashMap
	 * 
	 * @param m -> DefaultTableModel a llenar
	 */
	public static void cargarJTable() {

		for (Producto p : BaseDatosProductos.listaProductos.values()) {
			Object[] fila = { p.getName(), p.getPrice(), p.getStock() };
			model.addRow(fila);

		}
	}

	/**
	 * Elimina todas las filas del JTable
	 */
	public static void vaciarJTable() {
		// Se debe hacer hacia atras ya que el indice va cambiando
		for (int i = productos.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Metodo que se sobre-escribe para dar una accion tras un evento y asi
	 * gestionar los productos
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int fila = productos.getSelectedRow();
		// Para que siempre me traiga el nombre del producto
		int columna = 0;
		Object prodSeleccionado = productos.getValueAt(fila, columna);
		// Trae la fila seleccionada llamado al metodo getKey
		seleccionFila = BaseDatosProductos.getKey(prodSeleccionado);

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
	 * Muestra el informe con los productos actuales en un cuadro de JOptionPane
	 */
	private void muestraInforme() {
		JOptionPane.showMessageDialog(this, BaseDatosProductos.generarInforme(), "Información",
				JOptionPane.INFORMATION_MESSAGE, null);
	}

	/**
	 * Verifica que ninguno de los campos de texto haya quedado en blanco, muestra
	 * un mensaje de error de ser asi
	 * 
	 * @return -> boolean
	 */
	public static boolean verificaCampos() {
		if (txtNombre.getText().isBlank() || txtPrecio.getText().isBlank() || txtInventario.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Advertencia",
					JOptionPane.WARNING_MESSAGE, null);

			return false;
		
		}
		return true;
	}

	/**
	 * Verifica que se haya seleccionado alguna fila en el JTable, muestra un
	 * mensaje de error de ser asi
	 * 
	 * @return -> boolean
	 */
	public static boolean verificaSeleccion() {
		// Si no hay fila seleccionada regresa -1
		if (Gui.productos.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Primero debe seleccionar un producto", "Error",
					JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		return true;
	}

	/**
	 * Metodo que invoca o no la ventana secundaria para actualiza producto
	 */
	public void ventanaActualizaProd() {
		// Si se ha seleccionado la fila de un producto a actualizar llama la nueva
		// ventana
		if (verificaSeleccion()) {
			ventanaActualizar = new VentanaActualizarProd();
			ventanaActualizar.setVisible(true);
		}
		// De lo contrario muestra el mensaje de error de verificaSeleccion()

	}
}
