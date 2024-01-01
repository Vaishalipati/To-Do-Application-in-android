package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;


public class Themes extends AppCompatActivity implements View.OnClickListener{

    Button btn1,btn2, btn3, btn4;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        int selectedTheme=sharedPreferences.getInt("selectedTheme",0);
        setDynamicTheme(selectedTheme);
        setContentView(R.layout.activity_themes);

        btn1=findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        btn4=findViewById(R.id.button4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    private void setDynamicTheme(int selectedTheme){
        switch (selectedTheme){
            case 1:
                Themes.this.setTheme(R.style.DynamicTheme1);
                break;
            case 2:
                Themes.this.setTheme(R.style.DynamicTheme2);
                break;
            case 3:
                Themes.this.setTheme(R.style.DynamicTheme3);
                break;
            case 4:
                Themes.this.setTheme(R.style.DynamicTheme4);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor=sharedPreferences.edit();

        switch (v.getId()){
            case R.id.button:
                editor.putInt("selectedTheme", 1);
                break;
            case R.id.button2:
                editor.putInt("selectedTheme", 2);
                break;
            case R.id.button3:
                editor.putInt("selectedTheme", 3);
                break;
            case R.id.button4:
                editor.putInt("selectedTheme", 4);
                break;

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        }

        startActivity(new Intent(Themes.this,Themes.class));
        finish();
    }
}