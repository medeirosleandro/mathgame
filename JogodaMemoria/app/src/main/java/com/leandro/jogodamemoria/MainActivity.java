package com.leandro.jogodamemoria;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    RelativeLayout gameRelativeLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>() ;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain (View view){

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgain.setVisibility(View.INVISIBLE);

        generationQuestion();


        new CountDownTimer(30100, 1000){


            @Override
            public void onTick(long millisUntilFinished) {


                timerTextView.setText(String.valueOf(millisUntilFinished /1000)+"s");



            }

            @Override
            public void onFinish() {

                playAgain.setVisibility(View.VISIBLE);

                timerTextView.setText("0s");


                resultTextView.setText("PONTUAÇÃO: "+ Integer.toString(score)+ " / " + Integer.toString(numberOfQuestions));

            }
        }.start();


    }

    public void generationQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorretAnswer;

        for (int i = 0; i <4; i++){

            if (i == locationOfCorrectAnswer){

                answers.add(a + b);

            }else{

                incorretAnswer = rand.nextInt(41);

                while (incorretAnswer == a+b){

                    incorretAnswer = rand.nextInt(41);


                }

                answers.add(incorretAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }


    public void chooseAnswer(View view){

        Log.i("Tag",(String) view.getTag());

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){


            score++;
            resultTextView.setText("ACERTOU :)");

        }else{

            resultTextView.setText("ERROU :(");

        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        generationQuestion();

    }

    public void start(View view){

        startButton.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgain));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumtextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgain = findViewById(R.id.playAgain);
        gameRelativeLayout = findViewById(R.id.gameRelativeLayout);






    }
}
