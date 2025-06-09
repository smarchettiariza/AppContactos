package com.example.marchetti399;

public class Contacto {
    private String nombre;
    private String telefono;
    private String correo;
    private String domicilio;
    private String genero;

    public Contacto(String nombre, String telefono, String correo, String domicilio, String genero) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.domicilio = domicilio;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getGenero() {
        return genero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
