package com.tixs.tixsdriver;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tixs.maps.Coordenada;
import com.tixs.maps.CoordenadaListFactory;
import com.tixs.maps.EnderecoBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void onAdicionarRota(View view) {
        Intent i = new Intent(this, AdicionarEscolaActivity.class);
        startActivity(i);
    }

    public void bntMapa(View view) {


        List<String> lista = new ArrayList<>();
        /*TO DO - Pegar as informações do banco*/
        lista.add("Rua Matias Peres");
        lista.add("Rua Lea Maria Brandao Russo");
        lista.add("Rua Cassiopeia São José dos Campos");

        List<Coordenada> coordenadas = CoordenadaListFactory.createCoordenadas(lista, this);
        Coordenada coordenadaOrigem = coordenadas.get(0);
        Coordenada coordenadaDestino = coordenadas.get(coordenadas.size() - 1);

        coordenadas.remove(0);
        coordenadas.remove(coordenadas.size() - 1);

        Uri gmmIntentUri = new EnderecoBuilder()
                            .header()
                            .origem(coordenadaOrigem)
                            .destino(coordenadaDestino)
                            .waypoints(coordenadas)
                            .travelMode()
                            .build();


      /*  Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1" +
                "&origin=" + fromLat + "," + fromLon + "&" +
                "destination=" + toLat + "," + toLon +
                "&waypoints=" + thirdLat + "," + thirdLon +
                "&travelmode=driving");*/

        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(unrestrictedIntent);
            } catch (ActivityNotFoundException innerEx) {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }


}
