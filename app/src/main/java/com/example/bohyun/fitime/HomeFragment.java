package com.example.bohyun.fitime;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"Home", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        ImageButton button = (ImageButton) view.findViewById(R.id.daily_startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Play Button Clicked", Toast.LENGTH_SHORT).show();
                Intent intentLoadNewActivity = new Intent(getActivity(), StartButtonActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        return view;
    }



}
