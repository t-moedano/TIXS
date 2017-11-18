package com.tixs.tixsdriver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.tixs.database.Van;

import butterknife.ButterKnife;

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
    ListCheckInAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_condutor);
        ButterKnife.bind(this);

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
                mAdapter = new ListCheckInAdapter(CheckInCondutorActivity.this, vanSelecionada.criancas);
                mAdapter.van = vanSelecionada;
                mAdapter.condutor = HomeActivity.condutorLogado;
                mListview.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}



