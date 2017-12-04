package com.example.bohyun.fitime;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment {

    private TimePicker timePickerInFrg = MainActivity.timepicker;
    Calendar c;
    int hour, minute;
    public String timeforNotification;
    public interface TimeDialogListener {
        void onFinishDialog(String time);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time,null);

        timePickerInFrg = (TimePicker) v.findViewById(R.id.dialog_time_picker);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:ss");
        Date date = null;
        try {
            date = sdf.parse("00:00");
        } catch (ParseException e) {
        }
        c = Calendar.getInstance();
        c.setTime(date);
        timePickerInFrg.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        timePickerInFrg.setCurrentMinute(c.get(Calendar.MINUTE));
        ScheduleWorkoutActivity activity = (ScheduleWorkoutActivity) getActivity();
        String time = activity.getTimeTitle();
        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(time)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.timepickerTimeHr = timePickerInFrg.getCurrentHour();
                                MainActivity.timepickerTimeMin = timePickerInFrg.getCurrentMinute();
                                TimeDialogListener activity = (TimeDialogListener) getActivity();
                                String time = updateTime(MainActivity.timepickerTimeHr, MainActivity.timepickerTimeMin);
                                saveTime(time);
                                activity.onFinishDialog(time);
                                ScheduleWorkoutActivity activity1 = (ScheduleWorkoutActivity) getActivity();
                                int timeNum = activity1.getTimeNum();

                                if(timeNum==1){
                                    TextView textview = (TextView)getActivity().findViewById(R.id.timeSelected1);
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

    private void saveTime(String time){
        timeforNotification = time;
    }

}
