package com.tixs.tixsdriver;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

class CriancaAdapter extends ArrayAdapter<Crianca> {

    public CriancaAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public CriancaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Crianca> objects) {
        super(context, resource, objects);
    }

    @Override
    public boolean isEnabled(int position) {
        return this.getItem(position).confirma_ida;
    }
}

public class CheckInIdaActivity extends AppCompatActivity {

    TaskCompletionSource<Boolean> taskCompletionSource;

    Spinner vanSpinner;
    Button irMapaButton;

    ArrayAdapter<Van> vanArrayAdapter;
    Van vanSelecionada;
    CriancaAdapter criancaArrayAdapter;
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

        vanArrayAdapter = new ArrayAdapter<Van>(this, R.layout.selection_text_view, HomeActivity.condutorLogado.vans);
        vanSpinner.setAdapter(vanArrayAdapter);

        vanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                irMapaButton.setEnabled(false);
                vanSelecionada = (Van) vanSpinner.getSelectedItem();
                criancaArrayAdapter = new CriancaAdapter(getApplicationContext(), R.layout.check_text_view, vanSelecionada.criancas);
                criancasListView.setAdapter(criancaArrayAdapter);
                checkCriancas.clear();
                responsaveis.clear();
                for (int j = 0; j < vanSelecionada.criancas.size(); j++) {
                    checkCriancas.add(vanSelecionada.criancas.get(j).confirma_ida);
                    criancasListView.setItemChecked(j, vanSelecionada.criancas.get(j).confirma_ida);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        criancasListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        criancasListView.setItemsCanFocus(false);

        criancasListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                checkCriancas.set(i, !checkCriancas.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        criancasListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
//            @Override
//            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
//                checkCriancas.set(i, b && vanSelecionada.criancas.get(i).confirma_ida);
//            }
//
//            @Override
//            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//                return false;
//            }
//
//            @Override
//            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
//                return false;
//            }
//
//            @Override
//            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//                int i = menuItem.getItemId();
//                return false;
//            }
//
//            @Override
//            public void onDestroyActionMode(ActionMode actionMode) {
//
//            }
//        });

//        criancaArrayAdapter = new ArrayAdapter<Crianca>(this, R.layout.selection_text_view, )
    }


    public void onButtonIrMapaCLick(View view) {
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
