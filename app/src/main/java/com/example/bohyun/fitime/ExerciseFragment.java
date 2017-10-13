package com.example.bohyun.fitime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Bohyun on 2/26/2017.
 */

public class ExerciseFragment extends Fragment {

    public ExerciseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       Toast.makeText(getActivity(),"Exercise", Toast.LENGTH_SHORT).show();
       View view = inflater.inflate(R.layout.exercise_fragment, container, false);

        return view;
    }


}
