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

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdicionarEscolaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    @BindView(R.id.add_escola_nome_edit_text) EditText nomeEscolaEditText;
    @BindView(R.id.addEscolaRuaEditText) EditText ruaEditText;
    @BindView(R.id.addEscolaNumeroEditText) EditText numeroEditText;
    @BindView(R.id.addEscolaBairroEditText) EditText bairroEditText;
    @BindView(R.id.addEscolaCEPEditText) EditText cepEditText;

    private List escolaLista = new ArrayList<>();
    ArrayAdapter<Escola> escolaArrayAdapter;
    private List bairroLista = new ArrayList<>();

    Van van;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_escola);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        van = HomeActivity.condutorLogado.vans.get(bundle.getInt("vanSelecionada"));

    }


    public void bntAdicionarEscolaClick(View view) {
        Condutor condutor = HomeActivity.condutorLogado;
        Escola escola = new Escola();
        escola.nome = nomeEscolaEditText.getText().toString();
        escola.endereco.rua = ruaEditText.getText().toString();
        escola.endereco.bairro = bairroEditText.getText().toString();
        escola.endereco.numero = new Integer(numeroEditText.getText().toString());

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

