package com.tixs.tixsparents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastraCriancaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private EditText nome;
    private EditText sobrenome;
    private EditText horarioEntrada;
    private EditText horarioSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_crianca);
        nome = (EditText) findViewById(R.id.nomeTxtEdit);
        sobrenome = (EditText) findViewById(R.id.sobrenomeTxtEdit);
        horarioEntrada = (EditText) findViewById(R.id.entradaTxtEdit);
        horarioSaida = (EditText) findViewById(R.id.saidaTxtEdit);
        firebaseAuth = firebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void bntEnviarClick(View v)
    {

    }

    public void bntAdicionarEscolaClick(View v)
    {
        Intent i = new Intent(CadastraCriancaActivity.this, BuscaEscolaActivity.class);
        startActivity(i);
    }
}
