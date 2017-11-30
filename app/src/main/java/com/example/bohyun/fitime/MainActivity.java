package com.example.bohyun.fitime;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationItem;
    private Fragment fragment;
    private Fragment homeFrg;
    private Fragment plyFrg;
    private Fragment exeFrg;
    private Fragment schFrg;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFrg = new HomeFragment();
        plyFrg = new PlaylistFragment();
        exeFrg = new ExerciseFragment();
        schFrg = new CalendarFragment();

        fragment = homeFrg;

        //Daily View || Start View
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();

        mBottomNavigationItem = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNavigationItem.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.daily_menu:
                        fragment = homeFrg;
                        break;
                    case R.id.playlist_menu:
                        fragment = plyFrg;
                        break;
                    case R.id.exercise_menu:
                        fragment = exeFrg;
                        break;
                    case R.id.info_menu:
                        fragment = schFrg;
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.setting_actionbar:
                Intent intent = new Intent(this, Settings.class);
                this.startActivity(intent);
                break;
        }
        return true;
    }
}
