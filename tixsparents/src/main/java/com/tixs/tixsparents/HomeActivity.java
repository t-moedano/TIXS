package com.tixs.tixsparents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Responsavel;

public class HomeActivity extends AppCompatActivity {

    public static Responsavel responsavelLogado = new Responsavel();

    Button buscarVanButton;
    Button cadastrarCriancaButton;
    Button cancelamentoButton;

    boolean carregado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buscarVanButton = (Button) findViewById(R.id.buscarVanButton);
        cadastrarCriancaButton = (Button) findViewById(R.id.cadastrarCriancaButton);
        cancelamentoButton = (Button) findViewById(R.id.cancelamentoButton);

        carregado = false;
        buscarVanButton.setEnabled(false);
        cadastrarCriancaButton.setEnabled(false);
        cancelamentoButton.setEnabled(false);

        FirebaseDatabase.getInstance().getReference("responsaveis")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Responsavel r = (Responsavel) dataSnapshot.getValue(Responsavel.class);
                        r.id = dataSnapshot.getKey();
                        HomeActivity.responsavelLogado = r;
                        if (!carregado) {
                            carregado = true;
                            buscarVanButton.setEnabled(true);
                            cadastrarCriancaButton.setEnabled(true);
                            cancelamentoButton.setEnabled(true);
                        }
//                                            r.carregarCrianca();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void bntCadastraFilhoClick(View v)
    {
        Intent i = new Intent(HomeActivity.this, CadastraCriancaActivity.class);
        startActivity(i);
    }

    public void bntBuscarVan(View v)
    {
        Intent i = new Intent(HomeActivity.this, BuscaVanActivity.class);
        startActivity(i);
    }

    public void bntCancelar(View view) {
        Intent i = new Intent(HomeActivity.this, CancelarIdaActivity.class);
        startActivity(i);
    }

    public void btnMostrarVan(View view)
    {
        Intent i = new Intent(HomeActivity.this, MostrarLocalizacao.class);
        startActivity(i);
    }

}
