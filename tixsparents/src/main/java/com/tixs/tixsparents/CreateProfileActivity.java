package com.tixs.tixsparents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.tixs.database.Crianca;
import com.tixs.database.Endereco;
import com.tixs.database.Responsavel;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProfileActivity extends AppCompatActivity
{
    @BindView(R.id.nomeTxtEdit) public EditText nome;
    @BindView(R.id.sobrenomeTxtEdit) public EditText sobrenome;
    @BindView(R.id.telefoneTxtEdit) public EditText telefone;
    @BindView(R.id.ruaTxtEdit) public EditText rua;
    @BindView(R.id.numeroTxtEdit) public EditText numero;
    @BindView(R.id.bairroTxtEdit) public EditText bairro;
    @BindView(R.id.cpfTextEdit) public EditText cpf;
    @BindView(R.id.cepTxtEdit) public EditText cep;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        ButterKnife.bind(this);
    }

    public void bntEnviarClick(View v)
    {

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Responsavel responsavel = new Responsavel(id,
                nome.getText().toString(),
                sobrenome.getText().toString(),
                cpf.getText().toString(),
                telefone.getText().toString(),
                new Endereco(rua.getText().toString(),
                        bairro.getText().toString(),
                        new Integer(numero.getText().toString()),
                        new Integer(cep.getText().toString())),
                Collections.<Crianca>emptyList());

        FirebaseDatabase.getInstance().getReference("responsaveis").child(id).setValue(responsavel);
        Intent i = new Intent(CreateProfileActivity.this, MainActivity.class);
        startActivity(i);
        finish();

    }
}
