package com.example.banksters.dudewheresmycarv2;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double latitude;
    private Double longitude;

    private Toast toast;
    private long lastBackPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

            latitude = getIntent().getExtras().getDouble("latitude");
            longitude = getIntent().getExtras().getDouble("longitude");
    }

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("latitude", latitude);
        outState.putDouble("longitude", longitude);
    }*/

    /*@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        latitude = savedInstanceState.getDouble("latitude");
        longitude = savedInstanceState.getDouble("longitude");
    }*/

    //Test if you can save a location, drive somewhere else, switch to compass, then bring it back to map
    //Figure out a way to switch between activities without having to start a new one

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng pos = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(pos).title("Your Location"));
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
    }


    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Press back again to close this app", Toast.LENGTH_SHORT);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }

    public void goMemos (View view) {
        Intent go = new Intent(this, MemoScreen.class);
        go.putExtra("longitude", longitude);
        go.putExtra("latitude", latitude);
        startActivity(go);
        //finish();
    }

    public void goCompass (View view) {
        Intent go = new Intent(this, CompassScreen.class);
        go.putExtra("longitude", longitude);
        go.putExtra("latitude", latitude);
        startActivity(go);
        //finish();
    }

    public void goCamera (View view) {
        Intent go = new Intent(this, CameraScreen.class);
        go.putExtra("longitude", longitude);
        go.putExtra("latitude", latitude);
        startActivity(go);
        //finish();
    }
}