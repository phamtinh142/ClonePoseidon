package com.arsenal.santiha.cloneposeidon.Activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.arsenal.santiha.cloneposeidon.R;

public class StartActivity extends AppCompatActivity {
    TextView txtStart, txtHa;
    ImageView imgStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        txtStart = (TextView) findViewById(R.id.textviewStart);
        txtHa =(TextView) findViewById(R.id.textviewHa);
        imgStart = findViewById(R.id.imageviewStart);
        Animation animation = AnimationUtils.loadAnimation(StartActivity.this, R.anim.start);
        txtStart.startAnimation(animation);
        txtHa.setAlpha(0);
        imgStart.setVisibility(View.GONE);
        new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                txtHa.setAlpha(1);
                imgStart.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(StartActivity.this, R.anim.start_alpha);
                txtHa.startAnimation(anim);
            }
        }.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
}
