package com.Project.project.controller;

import com.Project.project.model.AccesoUsuario;
import com.Project.project.model.Cuenta;
import com.Project.project.model.CuentaRequest;
import com.Project.project.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestUsuarioController {

    private static final List<Cuenta> cuentas = new ArrayList<>();

    static {

        Usuario u1 = new Usuario("Carlos Lopez", "1002456789", "3157894561", "carlos.lopez@gmail.com", "car123", 28);
        Cuenta c1 = new Cuenta("1", u1, 500000.0);

        Usuario u2 = new Usuario("Juan Maya", "1089603221", "3245609330", "juan.maya@gmail.com", "juan123", 18);
        Cuenta c2 = new Cuenta("2", u2, 100000.0);

        Usuario u3 = new Usuario("David Jaramillo", "1063420560", "3113755163", "david.jara@gmail.com", "dav123", 29);
        Cuenta c3 = new Cuenta("3", u3, 200000.0);

        cuentas.add(c1);
        cuentas.add(c2);
        cuentas.add(c3);
    }

    @GetMapping("/")
    public String Inicio() {
        return "Esta es la página de Acceso Usuarios";
    }


    @DeleteMapping("AccesoUsuarios/{id}")
    public ResponseEntity<String> eliminarCuenta(@PathVariable String id) {

        for (Cuenta c : cuentas) {
            if (c.getNumCuenta().equals(id)) {
                cuentas.remove(c);
                return ResponseEntity.ok("Cuenta eliminada correctamente");
            }
        }

        return ResponseEntity.status(404).body("Cuenta no encontrada");

    }
    @PostMapping("/CrearCuenta")
    public ResponseEntity<String> addCuenta(@RequestBody CuentaRequest request) {
        try {
            if (request == null || request.getTitular() == null || request.getSaldo() == null) {
                return ResponseEntity.badRequest()
                        .body("Faltan datos: titular o saldo");
            }

            Long id = cuentas.stream()
                    .map(Cuenta::getNumCuenta)
                    .filter(num -> num != null && num.matches("\\d+"))
                    .mapToLong(Long::parseLong)
                    .max()
                    .orElse(0L) + 1;

            Cuenta nuevaCuenta = new Cuenta();
            nuevaCuenta.setNumCuenta(String.valueOf(id));
            nuevaCuenta.setTitular(request.getTitular());
            nuevaCuenta.setSaldo(request.getSaldo());

            cuentas.add(nuevaCuenta);

            return ResponseEntity.ok("La cuenta fue creada correctamente, ID " + id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Error interno: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @PutMapping("/ActualizarUsuario/{id}")
    public
    ResponseEntity<String> actualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        for (Cuenta c : cuentas) {
            if (c.getNumCuenta().equals(id)) {
                c.getTitular().setNombre(usuario.getNombre());
                c.getTitular().setDocumento(usuario.getDocumento());
                c.getTitular().setTelefono(usuario.getTelefono());
                c.getTitular().setCorreo(usuario.getCorreo());
                return ResponseEntity.ok("Usuario actualizado correctamente");
            }
        }
        return ResponseEntity.status(404).body("Usuario no encontrado");

    }
    }

