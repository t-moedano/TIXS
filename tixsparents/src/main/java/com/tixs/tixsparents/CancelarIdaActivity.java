package com.tixs.tixsparents;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Condutor;
import com.tixs.database.Crianca;
import com.tixs.database.Responsavel;
import com.tixs.database.Van;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aline on 13/10/17.
 */

public class CancelarIdaActivity extends AppCompatActivity {

    ListView filhoListView;
    ArrayAdapter<Crianca> ArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_ida);

        filhoListView = (ListView) findViewById(R.id.filhoListView);

        filhoListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.check_text_view, HomeActivity.responsavelLogado.criancas);
        filhoListView.setAdapter(ArrayAdapter);
        for (int i = 0; i < HomeActivity.responsavelLogado.criancas.size(); i++) {
            Crianca c = HomeActivity.responsavelLogado.criancas.get(i);
            filhoListView.setItemChecked(i, c.confirma_ida);

        }

        filhoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Crianca c = HomeActivity.responsavelLogado.criancas.get(position);
                c.confirma_ida = !c.confirma_ida;
            }
        });
    }

    public void bntCancelar(View view) {
        if (filhoListView.getCheckedItemCount() > 0) {
            for (final Crianca c : HomeActivity.responsavelLogado.criancas) {
                // mudanca na crianca
                FirebaseDatabase.getInstance().getReference("criancas").child(c.id).setValue(c);
                // Mudança na van
                FirebaseDatabase.getInstance().getReference("vans").child(c.vanID).
                        addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    Van van = (Van) dataSnapshot.getValue(Van.class);
                                    // atualize a crianca dentro da van se necessario ou apenas
                                    // adicione uma nova.
                                    van.pushCrianca(c);
                                    FirebaseDatabase.getInstance().getReference("vans")
                                            .child(c.vanID).setValue(van);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
            // Mudança no responsavel
            FirebaseDatabase.getInstance().getReference("responsaveis").
                    child(HomeActivity.responsavelLogado.id).setValue(HomeActivity.responsavelLogado);
        } else {
            Toast.makeText(getApplicationContext(), "Selecine alguma crianca para cancelar", Toast.LENGTH_LONG).show();
        }
    }
}

