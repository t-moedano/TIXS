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
import com.tixs.database.Endereco;
import com.tixs.database.Van;

import java.util.ArrayList;
import java.util.List;

public class AdicionarBairroActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private EditText nomeBairroEditText;
    ListView bairrosListView;

    private List escolaLista = new ArrayList<>();
    ArrayAdapter<Endereco> bairrosArrayAdapter;

    Van van;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_bairro);
        nomeBairroEditText = (EditText) findViewById(R.id.addEscolaNomeEditText);
        bairrosListView = (ListView) findViewById(R.id.bairrosListView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        van = HomeActivity.condutorLogado.vans.get(bundle.getInt("vanSelecionada"));



    }



    public void bntAdicionarEscolaClick(View view) {

        Toast.makeText(AdicionarBairroActivity.this, "endereco Adicionado", Toast.LENGTH_LONG).show();
        finish();
    }
}

