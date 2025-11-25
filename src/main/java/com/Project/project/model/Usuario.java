package com.Project.project.model;

public class Usuario {

    public String nombre;
    private String documento;
    private String telefono;
    private String correo;
    private String contraseña;
    private int edad;

    public Usuario() {
    }

    public Usuario(String nombre, String documento, String telefono, String correo, String contraseña, int edad) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Estados internos de registro y sesión
    private boolean registrado = false;
    private boolean sesionActiva = false;

    //  verificar unicidad básica por correo en memoria
    private static final java.util.Set<String> CORREOS_REGISTRADOS = new java.util.HashSet<>();

    public void registrarse() {
        // Validaciones mínimas
        if (this.nombre == null || this.nombre.isBlank()) throw new IllegalStateException("Nombre requerido");
        if (this.documento == null || this.documento.isBlank()) throw new IllegalStateException("Documento requerido");
        if (this.correo == null || this.correo.isBlank()) throw new IllegalStateException("Correo requerido");
        if (this.contraseña == null || this.contraseña.isBlank()) throw new IllegalStateException("Contraseña requerida");
        if (this.edad < 18) throw new IllegalStateException("Debe ser mayor de edad");

        // Unicidad de correo
        if (CORREOS_REGISTRADOS.contains(this.correo)) {
            throw new IllegalStateException("Correo ya registrado");
        }
        CORREOS_REGISTRADOS.add(this.correo);

        this.registrado = true;
    }

    public boolean iniciarSesion() {
        if (!registrado) return false;
        if (sesionActiva) return true; // ya iniciada

        this.sesionActiva = true;
        return true;
    }

    public void cerrarSesion() {
        this.sesionActiva = false;
    }


}


