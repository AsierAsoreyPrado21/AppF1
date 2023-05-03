package com.appf1.entidades;

import java.io.Serializable;

public class Equipo implements Serializable {
    private String nombre;
    private int imagenId;
    private String nacionalidad;

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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

    public Equipo() {
    }

    public Equipo(String nombre, int imagenId, String nacionalidad) {
        this.nombre = nombre;
        this.imagenId = imagenId;
        this.nacionalidad=nacionalidad;
    }
}
