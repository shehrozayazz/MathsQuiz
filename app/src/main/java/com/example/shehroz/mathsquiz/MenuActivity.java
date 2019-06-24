package com.example.shehroz.mathsquiz;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
public class MenuActivity extends AppCompatActivity {
    MediaPlayer wosh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        wosh= MediaPlayer.create(MenuActivity.this,R.raw.wosh);

    }

    public void onclickAdd(View view) {
        wosh.start();
        Intent i=new Intent(this,AddActivity.class);
        startActivity(i);
    }

    public void onclickSub(View view) {
        wosh.start();
        Intent i=new Intent(this,SubActivity.class);
        startActivity(i);
    }

    public void onclickMul(View view) {
        wosh.start();
        Intent i=new Intent(this,MulActivity.class);
        startActivity(i);
    }

    public void onclickDiv(View view) {
        wosh.start();
        Intent i=new Intent(this,DivActivity.class);
        startActivity(i);
    }

    public void onclickAll(View view) {
        wosh.start();
        Intent i=new Intent(this,AllActivity.class);
        startActivity(i);
    }
}
