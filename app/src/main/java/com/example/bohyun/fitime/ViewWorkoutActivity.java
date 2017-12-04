package com.example.bohyun.fitime;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class ViewWorkoutActivity extends Activity {
    private DatabaseReference mDatabase;
    private String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    TextView workOutName;
    TextView workOutDesc;
    TextView workOutType;
    Button delworkOut;
    public static String workOut_clicked;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);
        workOutName = (TextView) findViewById(R.id.workOutNameView2);
        workOutDesc = (TextView) findViewById(R.id.workOutDescView2);
        workOutType = (TextView) findViewById(R.id.workOutTypeView2);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("playlists").child(userId);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(workOut_clicked)){
//                        playlistNum = (String) postSnapshot.getKey();
                    String desc = (String) dataSnapshot.child(workOut_clicked).child("description").getValue();
                    String type = (String) dataSnapshot.child(workOut_clicked).child("exercises").getValue();
                    workOutName.setText(workOut_clicked);
                    workOutDesc.setText(desc);
                    workOutType.setText(type);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        delworkOut = (Button) findViewById(R.id.delworkOut);

        delworkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(workOut_clicked).removeValue();
                finish();
            }
        });

    }
}


