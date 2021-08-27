package com.spring_mvc.app_inventario.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Isaac Diaz
 */
@Entity
@Table(name="productos")
public class Producto {
    /**
     * Clase producto, atributos y metodos de los productos
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column
    private int inventario;
    @Column
    private double precio;
    @Column
    private String nombre;

    // Constructores
    public Producto(String nombre, double precio, int inventario) {
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }
    public Producto() {}

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
        return "Producto:" + this.nombre + " código: " + this.codigo + "precio: " + this.precio + " inventario: "
                + this.inventario;
    }

    public Producto crearProducto(String nombre, double precio, int inventario) {
        return new Producto(nombre, precio, inventario);
    }
}
