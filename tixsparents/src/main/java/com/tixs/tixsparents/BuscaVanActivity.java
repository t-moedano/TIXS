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
import com.tixs.database.Condutor;
import com.tixs.database.Ponto;

import java.util.ArrayList;

public class BuscaVanActivity extends AppCompatActivity {

//    private ArrayAdapter<> vanAdapter;

    private EditText nomeEdit;
    private EditText bairroEdit;
    private Button buscarButton;
    private ArrayList<Condutor> condutores;
    private ArrayAdapter<Condutor> condutorAdapter;
    private  ListView listC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_van);

        nomeEdit = (EditText) findViewById(R.id.buscaNomeTxtEdit);
        bairroEdit = (EditText) findViewById(R.id.bairroTxtEdit);
        listC = (ListView) findViewById(R.id.condutorList);

        condutores = new ArrayList<>();
        condutorAdapter = new ArrayAdapter<Condutor>(this, R.layout.activity_simple_text_view, condutores);
        listC.setAdapter(condutorAdapter);
    }

    public void btnBuscar(View view) {
        condutorAdapter.clear();
        final Ponto bairro = new Ponto(bairroEdit.getText().toString());
        // procurar por nome
        FirebaseDatabase.getInstance().getReference("condutor")
                .orderByChild("nome")
                .startAt(nomeEdit.getText().toString())
                .limitToLast(100)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                Condutor condutor = snap.getValue(Condutor.class);
                                if (condutor.containsBairro(bairro.getNome())){
                                    condutorAdapter.add(condutor);
                                }
                            }
                            // procurar por bairro
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
