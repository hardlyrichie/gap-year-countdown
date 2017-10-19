package com.richgao.gapyearcountdown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextClock currentTime;
    private TextView currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTime = (TextClock) findViewById(R.id.currentTime);
        currentTime.setFormat12Hour("hh:mm:ss a");

        currentDate = (TextView) findViewById(R.id.currentDateTextView);
        currentDate.setText(DateFormat.getDateInstance().format(new Date()));


    }
}
