package com.tixs.tixsdriver;

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
import com.tixs.database.Condutor;

public class HomeActivity extends AppCompatActivity {


    // representacao do condutor que logou no programa para ser usado em todas as atividades.
    public static Condutor condutorLogado;

    Button iniciarIdaButton;
    Button adiministrarVanButton;
    Button addBanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iniciarIdaButton = (Button) findViewById(R.id.iniciarIdaButton);
        adiministrarVanButton = (Button) findViewById(R.id.adiministrarVanButton);
        addBanButton = (Button) findViewById(R.id.addBanButton);


        iniciarIdaButton.setEnabled(false);
        adiministrarVanButton.setEnabled(false);
        addBanButton.setEnabled(false);


        FirebaseDatabase.getInstance().getReference("condutores")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Condutor c = (Condutor) dataSnapshot.getValue(Condutor.class);
                        c.id = dataSnapshot.getKey();
                        HomeActivity.condutorLogado = c;

                        iniciarIdaButton.setEnabled(true);
                        adiministrarVanButton.setEnabled(true);
                        addBanButton.setEnabled(true);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        FirebaseDatabase.getInstance().getReference("condutores")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Condutor c = (Condutor) dataSnapshot.getValue(Condutor.class);
                        c.id = dataSnapshot.getKey();
                        HomeActivity.condutorLogado = c;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void onAdicionarVan(View view) {
        Intent i = new Intent(this, CreateVanActivity.class);
        startActivity(i);
    }

    public void onAdicionarRota(View view) {
        Intent i = new Intent(this, AdicionarEscolaActivity.class);
        startActivity(i);
    }

    public void onAdministrarVans(View view) {
        Intent i = new Intent(this, AdministrarVanActivity.class);
        startActivity(i);
    }

    public void onCheckinButtonClick(View view) {
        Intent i = new Intent(this, CheckInIdaActivity.class);
        startActivity(i);
    }


    public void onLocateVanButtonClick(View view) {
        Intent i = new Intent(this, LocateVanActivity.class);
        startActivity(i);
    }
}
