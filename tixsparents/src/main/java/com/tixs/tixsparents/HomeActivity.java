package com.tixs.tixsparents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void bntCadastraFilhoClick(View v)
    {
        Intent i = new Intent(HomeActivity.this, CadastraCriancaActivity.class);
        startActivity(i);
    }

    public void bntBuscarVan(View v)
    {
        Intent i = new Intent(HomeActivity.this, BuscaVanActivity.class);
        startActivity(i);
    }
}
