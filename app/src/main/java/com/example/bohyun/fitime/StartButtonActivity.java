package com.example.bohyun.fitime;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;


public class StartButtonActivity extends Activity {


    private VideoView videoView;
    private Button bplay, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exercise);

       videoView = (VideoView) findViewById(R.id.video);
        bplay = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.pause);


        String path = "android.resource://"+getPackageName()+"/"+R.raw.example;
        Uri uri = Uri.parse(path);


        videoView.setVideoURI(uri);
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                mp.setLooping(true);
            }
        });

        bplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });

    }
}
