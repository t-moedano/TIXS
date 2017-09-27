package com.tixs.tixsdriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tixs.database.Bairro;
import com.tixs.database.Condutor;
import com.tixs.database.Escola;

import java.util.ArrayList;
import java.util.List;

public class AdicionarEscolaActivity extends AppCompatActivity {

    private String id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String rua;
    private String bairro;
    private String numero;
    private String cep;
    private String modelo;
    private String placa;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private EditText nomeEscola;
    private EditText nomePonto;

    private List escolaLista = new ArrayList<>();
    private List bairroLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
        nomeEscola = (EditText) findViewById(R.id.nomeEscola);
        nomePonto = (EditText) findViewById(R.id.nomePonto);
        firebaseAuth = firebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        nome = bundle.getString("nome");
        sobrenome = bundle.getString("sobrenome");
        telefone = bundle.getString("telefone");
        rua = bundle.getString("rua");
        numero = bundle.getString("numero");
        bairro = bundle.getString("bairro");
        cpf = bundle.getString("cpf");
        cep = bundle.getString("cep");
        modelo = bundle.getString("modelo");
        placa = bundle.getString("placa");



    }

    public void bntEscolaClick(View v)
    {
        String escolaNome = nomeEscola.getText().toString();
        Escola escola = new Escola(escolaNome);
        escolaLista.add(escola);
        Toast.makeText(AdicionarEscolaActivity.this, "Escola Adicionada", Toast.LENGTH_LONG).show();
    }

    public void bntBairroClick(View v)
    {
        String bairroNome = nomePonto.getText().toString();
        Bairro bairro = new Bairro(bairroNome);
        bairroLista.add(bairro);
        Toast.makeText(AdicionarEscolaActivity.this, "Bairro adicionado", Toast.LENGTH_LONG).show();

    }

    public void bntEnviarClick(View v)
    {
        String id = firebaseAuth.getCurrentUser().getUid();

        Condutor condutor = new Condutor(id, nome, sobrenome, cpf, telefone, rua, bairro, numero, cep,
                modelo, placa, bairroLista, escolaLista);

        mDatabase.child("condutor").child(id).setValue(condutor);
        Intent i = new Intent(AdicionarEscolaActivity.this, MainActivity.class);
        startActivity(i);
    }


}

