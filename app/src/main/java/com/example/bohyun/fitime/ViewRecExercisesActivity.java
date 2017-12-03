package com.example.bohyun.fitime;

/**
 * Created by zschr on 11/28/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class ViewRecExercisesActivity extends Activity {

    //a List of type hero for holding list items
    List<Exercise> exerciseList;

    //the listview
    ListView listView;

    private DatabaseReference mDatabase;
    private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rec_exercises);

        //initializing objects
        exerciseList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.reclistView);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("exercisesPref");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String name = (String) postSnapshot.getKey();
                    String description = (String) postSnapshot.child("description").getValue();
                    String type = (String) postSnapshot.child("type").getValue();
//                    Toast.makeText(ViewMyExercisesActivity.this,name, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(ViewMyExercisesActivity.this,description, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(ViewMyExercisesActivity.this,type, Toast.LENGTH_SHORT).show();
                    //Exercise exercise = new Exercise(name, description, type);
                    exerciseList.add(new Exercise(name, description, type));
//                    Toast.makeText(ViewMyExercisesActivity.this, Arrays.toString(MainActivity.exerciseList.toArray()), Toast.LENGTH_SHORT).show();
                }
                RecListAdapter adapter = new RecListAdapter(ViewRecExercisesActivity.this, R.layout.my_custom_list, exerciseList);

                //attaching adapter to the listview
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView v = (TextView) view.findViewById(R.id.textViewName);
                        Toast.makeText(getApplicationContext(), "selected Item Name is " + v.getText(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ViewRecExercisesActivity.this, ViewExerciseActivity.class));
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

        //adding some values to our list
//        exerciseList.add(new Exercise("Exercise 1", "Spiderman", "Avengers"));
//        exerciseList.add(new Exercise("Exercise 2", "Joker", "Injustice Gang"));
//        exerciseList.add(new Exercise("Exercise 3", "Iron Man", "Avengers"));
//        exerciseList.add(new Exercise("Exercise 4", "Doctor Strange", "Avengers"));
//        exerciseList.add(new Exercise("Exercise 5", "Captain America", "Avengers"));
//        exerciseList.add(new Exercise("Exercise 6", "Batman", "Justice League"));

        //creating the adapter

    }
}