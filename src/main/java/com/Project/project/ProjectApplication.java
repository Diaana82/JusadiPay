package com.Project.project;

import com.Project.project.model.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

    private String numCuenta;
    private Usuario titular;
    private double saldo;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
