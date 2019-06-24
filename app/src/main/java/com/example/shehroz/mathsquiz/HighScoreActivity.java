package com.example.shehroz.mathsquiz;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {
MediaPlayer wosh;
    Button deleteButton,AddHighScoreButton,SubHighScoreButton,MulHighScoreButton,DivHighScoreButton,ALLHighScoreButton;
    TextView Hscore,HSscores;
    TextView perView;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        wosh= MediaPlayer.create(HighScoreActivity.this,R.raw.wosh);
        AddHighScoreButton=(Button)findViewById(R.id.buttonAddHighScore);
        SubHighScoreButton=(Button)findViewById(R.id.buttonSubHighScore);
        MulHighScoreButton=(Button)findViewById(R.id.buttonMulHighScore);
        DivHighScoreButton=(Button)findViewById(R.id.buttonDivHighScore);
        ALLHighScoreButton=(Button)findViewById(R.id.buttonAllHighScore);
        AddHighScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
wosh.start();
                SharedPreferences sp=getSharedPreferences("HighScores", Context.MODE_PRIVATE);
      /* SharedPreferences.Editor ed=sp.edit();
       ed.putString("Score","0");*/
                String scorexx=sp.getString("ScoreAdd","0");
                String perxx=sp.getString("PerAdd","0");

                if (scorexx==null)
                    scorexx="0";

                //  ed.apply();
              /*  score++;
                String p= String.valueOf(score);*/

                ShowHighScoreDBox(scorexx,"Addition",perxx);
            }
        });
        SubHighScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wosh.start();
                SharedPreferences sp=getSharedPreferences("HighScores", Context.MODE_PRIVATE);
      /* SharedPreferences.Editor ed=sp.edit();
       ed.putString("Score","0");*/
                String scorexx=sp.getString("ScoreSub","0");
                String perxx=sp.getString("PerSub","0");

                if (scorexx==null)
                    scorexx="0";

                //  ed.apply();
              /*  score++;
                String p= String.valueOf(score);*/

                ShowHighScoreDBox(scorexx,"Subtraction",perxx);

            }
        });
        MulHighScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wosh.start();
                SharedPreferences sp=getSharedPreferences("HighScores", Context.MODE_PRIVATE);

                String scorexx=sp.getString("ScoreMul","0");
                String perxx=sp.getString("PerMul","0");

                if (scorexx==null)
                    scorexx="0";



                ShowHighScoreDBox(scorexx,"Multiplication",perxx);

            }
        });
        DivHighScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wosh.start();
                SharedPreferences sp=getSharedPreferences("HighScores", Context.MODE_PRIVATE);

                String scorexx=sp.getString("ScoreDiv","0");
                String perxx=sp.getString("PerDiv","0");

                if (scorexx==null)
                    scorexx="0";



                ShowHighScoreDBox(scorexx,"Division",perxx);

            }
        });

        ALLHighScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wosh.start();
                SharedPreferences sp=getSharedPreferences("HighScores", Context.MODE_PRIVATE);

                String scorexx=sp.getString("ScoreAll","0");
                String perxx=sp.getString("PerAll","0");



                ShowHighScoreDBox(scorexx," All ",perxx);

            }
        });









    }



    public boolean ShowHighScoreDBox(String ss,String cato,String persc) {

        final Dialog de = new Dialog(HighScoreActivity.this);

        de.setTitle("Highest Score");
        de.setContentView(R.layout.highscore_box);
        Hscore = (TextView) de.findViewById(R.id.HighScoreID);
        HSscores = (TextView) de.findViewById(R.id.HSshow);
        Hscore.setText(ss);
        perView = (TextView) de.findViewById(R.id.PercentageID);

        perView.setText(persc+"%");
        HSscores.setText(" The Highest Score in \"" +cato + "\" is : ");
        deleteButton = (Button)de.findViewById(R.id.btn_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                de.dismiss();
            }
        });

        de.show();
        return true;
    }
}