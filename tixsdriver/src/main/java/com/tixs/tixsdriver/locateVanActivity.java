package com.tixs.tixsdriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class locateVanActivity extends AppCompatActivity {

    private TextView locationText;
    private LocationManager locationManager;
    private LocationListener listener;
    private String mprovider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_van);
        locationText = (TextView) findViewById(R.id.locationTextView);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        mprovider = locationManager.getBestProvider(criteria, false);

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    locationText.setText(location.getLongitude() + "  " + location.getLatitude());
                    updateVanLocation(location, "-Kxrq9TZ3TheIimNZC_n");
                }else {
                    locationText.setText("Não foi possível obter a localização. Tetando novamente...");
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(mprovider);
            if (location != null) {
                locationText.setText(location.getLongitude() + "  " + location.getLatitude());
                updateVanLocation(location, "-Kxrq9TZ3TheIimNZC_n");
            }else {
                locationText.setText("Não foi possível obter a localização. Tetando novamente...");
            }
            locationManager.requestLocationUpdates(mprovider, 5000, 0, listener);
        }
    }

    private void updateVanLocation(Location loc, String van) {
        final Location location = loc;
        final String vanId = van;
        FirebaseDatabase.getInstance().getReference("vans").child(vanId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        Map<String, Object> postValues = new HashMap<String,Object>();
                                                        postValues.put("latitude", location.getLatitude());
                                                        postValues.put("long", location.getLongitude());
                                                        FirebaseDatabase.getInstance().getReference("vans").child(vanId).updateChildren(postValues);
                                                    }
                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {}
                                                }
                );
    }
}
