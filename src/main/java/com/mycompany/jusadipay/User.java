package com.mycompany.jusadipay;

/**
 *
 * @author patin
 */
public class User {
    //atributos
    public String nombre;
    private String documento;
    private String telefono;
    private String correo;
    private String contraseña;
    private int edad;
    
    //Contructor vacio(no seleccionas nada)
    public User() {
    }
    //contructor con todo(seleccinas todo)
    public User(String nombre, String documento, String telefono, String correo, String contraseña, int edad) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.edad = edad;
    }
    //getters and setters
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
    
    
}
