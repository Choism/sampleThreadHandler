package com.example.tacademy.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class TimeoutActivity extends AppCompatActivity {

    public static final int MESSAGE_BACK_KEY_TIMEOUT = 1;
    public static final int TIMEOUT_TIME = 2000;


    Handler mhandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        switch(msg.what){
            case MESSAGE_BACK_KEY_TIMEOUT :
                isBackPressed = false;
                break;
        }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeout);
    }

    boolean isBackPressed = false;

    public void onBackPressed(){
        if(!isBackPressed){
            Toast.makeText(this, "one more back press", Toast.LENGTH_SHORT).show();
            isBackPressed = true;

            mhandler.sendEmptyMessageDelayed(MESSAGE_BACK_KEY_TIMEOUT, TIMEOUT_TIME);

        }else{
            mhandler.removeMessages(MESSAGE_BACK_KEY_TIMEOUT);
            finish();

        }
    }



}
