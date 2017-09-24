package com.tixs.tixsdriver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdicionarEscolaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private EditText nome;
    private EditText rua;
    private EditText numero;
    private EditText bairro;
    private EditText cep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
        nome = (EditText) findViewById(R.id.nome_escola);
        rua = (EditText) findViewById(R.id.rua_escola);
        numero = (EditText) findViewById(R.id.numero_escola);
        bairro = (EditText) findViewById(R.id.bairro_escola);
        cep = (EditText) findViewById(R.id.cep_escola);
        firebaseAuth = firebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


    }
    public void bntbutton2Click(View v)
    {
        //Intent i = new Intent(AdicionarEscolaActivity.this, CadastraCriancaActivity.class);
        //startActivity(i);
    }
}

