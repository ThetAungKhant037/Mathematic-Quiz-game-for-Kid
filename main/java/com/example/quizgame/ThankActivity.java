package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThankActivity extends AppCompatActivity {
     TextView textResult;
     Button btnBack;
     Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank);
        textResult=findViewById(R.id.textResult);
        i=getIntent();
        textResult.setText(" Your Total score : " + i.getIntExtra("Result",0));

        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThankActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}