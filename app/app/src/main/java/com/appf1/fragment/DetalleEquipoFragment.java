package com.appf1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.appf1.R;
import com.appf1.entidades.Equipo;


public class DetalleEquipoFragment extends Fragment {
    TextView nombreDetalle;
    ImageView imagenDetalle;
    TextView nacionDetalle;
    TextView titulosDetalle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_equipo_fragment, container, false);

        nombreDetalle= view.findViewById(R.id.nombreDetalleEquipo);
        imagenDetalle=view.findViewById(R.id.imagenDetalleEquipo);
        nacionDetalle=view.findViewById(R.id.nacionDetalleEquipo);
        titulosDetalle=view.findViewById(R.id.titulosDetalleEquipo);

        Bundle objetoEquipo = getArguments();
        Equipo equipo= null;
        if(objetoEquipo!=null){
            equipo=(Equipo) objetoEquipo.getSerializable("objeto");
            nombreDetalle.setText(equipo.getNombre());
            imagenDetalle.setImageResource(equipo.getImagenId());
            nacionDetalle.setText(equipo.getNacionalidad());
            titulosDetalle.setText(String.valueOf(equipo.getTitulos()));
        }

        return view;
    }

}
