package com.appf1.NavDrawer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.appf1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Response;

public class ClasificacionFragment extends Fragment {

    private TextView textView;
    private ProgressBar progressBar;

    private class ObtenerPilotosTask extends AsyncTask<Void, Void, String> {

        private static final String URL = "https://ergast.com/api/f1/current/driverStandings.json";

        @Override
        protected String doInBackground(Void... voids) {
            String clasificacion = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(URL)
                        .build();

                Response response = client.newCall(request).execute();
                String json = response.body().string();

                JSONObject jsonObject = new JSONObject(json);
                JSONArray array = jsonObject.getJSONObject("MRData").getJSONObject("StandingsTable")
                        .getJSONArray("StandingsLists").getJSONObject(0).getJSONArray("DriverStandings");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject piloto = array.getJSONObject(i).getJSONObject("Driver");
                    String nombre = piloto.getString("givenName") + " " + piloto.getString("familyName");
                    int posicion = array.getJSONObject(i).getInt("position");
                    int puntos = array.getJSONObject(i).getInt("points");

                    clasificacion += posicion + ". " + nombre + " - " + puntos + " puntos\n";
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return clasificacion;
        }

        @Override
        protected void onPostExecute(String clasificacion) {
            textView.setText(clasificacion);
            progressBar.setVisibility(View.GONE); // ocultar ProgressBar cuando se completa la tarea
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clasificacion, container, false);

        textView = view.findViewById(R.id.clasificacion);

        //progressBAr
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE); // mostrar ProgressBar al inicio

        ObtenerPilotosTask task = new ObtenerPilotosTask();
        task.execute();

        return view;
    }
}
