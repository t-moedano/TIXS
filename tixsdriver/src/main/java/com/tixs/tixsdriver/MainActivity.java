package com.tixs.tixsdriver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Condutor;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtSenha;
    CheckBox rememberCheckBox;
    private FirebaseAuth firebaseAuth;
    FirebaseApp firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail = (EditText) findViewById(R.id.emailTxtEdit);
        txtSenha = (EditText) findViewById(R.id.senhaTxtEdit);
        rememberCheckBox = (CheckBox) findViewById(R.id.rememberCheckBox);
        firebaseAuth = FirebaseAuth.getInstance();

        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
        txtEmail.setText(sp.getString("email", ""), TextView.BufferType.EDITABLE);
        txtSenha.setText(sp.getString("pass", ""), TextView.BufferType.EDITABLE);
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
                            if (rememberCheckBox.isActivated()) {
                                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                                SharedPreferences.Editor spe = sp.edit();
                                spe.putString("email", txtEmail.getText().toString());
                                spe.putString("pass", txtSenha.getText().toString());
                                spe.commit();
                            }
                            FirebaseDatabase.getInstance().getReference("condutor")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            Condutor c = (Condutor) dataSnapshot.getValue(Condutor.class);
                                            c.id = dataSnapshot.getKey();
                                            HomeActivity.condutorLogado = c;
//                                            c.carregarVans();
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
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
