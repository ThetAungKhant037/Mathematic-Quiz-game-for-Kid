package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView a =(TextView) findViewById(R.id.tvChooseLevel);

        Button btnlevel0 =(Button) findViewById(R.id.btnLevel0);
        btnlevel0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( MenuActivity.this,ZeroActivity.class);
                startActivity(intent);
            }
        });




        Button btnlevel1 = (Button) findViewById(R.id.btnLevel1);
        btnlevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(MenuActivity.this,OneActivity.class) ;
                    startActivity(intent);
                                         }
                                     });

                Button btnlevel2 = (Button) findViewById(R.id.btnLevel2);
                btnlevel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(MenuActivity.this,TwoActivity.class);
                        startActivity(intent);
                    }
                });


    }
}