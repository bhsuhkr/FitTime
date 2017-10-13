package com.example.bohyun.fitime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class CalendarFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"Calendar", Toast.LENGTH_SHORT).show();
        View view =inflater.inflate(R.layout.calendar_fragment, container, false);

        return view;
    }
}