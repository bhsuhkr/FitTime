package com.example.bohyun.fitime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by zschr on 10/24/2017.
 */

public class ScheduleWorkoutActivity extends AppCompatActivity {

    private Button b;
    CalendarFragment fragment = new CalendarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_workout);
        b = (Button) findViewById(R.id.schedule_workout);

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(ScheduleWorkoutActivity.this,"scheduled workout", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
