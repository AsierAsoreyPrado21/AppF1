package com.appf1.NavDrawer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.appf1.IComunicarFragment;
import com.appf1.InitActivity.InitActivity;
import com.appf1.R;
import com.appf1.entidades.Equipo;
import com.appf1.fragment.DetalleEquipoFragment;
import com.google.android.material.navigation.NavigationView;

public class NavDrawerF1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IComunicarFragment {

    private Context context=this;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Menu menu;

    //variables detalleEquipo
    DetalleEquipoFragment detalleEquipoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar_id);
        //setSupportActionBar(toolbar);

        menu=navigationView.getMenu();
        navigationView.getHeaderView(0);
        navigationView.bringToFront();


        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle( this, drawerLayout, toolbar, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        /*MenuItem menuItem = navigationView.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);*/

        // Establecer el fragmento Home como el fragmento seleccionado

        Fragment fragmentPortada = new PortadaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentPortada).commit();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                Fragment fragmentHome = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentHome).commit();

                break;
            case R.id.nav_pilotos:
                Fragment fragmentPilotos = new PilotosFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentPilotos).commit();
                break;
            case R.id.nav_equipos:
                Fragment fragmentEquipos = new EquiposFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentEquipos).commit();

                break;
            case R.id.nav_perfil:
                Fragment fragmentPerfil = new PerfilFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentPerfil).commit();

                break;
            case R.id.nav_calendario:
                Fragment fragmentCalendario = new CalendarioFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentCalendario).commit();
                break;
            case R.id.nav_compras:
                String url = "https://tickets.formula1.com/es";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.nav_salir:
                Intent salir = new Intent(this, InitActivity.class);
                startActivity(salir);
                finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.isDrawerOpen(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void enviarEquipo(Equipo equipo) {
        detalleEquipoFragment= new DetalleEquipoFragment();
        //objeto que transporta la info
        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("objeto",equipo);
        detalleEquipoFragment.setArguments(bundleEnvio);
        //abrir fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detalleEquipoFragment).addToBackStack(null).commit();

    }
}