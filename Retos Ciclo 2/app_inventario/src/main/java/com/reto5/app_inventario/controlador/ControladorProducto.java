package com.reto5.app_inventario.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto5.app_inventario.modelo.Producto;
import com.reto5.app_inventario.modelo.RepositorioProducto;


/**
 * @author: Isaac Diaz
 * Se implementan los metodos anteriormente existentes de la clase CrudRepository
 */

@Service
public class ControladorProducto implements RepositorioProducto {

	/**
	 * Inyecta la dependencia para poder trabajar con ella
	 */
	@Autowired
	private  RepositorioProducto repositorioProducto;

	public ControladorProducto(RepositorioProducto repo) {
		this.repositorioProducto = repo;
	}

	public ControladorProducto() {
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

	
	
}
