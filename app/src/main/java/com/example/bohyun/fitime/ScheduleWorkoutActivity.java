package com.example.bohyun.fitime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;
import static java.lang.StrictMath.abs;


public class ScheduleWorkoutActivity extends AppCompatActivity implements TimePickerFragment.TimeDialogListener {

    private Button b;
    Button mTime1;
    Button mTime2;
    Button mTime3;
    Button mPlaylist1;
    Button mPlaylist2;
    Button mPlaylist3;
    TextView mTimeSelected1;
    TextView mTimeSelected2;
    TextView mTimeSelected3;
    TextView mPlaylist1Selected;
    TextView mPlaylist2Selected;
    TextView mPlaylist3Selected;
    String[] listItems;
    boolean[] checkedItems;
    boolean dayExists1;
    ArrayList<String> checkedDays = new ArrayList<>();
    ArrayList<Integer> mUserItems = new ArrayList<>();
    private DatabaseReference mDatabase;
    private String title = "";
    private String time_title = "";
    private String playlist = "";
    private int timeNum = 0;
    private String dbTime1 = "";
    private String dbTime2 = "";
    private String dbTime3 = "";
    private String dbPlaylist1 = "";
    private String dbPlaylist2 = "";
    private String dbPlaylist3 = "";
    private String dayData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_workout);
        b = (Button) findViewById(R.id.schedule_workout);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final Calendar c = Calendar.getInstance();
        mPlaylist1 = (Button) findViewById(R.id.btnPlaylist1);
        mPlaylist2 = (Button) findViewById(R.id.btnPlaylist2);
        mPlaylist3 = (Button) findViewById(R.id.btnPlaylist3);
        mPlaylist1Selected = (TextView) findViewById(R.id.PlaylistSelected1);
        mPlaylist2Selected = (TextView) findViewById(R.id.PlaylistSelected2);
        mPlaylist3Selected = (TextView) findViewById(R.id.PlaylistSelected3);

        mTime1 = (Button) findViewById(R.id.btnTime1);
        mTime2 = (Button) findViewById(R.id.btnTime2);
        mTime3 = (Button) findViewById(R.id.btnTime3);
        mTimeSelected1 = (TextView) findViewById(R.id.timeSelected1);
        mTimeSelected2 = (TextView) findViewById(R.id.timeSelected2);
        mTimeSelected3 = (TextView) findViewById(R.id.timeSelected3);

        listItems = getResources().getStringArray(R.array.playlists);
        checkedItems = new boolean[listItems.length];

        ToggleButton mtoggle = (ToggleButton) findViewById(R.id.mtoggle);
        ToggleButton ttoggle = (ToggleButton) findViewById(R.id.ttoggle);
        ToggleButton wtoggle = (ToggleButton) findViewById(R.id.wtoggle);
        ToggleButton thtoggle = (ToggleButton) findViewById(R.id.thtoggle);
        ToggleButton ftoggle = (ToggleButton) findViewById(R.id.ftoggle);
        ToggleButton stoggle = (ToggleButton) findViewById(R.id.stoggle);
        ToggleButton sutoggle = (ToggleButton) findViewById(R.id.sutoggle);


        mtoggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedDays.add("Monday");
                } else {
                    checkedDays.remove(String.valueOf("Monday"));
                }
            }});

        ttoggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedDays.add("Tuesday");
                } else {
                    checkedDays.remove(String.valueOf("Tuesday"));
                }
            }});

        wtoggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedDays.add("Wednesday");
                } else {
                    checkedDays.remove(String.valueOf("Wednesday"));
                }
            }});

        thtoggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedDays.add("Thursday");
                } else {
                    checkedDays.remove(String.valueOf("Thursday"));
                }
            }});

        ftoggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedDays.add("Friday");
                } else {
                    checkedDays.remove(String.valueOf("Friday"));
                }
            }});

        stoggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedDays.add("Saturday");
                } else {
                    checkedDays.remove(String.valueOf("Saturday"));
                }
            }});

        sutoggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedDays.add("Sunday");
                } else {
                    checkedDays.remove(String.valueOf("Sunday"));
                }
            }});

//        mOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ScheduleWorkoutActivity.this);
//                mBuilder.setTitle(R.string.select_playlists);
//                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                        if(isChecked){
//                            mUserItems.add(position);
//                        }else{
//                            mUserItems.remove((Integer.valueOf(position)));
//                        }
//                    }
//                });
//
//                mBuilder.setCancelable(false);
//                mBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        String item = "";
//                        for (int i = 0; i < mUserItems.size(); i++) {
//                            item = item + listItems[mUserItems.get(i)];
//                            if (i != mUserItems.size() - 1) {
//                                item = item + ", ";
//                            }
//                        }
//                        if (item==""){
//                            mItemSelected.setText(R.string.select_playlists);
//                        }
//                        else {
//                            mItemSelected.setText(item);
//                        }
//                    }
//                });
//
//                mBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//
//                mBuilder.setNeutralButton(R.string.clear_all, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        for (int i = 0; i < checkedItems.length; i++) {
//                            checkedItems[i] = false;
//                            mUserItems.clear();
//                            mItemSelected.setText(R.string.select_playlists);
//                        }
//                    }
//                });
//
//                AlertDialog mDialog = mBuilder.create();
//                mDialog.show();
//            }
//        });

        mPlaylist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = "Select Playlist 1";
                playlist = "Playlist 1";
                SelectPlaylistFragment dialog = new SelectPlaylistFragment();
                dialog.show(getSupportFragmentManager(), "ScheduleWorkoutActivity.PlaylistDialog");
            }
        });

        mPlaylist2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = "Select Playlist 2";
                playlist = "Playlist 2";
                SelectPlaylistFragment dialog = new SelectPlaylistFragment();
                dialog.show(getSupportFragmentManager(), "ScheduleWorkoutActivity.PlaylistDialog");
            }
        });

        mPlaylist3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = "Select Playlist 3";
                playlist = "Playlist 3";
                SelectPlaylistFragment dialog = new SelectPlaylistFragment();
                dialog.show(getSupportFragmentManager(), "ScheduleWorkoutActivity.PlaylistDialog");
            }
        });

        mTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_title="Select Playlist 1 Time";
                timeNum = 1;
                TimePickerFragment dialog = new TimePickerFragment();
                dialog.show(getSupportFragmentManager(),"ScheduleWorkoutActivity.TimeDialog");
            }
        });

        mTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_title="Select Playlist 2 Time";
                timeNum = 2;
                TimePickerFragment dialog = new TimePickerFragment();
                dialog.show(getSupportFragmentManager(),"ScheduleWorkoutActivity.TimeDialog");
            }
        });

        mTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_title="Select Playlist 3 Time";
                timeNum = 3;
                TimePickerFragment dialog = new TimePickerFragment();
                dialog.show(getSupportFragmentManager(),"ScheduleWorkoutActivity.TimeDialog");
            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                final String playlist = "";
                if (mPlaylist1Selected.getText().toString().equals("Select Playlist 1")|mPlaylist2Selected.getText().toString().equals("Select Playlist 2")|mPlaylist3Selected.getText().toString().equals("Select Playlist 3")) {
                    Toast.makeText(ScheduleWorkoutActivity.this, dayData + "Please enter 3 playlists", Toast.LENGTH_SHORT).show();
                }
                else if (mTimeSelected1.getText().toString().equals("Select Playlist 1 Time")|mTimeSelected2.getText().toString().equals("Select Playlist 2 Time")|mTimeSelected3.getText().toString().equals("Select Playlist 3 Time")) {
                    Toast.makeText(ScheduleWorkoutActivity.this, dayData + "Please enter a time for each playlist", Toast.LENGTH_SHORT).show();
                }
                else if(checkTimeConflict(mTimeSelected1.getText().toString(), mTimeSelected2.getText().toString(), mTimeSelected3.getText().toString())){
                    Toast.makeText(ScheduleWorkoutActivity.this, "Please schedule workouts at least 30 minutes apart", Toast.LENGTH_SHORT).show();
                }
                else {
                    setTimePickerToVariable();
                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

//                        CHECKING IF DAY IS ALREADY SCHEDULED
                            for (int i = 0; i < checkedDays.size(); i++) {
                                String day = checkedDays.get(i);
                                MainActivity.dayOfTheWeek = day; //the output needs to be stored in (MainActivity.dayOfTheWeek) after the algorithm worked.
                                setAlarm(); // this will set the alarm
                                dayData = day;
                                mDatabase = FirebaseDatabase.getInstance().getReference().child("schedule").child(userId).child("Day");
                                if (dataSnapshot.child(dayData).exists()) {
//                                    Toast.makeText(ScheduleWorkoutActivity.this, dayData + "day exists!", Toast.LENGTH_SHORT).show();
                                    dayExists(1);
                                } else {
//                                    Toast.makeText(ScheduleWorkoutActivity.this, dayData + "day does not exists!", Toast.LENGTH_SHORT).show();
                                }
                            }
//                        CHECKING IF DAY IS ALREADY SCHEDULED


                            ///////////////////////////////////////////

                            if (dayExists1) {
                                Toast.makeText(ScheduleWorkoutActivity.this, "day exists", Toast.LENGTH_SHORT).show();
                                TwoButtonFragment dialog = new TwoButtonFragment();
                                dialog.show(getSupportFragmentManager(), "MainActivity.TwoButtonDialog");
                            } else {
                                for (int i = 0; i < checkedDays.size(); i++) {
                                    final String day = checkedDays.get(i);
                                    mDatabase.child(day).child("Playlist 1").child("PlaylistName").setValue(dbPlaylist1);
                                    mDatabase.child(day).child("Playlist 1").child("Time").setValue(dbTime1);
                                    mDatabase.child(day).child("Playlist 2").child("PlaylistName").setValue(dbPlaylist2);
                                    mDatabase.child(day).child("Playlist 2").child("Time").setValue(dbTime2);
                                    mDatabase.child(day).child("Playlist 3").child("PlaylistName").setValue(dbPlaylist3);
                                    mDatabase.child(day).child("Playlist 3").child("Time").setValue(dbTime3);
                                }
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Getting Post failed, log a message
                            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                            // ...
                        }
                    });
                    Toast.makeText(ScheduleWorkoutActivity.this, Boolean.toString(dayExists1), Toast.LENGTH_SHORT).show();
//                    mDatabase.child("schedule").child(userId).child("Day").child(day).child("Playlist 1").child("PlaylistName").setValue(dbPlaylist1);
//                    mDatabase.child("schedule").child(userId).child("Day").child(day).child("Playlist 1").child("Time").setValue(dbTime1);
//                    mDatabase.child("schedule").child(userId).child("Day").child(day).child("Playlist 2").child("PlaylistName").setValue(dbPlaylist2);
//                    mDatabase.child("schedule").child(userId).child("Day").child(day).child("Playlist 2").child("Time").setValue(dbTime2);
//                    mDatabase.child("schedule").child(userId).child("Day").child(day).child("Playlist 3").child("PlaylistName").setValue(dbPlaylist3);
//                    mDatabase.child("schedule").child(userId).child("Day").child(day).child("Playlist 3").child("Time").setValue(dbTime3);
//                Toast.makeText(ScheduleWorkoutActivity.this,Boolean.toString(dayExists1), Toast.LENGTH_SHORT).show();
//                if (dayExists1) {
//                    Toast.makeText(ScheduleWorkoutActivity.this, "day exists", Toast.LENGTH_SHORT).show();
//                    TwoButtonFragment dialog = new TwoButtonFragment();
//                    dialog.show(getSupportFragmentManager(), "MainActivity.TwoButtonDialog");
//                } else {
//                    for (int i = 0; i < checkedDays.size(); i++) {
//                        final String day = checkedDays.get(i);
//                        mDatabase.child(day).child("Playlist 1").child("PlaylistName").setValue(dbPlaylist1);
//                        mDatabase.child(day).child("Playlist 1").child("Time").setValue(dbTime1);
//                        mDatabase.child(day).child("Playlist 2").child("PlaylistName").setValue(dbPlaylist2);
//                        mDatabase.child(day).child("Playlist 2").child("Time").setValue(dbTime2);
//                        mDatabase.child(day).child("Playlist 3").child("PlaylistName").setValue(dbPlaylist3);
//                        mDatabase.child(day).child("Playlist 3").child("Time").setValue(dbTime3);
//                    }
//                }

//                Toast.makeText(ScheduleWorkoutActivity.this,"scheduled workout", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void onFinishDialog(String time) {
        Toast.makeText(this, "Selected Time : "+ time, Toast.LENGTH_SHORT).show();
    }

    public void setTimeDialog(String time, int timeNum) {
        if(timeNum==1) {
            dbTime1 = time;
        }
        if(timeNum==2) {
            dbTime2 = time;
        }
        if(timeNum==3) {
            dbTime3 = time;
        }
    }

    public int getTimeNum() {
        return timeNum;
    }

    public void dayExists(int i){
        if(i==0)
            this.dayExists1 = false;
        if(i==1)
            this.dayExists1 = true;
    }

    public void setData() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        for (int i = 0; i < checkedDays.size(); i++) {
            final String day = checkedDays.get(i);
            mDatabase.child(day).child("Playlist 1").child("PlaylistName").setValue(dbPlaylist1);
            mDatabase.child(day).child("Playlist 1").child("Time").setValue(dbTime1);
            mDatabase.child(day).child("Playlist 2").child("PlaylistName").setValue(dbPlaylist2);
            mDatabase.child(day).child("Playlist 2").child("Time").setValue(dbTime2);
            mDatabase.child(day).child("Playlist 3").child("PlaylistName").setValue(dbPlaylist3);
            mDatabase.child(day).child("Playlist 3").child("Time").setValue(dbTime3);
        }
    }

    public void onFinishPlaylistDialog(String playlist, String number) {
        if(number=="Playlist 1") {
            dbPlaylist1 = playlist;
        }
        if(number=="Playlist 2") {
            dbPlaylist2 = playlist;
        }
        if(number=="Playlist 3") {
            dbPlaylist3 = playlist;
        }
    }

    public boolean checkTimeConflict(String time1, String time2, String time3)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            Date t1 = sdf.parse(time1);
            Date t2 = sdf.parse(time2);
            Date t3 = sdf.parse(time3);
            long elapsed1 = t1.getTime() - t2.getTime();
            elapsed1 = abs(TimeUnit.MILLISECONDS.toMinutes(elapsed1));
            long elapsed2 = t1.getTime() - t3.getTime();
            elapsed2 = abs(TimeUnit.MILLISECONDS.toMinutes(elapsed2));
            long elapsed3 = t2.getTime() - t3.getTime();
            elapsed3 = abs(TimeUnit.MILLISECONDS.toMinutes(elapsed3));
            if(elapsed1<30|elapsed2<30|elapsed3<30){
                return true;
            }
            else
                return false;
        }
        catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return true;
        }
    }

    private void setTimePickerToVariable(){
        MainActivity.sendTimeToHomeHr = MainActivity.timepickerTimeHr;
        MainActivity.sendTimeToHomeMin = MainActivity.timepickerTimeMin;
    }

    private void setAlarm(){
        Toast.makeText(this, "Hour: " + MainActivity.sendTimeToHomeHr + " Min: " + MainActivity.sendTimeToHomeMin, Toast.LENGTH_SHORT).show();
        int hr = MainActivity.sendTimeToHomeHr;
        int min = MainActivity.sendTimeToHomeMin;
        Calendar cur_cal = new GregorianCalendar();
        cur_cal.setTimeInMillis(System.currentTimeMillis());

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_YEAR, cur_cal.get(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.DATE, cur_cal.get(Calendar.DATE));
        calendar.set(Calendar.MONTH, cur_cal.get(Calendar.MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, hr);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, cur_cal.get(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, cur_cal.get(Calendar.MILLISECOND));

        Intent intent = new Intent(this, NotificationActivity.class);
        final int _id = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, _id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }



    public String getTimeTitle() {
        return time_title;
    }
    public boolean getdayExists() {
        return dayExists1;
    }
    public String getPlaylistTitle() {
        return title;
    }
    public String getPlaylist() {
        return playlist;
    }

}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.add_workout);
//        b = (Button) findViewById(R.id.schedule_workout);
//        playlists = (SelectSpinner) findViewById(R.id.multi_spinner);
//        playlists.setItems(items, getString(R.string.for_all), this);
//
////        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
////                R.array.playlist_array, android.R.layout.simple_dropdown_item_1line);
//
////        playlists.setAdapter(adapter);
//
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s = playlists.getSelectedItemsAsString();
//                Log.e("getSelected", s);
//            }
//        });
//
//        b.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Toast.makeText(ScheduleWorkoutActivity.this,"scheduled workout", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });
//    }
//}
