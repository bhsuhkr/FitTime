package com.example.bohyun.fitime;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

/**
 * Created by zschr on 11/29/2017.
 */

public class ViewRecExerciseActivity extends Activity {
    private DatabaseReference mDatabase;
    private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    TextView exerName;
    TextView exerDesc;
    TextView exerType;
    public static String exercise_clicked;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rec_exercise);
        exerName = (TextView) findViewById(R.id.exerNameView2);
        exerDesc = (TextView) findViewById(R.id.exerDescView2);
        exerType = (TextView) findViewById(R.id.exerTypeView2);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("exercisesPref");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(exercise_clicked)) {
//                        playlistNum = (String) postSnapshot.getKey();
                    String desc = (String) dataSnapshot.child(exercise_clicked).child("description").getValue();
                    String type = (String) dataSnapshot.child(exercise_clicked).child("type").getValue();
                    exerName.setText(exercise_clicked);
                    exerDesc.setText(desc);
                    exerType.setText(type);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

    }
}