package com.example.banksters.dudewheresmycarv2;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Banky on 11/6/2017.
 */

public class HomeScreen extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double longitude;
    private double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        button = (Button) findViewById(R.id.button);



    }

    public void saveAndGo(View view) {
        /*LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        textView.append("\n " + longitude + " " + latitude);*/
        GPStracker g = new GPStracker(getApplicationContext());
        Location l = g.getLocation();
        if (l != null)
        {
            longitude =  l.getLongitude();
            latitude  =  l.getLatitude();
        }

        Intent go = new Intent(this, MapsActivity.class);
        go.putExtra("longitude", longitude);
        go.putExtra("latitude", latitude);
        startActivity(go);
        finish();
    }

    public void intentTest(View view) {
        Intent go = new Intent(this, MainScreen.class);
        startActivity(go);
        finish();
    }
}

