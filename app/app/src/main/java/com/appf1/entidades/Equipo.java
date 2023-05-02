package com.appf1.entidades;

public class Equipo {
    private String nombre;
    private int imagenId;

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

    public Equipo() {
    }

    public Equipo(String nombre, int imagenId) {
        this.nombre = nombre;
        this.imagenId = imagenId;
    }
}
