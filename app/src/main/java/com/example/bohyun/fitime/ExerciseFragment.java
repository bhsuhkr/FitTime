package com.example.bohyun.fitime;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class ExerciseFragment extends Fragment{

    public ExerciseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"Exercise", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.exercise_fragment, container, false);

        Button save;
        final ArrayList<String> addArray = new ArrayList<String>();
        final EditText txt;
        final ListView show;


        txt = (EditText)view.findViewById(R.id.exerName);
        show = (ListView)view.findViewById(R.id.exerList);
        save = (Button)view.findViewById(R.id.addExercise);
        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String getInput = txt.getText().toString();

                if(addArray.contains(getInput)){
                    Toast.makeText(getActivity(),"Exercise already exists", Toast.LENGTH_LONG).show();
                }
                else if (getInput == null || getInput.trim().equals("")){
                    Toast.makeText(getActivity(),"Input field is empty.", Toast.LENGTH_LONG).show();
                }
                else{
                    addArray.add(getInput);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, addArray);
                    show.setAdapter(adapter);
                    ((EditText)getView().findViewById(R.id.exerName)).setText(" ");
                }
            }


        });
        return view;
    }


}
