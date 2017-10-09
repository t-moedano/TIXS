package com.tixs.tixsparents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Condutor;
import com.tixs.database.Crianca;
import com.tixs.database.Rota;
import com.tixs.database.Van;

import java.util.ArrayList;

public class BuscaVanActivity extends AppCompatActivity {

//    private ArrayAdapter<> vanAdapter;

    private EditText nomeEdit;
    private EditText bairroEdit;
    private ArrayList<Van> vans;
    private ArrayAdapter<Van> vansAdapter;
    Integer vanSelecionada = -1;
    private ListView condutores;
    ArrayAdapter<Crianca> criancasArrayAdapter;
    Spinner criancaSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_van);

        nomeEdit = (EditText) findViewById(R.id.buscaNomeTxtEdit);
        bairroEdit = (EditText) findViewById(R.id.bairroTxtEdit);
        condutores = (ListView) findViewById(R.id.condutorList);
        criancaSpinner = (Spinner) findViewById(R.id.criancaSpinner);

        vans = new ArrayList<>();
        vansAdapter = new ArrayAdapter<Van>(this, R.layout.activity_simple_text_view, vans);
        condutores.setAdapter(vansAdapter);
        condutores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vanSelecionada = new Integer(i);
            }
        });

        criancasArrayAdapter = new ArrayAdapter<Crianca>(this, R.layout.activity_simple_text_view, HomeActivity.responsavelLogado.criancas);
        criancaSpinner.setAdapter(criancasArrayAdapter);
    }

    public void btnBuscar(View view) {
        vansAdapter.clear();
        final Rota bairro = new Rota(bairroEdit.getText().toString());
        // procurar por nome
        FirebaseDatabase.getInstance().getReference("vans")
                .orderByChild("nome")
                .startAt(nomeEdit.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                Van van = snap.getValue(Van.class);
                                van.id = snap.getKey();
                                if (van.containsBairro(bairro.nome)) {
                                    vansAdapter.add(van);
                                }
                            }
                            if (vansAdapter.getCount() == 0) {
                                Toast.makeText(getApplicationContext(), "Van nao encontrada", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            // Nao tem o nome
                            Toast.makeText(getApplicationContext(), "Van nao encontrada", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void onConfirmarButtonClick(View view) {
        if (vanSelecionada < 0) {
            Toast.makeText(getApplicationContext(), "Selecione uma van.", Toast.LENGTH_LONG).show();
        } else {
            Crianca crianca = (Crianca) criancaSpinner.getSelectedItem();
            final Van van = vans.get(vanSelecionada);
            van.addCrianca(crianca);
            FirebaseDatabase.getInstance().getReference("condutores").child(van.condutorID)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Condutor c = dataSnapshot.getValue(Condutor.class);
                                c.addVan(van);
                                FirebaseDatabase.getInstance().getReference("condutores").child(van.condutorID)
                                        .setValue(c);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
            FirebaseDatabase.getInstance().getReference("vans").child(van.id).setValue(van)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Adicionada com sucesso", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
        }

    }
}
