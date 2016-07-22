package com.example.tacademy.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
//post를 이용하여 MainThread 에게 처리해야할 코드 만 넘겨 줄 때

public class PostActivity extends AppCompatActivity {

    TextView messageView;
    ProgressBar downloadView;

    Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        messageView = (TextView) findViewById(R.id.textView);
        downloadView = (ProgressBar) findViewById(R.id.progressBar);

        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadView.setMax(100);
                messageView.setText("download start...");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int progress = 0;
                        while(progress <= 100){
                        mHandler.post(new ProgressRunnable(progress));
                        try {
                            Thread.sleep(200);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                            progress += 5;
                        }
                        mHandler.post(new DoneRunnable());
                    }
                }).start();
            }
        });
    }

    class ProgressRunnable implements Runnable{
        int progress;

        public ProgressRunnable(int progress){
            this.progress = progress;
        }

        @Override
        public void run() {
            downloadView.setProgress(progress);
            messageView.setText("Progress : " + progress);
        }
    }
    class DoneRunnable implements Runnable {
        @Override
        public void run() {
            downloadView.setProgress(100);
            messageView.setText("progress done");
        }
    }
}