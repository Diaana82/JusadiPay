package com.Project.project.controller;

import com.Project.project.model.Cuenta;
import com.Project.project.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestAccesoUsuarioController {

    private static final List<Cuenta> Cuenta = new ArrayList<>();

    static {

        Usuario u1 = new Usuario("Carlos Lopez", "1002456789", "3157894561", "carlos.lopez@gmail.com", "car123", 28);
        Cuenta c1 = new Cuenta("1", u1, 500000.0);

        Usuario u2 = new Usuario("Juan Maya", "1089603221", "3245609330", "juan.maya@gmail.com", "juan123", 18);
        Cuenta c2 = new Cuenta("2", u2, 100000.0);

        Usuario u3 = new Usuario("David Jaramillo", "1063420560", "3113755163", "david.jara@gmail.com", "dav123", 29);
        Cuenta c3 = new Cuenta("3", u3, 200000.0);

        Cuenta.add(c1);
        Cuenta.add(c2);
        Cuenta.add(c3);
    }

    @GetMapping("/")
    public String Inicio(){
        return "Esta es la página de Acceso Usuarios";
    }
    @GetMapping("/AccesosUsuario")
    public List<Cuenta> ListarCuenta() {
        return Cuenta;
    }

}
