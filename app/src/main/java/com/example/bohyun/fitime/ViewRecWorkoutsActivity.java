package com.example.bohyun.fitime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zschr on 11/29/2017.
 */

public class ViewRecWorkoutsActivity extends Activity{
    List<Exercise> exerciseList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rec_workouts);
        //initializing objects
        exerciseList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.reclistView);

        //adding some values to our list
        exerciseList.add(new Exercise("Exercise 1", "Spiderman", "Avengers"));
        exerciseList.add(new Exercise("Exercise 2", "Joker", "Injustice Gang"));
        exerciseList.add(new Exercise("Exercise 3", "Iron Man", "Avengers"));
        exerciseList.add(new Exercise("Exercise 4", "Doctor Strange", "Avengers"));
        exerciseList.add(new Exercise("Exercise 5", "Captain America", "Avengers"));
        exerciseList.add(new Exercise("Exercise 10", "Batman", "Justice League"));
        //creating the adapter
        MyListAdapter adapter = new MyListAdapter(this, R.layout.my_custom_list, exerciseList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView) view.findViewById(R.id.textViewName);
                Toast.makeText(getApplicationContext(), "selected Item Name is " + v.getText(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(ViewRecWorkoutsActivity.this, ViewWorkoutActivity.class));
            }
        });

    }
}
