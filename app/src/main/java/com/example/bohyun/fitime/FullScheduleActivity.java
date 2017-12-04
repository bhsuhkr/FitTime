package com.example.bohyun.fitime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class FullScheduleActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    TextView mDate1, mTime1_1, mTime2_1, mTime3_1, mPlaylist1_1, mPlaylist2_1, mPlaylist3_1;
    TextView mDate2, mTime1_2, mTime2_2, mTime3_2, mPlaylist1_2, mPlaylist2_2, mPlaylist3_2;
    TextView mDate3, mTime1_3, mTime2_3, mTime3_3, mPlaylist1_3, mPlaylist2_3, mPlaylist3_3;
    TextView mDate4, mTime1_4, mTime2_4, mTime3_4, mPlaylist1_4, mPlaylist2_4, mPlaylist3_4;
    TextView mDate5, mTime1_5, mTime2_5, mTime3_5, mPlaylist1_5, mPlaylist2_5, mPlaylist3_5;
    TextView mDate6, mTime1_6, mTime2_6, mTime3_6, mPlaylist1_6, mPlaylist2_6, mPlaylist3_6;
    TextView mDate7, mTime1_7, mTime2_7, mTime3_7, mPlaylist1_7, mPlaylist2_7, mPlaylist3_7;
    ImageButton deleteButton1, deleteButton2, deleteButton3, deleteButton4, deleteButton5, deleteButton6, deleteButton7;
    FrameLayout frameLayout1, frameLayout2, frameLayout3, frameLayout4, frameLayout5, frameLayout6, frameLayout7;
    String curExercise1;
    String curExercise2;
    String curExercise3;
    String curTime1;
    String curTime2;
    String curTime3;
    String playlistName;
    String playlistTime;
    String playlistNum;
    String dayDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_schedule);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        Calendar cal4 = Calendar.getInstance();
        Calendar cal5 = Calendar.getInstance();
        Calendar cal6 = Calendar.getInstance();
        Calendar cal7 = Calendar.getInstance();
        cal1.set(Calendar.DAY_OF_WEEK, cal1.getFirstDayOfWeek());
        cal2.set(Calendar.DAY_OF_WEEK, cal1.getFirstDayOfWeek()+1);
        cal3.set(Calendar.DAY_OF_WEEK, cal1.getFirstDayOfWeek()+2);
        cal4.set(Calendar.DAY_OF_WEEK, cal1.getFirstDayOfWeek()+3);
        cal5.set(Calendar.DAY_OF_WEEK, cal1.getFirstDayOfWeek()+4);
        cal6.set(Calendar.DAY_OF_WEEK, cal1.getFirstDayOfWeek()+5);
        cal7.set(Calendar.DAY_OF_WEEK, cal1.getFirstDayOfWeek()+6);
        String day1 = cal1.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+" "+Integer.toString(cal1.get(Calendar.DAY_OF_MONTH));
        String day2 = cal2.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+" "+Integer.toString(cal2.get(Calendar.DAY_OF_MONTH));
        String day3 = cal3.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+" "+Integer.toString(cal3.get(Calendar.DAY_OF_MONTH));
        String day4 = cal4.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+" "+Integer.toString(cal4.get(Calendar.DAY_OF_MONTH));
        String day5 = cal5.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+" "+Integer.toString(cal5.get(Calendar.DAY_OF_MONTH));
        String day6 = cal6.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+" "+Integer.toString(cal6.get(Calendar.DAY_OF_MONTH));
        String day7 = cal7.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+" "+Integer.toString(cal7.get(Calendar.DAY_OF_MONTH));
        mDate1 = (TextView)findViewById(R.id.date1);mDate2 = (TextView)findViewById(R.id.date2);mDate3 = (TextView)findViewById(R.id.date3);mDate4 = (TextView)findViewById(R.id.date4);mDate5 = (TextView)findViewById(R.id.date5);mDate6 = (TextView)findViewById(R.id.date6);mDate7 = (TextView)findViewById(R.id.date7);
        mDate1.setText(day1);mDate2.setText(day2);mDate3.setText(day3);mDate4.setText(day4);mDate5.setText(day5);mDate6.setText(day6);mDate7.setText(day7);
        mTime1_1 = (TextView)findViewById(R.id.time1_1);mTime2_1 = (TextView)findViewById(R.id.time2_1);mTime3_1 = (TextView)findViewById(R.id.time3_1);mPlaylist1_1 = (TextView)findViewById(R.id.exercise1_1);mPlaylist2_1 = (TextView)findViewById(R.id.exercise2_1);mPlaylist3_1 = (TextView)findViewById(R.id.exercise3_1);frameLayout1=(FrameLayout)findViewById(R.id.FrameLayout1);
        mTime1_2 = (TextView)findViewById(R.id.time1_2);mTime2_2 = (TextView)findViewById(R.id.time2_2);mTime3_2 = (TextView)findViewById(R.id.time3_2);mPlaylist1_2 = (TextView)findViewById(R.id.exercise1_2);mPlaylist2_2 = (TextView)findViewById(R.id.exercise2_2);mPlaylist3_2 = (TextView)findViewById(R.id.exercise3_2);frameLayout2=(FrameLayout)findViewById(R.id.FrameLayout2);
        mTime1_3 = (TextView)findViewById(R.id.time1_3);mTime2_3 = (TextView)findViewById(R.id.time2_3);mTime3_3 = (TextView)findViewById(R.id.time3_3);mPlaylist1_3 = (TextView)findViewById(R.id.exercise1_3);mPlaylist2_3 = (TextView)findViewById(R.id.exercise2_3);mPlaylist3_3 = (TextView)findViewById(R.id.exercise3_3);frameLayout3=(FrameLayout)findViewById(R.id.FrameLayout3);
        mTime1_4 = (TextView)findViewById(R.id.time1_4);mTime2_4 = (TextView)findViewById(R.id.time2_4);mTime3_4 = (TextView)findViewById(R.id.time3_4);mPlaylist1_4 = (TextView)findViewById(R.id.exercise1_4);mPlaylist2_4 = (TextView)findViewById(R.id.exercise2_4);mPlaylist3_4 = (TextView)findViewById(R.id.exercise3_4);frameLayout4=(FrameLayout)findViewById(R.id.FrameLayout4);
        mTime1_5 = (TextView)findViewById(R.id.time1_5);mTime2_5 = (TextView)findViewById(R.id.time2_5);mTime3_5 = (TextView)findViewById(R.id.time3_5);mPlaylist1_5 = (TextView)findViewById(R.id.exercise1_5);mPlaylist2_5 = (TextView)findViewById(R.id.exercise2_5);mPlaylist3_5 = (TextView)findViewById(R.id.exercise3_5);frameLayout5=(FrameLayout)findViewById(R.id.FrameLayout5);
        mTime1_6 = (TextView)findViewById(R.id.time1_6);mTime2_6 = (TextView)findViewById(R.id.time2_6);mTime3_6 = (TextView)findViewById(R.id.time3_6);mPlaylist1_6 = (TextView)findViewById(R.id.exercise1_6);mPlaylist2_6 = (TextView)findViewById(R.id.exercise2_6);mPlaylist3_6 = (TextView)findViewById(R.id.exercise3_6);frameLayout6=(FrameLayout)findViewById(R.id.FrameLayout6);
        mTime1_7 = (TextView)findViewById(R.id.time1_7);mTime2_7 = (TextView)findViewById(R.id.time2_7);mTime3_7 = (TextView)findViewById(R.id.time3_7);mPlaylist1_7 = (TextView)findViewById(R.id.exercise1_7);mPlaylist2_7 = (TextView)findViewById(R.id.exercise2_7);mPlaylist3_7 = (TextView)findViewById(R.id.exercise3_7);frameLayout7=(FrameLayout)findViewById(R.id.FrameLayout7);
        deleteButton1 = (ImageButton)findViewById(R.id.delete_button1); deleteButton2 = (ImageButton)findViewById(R.id.delete_button2); deleteButton3 = (ImageButton)findViewById(R.id.delete_button3); deleteButton4 = (ImageButton)findViewById(R.id.delete_button4); deleteButton5 = (ImageButton)findViewById(R.id.delete_button5); deleteButton6 = (ImageButton)findViewById(R.id.delete_button6); deleteButton7 = (ImageButton)findViewById(R.id.delete_button7);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("schedule").child(userId).child("Day");
//        String day = (String) mDatabase.child("schedule").child(userId).child(curDay).getKey();
//        Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();
//        setInvisible();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                mTime1_1.setText(curTime1);
//                mTime2_1.setText(curTime2);
//                mTime3_1.setText(curTime3);
                if(dataSnapshot.hasChild("Sunday")){
                    for (DataSnapshot postSnapshot : dataSnapshot.child("Sunday").getChildren()) {
                        playlistNum = (String) postSnapshot.getKey();
                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
                        playlistTime = (String) postSnapshot.child("Time").getValue();
                        setcurExercise(playlistName,playlistTime, playlistNum);
                        frameLayout1.setVisibility(View.VISIBLE);
                        mPlaylist1_1.setText(curExercise1);
                        mPlaylist2_1.setText(curExercise2);
                        mPlaylist3_1.setText(curExercise3);
                        mTime1_1.setText(curTime1);
                        mTime2_1.setText(curTime2);
                        mTime3_1.setText(curTime3);
//                        Toast.makeText(getActivity(),"'"+playlistName+"'", Toast.LENGTH_SHORT).show();
//                        for (DataSnapshot pSnapshot : dataSnapshot.child(curDay).child(playlistNum).child("PlaylistName").getChildren()) {
//                            playlistName = (String) pSnapshot.getKey();
//                            Toast.makeText(getActivity(),"'"+playlistName+"'", Toast.LENGTH_SHORT).show();
//                        }
//                    playlistName = (String) mDatabase.child(curDay).child(playlistNum).child("PlaylistName").getKey();
//                    Toast.makeText(getActivity(),"'"+playlistNum+"'", Toast.LENGTH_SHORT).show();
//                    if(playlistNum.equals("Playlist 1")){
//                        curExercise1 = playlistName;
//                    }
//                    if(playlistNum.equals("Playlist 2")){
//                        curExercise2 = playlistName;
//                    }
//                    if(playlistNum.equals("Playlist 3")){
//                        curExercise3 = playlistName;
//                    }
//                    Toast.makeText(getActivity(),"'"+playlistNum+"'", Toast.LENGTH_SHORT).show();
                    }
                }

                if(dataSnapshot.hasChild("Monday")){
                    for (DataSnapshot postSnapshot : dataSnapshot.child("Monday").getChildren()) {
                        playlistNum = (String) postSnapshot.getKey();
                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
                        playlistTime = (String) postSnapshot.child("Time").getValue();
                        setcurExercise(playlistName,playlistTime, playlistNum);
//                        Toast.makeText(FullScheduleActivity.this,curExercise1,Toast.LENGTH_SHORT).show();
                        frameLayout2.setVisibility(View.VISIBLE);
                        mPlaylist1_2.setText(curExercise1);
                        mPlaylist2_2.setText(curExercise2);
                        mPlaylist3_2.setText(curExercise3);
                        mTime1_2.setText(curTime1);
                        mTime2_2.setText(curTime2);
                        mTime3_2.setText(curTime3);

                    }
                }
                if(dataSnapshot.hasChild("Tuesday")){
                    for (DataSnapshot postSnapshot : dataSnapshot.child("Tuesday").getChildren()) {
                        playlistNum = (String) postSnapshot.getKey();
                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
                        playlistTime = (String) postSnapshot.child("Time").getValue();
                        setcurExercise(playlistName,playlistTime, playlistNum);
                        frameLayout3.setVisibility(View.VISIBLE);
                        mPlaylist1_3.setText(curExercise1);
                        mPlaylist2_3.setText(curExercise2);
                        mPlaylist3_3.setText(curExercise3);
                        mTime1_3.setText(curTime1);
                        mTime2_3.setText(curTime2);
                        mTime3_3.setText(curTime3);
                    }
                }
                if(dataSnapshot.hasChild("Wednesday")){
                    for (DataSnapshot postSnapshot : dataSnapshot.child("Wednesday").getChildren()) {
                        playlistNum = (String) postSnapshot.getKey();
                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
                        playlistTime = (String) postSnapshot.child("Time").getValue();
                        setcurExercise(playlistName,playlistTime, playlistNum);
                        frameLayout4.setVisibility(View.VISIBLE);
                        mPlaylist1_4.setText(curExercise1);
                        mPlaylist2_4.setText(curExercise2);
                        mPlaylist3_4.setText(curExercise3);
                        mTime1_4.setText(curTime1);
                        mTime2_4.setText(curTime2);
                        mTime3_4.setText(curTime3);
                    }
                }
                if(dataSnapshot.hasChild("Thursday")){
                    for (DataSnapshot postSnapshot : dataSnapshot.child("Thursday").getChildren()) {
                        playlistNum = (String) postSnapshot.getKey();
                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
                        playlistTime = (String) postSnapshot.child("Time").getValue();
                        setcurExercise(playlistName,playlistTime, playlistNum);
                        frameLayout5.setVisibility(View.VISIBLE);
                        mPlaylist1_5.setText(curExercise1);
                        mPlaylist2_5.setText(curExercise2);
                        mPlaylist3_5.setText(curExercise3);
                        mTime1_5.setText(curTime1);
                        mTime2_5.setText(curTime2);
                        mTime3_5.setText(curTime3);
                    }
                }
                if(dataSnapshot.hasChild("Friday")){
                    for (DataSnapshot postSnapshot : dataSnapshot.child("Friday").getChildren()) {
                        playlistNum = (String) postSnapshot.getKey();
                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
                        playlistTime = (String) postSnapshot.child("Time").getValue();
                        setcurExercise(playlistName,playlistTime, playlistNum);
                        frameLayout6.setVisibility(View.VISIBLE);
                        mPlaylist1_6.setText(curExercise1);
                        mPlaylist2_6.setText(curExercise2);
                        mPlaylist3_6.setText(curExercise3);
                        mTime1_6.setText(curTime1);
                        mTime2_6.setText(curTime2);
                        mTime3_6.setText(curTime3);
                    }
                }
                if(dataSnapshot.hasChild("Saturday")){
                    for (DataSnapshot postSnapshot : dataSnapshot.child("Saturday").getChildren()) {
                        playlistNum = (String) postSnapshot.getKey();
                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
                        playlistTime = (String) postSnapshot.child("Time").getValue();
                        setcurExercise(playlistName,playlistTime, playlistNum);
                        frameLayout7.setVisibility(View.VISIBLE);
                        mPlaylist1_7.setText(curExercise1);
                        mPlaylist2_7.setText(curExercise2);
                        mPlaylist3_7.setText(curExercise3);
                        mTime1_7.setText(curTime1);
                        mTime2_7.setText(curTime2);
                        mTime3_7.setText(curTime3);
                    }
                }
//
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        deleteButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayDelete = "Sunday";
                DeleteButtonFragment dialog = new DeleteButtonFragment();
                dialog.show(getSupportFragmentManager(),"ScheduleWorkoutActivity.TimeDialog");
            }
        });
        deleteButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayDelete = "Monday";
                DeleteButtonFragment dialog = new DeleteButtonFragment();
                dialog.show(getSupportFragmentManager(),"ScheduleWorkoutActivity.TimeDialog");
            }
        });
        deleteButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayDelete = "Tuesday";
                DeleteButtonFragment dialog = new DeleteButtonFragment();
                dialog.show(getSupportFragmentManager(),"ScheduleWorkoutActivity.TimeDialog");
            }
        });
        deleteButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayDelete = "Wednesday";
                DeleteButtonFragment dialog = new DeleteButtonFragment();
                dialog.show(getSupportFragmentManager(),"ScheduleWorkoutActivity.TimeDialog");
            }
        });
        deleteButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayDelete = "Thursday";
                DeleteButtonFragment dialog = new DeleteButtonFragment();
                dialog.show(getSupportFragmentManager(),"ScheduleWorkoutActivity.TimeDialog");
            }
        });
        deleteButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayDelete = "Friday";
                DeleteButtonFragment dialog = new DeleteButtonFragment();
                dialog.show(getSupportFragmentManager(),"ScheduleWorkoutActivity.TimeDialog");
            }
        });
        deleteButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayDelete = "Saturday";
                DeleteButtonFragment dialog = new DeleteButtonFragment();
                dialog.show(getSupportFragmentManager(),"ScheduleWorkoutActivity.TimeDialog");
            }
        });

    }

    protected void onStart(Bundle savedInstanceState){
        super.onStart();



//        mDatabase = FirebaseDatabase.getInstance().getReference().child("schedule").child(userId).child("Day");
////        String day = (String) mDatabase.child("schedule").child(userId).child(curDay).getKey();
////        Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();
//        Toast.makeText(FullScheduleActivity.this,"started", Toast.LENGTH_SHORT).show();
//
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Toast.makeText(FullScheduleActivity.this,"listening", Toast.LENGTH_SHORT).show();
//
//                if(dataSnapshot.hasChild("Monday")){
//                    for (DataSnapshot postSnapshot : dataSnapshot.child("Monday").getChildren()) {
//                        playlistNum = (String) postSnapshot.getKey();
//                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
//                        playlistTime = (String) postSnapshot.child("Time").getValue();
//                        setcurExercise(playlistName,playlistTime, playlistNum);
//                        mPlaylist1_1.setText(curExercise1);
//                        mPlaylist2_1.setText(curExercise2);
//                        mPlaylist3_1.setText(curExercise3);
//                        mTime1_1.setText(curTime1);
//                        mTime2_1.setText(curTime2);
//                        mTime3_1.setText(curTime3);
////                        Toast.makeText(getActivity(),"'"+playlistName+"'", Toast.LENGTH_SHORT).show();
////                        for (DataSnapshot pSnapshot : dataSnapshot.child(curDay).child(playlistNum).child("PlaylistName").getChildren()) {
////                            playlistName = (String) pSnapshot.getKey();
////                            Toast.makeText(getActivity(),"'"+playlistName+"'", Toast.LENGTH_SHORT).show();
////                        }
////                    playlistName = (String) mDatabase.child(curDay).child(playlistNum).child("PlaylistName").getKey();
////                    Toast.makeText(getActivity(),"'"+playlistNum+"'", Toast.LENGTH_SHORT).show();
////                    if(playlistNum.equals("Playlist 1")){
////                        curExercise1 = playlistName;
////                    }
////                    if(playlistNum.equals("Playlist 2")){
////                        curExercise2 = playlistName;
////                    }
////                    if(playlistNum.equals("Playlist 3")){
////                        curExercise3 = playlistName;
////                    }
////                    Toast.makeText(getActivity(),"'"+playlistNum+"'", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                if(dataSnapshot.hasChild("Tuesday")){
//                    for (DataSnapshot postSnapshot : dataSnapshot.child("Tuesday").getChildren()) {
//                        playlistNum = (String) postSnapshot.getKey();
//                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
//                        playlistTime = (String) postSnapshot.child("Time").getValue();
//                        setcurExercise(playlistName,playlistTime, playlistNum);
//                        mPlaylist1_2.setText(curExercise1);
//                        mPlaylist2_2.setText(curExercise2);
//                        mPlaylist3_2.setText(curExercise3);
//                        mTime1_2.setText(curTime1);
//                        mTime2_2.setText(curTime2);
//                        mTime3_2.setText(curTime3);
//                    }
//                }
//                if(dataSnapshot.hasChild("Wednesday")){
//                    for (DataSnapshot postSnapshot : dataSnapshot.child("Wednesday").getChildren()) {
//                        playlistNum = (String) postSnapshot.getKey();
//                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
//                        playlistTime = (String) postSnapshot.child("Time").getValue();
//                        setcurExercise(playlistName,playlistTime, playlistNum);
//                        mPlaylist1_3.setText(curExercise1);
//                        mPlaylist2_3.setText(curExercise2);
//                        mPlaylist3_3.setText(curExercise3);
//                        mTime1_3.setText(curTime1);
//                        mTime2_3.setText(curTime2);
//                        mTime3_3.setText(curTime3);
//                    }
//                }
//                if(dataSnapshot.hasChild("Thursday")){
//                    for (DataSnapshot postSnapshot : dataSnapshot.child("Thursday").getChildren()) {
//                        playlistNum = (String) postSnapshot.getKey();
//                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
//                        playlistTime = (String) postSnapshot.child("Time").getValue();
//                        setcurExercise(playlistName,playlistTime, playlistNum);
//                        mPlaylist1_4.setText(curExercise1);
//                        mPlaylist2_4.setText(curExercise2);
//                        mPlaylist3_4.setText(curExercise3);
//                        mTime1_4.setText(curTime1);
//                        mTime2_4.setText(curTime2);
//                        mTime3_4.setText(curTime3);
//                    }
//                }
//                if(dataSnapshot.hasChild("Friday")){
//                    for (DataSnapshot postSnapshot : dataSnapshot.child("Friday").getChildren()) {
//                        playlistNum = (String) postSnapshot.getKey();
//                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
//                        playlistTime = (String) postSnapshot.child("Time").getValue();
//                        setcurExercise(playlistName,playlistTime, playlistNum);
//                        mPlaylist1_5.setText(curExercise1);
//                        mPlaylist2_5.setText(curExercise2);
//                        mPlaylist3_5.setText(curExercise3);
//                        mTime1_5.setText(curTime1);
//                        mTime2_5.setText(curTime2);
//                        mTime3_5.setText(curTime3);
//                    }
//                }
//                if(dataSnapshot.hasChild("Saturday")){
//                    for (DataSnapshot postSnapshot : dataSnapshot.child("Saturday").getChildren()) {
//                        playlistNum = (String) postSnapshot.getKey();
//                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
//                        playlistTime = (String) postSnapshot.child("Time").getValue();
//                        setcurExercise(playlistName,playlistTime, playlistNum);
//                        mPlaylist1_6.setText(curExercise1);
//                        mPlaylist2_6.setText(curExercise2);
//                        mPlaylist3_6.setText(curExercise3);
//                        mTime1_6.setText(curTime1);
//                        mTime2_6.setText(curTime2);
//                        mTime3_6.setText(curTime3);
//                    }
//                }
//                if(dataSnapshot.hasChild("Sunday")){
//                    for (DataSnapshot postSnapshot : dataSnapshot.child("Sunday").getChildren()) {
//                        playlistNum = (String) postSnapshot.getKey();
//                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
//                        playlistTime = (String) postSnapshot.child("Time").getValue();
//                        setcurExercise(playlistName,playlistTime, playlistNum);
//                        mPlaylist1_7.setText(curExercise1);
//                        mPlaylist2_7.setText(curExercise2);
//                        mPlaylist3_7.setText(curExercise3);
//                        mTime1_7.setText(curTime1);
//                        mTime2_7.setText(curTime2);
//                        mTime3_7.setText(curTime3);
//                    }
//                }
////
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        });

    }

    public void setcurExercise(String playlistName,String playlistTime, String playlistNum) {
        if(playlistNum.equals("Playlist 1")){
            curExercise1 = playlistName;
            curTime1 = playlistTime;
        }
        if(playlistNum.equals("Playlist 2")){
            curExercise2 = playlistName;
            curTime2 = playlistTime;
        }
        if(playlistNum.equals("Playlist 3")){
            curExercise3 = playlistName;
            curTime3 = playlistTime;
        }

    }
    public String getDayDelete() {
        return dayDelete;
    }
    public void dayDelete(String day) {
        mDatabase.child(day).removeValue();
        if(day.equals("Sunday")){
            frameLayout1.setVisibility(View.GONE);
        }
        if(day.equals("Monday")){
            curExercise1 = playlistName;
            curTime1 = playlistTime;
            frameLayout2.setVisibility(View.GONE);
        }
        if(day.equals("Tuesday")){
            curExercise1 = playlistName;
            curTime1 = playlistTime;
            frameLayout3.setVisibility(View.GONE);
        }
        if(day.equals("Wednesday")){
            curExercise1 = playlistName;
            curTime1 = playlistTime;
            frameLayout4.setVisibility(View.GONE);
        }
        if(day.equals("Thursday")){
            curExercise1 = playlistName;
            curTime1 = playlistTime;
            frameLayout5.setVisibility(View.GONE);
        }
        if(day.equals("Friday")){
            curExercise1 = playlistName;
            curTime1 = playlistTime;
            frameLayout6.setVisibility(View.GONE);
        }
        if(day.equals("Saturday")){
            curExercise1 = playlistName;
            curTime1 = playlistTime;
            frameLayout7.setVisibility(View.GONE);
        }
    }

//    public void setInvisible() {
//        mTime1_1.setVisibility(View.INVISIBLE);
//        mTime2_1.setVisibility(View.INVISIBLE);
//        mTime3_1.setVisibility(View.INVISIBLE);
//        mPlaylist1_1.setVisibility(View.INVISIBLE);
//        mPlaylist2_1.setVisibility(View.INVISIBLE);
//        mPlaylist3_1.setVisibility(View.INVISIBLE);
//        mTime1_2.setVisibility(View.INVISIBLE);
//        mTime2_2.setVisibility(View.INVISIBLE);
//        mTime3_2.setVisibility(View.INVISIBLE);
//        mPlaylist1_2.setVisibility(View.INVISIBLE);
//        mPlaylist2_2.setVisibility(View.INVISIBLE);
//        mPlaylist3_2.setVisibility(View.INVISIBLE);
//        mTime1_3.setVisibility(View.INVISIBLE);
//        mTime2_3.setVisibility(View.INVISIBLE);
//        mTime3_3.setVisibility(View.INVISIBLE);
//        mPlaylist1_3.setVisibility(View.INVISIBLE);
//        mPlaylist2_3.setVisibility(View.INVISIBLE);
//        mPlaylist3_3.setVisibility(View.INVISIBLE);
//        mTime1_4.setVisibility(View.INVISIBLE);
//        mTime2_4.setVisibility(View.INVISIBLE);
//        mTime3_4.setVisibility(View.INVISIBLE);
//        mPlaylist1_4.setVisibility(View.INVISIBLE);
//        mPlaylist2_4.setVisibility(View.INVISIBLE);
//        mPlaylist3_4.setVisibility(View.INVISIBLE);
//        mTime1_5.setVisibility(View.INVISIBLE);
//        mTime2_5.setVisibility(View.INVISIBLE);
//        mTime3_5.setVisibility(View.INVISIBLE);
//        mPlaylist1_5.setVisibility(View.INVISIBLE);
//        mPlaylist2_5.setVisibility(View.INVISIBLE);
//        mPlaylist3_5.setVisibility(View.INVISIBLE);
//        mTime1_6.setVisibility(View.INVISIBLE);
//        mTime2_6.setVisibility(View.INVISIBLE);
//        mTime3_6.setVisibility(View.INVISIBLE);
//        mPlaylist1_6.setVisibility(View.INVISIBLE);
//        mPlaylist2_6.setVisibility(View.INVISIBLE);
//        mPlaylist3_6.setVisibility(View.INVISIBLE);
//        mTime1_7.setVisibility(View.INVISIBLE);
//        mTime2_7.setVisibility(View.INVISIBLE);
//        mTime3_7.setVisibility(View.INVISIBLE);
//        mPlaylist1_7.setVisibility(View.INVISIBLE);
//        mPlaylist2_7.setVisibility(View.INVISIBLE);
//        mPlaylist3_7.setVisibility(View.INVISIBLE);
//
//    }

    }

