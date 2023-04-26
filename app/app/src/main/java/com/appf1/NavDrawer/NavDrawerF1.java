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

import com.appf1.InitActivity.InitActivity;
import com.appf1.R;
import com.google.android.material.navigation.NavigationView;

public class NavDrawerF1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Context context=this;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar_id);

        menu=navigationView.getMenu();
        navigationView.getHeaderView(0);
        navigationView.bringToFront();


        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle( this, drawerLayout, toolbar, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                Fragment fragmentHome = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, fragmentHome).commit();

                break;
            case R.id.nav_pilotos:
                Fragment fragmentPilotos = new PilotosFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, fragmentPilotos).commit();
                break;
            case R.id.nav_equipos:
                Fragment fragmentEquipos = new EquiposFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, fragmentEquipos).commit();

                break;
            case R.id.nav_perfil:
                Fragment fragmentPerfil = new PerfilFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, fragmentPerfil).commit();

                break;
            case R.id.nav_calendario:
                Fragment fragmentCalendario = new CalendarioFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, fragmentCalendario).commit();
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

}