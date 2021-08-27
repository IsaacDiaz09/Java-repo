package com.app.proyecto_spring.modelo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Heredar metodos de la interfaz JpaRepository/CrudRepository, indicar clase entidad: Producto, tipo id: int
@Repository
public interface RepositorioProducto extends CrudRepository<Producto,Integer> {

}
