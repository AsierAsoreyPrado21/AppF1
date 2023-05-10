package com.appf1.NavDrawer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appf1.IComunicarFragment;
import com.appf1.R;
import com.appf1.adaptadores.AdapterEquipo;
import com.appf1.adaptadores.AdapterPiloto;
import com.appf1.entidades.Equipo;
import com.appf1.entidades.Piloto;

import java.util.ArrayList;

public class PilotosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PilotosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PilotosFragment newInstance(String param1, String param2) {
        PilotosFragment fragment = new PilotosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    //variables
    AdapterPiloto adapterPiloto;
    RecyclerView recyclerViewPiloto;
    ArrayList<Piloto> listaPilotos;
    //comunicar fragments
    Activity activity;
    IComunicarFragment interfaceComunicarFragment;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pilotos,container,false);

        recyclerViewPiloto= view.findViewById(R.id.recycler_viewPiloto);
        listaPilotos=new ArrayList<>();
        //cargar lista
        cargarLista();
        //mostrar datos
        mostrarDatos();
        return view;
    }

    private void mostrarDatos() {
        recyclerViewPiloto.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPiloto= new AdapterPiloto(getContext(),listaPilotos);
        recyclerViewPiloto.setAdapter(adapterPiloto);

        adapterPiloto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre= listaPilotos.get(recyclerViewPiloto.getChildAdapterPosition(view)).getNombre();
                Toast.makeText(getContext(),"Selecciono: "+nombre,Toast.LENGTH_LONG).show();
                interfaceComunicarFragment.enviarPiloto(listaPilotos.get(recyclerViewPiloto.getChildAdapterPosition(view)));
            }
        });
    }

    private void cargarLista() {
        listaPilotos.add(new Piloto("Max Verstappen",R.drawable.verstappen,"Holandesa",1,38,2));
        listaPilotos.add(new Piloto("Sergio Pérez",R.drawable.checo,"Mexicana",11,6,0));
        listaPilotos.add(new Piloto("Charles Leclerc",R.drawable.leclerc,"Monegasca",16,5,0));
        listaPilotos.add(new Piloto("Carlos Sainz",R.drawable.sainz,"Española",55,1,0));
        listaPilotos.add(new Piloto("Lewis Hamilton",R.drawable.hamilton,"Británica",44,103,7));
        listaPilotos.add(new Piloto("George Russell",R.drawable.russell,"Británica",63,1,0));
        listaPilotos.add(new Piloto("Esteban Ocon",R.drawable.ocon,"Francesa",31,1,0));
        listaPilotos.add(new Piloto("Pierre Gasly",R.drawable.gasly,"Francesa",10,1,0));
        listaPilotos.add(new Piloto("Lando Norris",R.drawable.norris,"Británica",4,0,0));
        listaPilotos.add(new Piloto("Oscar Piastri",R.drawable.piastri,"Australiana",81,0,0));
        listaPilotos.add(new Piloto("Lance Stroll",R.drawable.stroll,"Canadiense",18,0,0));
        listaPilotos.add(new Piloto("Fernando Alonso",R.drawable.alonso,"Española",14,32,2));
        listaPilotos.add(new Piloto("Guanyu Zhou",R.drawable.zhou,"China",24,0,0));
        listaPilotos.add(new Piloto("Valtteri Bottas",R.drawable.bottas,"Finlandesa",77,10,0));
        listaPilotos.add(new Piloto("Kevin Magnussen",R.drawable.magnussen,"Danesa",20,0,0));
        listaPilotos.add(new Piloto("Nico Hülkenberg",R.drawable.hulkenberg,"Alemana",27,0,0));
        listaPilotos.add(new Piloto("Yuki Tsunoda",R.drawable.yuki,"Japonesa",22,0,0));
        listaPilotos.add(new Piloto("Nyck de Vries",R.drawable.nyck,"Holandesa",21,0,0));
        listaPilotos.add(new Piloto("Logan Sargeant",R.drawable.sargeant,"Americana",2,0,0));
        listaPilotos.add(new Piloto("Alexander Albon",R.drawable.albon,"Tailandesa",23,0,0));



    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.activity=(Activity) context;
            interfaceComunicarFragment=(IComunicarFragment) this.activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}


