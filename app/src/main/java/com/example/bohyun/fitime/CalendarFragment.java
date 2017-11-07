package com.example.bohyun.fitime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class CalendarFragment extends Fragment{
    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_fragment, container, false);

        Button button = (Button) view.findViewById(R.id.plus_button);
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
}