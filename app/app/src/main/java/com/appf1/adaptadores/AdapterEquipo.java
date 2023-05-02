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
import com.appf1.entidades.Equipo;

import java.util.ArrayList;

public class AdapterEquipo extends RecyclerView.Adapter<AdapterEquipo.ViewHolder> implements View.OnClickListener{

    ArrayList<Equipo>model;
    LayoutInflater inflater;
    private View.OnClickListener listener;
    public AdapterEquipo(Context context,ArrayList<Equipo>model) {
        this.inflater=LayoutInflater.from(context);
        this.model=model;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.lista_equipos,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre=model.get(position).getNombre();
        int imagen=model.get(position).getImagenId();
        holder.nombre.setText(nombre);
        holder.imagen.setImageResource(imagen);
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
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nombre=itemView.findViewById(R.id.tituloEquipo);
            imagen=itemView.findViewById(R.id.imagenEquipo);
        }
    }
}
