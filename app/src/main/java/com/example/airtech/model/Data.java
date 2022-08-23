package com.example.airtech.model;

public class Data {
    public Double temperatura;
    public Long humedad;
    public Long calidadaire;
    public String alertacalidad;
    public String alertahumedad;
    public String alertatemperatura;
    public String direccion;
    public String aforo;
    public String telefono;


    public Data() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Long getHumedad() {
        return humedad;
    }
    public Double getTemp() {
        return temperatura;
    }
    public Long getCalidad() {
        return calidadaire;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getAforo() {
        return aforo;
    }
    public String getAlertaC() {
        return alertacalidad;
    }
    public String getAlertaT() {
        return alertatemperatura;
    }
    public String getAlertaH() {
        return alertahumedad;
    }
    public void setTelefono(String tele) {
        this.telefono = tele;
    }
    public void setDireccion(String dir) {
        this.direccion = dir;
    }
    public void setAforo(String afor) {
        this.aforo = afor;
    }
}
