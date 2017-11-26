package com.example.banksters.dudewheresmycarv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by banksters on 11/15/2017.
 */

public class MemoScreen extends AppCompatActivity{
    private Double latitude;
    private Double longitude;
    EditText memo_edittext;
    String memo;
    private long lastBackPressTime = 0;
    private Toast toast;
    //Button savememo_button, clearmemo_button;

//TODO: Maybe send back and forth the textedit shit??

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memoscreen);
        latitude = getIntent().getExtras().getDouble("latitude");
        longitude = getIntent().getExtras().getDouble("longitude");
        memo_edittext = (EditText) findViewById(R.id.memo_edittext);

        SharedPreferences pref = getSharedPreferences("PREFS", 0);
        memo = pref.getString("memo", "");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("memo", memo);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        memo = savedInstanceState.getString("memo");
    }

    @Override
    public void onResume() {
        super.onResume();

        memo_edittext.setText(memo);

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        SharedPreferences pref = this.getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
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

    public void saveMemo(View view)
    {
        memo = memo_edittext.getText().toString();
        SharedPreferences pref = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("memo", memo);
        editor.commit();

        Toast.makeText(MemoScreen.this, "Memo saved!", Toast.LENGTH_SHORT).show();
    }

    public void clearMemo(View view)
    {
        memo = "";
        memo_edittext.setText(memo);
    }

    public void goMap (View view){
        Intent go = new Intent(this, MapsActivity.class);
        go.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        //go.putExtra("longitude", longitude);
        //go.putExtra("latitude", latitude);
        startActivity(go);
        //finish();
    }
}
