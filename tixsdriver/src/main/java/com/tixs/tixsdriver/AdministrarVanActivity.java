package com.tixs.tixsdriver;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;
import com.tixs.database.Condutor;
import com.tixs.database.Van;

/**
 * Created by aline on 07/10/17.
 */

public class AdministrarVanActivity extends Activity {

    EditText nomeVanEditText;
    EditText modeloVanEditText;
    EditText placaVanEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_van);

        nomeVanEditText = (EditText) findViewById(R.id.nomeVanEditText);
        modeloVanEditText = (EditText) findViewById(R.id.modeloVanEditText);
        placaVanEditText = (EditText) findViewById(R.id.placaVanEditText);

    }

    public void onAdicionarVanBtnClick(View view) {
        Condutor condutor = HomeActivity.condutorLogado;
        Van van = new Van();
        van.setCondutor(condutor);
        van.nome = nomeVanEditText.getText().toString();
        van.placa = placaVanEditText.getText().toString();
        van.modelo = modeloVanEditText.getText().toString();
        van.id = FirebaseDatabase.getInstance().getReference("van").push().getKey();
        FirebaseDatabase.getInstance().getReference("van").child(van.id).setValue(van);
        condutor.addVan(van);
        FirebaseDatabase.getInstance().getReference("condutor").child(condutor.id).setValue(condutor);
    }
}
