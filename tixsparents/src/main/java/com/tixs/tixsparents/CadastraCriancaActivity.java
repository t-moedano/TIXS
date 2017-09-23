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
import com.tixs.database.Escola;

import java.util.ArrayList;
import java.util.Timer;

public class CadastraCriancaActivity extends AppCompatActivity {

    private EditText nome;
    private EditText sobrenome;
    private EditText horarioEntrada;
    private EditText horarioSaida;
    private EditText escolaEditText;
    private ListView listaBuscaEscola;
    private Button buscarEscolaButton;
    private ArrayList<Escola> escolas;
    private ArrayAdapter<Escola> escolaAdapter;
    private Timer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_crianca);
        nome = (EditText) findViewById(R.id.nomeTxtEdit);
        sobrenome = (EditText) findViewById(R.id.sobrenomeTxtEdit);
        horarioEntrada = (EditText) findViewById(R.id.entradaTxtEdit);
        horarioSaida = (EditText) findViewById(R.id.saidaTxtEdit);
        listaBuscaEscola = (ListView) findViewById(R.id.listaBuscaEscola);
        escolaEditText = (EditText) findViewById(R.id.escolaEditText);
        buscarEscolaButton = (Button) findViewById(R.id.buscarEscolaButton);

        escolas = new ArrayList<Escola>();

        escolaAdapter = new ArrayAdapter<Escola>(this, R.layout.activity_simple_text_view, escolas);
        listaBuscaEscola.setAdapter(escolaAdapter);
//        listaBuscaEscola.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });

    }

    public void bntEnviarClick(View v) {

    }

    public void buscarEscolas(View v) {
        escolaAdapter.clear();
        /*Busca Escola*/
        // Read from the database
//        escolaAdapter.notifyDataSetChanged();
        FirebaseDatabase.getInstance().getReference("escolas").orderByChild("nome")
                .startAt(escolaEditText.getText().toString().trim())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot escolaSnap : dataSnapshot.getChildren()) {
                                Escola escola = escolaSnap.getValue(Escola.class);
                                escolaAdapter.add(escola);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Escola nao cadastrada", Toast.LENGTH_LONG).show();
                        }
                    }

                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

    }

}
