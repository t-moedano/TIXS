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
import com.tixs.database.Bairro;
import com.tixs.database.Condutor;
import com.tixs.database.Van;

import java.util.ArrayList;
import java.util.List;

public class AdicionarBairroActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private EditText nomeBairroEditText;
    ListView bairrosListView;

    private List escolaLista = new ArrayList<>();
    ArrayAdapter<Bairro> bairrosArrayAdapter;

    Van van;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_bairro);
        nomeBairroEditText = (EditText) findViewById(R.id.nomeBairroEditText);
        bairrosListView = (ListView) findViewById(R.id.bairrosListView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        van = HomeActivity.condutorLogado.vans.get(bundle.getInt("vanSelecionada"));

        bairrosArrayAdapter = new ArrayAdapter<Bairro>(this, R.layout.activity_simple_text_view, van.bairros);
        bairrosListView.setAdapter(bairrosArrayAdapter);

    }


    public void bntAdicionarEscolaClick(View view) {
        Condutor condutor = HomeActivity.condutorLogado;
        String escolaNome = nomeBairroEditText.getText().toString();
        Bairro bairro = new Bairro();
        bairro.nome = escolaNome;
        bairro.id = FirebaseDatabase.getInstance().getReference("escolas").push().getKey();
        van.addBairro(bairro);
        FirebaseDatabase.getInstance().getReference("bairros").child(bairro.id).setValue(bairro);
        FirebaseDatabase.getInstance().getReference("vans").child(van.id).setValue(van);
        FirebaseDatabase.getInstance().getReference("condutores").child(condutor.id).setValue(condutor);
//        Intent i = new Intent(AdicionarEscolaActivity.this, MainActivity.class);
//        startActivity(i);
        Toast.makeText(AdicionarBairroActivity.this, "bairro Adicionado", Toast.LENGTH_LONG).show();
        finish();
    }
}

