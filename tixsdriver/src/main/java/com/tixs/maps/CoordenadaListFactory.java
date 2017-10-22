package com.tixs.maps;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tixs.utils.ErrorDictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author.
 * Factory dedicada a criação de instâncias de coordenadas.
 */

public class CoordenadaListFactory
{
    public static int NUMERO_DE_ENDERECOS_DE_RESPOSTA = 1;
    /**
     * Dado uma lista de endereços, o método retorna uma lista de coordenadas correspondentes.
     * @param enderecos lista de endereços
     * @param activity classe activity que dispara o método
     * @return lista de coordenadas
     */
    public static List<Coordenada> createCoordenadas (List<String> enderecos, AppCompatActivity activity)
    {
        Geocoder geoCoder = new Geocoder(activity.getApplicationContext(), Locale.getDefault());
        List<Address> addresses = null;
        List<Coordenada> coordenadas = new ArrayList<>();
        double latitude, longitude;

        for(String endereco : enderecos)
        {
            try
            {
                addresses = geoCoder.getFromLocationName(endereco, NUMERO_DE_ENDERECOS_DE_RESPOSTA);

            } catch (IOException e)
            {
                Log.d(CoordenadaListFactory.class.getSimpleName(), ErrorDictionary.ADDRESS_NOT_FOUND);
            }
            latitude = addresses.get(0).getLatitude();
            longitude = addresses.get(0).getLongitude();
            Coordenada coordenada = new Coordenada(latitude, longitude);
            coordenadas.add(coordenada);
        }
        return coordenadas;
    }
}
