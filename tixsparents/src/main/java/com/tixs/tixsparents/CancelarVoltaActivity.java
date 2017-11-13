package com.tixs.tixsparents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Condutor;
import com.tixs.database.Crianca;
import com.tixs.database.Van;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aline on 13/10/17.
 */

public class CancelarVoltaActivity extends AppCompatActivity {

    @BindView(R.id.filhoListView) ListView filhoListView;

    ArrayAdapter<Crianca> criancaArrayAdapter;
    Boolean[] podeCancelar = new Boolean[HomeActivity.responsavelLogado.criancas.size()];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_ida);
        ButterKnife.bind(this);

//        filhoListView = (ListView) findViewById(R.id.filhoListView);

        filhoListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        criancaArrayAdapter = new ArrayAdapter<Crianca>(getApplicationContext(), R.layout.check_text_view, HomeActivity.responsavelLogado.criancas);
        filhoListView.setAdapter(criancaArrayAdapter);
        for (int i = 0; i < HomeActivity.responsavelLogado.criancas.size(); i++) {
            Crianca c = HomeActivity.responsavelLogado.criancas.get(i);
            podeCancelar[i] = c.confirma_ida;
            filhoListView.setItemChecked(i, false);

        }

        filhoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Crianca c = criancaArrayAdapter.getItem(position);
                if (podeCancelar[position])
                    c.confirma_ida = !c.confirma_ida;
                else {
                    view.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Essa crianca ja nao vai", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void bntCancelar(View view) {
        final ArrayList<Van> vans = new ArrayList<>();
        final ArrayList<Condutor> condutores = new ArrayList<>();
        final HashMap<String, String> vansDasCriancas = new HashMap<>();
        final HashMap<String, String> condutoresDasVans = new HashMap<>();
        if (filhoListView.getCheckedItemCount() > 0) {
            for (final Crianca c : HomeActivity.responsavelLogado.criancas) {
                // mudanca na crianca
                FirebaseDatabase.getInstance().getReference("criancas").child(c.id).setValue(c);
                // Mudança na van
                FirebaseDatabase.getInstance().getReference("vans").child(c.vanID).
                        addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(final DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    final Van van = (Van) dataSnapshot.getValue(Van.class);
                                    // atualize a crianca dentro da van se necessario ou apenas
                                    // adicione uma nova.
                                    final Van vanadd = saveChildInVan(vans, van, c);
                                    FirebaseDatabase.getInstance().getReference("vans")
                                            .child(c.vanID).setValue(vanadd);
                                    // Muadanca no condutor da van
                                    FirebaseDatabase.getInstance().getReference("condutores")
                                            .child(van.condutorID).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot1) {
                                            // nomeie dataSnapshot1 porque sem numero ja tem no
                                            // primeiro cahamado
                                            if (dataSnapshot1.exists()) {
                                                Condutor condutor = (Condutor) dataSnapshot1.getValue(Condutor.class);
                                                condutor = saveVanCondutor(condutores, condutor, vanadd);
                                                FirebaseDatabase.getInstance().getReference("condutores")
                                                        .child(van.condutorID).setValue(condutor);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
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
            finish();
            Toast.makeText(getApplicationContext(), "Cancelamento com sucesso", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Selecine alguma crianca para cancelar", Toast.LENGTH_LONG).show();
        }
    }

    synchronized private Condutor saveVanCondutor(ArrayList<Condutor> condutores, Condutor condutor, Van van) {
        boolean found = false;
        for (Condutor c : condutores) {
            if (c.id.equals(condutor.id)) {
                condutor = c;
                found = true;
                break;
            }
        }
        if (!found) {
            condutores.add(condutor);
        }
        condutor.pushVan(van);
        return condutor;
    }

    synchronized private Van saveChildInVan(List<Van> vans, Van van, Crianca crianca) {
        boolean found = false;
        for (Van v : vans) {
            if (v.id.equals(van.id)) {
                van = v;
                found = true;
                break;
            }
        }
        if (!found) {
            vans.add(van);
        }
        van.pushCrianca(crianca);
        return van;
    }
}