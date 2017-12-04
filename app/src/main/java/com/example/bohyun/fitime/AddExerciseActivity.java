package com.example.bohyun.fitime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by zschr on 11/29/2017.
 */

public class AddExerciseActivity extends Activity {
    private DatabaseReference mDatabase;
    private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    EditText name;
    EditText description;
    Spinner type;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        name = (EditText) findViewById(R.id.exerTitle2);
        description = (EditText) findViewById(R.id.exerDesc2);
        type = (Spinner) findViewById(R.id.spinner);


        Button addExerc = (Button) findViewById(R.id.addExerc);
        addExerc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference().child("exercises").child(userId);
                mDatabase.child(name.getText().toString()).child("description").setValue(description.getText().toString());
                mDatabase.child(name.getText().toString()).child("type").setValue(type.getSelectedItem().toString());
//                startActivity(new Intent(getApplicationContext(),ViewMyExercisesActivity.class ));
                finish();
            }
        });
    }
}