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
import com.tixs.database.Endereco;
import com.tixs.database.Van;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdicionarBairroActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    private EditText nomeBairroEditText;
    @BindView(R.id.adicionar_bairro_list_view) ListView bairrosListView;

    private List escolaLista = new ArrayList<>();
    ArrayAdapter<Endereco> bairrosArrayAdapter;

    Van van;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_bairro);
        ButterKnife.bind(this);

        nomeBairroEditText = (EditText) findViewById(R.id.add_escola_nome_edit_text);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        van = HomeActivity.condutorLogado.vans.get(bundle.getInt("vanSelecionada"));

        bairrosArrayAdapter = new ArrayAdapter<Endereco>(getApplicationContext(), R.layout.selection_text_view, van.enderecos);
        bairrosListView.setAdapter(bairrosArrayAdapter);

    }

    public void bntAdicionarBairroClick(View view) {
        Endereco endereco = new Endereco();
        endereco.bairro = nomeBairroEditText.getText().toString();
        Condutor c = HomeActivity.condutorLogado;
        van.addEndereco(endereco);
        c.addVan(van);
        FirebaseDatabase.getInstance().getReference("condutores").child(c.id).setValue(c);
        FirebaseDatabase.getInstance().getReference("vans").child(van.id).setValue(van);
        Toast.makeText(AdicionarBairroActivity.this, "Endereco Adicionado", Toast.LENGTH_LONG).show();
        finish();
    }
}

