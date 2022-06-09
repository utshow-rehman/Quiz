package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class questions extends AppCompatActivity {
    Button btn_one, btn_two, btn_three, btn_four;
    TextView tv_question,score,at;
      String atm="Quiz Attempted: ";
     int ct=0;
    private data question = new data(questions.this, "Quiz.db", null, 1);
     int correct=0;
    private String answer;
    private int questionLength =7;


    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        random = new Random();

         loadData();

        btn_one = (Button)findViewById(R.id.btn1);
        btn_one.setOnClickListener(this::onClick);
        btn_two = (Button)findViewById(R.id.btn2);
        btn_two.setOnClickListener(this::onClick);
        btn_three = (Button)findViewById(R.id.btn3);
        btn_three.setOnClickListener(this::onClick);
        btn_four = (Button)findViewById(R.id.btn4);
        btn_four.setOnClickListener(this::onClick);
        score = (TextView)findViewById(R.id.score);
        at = (TextView)findViewById(R.id.atm);
        tv_question = (TextView)findViewById(R.id.qs);
         attemp();
        NextQuestion(random.nextInt(questionLength));

    }
     void attemp(){
         String val=atm+""+ct;
         val+="/5";
         at.setText(val);
     }
     void loadData(){
        question.add();

     }

     public void color(){
         String a="Score: "+correct;
           attemp();
         score.setText(a);
         if(ct==5)
             GameOver();
         else {
             new Timer().schedule(new TimerTask() {

                 @Override
                 public void run() {
                     NextQuestion(random.nextInt(questionLength));
                     btn_one.setBackgroundColor(getResources().getColor(R.color.purple_500));
                     btn_two.setBackgroundColor(getResources().getColor(R.color.purple_500));
                     btn_three.setBackgroundColor(getResources().getColor(R.color.purple_500));
                     btn_four.setBackgroundColor(getResources().getColor(R.color.purple_500));


                 }
             }, 800);
         }
     }
    public void onClick(View v) {
        int bt = v.getId();
        Log.d("ans",bt+" Click "+answer);

        if (R.id.btn1 == bt) {
            Log.d("ans",btn_one.getText()+" | "+answer);
            if (btn_one.getText().equals(answer)) {
                correct++;
                Toast.makeText(questions.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                btn_one.setBackgroundColor(getResources().getColor(R.color.green));

            }
            else {
                btn_one.setBackgroundColor(getResources().getColor(R.color.red));
                if (btn_two.getText().equals(answer)) {
                    btn_two.setBackgroundColor(getResources().getColor(R.color.green));
                } else if (btn_three.getText().equals(answer)) {
                    btn_three.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    btn_four.setBackgroundColor(getResources().getColor(R.color.green));
                }
            }
        }



        else if (R.id.btn2 == bt) {

            if (btn_two.getText().equals(answer)) {
                correct++;
                Toast.makeText(questions.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                btn_two.setBackgroundColor(getResources().getColor(R.color.green));


            } else {
                btn_two.setBackgroundColor(getResources().getColor(R.color.red));
                if (btn_one.getText().equals(answer)) {
                    btn_one.setBackgroundColor(getResources().getColor(R.color.green));
                } else if (btn_three.getText().equals(answer)) {
                    btn_three.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    btn_four.setBackgroundColor(getResources().getColor(R.color.green));
                }


            }
        }


        else if (R.id.btn3 == bt) {

            if (btn_three.getText().equals(answer)) {
                correct++;
                Toast.makeText(questions.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                btn_three.setBackgroundColor(getResources().getColor(R.color.green));


            } else {
                btn_three.setBackgroundColor(getResources().getColor(R.color.red));
                if (btn_two.getText().equals(answer)) {
                    btn_two.setBackgroundColor(getResources().getColor(R.color.green));
                } else if (btn_one.getText().equals(answer)) {
                    btn_one.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    btn_four.setBackgroundColor(getResources().getColor(R.color.green));
                }

            }
        }



        else if (R.id.btn4 == bt) {

            if (btn_four.getText().equals(answer)) {
                correct++;
                Toast.makeText(questions.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                btn_four.setBackgroundColor(getResources().getColor(R.color.green));

            }
            else {
                btn_four.setBackgroundColor(getResources().getColor(R.color.red));
                if (btn_two.getText().equals(answer)) {
                    btn_two.setBackgroundColor(getResources().getColor(R.color.green));
                } else if (btn_three.getText().equals(answer)) {
                    btn_three.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    btn_one.setBackgroundColor(getResources().getColor(R.color.green));
                }

            }

        }

        ct++;

            color();



    }


    private void GameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(questions.this);
        alertDialogBuilder
                .setMessage("Game Over")
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(getApplicationContext(), questions.class));
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    private void NextQuestion(int n){
        tv_question.setText(question.getQuestion(n));
        btn_one.setText(question.getchoice1(n));
        btn_two.setText(question.getchoice2(n));
        btn_three.setText(question.getchoice3(n));
        btn_four.setText(question.getchoice4(n));
        answer = question.getCorrectAnswer(n);

    }
}