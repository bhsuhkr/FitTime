package com.example.bohyun.fitime;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


public class DeleteButtonFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_button)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FullScheduleActivity activity = (FullScheduleActivity) getActivity();
                        String day = activity.getDayDelete();
                        activity.dayDelete(day);
                        dismiss();
                        Toast.makeText(getActivity(),"You clicked Yes !!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FullScheduleActivity activity = (FullScheduleActivity) getActivity();
                        dismiss();
                        Toast.makeText(getActivity(),"You clicked No !!",Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
