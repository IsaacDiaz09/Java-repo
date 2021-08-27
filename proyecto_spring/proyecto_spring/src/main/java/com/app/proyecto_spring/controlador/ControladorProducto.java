package com.app.proyecto_spring.controlador;

import java.text.DecimalFormat;
import java.util.Optional;

import com.app.proyecto_spring.modelo.Producto;
import com.app.proyecto_spring.modelo.RepositorioProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Controller;
// Se implementan los metodos anteriormente existentes de la clase CrudRepository
/**
 * @author: Isaac Diaz
 */
@Service
@Controller
public class ControladorProducto implements RepositorioProducto {
	private final static DecimalFormat df = new DecimalFormat("#.0");
	Producto prod = Producto.crearProducto();
	@Autowired
	private RepositorioProducto repositorioProducto;
    public ControladorProducto() {
        super();
    }
	public ControladorProducto(RepositorioProducto repo) {
        this.repositorioProducto = repo;
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

	
	public String generarInforme() {

		return "Producto precio mayor: " + mayor() + "\n" + "Producto precio menor: " + menor() + "\n"
				+ "Promedio de precios:   " + promedio() + "\n" + "Valor del inventario:  " + valTotal();
	}

	/**
	 * Metodo que haya el nombre del producto con el precio mayor
	 * 
	 * @return retorna un string
	 */
	private String mayor() {
		double mayor = 0;
		String nombre = "";
		for (Producto p : repositorioProducto.findAll()) {
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
	private String menor() {
		double menor = 0;
		String nombre = "";
		for (Producto p : repositorioProducto.findAll()) {
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
	private double promedio() {
		double suma = 0;
		for (Producto p : repositorioProducto.findAll()) {
			suma += p.getPrice();
		}

		return Double.valueOf(df.format(suma / repositorioProducto.count()));
	}

	/**
	 * Metodo que haya el valor total de los productos actuales en la tabla
	 * 
	 * @return retorna un double
	 */
	private Double valTotal() {
		double suma = 0;
		for (Producto p : repositorioProducto.findAll()) {
			suma += p.getPrice() * p.getStock();
		}

		return Double.valueOf(df.format(suma / repositorioProducto.count()));
	}
}
