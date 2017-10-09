package com.tixs.tixsdriver;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.tixs.database.Van;

/**
 * Created by aline on 07/10/17.
 */

public class CreateVanActivity extends Activity {

    EditText nomeVanEditText;
    EditText modeloVanEditText;
    EditText placaVanEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_van);

        nomeVanEditText = (EditText) findViewById(R.id.nomeVanEditText);
        modeloVanEditText = (EditText) findViewById(R.id.modeloVanEditText);
        placaVanEditText = (EditText) findViewById(R.id.placaVanEditText);

    }

    public void onAdicionarVanBtnClick(View view) {
        final Van van = new Van();
        van.setCondutor(HomeActivity.condutorLogado);
        van.id = FirebaseDatabase.getInstance().getReference("vans").push().getKey();
        van.nome = nomeVanEditText.getText().toString();
        van.modelo = modeloVanEditText.getText().toString();
        van.placa = placaVanEditText.getText().toString();
        FirebaseDatabase.getInstance().getReference("vans").child(van.id).setValue(van)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        HomeActivity.condutorLogado.addVan(van);
                        FirebaseDatabase.getInstance().getReference("condutores").child(HomeActivity.condutorLogado.id)
                                .setValue(HomeActivity.condutorLogado);
                        Toast.makeText(getApplicationContext(), "Van cadastrada com sucesso.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
//        Condutor condutor = HomeActivity.condutorLogado;
//        condutor.addVan(vanSelecionada);
//        FirebaseDatabase.getInstance().getReference("condutores").child(condutor.id).setValue(condutor);

    }
}
