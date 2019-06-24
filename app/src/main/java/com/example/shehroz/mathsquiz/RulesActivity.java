package com.example.shehroz.mathsquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RulesActivity extends AppCompatActivity {
MediaPlayer wosh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        wosh= MediaPlayer.create(RulesActivity.this,R.raw.wosh);
    }

    public void onGOback(View view) {
wosh.start();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
