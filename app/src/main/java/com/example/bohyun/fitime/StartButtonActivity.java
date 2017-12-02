package com.example.bohyun.fitime;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;


public class StartButtonActivity extends Activity {

    private String[] videoArray = {"abcrnch_1", "all4s_al_u_2", "altkn_3", "altsdplnks_4", "bcklng_5",
                                    "brcrwl_6", "brnckck_7", "burpees_8", "chrkck_9", "clmshll_10"};
    private VideoView videoView;
    MediaController mediaC;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exercise);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoView = (VideoView) findViewById(R.id.video);
        mediaC = new MediaController(this);
        mediaC.setAnchorView(videoView);
        //String path = "android.resource://"+getPackageName()+"/"+ R.raw.abcrnch_1;
        //Uri uri = Uri.parse(path);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + videoArray[i]);
        videoView.setMediaController(mediaC);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + videoArray[(i+1)%9]);
                videoView.setVideoURI(videoUri);
                videoView.start();
                i++;

            }
        });
    }
}
