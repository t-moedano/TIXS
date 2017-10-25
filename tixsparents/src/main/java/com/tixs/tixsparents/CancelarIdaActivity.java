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
import android.widget.ListView;

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

    }
}
