package com.example.bohyun.fitime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class ViewMyWorkoutsActivity extends Activity{
    //a List of type hero for holding list items
    List<Workout> workoutList;

    //the listview
    ListView listView;
    FloatingActionButton addButton;
    private DatabaseReference mDatabase;
    private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_workouts);
        //initializing objects
        workoutList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.mylistView2);
        addButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("playlists").child(userId);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String name = (String) postSnapshot.getKey();
                    String description = (String) postSnapshot.child("description").getValue();
                    String exercises = (String) postSnapshot.child("exercises").getValue();
                    workoutList.add(new Workout(name, description, exercises));
                }
                MyWorkoutListAdapter adapter = new MyWorkoutListAdapter(ViewMyWorkoutsActivity.this, R.layout.my_custom_list, workoutList);

                //attaching adapter to the listview
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView v = (TextView) view.findViewById(R.id.textViewName);
                        Toast.makeText(getApplicationContext(), "selected Item Name is " + v.getText(), Toast.LENGTH_LONG).show();
                        ViewWorkoutActivity.workOut_clicked = v.getText().toString();
                        startActivity(new Intent(ViewMyWorkoutsActivity.this, ViewWorkoutActivity.class));
                    }
                });
                addButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(new Intent(ViewMyWorkoutsActivity.this, AddWorkoutActivity.class));
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView) view.findViewById(R.id.textViewName);
                Toast.makeText(getApplicationContext(), "selected Item Name is " + v.getText(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(ViewMyWorkoutsActivity.this, ViewWorkoutActivity.class));
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ViewMyWorkoutsActivity.this, AddWorkoutActivity.class));

            }
        });
    }
}



