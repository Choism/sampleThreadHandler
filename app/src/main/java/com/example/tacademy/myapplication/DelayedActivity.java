package com.example.tacademy.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DelayedActivity extends AppCompatActivity {
    TextView messageView;
    ProgressBar downloadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageView = (TextView) findViewById(R.id.textView);
        downloadView = (ProgressBar) findViewById(R.id.progressBar);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = 0;
                downloadView.setMax(100);
                messageView.setText("download start...");
                mHandler.post(progressRunnable);
            }
        });
    }

    Handler mHandler = new Handler(Looper.getMainLooper());

    int progress = 0;
    Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            if (progress <= 100) {
                downloadView.setProgress(progress);
                messageView.setText("progress : " + progress);
                progress += 5;
                mHandler.postDelayed(this, 500);
            } else {
                downloadView.setProgress(100);
                messageView.setText("progress done");
            }
        }
    };
}