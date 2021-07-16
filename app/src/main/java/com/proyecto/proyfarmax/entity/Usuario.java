package com.proyecto.proyfarmax.entity;

import android.content.Intent;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {
    private String idUsuario;
    private String idTipoDocumento;
    private String numDocumento;
    private String nombres;
    private String apelPaterno;
    private String apelMaterno;
    private String password;
    private String genero;
    private String idDepartamento;
    private String idProvincia;
    private String idDistrito;
    private String direccion;
    private String email;
    private String numCelular;
    private String fechaNacimiento;
    private Boolean termino;
    private Boolean envioOferta;
    private Boolean notificacion;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApelPaterno() {
        return apelPaterno;
    }

    public void setApelPaterno(String apelPaterno) {
        this.apelPaterno = apelPaterno;
    }

    public String getApelMaterno() {
        return apelMaterno;
    }

    public void setApelMaterno(String apelMaterno) {
        this.apelMaterno = apelMaterno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getTermino() {
        return termino;
    }

    public void setTermino(Boolean termino) {
        this.termino = termino;
    }

    public Boolean getEnvioOferta() {
        return envioOferta;
    }

    public void setEnvioOferta(Boolean envioOfertas) {
        envioOferta = envioOferta;
    }

    public Boolean getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Boolean envioOfertas) {
        envioOferta = envioOferta;
    }

    public String getNumDocumento() {
        return numDocumento;
    }
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

}
