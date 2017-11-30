package com.example.bohyun.fitime;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import java.util.Calendar;


public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        ImageButton strButton = (ImageButton) view.findViewById(R.id.daily_startButton);
        strButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), StartButtonActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        Button btn = (Button)view.findViewById(R.id.testButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,23);
                calendar.set(Calendar.MINUTE,33);
                calendar.set(Calendar.SECOND,50);
                Intent intent = new Intent(getContext(), NotificationActivity.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            }
        });
        return view;
    }
}
