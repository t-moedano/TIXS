package com.tixs.tixsdriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tixs.database.Condutor;

public class HomeActivity extends AppCompatActivity {

    // representacao do condutor que logou no programa para ser usado em todas as atividades.
    public static Condutor condutorLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onAdicionarVan(View view) {
        Intent i = new Intent(this, CreateVanActivity.class);
        startActivity(i);
    }

    public void onAdicionarRota(View view) {
        Intent i = new Intent(this, AdicionarEscolaActivity.class);
        startActivity(i);
    }

    public void onAdministrarVans(View view) {
        Intent i = new Intent(this, AdministrarVanActivity.class);
        startActivity(i);
    }
}
