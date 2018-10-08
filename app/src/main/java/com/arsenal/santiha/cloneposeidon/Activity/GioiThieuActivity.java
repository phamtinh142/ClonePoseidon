package com.arsenal.santiha.cloneposeidon.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arsenal.santiha.cloneposeidon.R;
import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class GioiThieuActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener{

    String API_KEY = "AIzaSyCdG_tLv0Ul7q7-gBOjI0hQOJT2xAAEcIA";
    int REQUEST_VIDEO = 123;

    YouTubePlayerView video_ytb_1;
    ImageView img1, img2, img3, img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);

        img1 = findViewById(R.id.img_gioi_thieu_1);
        img2 = findViewById(R.id.img_gioi_thieu_2);
        img3 = findViewById(R.id.img_gioi_thieu_3);
        img4 = findViewById(R.id.img_gioi_thieu_4);
        video_ytb_1 =  findViewById(R.id.video_ytb_1);
        video_ytb_1.initialize(API_KEY, this);

        Glide.with(GioiThieuActivity.this).load("https://buffetposeidon.com/r/files/Tin-tuc/restaurant/8.jpg").into(img1);
        Glide.with(GioiThieuActivity.this).load("https://buffetposeidon.com/r/files/Tin-tuc/restaurant/3.jpg").into(img2);
        Glide.with(GioiThieuActivity.this).load("https://buffetposeidon.com/r/files/Tin-tuc/restaurant/10.jpg").into(img3);
        Glide.with(GioiThieuActivity.this).load("https://buffetposeidon.com/r/files/Tin-tuc/restaurant/9.jpg").into(img4);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo("777w1SIRVZY");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(GioiThieuActivity.this, REQUEST_VIDEO);
        } else {
            Toast.makeText(this, "Error !!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_VIDEO){
            video_ytb_1.initialize(API_KEY, GioiThieuActivity.this);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
