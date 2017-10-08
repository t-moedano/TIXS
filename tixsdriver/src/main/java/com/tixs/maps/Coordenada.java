package com.tixs.maps;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * @author Thauany Moedano.
 * Classe que representa uma coordenada.
 */

public class Coordenada
{
    private double latitude;
    private double longitude;

    public Coordenada(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }
}
