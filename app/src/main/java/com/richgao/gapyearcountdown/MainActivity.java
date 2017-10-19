package com.richgao.gapyearcountdown;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextClock currentTime;
    private TextView currentDate;
    private TextView daysLeft, hoursLeft, minutesLeft, secondsLeft;
    private long startTime;

    private static final String TAG = "Gap Year Countdown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDate = (TextView) findViewById(R.id.currentDateTextView);
        currentDate.setText(DateFormat.getDateInstance().format(new Date())); // Gets Current Date in default format

        currentTime = (TextClock) findViewById(R.id.currentTime);

        daysLeft = (TextView) findViewById(R.id.daysLeftTextView);
        hoursLeft = (TextView) findViewById(R.id.hoursLeftTextView);
        minutesLeft = (TextView) findViewById(R.id.minutesLeftTextView);
        secondsLeft = (TextView) findViewById(R.id.secondsLeftTextView);

        String endTime = "25.8.2018, 00:00:00"; // Olin Move-in Day
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyy, HH:mm:ss");
        formatter.setLenient(false); // Tells parsing to not be lenient (so don't interpret garbage)

        Date endDate;
        long miliseconds = 0;
        try {
            endDate = formatter.parse(endTime);
            miliseconds = endDate.getTime(); // Gets the time of endDate in miliseconds
        } catch (ParseException e) {
            Log.e(TAG, "Failed to parse endTime" + e.getStackTrace());
            finish();
        }

        startTime = System.currentTimeMillis(); // Placed as close as endDate.getTime() as possible

        // Implement abstract class CountDownTime with endTime counting down every second
        CountDownTimer countDown = new CountDownTimer(miliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Decrements startTime to simulate one second passing since previous onTick call
                long diff = (millisUntilFinished - (--startTime)) / 1000;

                daysLeft.setText(String.format("%d", diff / 86400));
                hoursLeft.setText(String.format("%d", (diff % 86400) / 3600));
                minutesLeft.setText(String.format("%d", ((diff % 86400) % 3600) / 60));
                secondsLeft.setText(String.format("%d", ((diff % 86400) % 3600) % 60));
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }
}
