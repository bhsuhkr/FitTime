package com.example.bohyun.fitime;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


public class TwoButtonFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_two_button)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ScheduleWorkoutActivity activity = (ScheduleWorkoutActivity) getActivity();
                        activity.setData();
                        activity.dayExists(0);
                        dismiss();
                        activity.finish();
                        Toast.makeText(getActivity(),"You clicked Yes !!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ScheduleWorkoutActivity activity = (ScheduleWorkoutActivity) getActivity();
                        activity.dayExists(0);
                        dismiss();
                        Toast.makeText(getActivity(),"You clicked No !!",Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
