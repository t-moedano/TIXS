package com.tixs.tixsdriver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tixs.database.Bairro;
import com.tixs.database.Escola;

public class AdicionarEscolaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private EditText nome;
    private EditText bairro;
    //private EditText rua;
    //private EditText numero;
    //private EditText cep;

    private Escola nova_escola = new Escola();
    private Bairro novo_bairro = new Bairro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
        nome = (EditText) findViewById(R.id.nome_escola);
        bairro = (EditText) findViewById(R.id.bairro_ponto);
        //rua = (EditText) findViewById(R.id.rua_escola);
        //numero = (EditText) findViewById(R.id.numero_escola);
        //cep = (EditText) findViewById(R.id.cep_escola);
        //firebaseAuth = firebaseAuth.getInstance();
        //mDatabase = FirebaseDatabase.getInstance().getReference();


    }
    public void bntbutton2Click(View v)
    {
        nova_escola.nome = nome.getText().toString();

        if (nova_escola.nome.length() == 0) {
            Toast.makeText(this, "Preencha o nome.", Toast.LENGTH_SHORT).show();
            return;
        }

        /***
         * Aqui fazer a adição ao array e ao BD.
         *
         *
         *
         *
         */


        //Intent i = new Intent(AdicionarEscolaActivity.this, CadastraCriancaActivity.class);
        //startActivity(i);
    }

    public void bntbutton3Click(View v)
    {
        novo_bairro.nome = bairro.getText().toString();
        if (novo_bairro.nome.length() == 0) {
            Toast.makeText(this, "Preencha o nome.", Toast.LENGTH_SHORT).show();
            return;
        }

        /***
         * Aqui fazer a adição ao array e ao BD.
         *
         *
         *
         *
         */

        //Intent i = new Intent(AdicionarEscolaActivity.this, CadastraCriancaActivity.class);
        //startActivity(i);
    }
}

