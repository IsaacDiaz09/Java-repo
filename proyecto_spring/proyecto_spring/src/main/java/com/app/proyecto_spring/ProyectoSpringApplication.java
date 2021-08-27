package com.app.proyecto_spring;

import com.app.proyecto_spring.modelo.RepositorioProducto;
import com.app.proyecto_spring.modelo.Producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.ApplicationRunner;
import com.app.proyecto_spring.vista.Gui;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Isaac Diaz
 */
@ComponentScan("com.app.proyecto_spring.modelo")
@SpringBootApplication
public class ProyectoSpringApplication {
	static ConfigurableApplicationContext ctx;
	@Autowired
	RepositorioProducto repo;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSpringApplication.class, args);
		ctx = new SpringApplicationBuilder(Gui.class)
				.headless(false).run(args);

	}

	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			System.out.println((List<Producto>) repo.findAll());

		};

	}
}
