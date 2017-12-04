package com.tixs.tixsparents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Crianca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SolicitarNovoLocal extends AppCompatActivity {

    ListView filhoListView;
    ArrayAdapter<Crianca> criancaArrayAdapter;
    Boolean[] podeAlterar = new Boolean[HomeActivity.responsavelLogado.criancas.size()];
    EditText novoEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_novo_local);

        novoEndereco = (EditText) findViewById(R.id.emailTxtEditR);
        filhoListView = (ListView) findViewById(R.id.mudarEndList);
        filhoListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        criancaArrayAdapter = new ArrayAdapter<Crianca>(getApplicationContext(), R.layout.check_text_view, HomeActivity.responsavelLogado.criancas);
        filhoListView.setAdapter(criancaArrayAdapter);

        for (int i = 0; i < HomeActivity.responsavelLogado.criancas.size(); i++) {
            podeAlterar[i] = false;
        }

        filhoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                podeAlterar[position] = true;
            }
        });
    }

    public void btnSubmeter(View view) {

        if (filhoListView.getCheckedItemCount() > 0) {
            for (int i = 0; i < HomeActivity.responsavelLogado.criancas.size(); i++) {
                if(podeAlterar[i]){
                    FirebaseDatabase.getInstance().getReference("criancas").child(HomeActivity.responsavelLogado.criancas.get(i).id)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    Map<String, Object> postValues = new HashMap<String,Object>();
                                                                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                                                    Date date = new Date();
                                                                    postValues.put("mudouEndereco", dateFormat.format(date).toString());
                                                                    postValues.put("novoEndereco", novoEndereco.getText().toString());
                                                                    dataSnapshot.getRef().updateChildren(postValues);
                                                                }
                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {}
                                                            }
                            );
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Selecine pelo menos uma criança.", Toast.LENGTH_LONG).show();
        }
    }
}