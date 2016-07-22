package com.example.tacademy.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountDownActivity extends AppCompatActivity {
    int count = 10;
    long startTime = -1;

    TextView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        countView = (TextView) findViewById(R.id.textView2);
        Button btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 10;
                startTime = -1;
                mHandler.removeCallbacks(downRunnable);
                mHandler.post(downRunnable);

            }
        });
    }

    Handler mHandler = new Handler(Looper.getMainLooper());
    Runnable downRunnable = new Runnable() {
        @Override
        public void run() {
            long time = SystemClock.elapsedRealtime();
            if (startTime == -1) {
                startTime = time;
            }
            int gap = (int)(time - startTime);
            int count = 10 - gap / 1000;
            int rest = 1000 - (gap % 1000);
            if (count >= 0) {
                countView.setText("count : " + count);
                mHandler.postDelayed(this, rest);
            } else {
                countView.setText("done");
            }
        }
    };
}