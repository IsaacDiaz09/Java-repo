package com.reto5.app_inventario.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author: Isaac Diaz
 */
@Entity
public class Producto {
    /**
     * Clase producto, atributos y metodos de los productos
     */
    @Id
    // Crea una tabla llamada hibernate_sequence donde guarda el valor auto-generado a usar
    @GeneratedValue
    private int codigo;
    private int inventario;
    private double precio;
    private String nombre;

    // Constructores
    public Producto(){}
    public Producto(String nombre, double precio, int inventario) {
    	this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }
    public Producto(int codigo,String nombre, double precio, int inventario) {
        this.codigo = codigo;
    	this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }
    // Getters y setters

    /**
     * Establece el Id del producto
     */
    public void setId(int id) {
        this.codigo = id;
    }

    /**
     * Establece el nombre del producto
     */
    public void setName(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto
     */
    public void setPrice(double precio) {
        this.precio = precio;
    }

    /**
     * Establece el inventario del producto
     */
    public void setStock(int stock) {
        this.inventario = stock;
    }

    /**
     * metodo para obtener el codigo de un producto
     *
     * @return retorna el codigo de un producto
     */
    public int getId() {
        return this.codigo;
    }

    /**
     * metodo para obtener el nombre de un producto
     *
     * @return retorna el nombre de un producto
     */
    public String getName() {
        return this.nombre;
    }

    /**
     * metodo para obtener el precio de un producto
     *
     * @return retorna el precio de un producto
     */
    public double getPrice() {
        return this.precio;
    }

    /**
     * metodo para obtener el inventario de un producto
     *
     * @return retorna el inventario de un producto
     */
    public int getStock() {
        return this.inventario;
    }

    public String toString() {
        return "Producto: " + this.nombre + " código: " + this.codigo + " precio: " + this.precio + " inventario: "
                + this.inventario;
    }


    public static Producto crearProducto(String nombre, double precio, int inventario) {
        return new Producto( nombre, precio, inventario);
    }

    public static Producto crearProducto(int codigo,String nombre, double precio, int inventario) {
        return new Producto(codigo, nombre, precio, inventario);
    }
}
