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

import com.google.firebase.database.FirebaseDatabase;
import com.tixs.database.Crianca;
import com.tixs.database.Responsavel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aline on 13/10/17.
 */

public class CancelarIdaActivity extends AppCompatActivity {

    ListView filhoListView;
    ArrayList<Boolean> criancasCanCheck;
    ArrayAdapter<Crianca> ArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_ida);

        filhoListView = (ListView) findViewById(R.id.filhoListView);
        criancasCanCheck = new ArrayList<>();

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
        for(Crianca c: HomeActivity.responsavelLogado.criancas) {
            FirebaseDatabase.getInstance().getReference("criancas").child(c.id).setValue(c);
        }



    }
}
