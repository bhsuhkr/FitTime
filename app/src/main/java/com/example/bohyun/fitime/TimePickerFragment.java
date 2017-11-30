package com.example.bohyun.fitime;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment {

    private TimePicker timePicker;
    Calendar c;
    public interface TimeDialogListener {
        void onFinishDialog(String time);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time,null);

        timePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
        Date date = null;
        try {
            date = sdf.parse("00:00");
        } catch (ParseException e) {
        }
        c = Calendar.getInstance();
        c.setTime(date);
        timePicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(c.get(Calendar.MINUTE));
        ScheduleWorkoutActivity activity = (ScheduleWorkoutActivity) getActivity();
        String time = activity.getTimeTitle();
        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(time)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int hour = 0;
                                int minute = 0;
                                hour = timePicker.getCurrentHour();
                                minute = timePicker.getCurrentMinute();
                                TimeDialogListener activity = (TimeDialogListener) getActivity();
                                String time = updateTime(hour,minute);
                                activity.onFinishDialog(time);
                                ScheduleWorkoutActivity activity1 = (ScheduleWorkoutActivity) getActivity();
                                int timeNum = activity1.getTimeNum();

                                if(timeNum==1){
                                    TextView textview = (TextView)getActivity().findViewById(R.id.timeSelected1);
                                    textview.setText(time);
                                }
                                else if(timeNum==2){
                                    TextView textview = (TextView)getActivity().findViewById(R.id.timeSelected2);
                                    textview.setText(time);
                                }
                                else if(timeNum==3){
                                    TextView textview = (TextView)getActivity().findViewById(R.id.timeSelected3);
                                    textview.setText(time);
                                }
                                else{}
                                activity.onFinishDialog(time);
                                activity1.setTimeDialog(time, timeNum);
                            }
                        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();

    }

    private String updateTime(int hours, int mins) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";

        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        String myTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        return myTime;
    }

}
