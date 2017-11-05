package com.tixs.tixsdriver;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.support.annotation.Nullable;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tixs.database.Crianca;
import com.tixs.database.Responsavel;
import com.tixs.database.Van;
import com.tixs.maps.EnderecoBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 29/10/2017.
 */

public class CheckInCondutorActivity extends AppCompatActivity {

    DatabaseReference dref;
    Spinner vanSpinner;
    Button encerrarCorrida;

    ArrayAdapter<Van> vanArrayAdapter;
    Van vanSelecionada;

    ListView mListview;
    listCheckInAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_condutor);

        vanSpinner = (Spinner) findViewById(R.id.vanSpinner);
        encerrarCorrida= (Button) findViewById(R.id.irMapaButton);

        vanSpinner = (Spinner) findViewById(R.id.vanSpinner);
        vanArrayAdapter = new ArrayAdapter<Van>(this, R.layout.selection_text_view, HomeActivity.condutorLogado.vans);
        vanSpinner.setAdapter(vanArrayAdapter);

        mListview = (ListView) findViewById(R.id.listSchool);

        vanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vanSelecionada = (Van) vanSpinner.getSelectedItem();
                mAdapter = new listCheckInAdapter(CheckInCondutorActivity.this, vanSelecionada.criancas);
                mListview.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}



