package com.tixs.maps;

import android.net.Uri;

import com.tixs.database.Endereco;

import java.util.List;

/**
 * @author Thauany Moedano.
 * Classe que devolve uma instância de um endereço do google maps.
 */

public class MapBuilder {

   private StringBuilder endereco;
   private Uri enderecoUri;
   public final String HEADER = "https://www.google.com/maps/dir/?api=1";
    public final String CODED_SEPARATOR = "%7C";
    public final String TRAVEL_MODE = "&travelmode=driving";

    /**
     * Adiciona o header na string de endereço.
     *
     * @return
     */
   public MapBuilder header()
   {
       endereco = new StringBuilder();
       endereco.append(HEADER);
       return this;
   }


    public MapBuilder origem(String origem) {
        String origemString = "&origin=" + Uri.encode(origem);
        endereco.append(origemString);
        return this;
    }

    /**
     * Adiciona o ponto de saída do endereço de origem.
     *
     * @param origem
     * @return
     */

    public MapBuilder origem(Coordenada origem) {
        String origemString = "&origin=" + origem.getLatitude() + "," + origem.getLongitude();
        endereco.append(origemString);
       return this;
    }

    public MapBuilder destino(String destino) {
        String destinoString = "&destination=" + Uri.encode(destino);
        endereco.append(destinoString);
        return this;
    }


    /**
     * Adiciona o ponto de chegada do endereço de destino.
     *
     * @param destino
     * @return
     */
    public MapBuilder destino(Coordenada destino) {
        String destinoString = "&destination=" + destino.getLatitude() + "," + destino.getLongitude();
       endereco.append(destinoString);
       return this;
    }

    public MapBuilder waypointsString(List<String> waypoints) {

        endereco.append("&waypoints=");
        for (String waypoint : waypoints) {
            String waypointString = Uri.encode(waypoint) + CODED_SEPARATOR;
            endereco.append(waypointString);
        }

        return this;
    }

    public MapBuilder waypointsEnderecos(List<Endereco> waypoints) {

        endereco.append("&waypoints=");

        for (Endereco waypoint : waypoints) {
            String waypointString = Uri.encode(waypoint.toString()) + CODED_SEPARATOR;
            endereco.append(waypointString);
        }
        return this;
    }

    /**
     * Adiciona uma série de pontos de parada
     * @param waypoints
     * @return
     */
   public MapBuilder waypoints(List<Coordenada> waypoints)
   {
       endereco.append("&waypoints=");
       for(Coordenada waypoint : waypoints)
       {
           String waypointString = waypoint.getLatitude() + "," + waypoint.getLongitude() + "|";
           endereco.append(waypointString);
       }
       return this;
   }


    /**
     * Configura o modo de partida.
     *
     * @return
     */
    public MapBuilder travelMode() {
        endereco.append(TRAVEL_MODE);
        return this;
    }

    /**
     * Constrói uma instância Uri (URL do endereço)
     * @return retorna a instância de endereço do google maps.
     */
   public Uri build()
   {
       enderecoUri = Uri.parse(endereco.toString());
       return enderecoUri;
   }
}