package com.spring_mvc.app_inventario.controlador;

import java.text.DecimalFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring_mvc.app_inventario.modelo.Producto;
import com.spring_mvc.app_inventario.modelo.RepositorioProducto;


// Se implementan los metodos anteriormente existentes de la clase CrudRepository
/**
 * @author: Isaac Diaz
 */
@Service
public class ControladorProducto implements RepositorioProducto {
	private final static DecimalFormat df = new DecimalFormat("#.0");

	@Autowired
	static
	RepositorioProducto ControladorProducto;


	@Override
	public <S extends Producto> S save(S entity) {
		return ControladorProducto.save(entity);
	}

	@Override
	public <S extends Producto> Iterable<S> saveAll(Iterable<S> entities) {
		return ControladorProducto.saveAll(entities);
	}

	@Override
	public void delete(Producto entity) {
		ControladorProducto.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Producto> entities) {
		ControladorProducto.deleteAll(entities);
	}

	@Override
	public Optional<Producto> findById(Integer id) {
		return ControladorProducto.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return ControladorProducto.existsById(id);
	}

	@Override
	public Iterable<Producto> findAll() {
		return ControladorProducto.findAll();
	}

	@Override
	public Iterable<Producto> findAllById(Iterable<Integer> ids) {
		return ControladorProducto.findAllById(ids);
	}

	@Override
	public long count() {
		return ControladorProducto.count();
	}

	@Override
	public void deleteById(Integer id) {
		ControladorProducto.deleteById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		ControladorProducto.deleteAllById(ids);
	}

	@Override
	public void deleteAll() {
		ControladorProducto.deleteAll();
	}

	public String generarInforme() {

		return "Producto precio mayor: " + mayor() + "\n" + "Producto precio menor: " + menor() + "\n"
				+ "Promedio de precios:   " + promedio() + "\n" + "Valor del inventario:  " + valTotal();
	}

	/**
	 * Metodo que haya el nombre del producto con el precio mayor
	 * 
	 * @return retorna un string
	 */
	private static String mayor() {
		double mayor = 0;
		String nombre = "";
		for (Producto p : ControladorProducto.findAll()) {
			if (p.getPrice() > mayor) {
				mayor = p.getPrice();
				nombre = p.getName();
			}

		}
		return nombre;
	}

	/**
	 * Metodo que haya el nombre del producto con el precio menor
	 * 
	 * @return retorna un string
	 */
	private static String menor() {
		double menor = 0;
		String nombre = "";
		for (Producto p : ControladorProducto.findAll()) {
			if (p.getPrice() < menor) {
				menor = p.getPrice();
				nombre = p.getName();
			}

		}
		return nombre;
	}

	/**
	 * Metodo que haya el promedio entre los precios de los productos actuales en la
	 * tabla
	 * 
	 * @return retorna un double
	 */
	private static double promedio() {
		double suma = 0;
		for (Producto p : ControladorProducto.findAll()) {
			suma += p.getPrice();
		}

		return Double.valueOf(df.format(suma / ControladorProducto.count()));
	}

	/**
	 * Metodo que haya el valor total de los productos actuales en la tabla
	 * 
	 * @return retorna un double
	 */
	private static Double valTotal() {
		double suma = 0;
		for (Producto p : ControladorProducto.findAll()) {
			suma += p.getPrice() * p.getStock();
		}

		return Double.valueOf(df.format(suma / ControladorProducto.count()));
	}

}
