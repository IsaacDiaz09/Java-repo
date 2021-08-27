package com.reto5.app_inventario.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.reto5.app_inventario.controlador.ControladorVentana;

/**
 * @author Isaac
 */
public class Gui extends JFrame {
	ControladorVentana controlador = new ControladorVentana();

	public void setControlador(ControladorVentana controlador) {
		this.controlador = controlador;
	}

	private static final long serialVersionUID = 6129342687811689419L;
	/**
	 * Fuente para el titulo
	 */
	static final Font FNT_TITULO = new Font("Century Gothic", Font.BOLD, 22);
	/**
	 * Fuente para los bordes
	 */
	static final Font FNT_BORDE = new Font("Century Gothic", Font.ITALIC, 18);
	/**
	 * Fuente para las etiquetas
	 */
	static final Font FNT_ETIQUETAS = new Font("Century Gothic", Font.BOLD, 14);
	/**
	 * Fuente para los botones
	 */
	static final Font FNT_BOTONES = new Font("Century Gothic", Font.ITALIC, 14);
	/**
	 * Fuente para los campos de texto
	 */
	static final Font FNT_JTXTS = new Font("Century Gothic", Font.PLAIN, 16);

	JTable productos = new JTable();
	static int seleccionFila;
	private static VentanaActualizarProd ventanaActualizar = null;
	public JTextField txtNombre = new JTextField();
	public JTextField txtInventario = new JTextField();
	public JTextField txtPrecio = new JTextField();
	JButton agregaProducto = new JButton("Agregar Producto");
	JButton btnActualiza = new JButton("Actualizar producto");
	JButton btnElimina = new JButton("Eliminar");
	JButton btnInforme = new JButton("Generar informe");
	final String[] columnas = { "Nombre", "Precio", "Inventario" };
	DefaultTableModel model = new DefaultTableModel(null, columnas) {
		@Override
		public boolean isCellEditable(int filas, int columnas) {
			return false;
		}
	};

	public DefaultTableModel getModel() {
		return model;
	}

	public JTable getTable() {
		return productos;
	}

	public JButton getActualizaProd() {
		return btnActualiza;
	}

	public JButton getEliminaProd() {
		return btnElimina;
	}

	public JButton getAgregaProd() {
		return agregaProducto;
	}

	public JButton getInformeBtn() {
		return btnInforme;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtInventario() {
		return txtInventario;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	/**
	 * Constructor
	 */
	public Gui() {
		super("Reto 5: Inventario de productos ~ Spring Boot JDBC - MVC ~");
		this.setSize(700, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		ImageIcon ico = new ImageIcon(getClass().getResource("icono.png"));
		this.setIconImage(ico.getImage());
		this.setLocationRelativeTo(null);

		// JPanel principal
		JPanel panel = new JPanel();
		Border bordeBase = BorderFactory.createLineBorder(new Color(204, 169, 221), 2, true);
		panel.setBorder(BorderFactory.createTitledBorder(bordeBase, " Bienvenido a la aplicación de Inventario ",
				TitledBorder.CENTER, TitledBorder.TOP, FNT_TITULO));
		this.add(panel);

		/**
		 * ---- Primer JPanel --- JPanel contenedor de las opciones de agregar producto
		 */

		// Borde con titulo
		JPanel panelAgregar = new JPanel();
		panelAgregar.setBorder(BorderFactory.createTitledBorder(bordeBase, " Agregar nuevo producto ",
				TitledBorder.LEFT, TitledBorder.TOP, FNT_BORDE));
		// Se añade al panel principal
		panel.add(panelAgregar);
		panelAgregar.setPreferredSize(new Dimension(350, 190));
		panelAgregar.setBackground(Color.white);
		panelAgregar.setLayout(null);

		// Se declaran las etiquetas
		JLabel nombre = new JLabel("Nombre");
		nombre.setFont(FNT_ETIQUETAS);
		nombre.setBounds(30, 30, 80, 30);
		panelAgregar.add(nombre);

		JLabel precio = new JLabel("Precio");
		precio.setFont(FNT_ETIQUETAS);
		precio.setBounds(30, 70, 80, 30);
		panelAgregar.add(precio);

		JLabel prod = new JLabel("Inventario");
		prod.setFont(FNT_ETIQUETAS);
		prod.setBounds(30, 110, 80, 30);
		panelAgregar.add(prod);

		// Se definen los campos de texto
		txtNombre.setFont(FNT_JTXTS);
		txtNombre.setHorizontalAlignment(JTextField.CENTER);
		txtNombre.setBounds(160, 30, 150, 30);
		panelAgregar.add(txtNombre);

		txtPrecio.setFont(FNT_JTXTS);
		txtPrecio.setHorizontalAlignment(JTextField.CENTER);
		txtPrecio.setBounds(160, 70, 150, 30);
		panelAgregar.add(txtPrecio);

		txtInventario.setFont(FNT_JTXTS);
		txtInventario.setHorizontalAlignment(JTextField.CENTER);
		txtInventario.setBounds(160, 110, 150, 30);
		panelAgregar.add(txtInventario);

		// Se declara el boton que agregara el producto
		agregaProducto.setFont(FNT_BOTONES);
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

		// Ahora si el JTable
		productos = new JTable(model);

		productos.getTableHeader().setReorderingAllowed(false);
		panelTable.add(productos, BorderLayout.NORTH);
		productos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

		opciones.add(btnElimina);
		btnElimina.setFont(FNT_BOTONES);
		btnElimina.setFocusable(false);

		opciones.add(btnActualiza);
		btnActualiza.setFont(FNT_BOTONES);
		btnActualiza.setFocusable(false);

		btnInforme.setFont(FNT_BOTONES);
		opciones.add(btnInforme);
		btnInforme.setFocusable(false);
		this.setVisible(true);
	}

}
