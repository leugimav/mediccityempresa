package com.proyecto.sistemas.mediccityempresa.data.entities;

public class Medico {

    private String uid;
    private String correo;
    private String Nombres;
    private String Apellidos;
    private String Celular;
    private String NroColegiatura;
    private String EspPrincipal;
    private String EspSecundaria;
    private String CentroEstudios;
    private String FinalizaEstudios;
    private String Detalles;
    private String Latitud;
    private String Longitud;
    private String Tipo; //Medico , Paciente


    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEspPrincipal() {
        return EspPrincipal;
    }

    public void setEspPrincipal(String espPrincipal) {
        EspPrincipal = espPrincipal;
    }

    public String getEspSecundaria() {
        return EspSecundaria;
    }

    public void setEspSecundaria(String espSecundaria) {
        EspSecundaria = espSecundaria;
    }

    public String getCentroEstudios() {
        return CentroEstudios;
    }

    public void setCentroEstudios(String centroEstudios) {
        CentroEstudios = centroEstudios;
    }

    public String getFinalizaEstudios() {
        return FinalizaEstudios;
    }

    public void setFinalizaEstudios(String finalizaEstudios) {
        FinalizaEstudios = finalizaEstudios;
    }

    public String getDetalles() {
        return Detalles;
    }

    public void setDetalles(String detalles) {
        Detalles = detalles;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getNroColegiatura() {
        return NroColegiatura;
    }

    public void setNroColegiatura(String nroColegiatura) {
        NroColegiatura = nroColegiatura;
    }
}
