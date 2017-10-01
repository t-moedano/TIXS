package com.tixs.tixsparents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Rota;
import com.tixs.database.Van;

import java.util.ArrayList;

public class BuscaVanActivity extends AppCompatActivity {

//    private ArrayAdapter<> vanAdapter;

    private EditText nomeEdit;
    private EditText bairroEdit;
    private Button buscarButton;
    private ArrayList<Van> vans;
    private ArrayAdapter<Van> vansAdapter;
    private  ListView listC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_van);

        nomeEdit = (EditText) findViewById(R.id.buscaNomeTxtEdit);
        bairroEdit = (EditText) findViewById(R.id.bairroTxtEdit);
        listC = (ListView) findViewById(R.id.condutorList);

        vans = new ArrayList<>();
        vansAdapter = new ArrayAdapter<Van>(this, R.layout.activity_simple_text_view, vans);
        listC.setAdapter(vansAdapter);
    }

    public void btnBuscar(View view) {
        vansAdapter.clear();
        final Rota bairro = new Rota(bairroEdit.getText().toString());
        // procurar por nome
        FirebaseDatabase.getInstance().getReference("van")
                .orderByChild("nome")
                .startAt(nomeEdit.getText().toString())
                .limitToLast(100)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                final Van van = snap.getValue(Van.class);
                                van.id = snap.getKey();
                                van.carregarRotas();
                                if (van.containsBairro(bairro.nome)) {
                                    vansAdapter.add(van);
                                }
//                                for (String rid : van.rotasIDs) {
//                                    FirebaseDatabase.getInstance().getReference("bairros").child(rid)
//                                            .addListenerForSingleValueEvent(new ValueEventListener() {
//                                                @Override
//                                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                                    final Rota r = dataSnapshot.getValue(Rota.class);
//                                                    r.id = dataSnapshot.getKey();
//                                                    van.addRota(r);
//                                                    if (bairro.nome.length() == 0 || r.nome.contains(bairro.nome)){
//                                                        Toast.makeText(getApplicationContext(), "Achei", Toast.LENGTH_LONG).show();
//                                                        vansAdapter.add(van);
//                                                    }
//                                                }
//
//                                                @Override
//                                                public void onCancelled(DatabaseError databaseError) {
//
//                                                }
//                                            });
//                                }


                            }
//                            if (vansAdapter.getCount() == 0) {
//                                Toast.makeText(getApplicationContext(), "Van nao encontrada", Toast.LENGTH_LONG).show();
//                            }
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
}
