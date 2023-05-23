package com.appf1.entidades;


import java.io.Serializable;

public class Piloto implements Serializable {
    private String nombre;
    private int imagenId;
    private String nacionalidad;
    private int dorsal;
    private int victorias;
    private int titulos;

    public Piloto(String nombre, int imagenId, String nacionalidad, int dorsal, int victorias, int titulos) {
        this.nombre = nombre;
        this.imagenId = imagenId;
        this.nacionalidad = nacionalidad;
        this.dorsal = dorsal;
        this.victorias = victorias;
        this.titulos = titulos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getTitulos() {
        return titulos;
    }

    public void setTitulos(int titulos) {
        this.titulos = titulos;
    }

    public Piloto() {
    }
}
