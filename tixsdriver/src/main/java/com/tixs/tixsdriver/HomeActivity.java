package com.tixs.tixsdriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.database.Condutor;

public class HomeActivity extends AppCompatActivity {


    // representacao do condutor que logou no programa para ser usado em todas as atividades.
    public static Condutor condutorLogado;

    Button iniciarIdaButton;
    Button adiministrarVanButton;
    Button addBanButton;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToogle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // method invoked only when the actionBar is not null.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_menu);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){
                switch (menuItem.getItemId()){
                    case(R.id.iniciarIdaButton1):
                        Intent accountActivity = new Intent(getApplicationContext(), CheckInIdaActivity.class);
                        startActivity(accountActivity);
                        break;
                    case(R.id.btnIniciarVoltar1):
                        Intent accountActivity2 = new Intent(getApplicationContext(), CheckInVoltaActivity.class);
                        startActivity(accountActivity2);
                        break;
                    case(R.id.adiministrarVanButton1):
                        Intent accountActivity3 = new Intent(getApplicationContext(), AdministrarVanActivity.class);
                        startActivity(accountActivity3);
                        break;
                    case(R.id.addBanButton1):
                        Intent accountActivity4 = new Intent(getApplicationContext(), CreateVanActivity.class);
                        startActivity(accountActivity4);
                        break;
                    case(R.id.nav_locateVan1):
                        Intent accountActivity5 = new Intent(getApplicationContext(), LocateVanActivity.class);
                        startActivity(accountActivity5);
                        break;
                }
                return true;
            }
        });

        //iniciarIdaButton = (Button) findViewById(R.id.iniciarIdaButton);
        //adiministrarVanButton = (Button) findViewById(R.id.adiministrarVanButton);
        //addBanButton = (Button) findViewById(R.id.addBanButton);


        //iniciarIdaButton.setEnabled(false);
        //adiministrarVanButton.setEnabled(false);
        //addBanButton.setEnabled(false);


        FirebaseDatabase.getInstance().getReference("condutores")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Condutor c = (Condutor) dataSnapshot.getValue(Condutor.class);
                        c.id = dataSnapshot.getKey();
                        HomeActivity.condutorLogado = c;

                        //iniciarIdaButton.setEnabled(true);
                        //adiministrarVanButton.setEnabled(true);
                        //addBanButton.setEnabled(true);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        FirebaseDatabase.getInstance().getReference("condutores")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Condutor c = (Condutor) dataSnapshot.getValue(Condutor.class);
                        c.id = dataSnapshot.getKey();
                        HomeActivity.condutorLogado = c;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void onAdicionarVan(View view) {
        Intent i = new Intent(this, CreateVanActivity.class);
        startActivity(i);
    }

    public void onAdicionarRota(View view) {
        Intent i = new Intent(this, AdicionarEscolaActivity.class);
        startActivity(i);
    }

    public void onAdministrarVans(View view) {
        Intent i = new Intent(this, AdministrarVanActivity.class);
        startActivity(i);
    }

    public void onCheckinButtonClick(View view) {
        Intent i = new Intent(this, CheckInIdaActivity.class);
        startActivity(i);
    }


    public void onLocateVanButtonClick(View view) {
        Intent i = new Intent(this, LocateVanActivity.class);
        startActivity(i);
    }

    public void onCheckinVolta(View view)
    {
        Intent i = new Intent(this, CheckInVoltaActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
