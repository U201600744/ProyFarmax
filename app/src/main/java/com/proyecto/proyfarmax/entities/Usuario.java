package com.proyecto.proyfarmax.entities;

public class Usuario {
    private String id;
    private String tipodocumento;
    private String numerodocumento;
    private String nombre;
    private String apellido;
    private String correo;
    private String celular;
    private String contrasena;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;


    public Usuario() {
    }

    public Usuario(String id, String tipodocumento, String numerodocumento, String nombre, String apellido, String correo, String celular, String contrasena, String direccion, String departamento, String provincia, String distrito) {
        this.id = id;
        this.tipodocumento = tipodocumento;
        this.numerodocumento = numerodocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.celular = celular;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
}
