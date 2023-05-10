package com.appf1;

import com.appf1.entidades.Equipo;
import com.appf1.entidades.Piloto;

public interface IComunicarFragment {
    public void enviarEquipo(Equipo equipo);
    public void enviarPiloto(Piloto piloto);
}
