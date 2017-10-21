package com.tixs.tixsdriver;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.tixs.database.Crianca;
import com.tixs.database.Responsavel;
import com.tixs.database.Van;
import com.tixs.maps.Coordenada;
import com.tixs.maps.CoordenadaListFactory;
import com.tixs.maps.EnderecoBuilder;
import com.tixs.maps.GPSTracker;

import java.util.ArrayList;
import java.util.List;

public class CheckInIdaActivity extends AppCompatActivity {

    TaskCompletionSource<Boolean> taskCompletionSource;

    Spinner vanSpinner;
    Button irMapaButton;

    ArrayAdapter<Van> vanArrayAdapter;
    Van vanSelecionada;
    ArrayAdapter<Crianca> criancaArrayAdapter;
    ListView criancasListView;
    ArrayList<Boolean> checkCriancas;
    ArrayList<Responsavel> responsaveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_ida);

        checkCriancas = new ArrayList<>();
        responsaveis = new ArrayList<>();

        criancasListView = (ListView) findViewById(R.id.criancasListView);
        vanSpinner = (Spinner) findViewById(R.id.vanSpinner);
        irMapaButton = (Button) findViewById(R.id.irMapaButton);

//        irMapaButton.setEnabled(false);

        vanArrayAdapter = new ArrayAdapter<Van>(this, R.layout.activity_simple_text_view, HomeActivity.condutorLogado.vans);
        vanSpinner.setAdapter(vanArrayAdapter);

        vanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                irMapaButton.setEnabled(false);
                vanSelecionada = (Van) vanSpinner.getSelectedItem();
                criancaArrayAdapter = new ArrayAdapter<Crianca>(getApplicationContext(), R.layout.check_text_view, vanSelecionada.criancas);
                criancasListView.setAdapter(criancaArrayAdapter);
                checkCriancas.clear();
                responsaveis.clear();
                for (int j = 0; j < vanSelecionada.criancas.size(); j++) {
                    checkCriancas.add(true);
                    criancasListView.setItemChecked(j, true);
//                    FirebaseDatabase.getInstance().getReference("responsavel").child(vanSelecionada.criancas.get(j).id)
//                            .addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    responsaveis.add((Responsavel) dataSnapshot.getValue(Responsavel.class));
//                                    taskCompletionSource.setResult(true);
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        criancasListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        criancasListView.setItemsCanFocus(false);

        criancasListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                checkCriancas.set(i, b);
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

//        criancaArrayAdapter = new ArrayAdapter<Crianca>(this, R.layout.activity_simple_text_view, )
    }


    public void onButtonIrMapaCLick(View view) {
        List<String> lista = new ArrayList<>();

        /*TO DO - Pegar as informações do banco*/






        Uri gmmIntentUri = new EnderecoBuilder()
                .header()
                .destino("Rua Lea Maria Brandao Russo")
                .travelMode()
                .waypointsString(lista)
                .build();


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
