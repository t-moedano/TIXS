package com.tixs.maps;

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


    /**
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
