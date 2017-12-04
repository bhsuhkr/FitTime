package com.example.bohyun.fitime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class ExerciseFragment extends Fragment {

    public ExerciseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exercise_fragment, container, false);

        ImageButton recExercises = (ImageButton) view.findViewById(R.id.recExercises);
        recExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Add Exercise Clicked", Toast.LENGTH_SHORT).show();
                Intent intentLoadNewActivity    = new Intent(getActivity(), ViewRecExercisesActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        ImageButton myExercises = (ImageButton) view.findViewById(R.id.myExercises);
        myExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "View My Exercises", Toast.LENGTH_SHORT).show();
                Intent intentLoadNewActivity = new Intent(getActivity(), ViewMyExercisesActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        return view;
    }
}
