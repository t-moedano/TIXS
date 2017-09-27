package com.tixs.tixsdriver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tixs.database.Condutor;

public class CreateProfileActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private EditText nome;
    private EditText sobrenome;
    private EditText telefone;
    private EditText rua;
    private EditText numero;
    private EditText bairro;
    private EditText cpf;
    private EditText cep;
    private EditText modelo;
    private EditText placa;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        nome = (EditText) findViewById(R.id.nomeTxtEdit);
        sobrenome = (EditText) findViewById(R.id.sobrenomeTxtEdit);
        telefone = (EditText) findViewById(R.id.telefoneTxtEdit);
        rua = (EditText) findViewById(R.id.ruaTxtEdit);
        numero = (EditText) findViewById(R.id.numeroTxtEdit);
        bairro = (EditText) findViewById(R.id.bairroTxtEdit);
        cpf = (EditText) findViewById(R.id.cpfTextEdit);
        cep = (EditText) findViewById(R.id.cepTxtEdit);
        modelo = (EditText) findViewById(R.id.modeloVanTxtEdit);
        placa = (EditText) findViewById(R.id.placaTxtEdit);
        firebaseAuth = firebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }



    public void bntAdicionarEscolaClick(View v)
    {
        Bundle bundle = new Bundle();
        bundle.putString("nome", nome.getText().toString());
        bundle.putString("sobrenome", sobrenome.getText().toString());
        bundle.putString("telefone", telefone.getText().toString());
        bundle.putString("rua", rua.getText().toString());
        bundle.putString("numero", numero.getText().toString());
        bundle.putString("bairro", bairro.getText().toString());
        bundle.putString("cpf", cpf.getText().toString());
        bundle.putString("cep", cep.getText().toString());
        bundle.putString("modelo", modelo.getText().toString());
        bundle.putString("placa", placa.getText().toString());

        Intent i = new Intent(CreateProfileActivity.this, AdicionarEscolaActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }


}

