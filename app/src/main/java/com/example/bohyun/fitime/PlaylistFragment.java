package com.example.bohyun.fitime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class PlaylistFragment extends Fragment {

    public PlaylistFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"Playlist", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.playlist_fragment, container, false);
        Button button_1 = (Button) view.findViewById(R.id.button_videos);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListOfVideosActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
