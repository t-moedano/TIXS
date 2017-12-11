package com.tixs.tixsdriver;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.adapters.CriancaAdapter;
import com.tixs.database.Crianca;
import com.tixs.database.Endereco;
import com.tixs.database.Escola;
import com.tixs.database.Responsavel;
import com.tixs.database.Van;
import com.tixs.maps.MapBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tixs.tixsdriver.HomeActivity.condutorLogado;


public class CheckInVoltaActivity extends AppCompatActivity {

    TaskCompletionSource<Boolean> taskCompletionSource;

    Spinner vanSpinner;
    Button irMapaButton;
    Button irCheckInListButton;

    ArrayAdapter<Van> vanArrayAdapter;
    Van vanSelecionada;
    CriancaAdapter criancaArrayAdapter;
    ListView criancasListView;
    ArrayList<Boolean> criancasCanCheck;
    ArrayList<Responsavel> responsaveis;



    private LocationManager locationManager;
    private LocationListener listener;
    private String mprovider;
    List<String> enderecos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_ida);

        criancasCanCheck = new ArrayList<>();
        responsaveis = new ArrayList<>();

        criancasListView = (ListView) findViewById(R.id.criancasListView);
        vanSpinner = (Spinner) findViewById(R.id.vanSpinner);
        irMapaButton = (Button) findViewById(R.id.irMapaButton);
        irCheckInListButton = (Button) findViewById(R.id.irCheckInListButton);


        criancasListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        criancasListView.setItemsCanFocus(false);

//        irMapaButton.setEnabled(false);

        vanArrayAdapter = new ArrayAdapter<Van>(this, R.layout.selection_text_view, HomeActivity.condutorLogado.vans);
        vanSpinner.setAdapter(vanArrayAdapter);

        vanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                irMapaButton.setEnabled(false);
                vanSelecionada = (Van) vanSpinner.getSelectedItem();
                criancaArrayAdapter = new CriancaAdapter(getApplicationContext(), R.layout.check_text_view, vanSelecionada.criancas);
                criancasListView.setAdapter(criancaArrayAdapter);
//                responsaveis.clear();
                // Talvez tenha um jeito melhor do que ficar recriando vetor a cada selecionada,
                // mas por hora isso basta. No futuro, tentar deixar os items disabled.
                criancasCanCheck.clear();
                for (int j = 0; j < vanSelecionada.criancas.size(); j++) {
                    criancasListView.setItemChecked(j, vanSelecionada.criancas.get(j).confirma_volta);
                    criancasCanCheck.add(vanSelecionada.criancas.get(j).confirma_volta);

                }
                criancasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (criancasCanCheck.get(i)) {
                            Crianca c = (Crianca) adapterView.getAdapter().getItem(i);
                            c.confirma_volta = !c.confirma_volta;
                            criancasListView.setItemChecked(i, c.confirma_volta);
                        } else {
                            criancasListView.setItemChecked(i, false);
                            Toast.makeText(getApplicationContext(), "Crianca nao volta da escola hoje.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onButtonIrCheckInListButton(View view) {
        Intent i = new Intent(this, CheckInCondutorActivity.class);
        startActivity(i);
    }

    public void onButtonIrMapaCLick(View view) {

        Escola escolaTeste = vanSelecionada.criancas.get(0).escola;

        HashMap<Escola, List<Crianca>> map = criacaoListas(vanSelecionada.criancas);

        List<Endereco> lista = new ArrayList<>();

        for (Crianca c : map.get(escolaTeste)) {
            lista.add(c.endereco);
        }

        Uri gmmIntentUri = new MapBuilder()
                .header()
                .travelMode()
                .destino(condutorLogado.endereco.toString())
                .waypointsEnderecos(lista)
                .build();


        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        try {

            startActivity(intent);


            /************************************/

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            mprovider = locationManager.getBestProvider(criteria, false);

            listener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {

                        updateVanLocation(location, vanSelecionada.id);
                    }else {

                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {
                    Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(i);
                }
            };

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location location = locationManager.getLastKnownLocation(mprovider);
                if (location != null) {

                    updateVanLocation(location, vanSelecionada.id);
                }else {

                }
                locationManager.requestLocationUpdates(mprovider, 5000, 0, listener);
            }


            Toast.makeText(this, "teste", Toast.LENGTH_LONG).show();
        } catch (ActivityNotFoundException ex) {
            try {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(unrestrictedIntent);
            } catch (ActivityNotFoundException innerEx) {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void updateVanLocation(Location loc, String van) {
        final Location location = loc;
        final String vanId = van;
        FirebaseDatabase.getInstance().getReference("vans").child(vanId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        Map<String, Object> postValues = new HashMap<String,Object>();
                                                        postValues.put("latitude", location.getLatitude());
                                                        postValues.put("longitude", location.getLongitude());
                                                        FirebaseDatabase.getInstance().getReference("vans").child(vanId).updateChildren(postValues);
                                                    }
                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {}
                                                }
                );

        vanSelecionada.latitude = location.getLatitude();
        vanSelecionada.longitude = location.getLongitude();

    }

    public HashMap<Escola, List<Crianca>> criacaoListas(List<Crianca> criancas){
        HashMap<Escola, List<Crianca>> map = new HashMap<Escola, List<Crianca>>();
        for (Crianca c : criancas) {
            if(!map.containsKey(c.escola)) {
                map.put(c.escola, new ArrayList<Crianca>());
            }
            if(c.confirma_ida)
                map.get(c.escola).add(c);
        }
        return map;
    }


}