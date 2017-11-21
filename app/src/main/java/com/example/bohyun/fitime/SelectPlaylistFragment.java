package com.example.bohyun.fitime;

/**
 * Created by zschr on 11/14/2017.
 */


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

public class SelectPlaylistFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ScheduleWorkoutActivity activity = (ScheduleWorkoutActivity) getActivity();
        String title = activity.getPlaylistTitle();
        builder.setTitle(title)
                .setItems(R.array.playlists, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ScheduleWorkoutActivity activity = (ScheduleWorkoutActivity) getActivity();
                        String playlist = activity.getPlaylist();
                        if(playlist=="Playlist 1"){
                            TextView textview = (TextView)getActivity().findViewById(R.id.PlaylistSelected1);
                            textview.setText(getResources().getStringArray(R.array.playlists)[which]);
                        }
                        else if(playlist=="Playlist 2"){
                            TextView textview = (TextView)getActivity().findViewById(R.id.PlaylistSelected2);
                            textview.setText(getResources().getStringArray(R.array.playlists)[which]);
                        }
                        else if(playlist=="Playlist 3"){
                            TextView textview = (TextView)getActivity().findViewById(R.id.PlaylistSelected3);
                            textview.setText(getResources().getStringArray(R.array.playlists)[which]);
                        }
                        else{}
                        activity.onFinishPlaylistDialog(getResources().getStringArray(R.array.playlists)[which], playlist);
                        Toast.makeText(getActivity(), "You clicked " + getResources().getStringArray(R.array.playlists)[which], Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}