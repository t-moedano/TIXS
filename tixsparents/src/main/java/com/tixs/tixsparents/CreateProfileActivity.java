package com.tixs.tixsparents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.tixs.database.Crianca;
import com.tixs.database.Responsavel;

import java.util.Collections;

public class CreateProfileActivity extends AppCompatActivity
{
    private EditText nome;
    private EditText sobrenome;
    private EditText telefone;
    private EditText rua;
    private EditText numero;
    private EditText bairro;
    private EditText cpf;
    private EditText cep;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        nome = (EditText) findViewById(R.id.nomeTxtEdit);
        sobrenome = (EditText) findViewById(R.id.sobrenomeTxtEdit);
        telefone = (EditText) findViewById(R.id.telefoneTxtEdit);
        rua = (EditText) findViewById(R.id.ruaTxtEdit);
        numero = (EditText) findViewById(R.id.numeroTxtEdit);
        bairro = (EditText) findViewById(R.id.bairroTxtEdit);
        cpf = (EditText) findViewById(R.id.cpfTextEdit);
        cep = (EditText) findViewById(R.id.cepTxtEdit);
    }

    public void bntEnviarClick(View v)
    {

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Responsavel responsavel = new Responsavel(id, nome.getText().toString(), sobrenome.getText().toString(),
                cpf.getText().toString(), telefone.getText().toString(), rua.getText().toString(),
                bairro.getText().toString(), numero.getText().toString(), cep.getText().toString(),
                Collections.<Crianca>emptyList());

        FirebaseDatabase.getInstance().getReference("responsaveis").child(id).setValue(responsavel);
//        Intent i = new I ntent(CreateProfileActivity.this, MainActivity.class);
//        startActivity(i);
        finish();

    }
}
