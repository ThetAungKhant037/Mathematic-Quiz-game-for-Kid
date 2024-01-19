package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OneActivity extends AppCompatActivity implements View.OnClickListener {
    TextView question,tvScore,tvQuestion, tmtextview;

    private static final int lengthinmili = 20000;
    private static final int interval = 1000;

    Button btnZeroQ1Ans1, btnZeroQ1Ans2, btnZeroQ1Ans3;
    Button btnZeroSummit;

    int score=0;
    int totQuestion=QuestionModel1.question.length;
    int noOfQ=0;
    int currentQuestionIndex=0;
    String selectedAnswer = "";

    int[] therandomorder = getRandomOrder();
    CountDownTimer timer;

int[] order= getRandomOrder();
MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        tmtextview = (TextView) findViewById(R.id.timer);
        question = (TextView) findViewById(R.id.tvQuestion1);
        tvScore=(TextView) findViewById(R.id.tvZeroScore);
        tvQuestion=(TextView) findViewById(R.id.tvZeroQuestion);
        tvQuestion.setText("Total question : 10");

        btnZeroQ1Ans1 = (Button) findViewById(R.id.btnZeroQ1Ans1);
        btnZeroQ1Ans2 = (Button) findViewById(R.id.btnZeroQ1Ans2);
        btnZeroQ1Ans3 = (Button) findViewById(R.id.btnZeroQ1Ans3);
        btnZeroSummit = (Button) findViewById(R.id.btnZeroSummit);

        btnZeroQ1Ans1.setOnClickListener(this);
        btnZeroQ1Ans2.setOnClickListener(this);
        btnZeroQ1Ans3.setOnClickListener(this);
        btnZeroSummit.setOnClickListener(this);
        loadQuestion();
    }


    @Override
    public void onClick(View view) {
        btnZeroQ1Ans1.setBackgroundColor(Color.MAGENTA);
        btnZeroQ1Ans2.setBackgroundColor(Color.MAGENTA);
        btnZeroQ1Ans3.setBackgroundColor(Color.MAGENTA);
        Button clieckedButton=(Button) view;

        if(clieckedButton.getId()==R.id.btnZeroSummit){
            timer.cancel();
            if(selectedAnswer.equals(QuestionModel1.answer[therandomorder[currentQuestionIndex]])){
                score++;
                tvScore.setText("Score:"+score);
                player= MediaPlayer.create(OneActivity.this,R.raw.correct);
                player.start();
                Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_LONG).show();
            }
            else {

                player= MediaPlayer.create(OneActivity.this,R.raw.wrong);
                player.start();
                Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_LONG).show();
            }
            noOfQ++;
            currentQuestionIndex++;
            loadQuestion();
        }
        else{
            selectedAnswer=clieckedButton.getText().toString();
            clieckedButton.setBackgroundColor(Color.RED);

        }

        tvQuestion.setText("Question Number : "+noOfQ);

    }

    private void loadQuestion() {
        if(currentQuestionIndex==totQuestion){
            finishQuiz();
            return;
        }
        StartCountDown();
        question.setText(QuestionModel1.question[therandomorder[currentQuestionIndex]]);
        btnZeroQ1Ans1.setText(QuestionModel1.choices[therandomorder[currentQuestionIndex]][0]);
        btnZeroQ1Ans2.setText(QuestionModel1.choices[therandomorder[currentQuestionIndex]][1]);
        btnZeroQ1Ans3.setText(QuestionModel1.choices[therandomorder[currentQuestionIndex]][2]);

    }

    private void finishQuiz() {
        Intent intent=new Intent(OneActivity.this,ThankActivity.class);
        startActivity(intent);
        intent.putExtra("Result",score);
        startActivity(intent);
    }

    private void restartQuiz() {
        score=0;
        currentQuestionIndex=0;
        loadQuestion();
    }

    private void StartCountDown(){
        timer = new CountDownTimer(lengthinmili, interval) {
            @Override
            public void onTick(long l) {

                long minute = l / 1000 / 60;
               long second = l / 1000 % 60;

               String formatted = String.format("%2d:%2d", minute, second);
               tmtextview.setText(formatted);

            }
            @Override
            public void onFinish() {
                currentQuestionIndex++;
                loadQuestion();
            }
        }.start();

    }

    public int[] getRandomOrder(){
        int[][] toberandomized = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                {4, 6, 3, 7, 5, 1, 0, 4, 2, 8},
                {0, 9, 8, 7, 6, 5, 4, 3, 2, 1}
        };

        int randomNum = (int) (Math.random() * toberandomized.length);
        return toberandomized[randomNum];
    }
}
