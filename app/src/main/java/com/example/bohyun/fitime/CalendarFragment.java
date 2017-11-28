package com.example.bohyun.fitime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class CalendarFragment extends Fragment {
    private DatabaseReference mDatabase;
    private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Calendar sCalendar = Calendar.getInstance();
    String curDay = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    String curDate = sCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())+" "+Integer.toString(sCalendar.get(Calendar.DAY_OF_MONTH));
    String playlistNum;
    String curExercise1;
    String curExercise2;
    String curExercise3;
    String curTime1;
    String curTime2;
    String curTime3;
    String playlistName;
    String playlistTime;


    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_fragment, container, false);

        ImageButton button = (ImageButton) view.findViewById(R.id.plus_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Add Exercise Clicked", Toast.LENGTH_SHORT).show();
                Intent intentLoadNewActivity = new Intent(getActivity(), ScheduleWorkoutActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        Button skipButton = (Button) view.findViewById(R.id.full_schedule_button);
        skipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(),"Full Schedule Clicked", Toast.LENGTH_SHORT).show();
                Intent intentLoadNewActivity = new Intent(getActivity(), FullScheduleActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        TextView day = (TextView) getView().findViewById(R.id.day);
        TextView date = (TextView) getView().findViewById(R.id.date);
        final TextView time1 = (TextView) getView().findViewById(R.id.time1);
        final TextView time2 = (TextView) getView().findViewById(R.id.time2);
        final TextView time3 = (TextView) getView().findViewById(R.id.time3);
        final TextView exercise1 = (TextView) getView().findViewById(R.id.exercise1);
        final TextView exercise2 = (TextView) getView().findViewById(R.id.exercise2);
        final TextView exercise3 = (TextView) getView().findViewById(R.id.exercise3);
        final LinearLayout info2 = (LinearLayout) getView().findViewById(R.id.info2);
        final LinearLayout info3 = (LinearLayout) getView().findViewById(R.id.info3);

        day.setText(curDay+",");
        date.setText(curDate);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("schedule").child(userId).child("Day");
//        String day = (String) mDatabase.child("schedule").child(userId).child(curDay).getKey();
//        Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                time1.setText("No workouts today");
                exercise1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);
                info3.setVisibility(View.GONE);
                if(dataSnapshot.hasChild(curDay)){
                    for (DataSnapshot postSnapshot : dataSnapshot.child(curDay).getChildren()) {
                        playlistNum = (String) postSnapshot.getKey();
                        playlistName = (String) postSnapshot.child("PlaylistName").getValue();
                        playlistTime = (String) postSnapshot.child("Time").getValue();
                        setcurExercise(playlistName,playlistTime, playlistNum);
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
                    exercise1.setVisibility(View.VISIBLE);
                    info2.setVisibility(View.VISIBLE);
                    info3.setVisibility(View.VISIBLE);
                    exercise1.setText(curExercise1);
                    exercise2.setText(curExercise2);
                    exercise3.setText(curExercise3);
                    time1.setText(curTime1);
                    time2.setText(curTime2);
                    time3.setText(curTime3);
                }
//                day = (String) dataSnapshot.getKey();
//                Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    day = (String) postSnapshot.getKey();
//                    Toast.makeText(getActivity(),"'"+day+"'", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(),"'"+curDay+"'", Toast.LENGTH_SHORT).show();
//
//                    if(day==curDay){
//                        Toast.makeText(getActivity(),curDay, Toast.LENGTH_SHORT).show();
//                    }
//                }

//                String day = dataSnapshot.getKey();
//                Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();
//                if(dataSnapshot.getKey()==curDay){
//                    String day = (String) dataSnapshot.getKey();
//                    Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();
//                    }
//                if(day==curDay){
//                    Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();
//                }
//                if (dataSnapshot.hasChild(curDay)) {
//                    Toast.makeText(getActivity(),curDay, Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

//        mDatabase.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                for (dataSnapshot.getKey())
//                {
//                    String day = (String) dataSnapshot.getKey();
//                    Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();
////                    if(mSnapshot.getKey()==curDay){
////                        String day = (String) mSnapshot.getKey();
////                        Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();
////                    }
//                }
////                Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();
////                if(day==curDay){
////                    Toast.makeText(getActivity(),day, Toast.LENGTH_SHORT).show();
////                }
////                if (dataSnapshot.hasChild(curDay)) {
////                    Toast.makeText(getActivity(),curDay, Toast.LENGTH_SHORT).show();
////                }
//            }
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {}
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {}
//        });


    }
    //    String[] players={"Exercise 1","Exercise 2","Exercise 3","Exercise 4","Exercise 5","Exercise 6","Exercise 7"};
//    int[] images={R.drawable.monday,R.drawable.tuesday,R.drawable.wednesday,R.drawable.thursday,R.drawable.friday,R.drawable.saturday,R.drawable.sunday};
//    ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String,String>>();
//    SimpleAdapter adapter;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        //MAP
//        HashMap<String, String> map=new HashMap<String, String>();
//        //FILL
//        for(int i=0;i<players.length;i++)
//        {
//            map=new HashMap<String, String>();
//            map.put("Player", players[i]);
//            map.put("Image", Integer.toString(images[i]));
//            data.add(map);
//        }
//        //KEYS IN MAP
//        String[] from={"Player","Image"};
//        //IDS OF VIEWS
//        int[] to={R.id.nameTxt,R.id.imageView1};
//        //ADAPTER
//        adapter=new SimpleAdapter(getActivity(), data, R.layout.calendar_fragment, from, to);
//        setListAdapter(adapter);
//
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> av, View v, int pos,
//                                    long id) {
//                Toast.makeText(getActivity(), data.get(pos).get("Player"), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
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
}