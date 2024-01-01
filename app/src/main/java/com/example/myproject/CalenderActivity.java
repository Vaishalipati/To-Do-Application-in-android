package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalenderActivity extends AppCompatActivity {

    CalendarView calendar;
    TextView date_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            calendar = (CalendarView)
                    findViewById(R.id.calendar);
        }
        date_view = (TextView)
                findViewById(R.id.date_view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                @Override

                                public void onSelectedDayChange(
                                        @NonNull CalendarView view,
                                        int year,
                                        int month,
                                        int dayOfMonth) {


                                    String Date = dayOfMonth + "-" + (month + 1) + "-" + year;
                                    date_view.setText(Date);
                                }
                            });
        }


    }
}