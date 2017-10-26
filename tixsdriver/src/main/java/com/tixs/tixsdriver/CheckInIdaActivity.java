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

//    @Override
//    public boolean isEnabled(int position) {
//        return this.getItem(position).confirma_ida;
//    }

}

public class CheckInIdaActivity extends AppCompatActivity {

    TaskCompletionSource<Boolean> taskCompletionSource;

    Spinner vanSpinner;
    Button irMapaButton;

    ArrayAdapter<Van> vanArrayAdapter;
    Van vanSelecionada;
    CriancaAdapter criancaArrayAdapter;
    ListView criancasListView;
    ArrayList<Boolean> criancasCanCheck;
    ArrayList<Responsavel> responsaveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_ida);

        criancasCanCheck = new ArrayList<>();
        responsaveis = new ArrayList<>();

        criancasListView = (ListView) findViewById(R.id.criancasListView);
        vanSpinner = (Spinner) findViewById(R.id.vanSpinner);
        irMapaButton = (Button) findViewById(R.id.irMapaButton);

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
                    criancasListView.setItemChecked(j, vanSelecionada.criancas.get(j).confirma_ida);
                    criancasCanCheck.add(vanSelecionada.criancas.get(j).confirma_ida);

                }
                criancasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (criancasCanCheck.get(i)) {
                            Crianca c = (Crianca) adapterView.getAdapter().getItem(i);
                            c.confirma_ida = !c.confirma_ida;
                            criancasListView.setItemChecked(i, c.confirma_ida);
                        } else {
                            criancasListView.setItemChecked(i, false);
                            Toast.makeText(getApplicationContext(), "Crianca nao vai a escola hoje.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
