package com.example.shehroz.mathsquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MediaPlayer wosh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wosh= MediaPlayer.create(MainActivity.this,R.raw.wosh);
    }

    public void btnHighScore(View view) {
        wosh.start();
        Intent i=new Intent(this,HighScoreActivity.class);
        startActivity(i);
    }

    public void onClickPlay(View view) {
        wosh.start();
        Intent i=new Intent(this,MenuActivity.class);
        startActivity(i);

    }

    public void btnRules(View view) {
        wosh.start();
        Intent i=new Intent(this,RulesActivity.class);
        startActivity(i);
    }
}
