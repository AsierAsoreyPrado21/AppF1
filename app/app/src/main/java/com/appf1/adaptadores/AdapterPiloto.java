package com.appf1.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appf1.R;
import com.appf1.entidades.Piloto;

import java.util.ArrayList;

public class AdapterPiloto extends RecyclerView.Adapter<AdapterPiloto.ViewHolder> implements View.OnClickListener{


    ArrayList<Piloto>model;
    LayoutInflater inflater;
    private View.OnClickListener listener;
    public AdapterPiloto(Context context,ArrayList<Piloto>model) {
        this.inflater=LayoutInflater.from(context);
        this.model=model;

    }

    @NonNull
    @Override
    public AdapterPiloto.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.lista_pilotos,parent,false);
        view.setOnClickListener(this);
        return new AdapterPiloto.ViewHolder(view);
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPiloto.ViewHolder holder, int position) {
        String nombre=model.get(position).getNombre();
        int imagen=model.get(position).getImagenId();
        String nacionalidad=model.get(position).getNacionalidad();
        String dorsal=model.get(position).getNacionalidad();
        String victorias=model.get(position).getNacionalidad();
        String titulos=model.get(position).getNacionalidad();

        holder.nombre.setText(nombre);
        holder.imagen.setImageResource(imagen);
        holder.nacionalidad.setText(nacionalidad);
        holder.dorsal.setText(dorsal);
        holder.victorias.setText(victorias);
        holder.titulos.setText(titulos);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        ImageView imagen;
        TextView nacionalidad;
        TextView dorsal;
        TextView victorias;
        TextView titulos;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nombre=itemView.findViewById(R.id.tituloPiloto);
            imagen=itemView.findViewById(R.id.imagenPiloto);

            nacionalidad=itemView.findViewById(R.id.nacionPiloto);
            nacionalidad.setVisibility(View.INVISIBLE);
            dorsal=itemView.findViewById(R.id.dorsalPiloto);
            dorsal.setVisibility(View.INVISIBLE);
            victorias=itemView.findViewById(R.id.victoriasPiloto);
            victorias.setVisibility(View.INVISIBLE);
            titulos=itemView.findViewById(R.id.titulosPiloto);
            titulos.setVisibility(View.INVISIBLE);

        }
    }
}
