package com.tixs.tixsparents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Crianca;
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

    private Crianca crianca = new Crianca();

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

        escolaAdapter = new ArrayAdapter<Escola>(this, R.layout.selection_text_view, escolas);
        listaBuscaEscola.setAdapter(escolaAdapter);
        listaBuscaEscola.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                crianca.setEscola((Escola) adapterView.getItemAtPosition(i));
                escolaEditText.setText(crianca.escola.toString());
            }
        });


    }

    public void bntEnviarClick(View v) {
        crianca.nome = nome.getText().toString();
        crianca.sobrenome = sobrenome.getText().toString();
        crianca.horarioEntrada = horarioEntrada.getText().toString();
        crianca.horarioSaida = horarioSaida.getText().toString();
        if (crianca.nome.length() == 0) {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show();
            return;
        }
        if (crianca.sobrenome.length() == 0) {
            Toast.makeText(this, "Preencha o sobrenome", Toast.LENGTH_SHORT).show();
            return;
        }
        if (crianca.horarioEntrada.length() == 0) {
            Toast.makeText(this, "Preencha horario de entrada", Toast.LENGTH_SHORT).show();
            return;
        }
        if (crianca.horarioSaida.length() == 0) {
            Toast.makeText(this, "Preencha horario de saida", Toast.LENGTH_SHORT).show();
            return;
        }
        if (crianca.escola == null) {
            Toast.makeText(this, "Escolha uma escola", Toast.LENGTH_SHORT).show();
            return;
        }
        crianca.setResponsavel(HomeActivity.responsavelLogado);
        crianca.id = FirebaseDatabase.getInstance().getReference("criancas").push().getKey();
        crianca.endereco = HomeActivity.responsavelLogado.endereco;

        HomeActivity.responsavelLogado.addCrianca(crianca);
        FirebaseDatabase.getInstance().getReference("responsaveis").child(HomeActivity.responsavelLogado.id)
                .setValue(HomeActivity.responsavelLogado);
        FirebaseDatabase.getInstance().getReference("criancas").child(crianca.id).setValue(crianca)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Cadastro com Sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    }
        });
    }

    public void buscarEscolas(View v) {
        escolaAdapter.clear();
        /*Busca Escola*/
        // Read from the database
//        escolaAdapter.notifyDataSetChanged();
        FirebaseDatabase.getInstance().getReference("escolas")
                .orderByChild("nome")
                .startAt(escolaEditText.getText().toString().trim())
                .limitToLast(10)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot escolaSnap : dataSnapshot.getChildren()) {
                                Escola escola = escolaSnap.getValue(Escola.class);
                                escola.id = escolaSnap.getKey();
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
