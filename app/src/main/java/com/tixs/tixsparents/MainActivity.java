package com.tixs.tixsparents;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtSenha;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail = (EditText) findViewById(R.id.emailTxtEdit);
        txtSenha = (EditText) findViewById(R.id.senhaTxtEdit);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void cadastroTxtViewClick(View v)
    {
        Intent i = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(i);
    }

    public void btnLoginClick(View v)
    {
        (firebaseAuth.signInWithEmailAndPassword(txtEmail.getText().toString(), txtSenha.getText().toString())).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Login com Sucesso", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
