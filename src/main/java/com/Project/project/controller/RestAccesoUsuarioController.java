package com.Project.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAccesoUsuarioController {

    @GetMapping
    public String Inicio(){
        return "Esta es la página de Acceso Usuarios";
    }
}
