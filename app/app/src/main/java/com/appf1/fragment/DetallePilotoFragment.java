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
import com.appf1.entidades.Piloto;

public class DetallePilotoFragment extends Fragment {
    TextView nombreDetalle;
    ImageView imagenDetalle;
    TextView nacionDetalle;
    TextView dorsalDetalle;
    TextView victoriasDetalle;
    TextView titulosDetalle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_piloto_fragment, container, false);

        nombreDetalle= view.findViewById(R.id.nombreDetallePiloto);
        imagenDetalle=view.findViewById(R.id.imagenDetalleEquipo);
        nacionDetalle=view.findViewById(R.id.nacionDetallePiloto);
        dorsalDetalle=view.findViewById(R.id.dorsalDetallePiloto);
        victoriasDetalle=view.findViewById(R.id.victoriasDetallePiloto);
        titulosDetalle=view.findViewById(R.id.titulosDetallePiloto);
        Bundle objetoPiloto = getArguments();
        Piloto piloto= null;
        if(objetoPiloto!=null){
            piloto=(Piloto) objetoPiloto.getSerializable("objeto");
            nombreDetalle.setText(piloto.getNombre());
            imagenDetalle.setImageResource(piloto.getImagenId());
            nacionDetalle.setText(piloto.getNacionalidad());
            dorsalDetalle.setText(String.valueOf(piloto.getDorsal()));
            victoriasDetalle.setText(String.valueOf(piloto.getVictorias()));
            titulosDetalle.setText(String.valueOf(piloto.getTitulos()));
        }

        return view;
    }
}
