package com.reto5.app_inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.reto5.app_inventario.controlador.ControladorVentana;
import com.reto5.app_inventario.modelo.RepositorioProducto;
import com.reto5.app_inventario.vista.Gui;


/**
 * @author Isaac Diaz
 */
@SpringBootApplication
@EntityScan("com.reto5.app_inventario.modelo")
@ComponentScan("com.reto5.app_inventario.modelo")
public class AppInventarioApplicaction {

	@Autowired
	RepositorioProducto repo;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AppInventarioApplicaction.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			Gui ventanaPrincipal = new Gui();
            ControladorVentana controlador = new ControladorVentana(repo, ventanaPrincipal);
            ventanaPrincipal.setControlador(controlador);
		};
	}

}
