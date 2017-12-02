package com.example.bohyun.fitime;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;


public class StartButtonActivity extends Activity {


    private VideoView videoView;
    MediaController mediaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exercise);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoView = (VideoView) findViewById(R.id.video);
        mediaC = new MediaController(this);
        mediaC.setAnchorView(videoView);
        String path = "android.resource://"+getPackageName()+"/"+ R.raw.AbCrnch1;
        Uri uri = Uri.parse(path);
        videoView.setMediaController(mediaC);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }
}
