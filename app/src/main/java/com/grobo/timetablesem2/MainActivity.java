package com.grobo.timetablesem2;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    String branchPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setElevation(0);
    //    actionBar.setIcon(R.drawable.icon);
        actionBar.setTitle("TIMETABLE");

        branchPreference = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("branchPreference", "");


        if (branchPreference == "") {
            startActivity(new Intent(MainActivity.this, FirstActivity.class));
        } else {
            showTimetable();
        }

    }

    private void showTimetable(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        DaysFragmentAdapter pagerAdapter = new DaysFragmentAdapter(MainActivity.this, getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

   //     Calendar calendar = Calendar.getInstance();
   //     int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
   //     viewPager.setCurrentItem(dayOfWeek + 1);

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (branchPreference == "") {
            finish();
        }
        showTimetable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (branchPreference == "") {
            finish();
        }
        showTimetable();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
