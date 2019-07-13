package com.example.mohamed.railwaymangementsystem.LoginPackage;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mohamed.railwaymangementsystem.R;

public class VerficationActivity extends AppCompatActivity {
    private   long time  = 5*60*1000;
    private EditText codeEditText ;
    private TextView timerTextView;
    private Button verifyButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_verification_layout);
        prepareViews();
        setUpTimer();

    }
    void prepareViews()
    {
        codeEditText=(EditText) findViewById(R.id.verication_code_edit_text);
        timerTextView =(TextView) findViewById(R.id.timer_tv);
        verifyButton = (Button)findViewById(R.id.verify_btn);
    }
    void setUpTimer()
    {
        CountDownTimer countDownTimer = new CountDownTimer(time,1000) {
        @Override
        public void onTick(long timeleft) {
            long minutes = (timeleft/1000)/60;
            long secodes =(timeleft/1000)%60;
            String time = minutes+" : "+secodes;
            timerTextView.setText(time);
        }

        @Override
        public void onFinish() {

        }

        }.start();

    }
}
