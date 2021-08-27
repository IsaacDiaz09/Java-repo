package com.spring_mvc.app_inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import com.spring_mvc.app_inventario.modelo.RepositorioProducto;

@SpringBootApplication
@ComponentScan("com.spring_mvc.app_inventario.modelo")
@EnableJdbcRepositories("com.spring_mvc.app_inventario.modelo")
public class AppInventarioApplication {
	@Autowired
	RepositorioProducto repo;

	public static void main(String[] args) {
		SpringApplication.run(AppInventarioApplication.class, args);
	}



}
