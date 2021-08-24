package Reto3.Reto3Parte1.src;

class Producto {
	/**
	 * Clase producto, atributos y metodos de los productos
	 */
	private final int codigo;
	private final int inventario;
	private final double precio;
	private final String nombre;

	// Constructor
	protected Producto(int codigo, String nombre, double precio, int inventario) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.inventario = inventario;

	}

	/**
	 * metodo para obtener el codigo de un producto
	 *
	 * @return
	 */
	protected int getId() {
		return this.codigo;
	}

	/**
	 * metodo para obtener el nombre de un producto
	 *
	 * @return
	 */
	protected String getName() {
		return this.nombre;
	}

	/**
	 * metodo para obtener el precio de un producto
	 *
	 * @return
	 */
	protected double getPrice() {
		return this.precio;
	}

	/**
	 * metodo para obtener el inventario de un producto
	 *
	 * @return
	 */
	protected int getStock() {
		return this.inventario;
	}

}