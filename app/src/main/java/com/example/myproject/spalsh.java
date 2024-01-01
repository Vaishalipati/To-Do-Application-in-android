package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class spalsh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        getSupportActionBar().hide();
        Thread td=new Thread() {
            public void run() {
                try {
                    sleep(2000);

                }catch(Exception ex) {
                    ex.printStackTrace();

                } finally{
                    Intent intent=new Intent(spalsh.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
        }

        };td.start();
    }
}