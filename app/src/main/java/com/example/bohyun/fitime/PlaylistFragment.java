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
        View view = inflater.inflate(R.layout.playlist_fragment, container, false);

        Button recExercises = (Button) view.findViewById(R.id.recWorkouts);
        recExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Add Exercise Clicked", Toast.LENGTH_SHORT).show();
                Intent intentLoadNewActivity = new Intent(getActivity(), ViewRecWorkoutsActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        Button myExercises = (Button) view.findViewById(R.id.myWorkouts);
        myExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "My Exercises Clicked", Toast.LENGTH_SHORT).show();
                Intent intentLoadNewActivity = new Intent(getActivity(), ViewMyWorkoutsActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        return view;
    }
}
