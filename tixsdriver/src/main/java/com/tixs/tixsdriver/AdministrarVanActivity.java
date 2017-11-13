package com.tixs.tixsdriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.tixs.database.Endereco;
import com.tixs.database.Escola;
import com.tixs.database.Van;

import java.util.List;

import butterknife.BindView;

/**
 * Created by aline on 07/10/17.
 */

public class AdministrarVanActivity extends Activity {

    EditText nomeVanEditText;
    EditText modeloVanEditText;
    EditText placaVanEditText;
    @BindView(R.id.spinnerVan) Spinner vansSpinner;
    ListView bairrosListView;
    ListView escolaListView;
    List<Van> vans;
    ArrayAdapter<Van> vansAdapter;
    Integer vanSelecionada = -1;
    ArrayAdapter<Endereco> bairrosArrayAdapter;
    ArrayAdapter<Escola> escolasArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_van);

        nomeVanEditText = (EditText) findViewById(R.id.nomeVanEditText);
        modeloVanEditText = (EditText) findViewById(R.id.modeloVanEditText);
        placaVanEditText = (EditText) findViewById(R.id.placaVanEditText);
        bairrosListView = (ListView) findViewById(R.id.vansListView);
        escolaListView = (ListView)findViewById(R.id.vansListView);

        vans = HomeActivity.condutorLogado.vans;

        vansAdapter = new ArrayAdapter<Van>(this, R.layout.selection_text_view, vans);
        vansSpinner.setAdapter(vansAdapter);
        vansSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vanSelecionada = new Integer(i);
                view.setSelected(true);
                Van van = vans.get(vanSelecionada);
                bairrosArrayAdapter = new ArrayAdapter<Endereco>(getApplicationContext(), R.layout.selection_text_view, van.enderecos);
                bairrosListView.setAdapter(bairrosArrayAdapter);
                escolasArrayAdapter = new ArrayAdapter<Escola>(getApplicationContext(), R.layout.selection_text_view, van.escolas);
                escolaListView.setAdapter(escolasArrayAdapter);
            }
        });




    }

    public void bntEscolaClick(View view) {
        if (vanSelecionada < 0) {
            Toast.makeText(getApplicationContext(), "Selecione uma Van primeiro.", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), AdicionarEscolaActivity.class);
            Bundle escolaBundle = new Bundle();
            escolaBundle.putInt("vanSelecionada", vanSelecionada);
            intent.putExtras(escolaBundle);
            startActivity(intent);
        }

    }

    public void bntBairroClick(View view) {
        if (vanSelecionada < 0) {
            Toast.makeText(getApplicationContext(), "Selecione uma Van primeiro.", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), AdicionarBairroActivity.class);
            Bundle bairroBundle = new Bundle();
            bairroBundle.putInt("vanSelecionada", vanSelecionada);
            intent.putExtras(bairroBundle);
            startActivity(intent);
        }
    }

}
