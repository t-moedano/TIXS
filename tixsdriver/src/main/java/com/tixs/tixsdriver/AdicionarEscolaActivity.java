package com.tixs.tixsdriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tixs.database.Condutor;
import com.tixs.database.Escola;
import com.tixs.database.Van;

import java.util.ArrayList;
import java.util.List;

public class AdicionarEscolaActivity extends AppCompatActivity {

    //    private String id;
//    private String rua;
//    private String sobrenome;
//    private String cpf;
//    private String telefone;
//    private String rua;
//    private String bairro;
//    private String numero;
//    private String cep;
//    private String modelo;
//    private String placa;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private EditText nomeEscolaEditText;
    ListView escolaListView;

    private List escolaLista = new ArrayList<>();
    ArrayAdapter<Escola> escolaArrayAdapter;
    private List bairroLista = new ArrayList<>();

    Van van;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_escola);
        nomeEscolaEditText = (EditText) findViewById(R.id.nomeBairroEditText);
        escolaListView = (ListView) findViewById(R.id.bairrosListView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        van = HomeActivity.condutorLogado.vans.get(bundle.getInt("vanSelecionada"));



    }


    public void bntAdicionarEscolaClick(View view) {
        Condutor condutor = HomeActivity.condutorLogado;
        String escolaNome = nomeEscolaEditText.getText().toString();
        Escola escola = new Escola();
        escola.nome = escolaNome;
        escola.id = FirebaseDatabase.getInstance().getReference("escolas").push().getKey();
        van.addEscola(escola);
        FirebaseDatabase.getInstance().getReference("escolas").child(escola.id).setValue(escola);
        FirebaseDatabase.getInstance().getReference("vans").child(van.id).setValue(van);
        FirebaseDatabase.getInstance().getReference("condutores").child(condutor.id).setValue(condutor);
//        Intent i = new Intent(AdicionarEscolaActivity.this, MainActivity.class);
//        startActivity(i);
        Toast.makeText(AdicionarEscolaActivity.this, "Escola Adicionada", Toast.LENGTH_LONG).show();
        finish();
    }
}

