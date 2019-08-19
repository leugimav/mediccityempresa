package com.proyecto.sistemas.mediccityempresa;

public class Medico {
    private String uid;
 private String Nombres;
 private String Apellidos;
 private String Celular;
 private String NroColegiatura;

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
