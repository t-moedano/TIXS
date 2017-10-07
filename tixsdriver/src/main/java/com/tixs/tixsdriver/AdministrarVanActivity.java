package com.tixs.tixsdriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tixs.database.Van;

import java.util.List;

/**
 * Created by aline on 07/10/17.
 */

public class AdministrarVanActivity extends Activity {

    EditText nomeVanEditText;
    EditText modeloVanEditText;
    EditText placaVanEditText;
    ListView vansListView;
    List<Van> vans;
    ArrayAdapter<Van> vansAdapter;
    Integer vanSelecionada = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_van);

        nomeVanEditText = (EditText) findViewById(R.id.nomeVanEditText);
        modeloVanEditText = (EditText) findViewById(R.id.modeloVanEditText);
        placaVanEditText = (EditText) findViewById(R.id.placaVanEditText);
        vansListView = (ListView) findViewById(R.id.vansListView);

        vans = HomeActivity.condutorLogado.vans;

        vansAdapter = new ArrayAdapter<Van>(this, R.layout.activity_simple_text_view, vans);
        vansListView.setAdapter(vansAdapter);
        vansListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vanSelecionada = new Integer(i);
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

//    public void onAdicionarVanBtnClick(View view) {
//        Condutor condutor = HomeActivity.condutorLogado;
//        Van vanSelecionada = new Van();
//        vanSelecionada.setCondutor(condutor);
//        vanSelecionada.nome = nomeVanEditText.getText().toString();
//        vanSelecionada.placa = placaVanEditText.getText().toString();
//        vanSelecionada.modelo = modeloVanEditText.getText().toString();
//        vanSelecionada.id = FirebaseDatabase.getInstance().getReference("vanSelecionada").push().getKey();
//        FirebaseDatabase.getInstance().getReference("vanSelecionada").child(vanSelecionada.id).setValue(vanSelecionada);
//        condutor.addVan(vanSelecionada);
//        FirebaseDatabase.getInstance().getReference("condutores").child(condutor.id).setValue(condutor);
//    }
}
