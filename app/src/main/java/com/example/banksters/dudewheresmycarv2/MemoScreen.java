package com.example.banksters.dudewheresmycarv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by banksters on 11/15/2017.
 */

public class MemoScreen extends AppCompatActivity{
    private Double latitude;
    private Double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memoscreen);
        latitude = getIntent().getExtras().getDouble("latitude");
        longitude = getIntent().getExtras().getDouble("longitude");
    }

    public void goMap (View view){
        Intent go = new Intent(this, MapsActivity.class);
        go.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        //go.putExtra("longitude", longitude);
        //go.putExtra("latitude", latitude);
        startActivity(go);
        finish();
    }
}
