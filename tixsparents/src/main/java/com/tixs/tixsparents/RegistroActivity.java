package com.tixs.tixsparents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtSenha;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtEmail = (EditText) findViewById(R.id.emailTxtEditR);
        txtSenha = (EditText) findViewById(R.id.senhaTxtEditR);
        firebaseAuth = firebaseAuth.getInstance();

    }

    public void bntRegistrarRClick(View v)
    {
        final ProgressBar progressBar = new ProgressBar(RegistroActivity.this, null, android.R.attr.progressBarStyleHorizontal);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtSenha.getText().toString())).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegistroActivity.this, "Registrado com Sucesso", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegistroActivity.this, CreateProfileActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(RegistroActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}
