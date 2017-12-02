package com.tixs.tixsdriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.tixs.database.Van;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaAceiteActivity extends AppCompatActivity {

    @BindView(R.id.listaAceiteListView) ListView listView;
    ArrayAdapter<Crianca> criancaArrayAdapter;
    Van van;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aceite);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        van = HomeActivity.condutorLogado.vans.get(bundle.getInt("vanSelecionada"));

        criancaArrayAdapter = new ArrayAdapter<Crianca>(getApplicationContext(), R.layout.check_text_view, van.filaDeAceite);
        listView.setAdapter(criancaArrayAdapter);

    }

    public void onButtonFilaDeAceiteDone(View view) {
        SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
        // vai de tras pra frente que nao havera problema de remecao no for
        for (int i = van.filaDeAceite.size()-1; i >= 0; i--) {
            if (sparseBooleanArray.get(i)) {
                van.addCrianca(van.filaDeAceite.get(i));
                van.filaDeAceite.remove(i);
            }
        }
        HomeActivity.condutorLogado.addVan(van);
        FirebaseDatabase.getInstance().getReference("condutores").child(van.condutorID)
                .setValue(HomeActivity.condutorLogado);
        FirebaseDatabase.getInstance().getReference("vans").child(van.id)
                .setValue(van);

        Toast.makeText(getApplicationContext(), "Adicionada com sucesso", Toast.LENGTH_LONG).show();
        finish();
    }
}
