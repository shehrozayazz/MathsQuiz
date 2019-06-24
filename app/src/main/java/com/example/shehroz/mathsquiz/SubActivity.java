package com.example.shehroz.mathsquiz;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SubActivity extends AppCompatActivity {
    public static int Tques = 0;
    CountDownTimer timer;
    int hs = 0;
    RelativeLayout rl;
    TextView Hscore, HSscores;
    TextView perView;
    static int count = 30;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    MediaPlayer ring, fTone, wosh, endingTone, wrongTone, winningTone, appp;

    TextView timerText, ques, result, scoreText;
    Button btn1, deleteButton, btn2, btn3, btn4, start_Button, backButton, playagainButton;
    public static int score = 0;

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            result.setText("CORRECT !");
            winningTone.start();

        } else {
            result.setText("WRONG !");
            wrongTone.start();
        }
        Tques++;
        scoreText.setText(Integer.toString(score) + "/" + Integer.toString(Tques));
        generrateQues();
    }

    public void generrateQues() {
        int a = 0, b = 0;

        Random rand = new Random();

        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;
        answers.clear();

        a = rand.nextInt(100) + 34;

        b = rand.nextInt(25) + 2;
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a - b);
            } else {
                if (i < 2)
                    incorrectAnswer = (a - b) - (rand.nextInt(5) + 1);
                else
                    incorrectAnswer = (a - b) + (rand.nextInt(5) + 1);
                while (incorrectAnswer == a - b) {
                    incorrectAnswer = (a - b) - (rand.nextInt(5));
                }
                answers.add(incorrectAnswer);
            }

        }


        ques.setText(Integer.toString(a) + " - " + Integer.toString(b));


        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
        btn4.setText(Integer.toString(answers.get(3)));

    }

    public void start(View view) {
        start_Button.setVisibility(View.INVISIBLE);

        rl.setVisibility(View.VISIBLE);
        timer.start();

    }

    public void ending() {
        if (hs == 1) {
            hs = 0;
            appp.start();
            Toast.makeText(this, "New High Score !", Toast.LENGTH_LONG).show();
        } else
            endingTone.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        rl = (RelativeLayout) findViewById(R.id.relative_id);
        ring = MediaPlayer.create(SubActivity.this, R.raw.tick_tock);
        fTone = MediaPlayer.create(SubActivity.this, R.raw.finishing_tone);
        wosh = MediaPlayer.create(SubActivity.this, R.raw.wosh);
        endingTone = MediaPlayer.create(SubActivity.this, R.raw.ending_tone);
        wrongTone = MediaPlayer.create(SubActivity.this, R.raw.wrong_ans);
        winningTone = MediaPlayer.create(SubActivity.this, R.raw.winning_tone);
        appp = MediaPlayer.create(SubActivity.this, R.raw.appp);


        start_Button = (Button) findViewById(R.id.start_id);
        timerText = (TextView) findViewById(R.id.time_id);
        ques = (TextView) findViewById(R.id.questions_id);
        result = (TextView) findViewById(R.id.canswer_id);
        scoreText = (TextView) findViewById(R.id.answers_id);
        btn1 = (Button) findViewById(R.id.op1_id);
        btn2 = (Button) findViewById(R.id.op2_id);
        btn3 = (Button) findViewById(R.id.op3_id);
        btn4 = (Button) findViewById(R.id.op4_id);
        backButton = (Button) findViewById(R.id.backButton_id);
        playagainButton = (Button) findViewById(R.id.replay_button_id);
        generrateQues();
        timer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf(l / 1000) + "s");
                if (count < 6)
                    ring.start();
                count--;
            }

            @Override
            public void onFinish() {
                double per = 0;
                String numberAsString = "0";
                per = score * 100;

                if (Tques == 0) {
                    Tques = 1;
                    per = per / Tques;

                } else {

                    per = per / Tques;

                    numberAsString = String.format("%.1f", per);

                }

                result.setText(Integer.toString(score) + "/" + Integer.toString(Tques));
                //ShowHighScoreDBox(String.valueOf(score));
                backButton.setVisibility(Button.VISIBLE);
                playagainButton.setVisibility(Button.VISIBLE);
                result.setVisibility(TextView.INVISIBLE);
                SharedPreferences sp = getSharedPreferences("HighScores", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                if (per >= 80) {


                    String sc = sp.getString("ScoreSub", "0");
                    // String persc = sp.getString("PerAdd", null);

                    if (sc != null) {
                        int ss = Integer.parseInt(sc);

                        if (score > ss) {
                            edit.putString("ScoreSub", Integer.toString(score));
                            edit.putString("PerSub", numberAsString);
                            hs = 1;
                            ending();
                        }
                    }
                } else {
                    String sc = sp.getString("ScoreSub", "0");
                    edit.putString("ScoreSub", sc);
                    String pc = sp.getString("PerSub", "0");
                    edit.putString("PerSub", pc);
                }
                edit.apply();
                ShowHighScoreDBox(String.valueOf(score), numberAsString);
                timerText.setText("0s");
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);


            }
        };


    }

    public boolean ShowHighScoreDBox(String ss, String perscs) {


        final Dialog de = new Dialog(SubActivity.this);

        de.setTitle(" Score");
        de.setContentView(R.layout.goback_box);
        Hscore = (TextView) de.findViewById(R.id.HighScoreID);
        HSscores = (TextView) de.findViewById(R.id.HSshow);
        perView = (TextView) de.findViewById(R.id.PercentageID);

        perView.setText(perscs + "%");
        int xc = Integer.parseInt(ss);
        Hscore.setText(ss);
        deleteButton = (Button) de.findViewById(R.id.btn_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                de.dismiss();
            }
        });
        de.show();

        return true;
    }

    public void onBack(View view) {
        score = 0;
        result.setText(" ");
        timerText.setText("30s");
        Tques = 0;
        generrateQues();
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        count = 30;

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void onPlayAgain(View view) {
        score = 0;
        result.setVisibility(TextView.VISIBLE);
        timerText.setText("30s");
        Tques = 0;
        generrateQues();
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        count = 30;
        timer.start();
        backButton.setVisibility(Button.INVISIBLE);
        playagainButton.setVisibility(Button.INVISIBLE);

    }
    @Override
    public void onBackPressed() {
        wosh.start();
        super.onBackPressed();
        timer.cancel();

    }

}
