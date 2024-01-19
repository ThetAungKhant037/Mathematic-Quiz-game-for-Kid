package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ZeroActivity extends AppCompatActivity implements View.OnClickListener {
    TextView question,tvScore,tvQuestion;
MediaPlayer player;
    Button btnZeroQ1Ans1, btnZeroQ1Ans2, btnZeroQ1Ans3;
    Button btnZeroSummit;
    TextView txt1,txt2;

    int score=0;
    int totQuestion=QuestionModel.question.length;
    int noOfQ=0;
    int currentQuestionIndex=0;
    String selectedAnswer="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero);

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
        txt1=(TextView) findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);
        btnZeroQ1Ans1.setBackgroundColor(Color.MAGENTA);
        btnZeroQ1Ans2.setBackgroundColor(Color.MAGENTA);
        btnZeroQ1Ans3.setBackgroundColor(Color.MAGENTA);
        Button clieckedButton=(Button) view;

        if(clieckedButton.getId()==R.id.btnZeroSummit){
            if(selectedAnswer.equals(QuestionModel.answer[currentQuestionIndex])){
                score++;
                tvScore.setText("Score:"+score);
                player= MediaPlayer.create(ZeroActivity.this,R.raw.correct);
                player.start();

                Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_LONG).show();

            }


            else{

                player= MediaPlayer.create(ZeroActivity.this,R.raw.wrong);
                player.start();
                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
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
        question.setText(QuestionModel.question[currentQuestionIndex]);
        btnZeroQ1Ans1.setText(QuestionModel.choices[currentQuestionIndex][0]);
        btnZeroQ1Ans2.setText(QuestionModel.choices[currentQuestionIndex][1]);
        btnZeroQ1Ans3.setText(QuestionModel.choices[currentQuestionIndex][2]);
    }

    private void finishQuiz() {
        Intent intent=new Intent(ZeroActivity.this,ThankActivity.class);
        startActivity(intent);
        intent.putExtra("Result",score);
        startActivity(intent);
       /* String passStatus="";
        if(score>totQuestion*0.6){
            passStatus="Pass";


        }
        else{
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your Score is"+score+" out of "+totQuestion)
                .setPositiveButton("Restart",((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();*/
    }

    private void restartQuiz() {
        score=0;
        currentQuestionIndex=0;
        loadQuestion();
    }
}
