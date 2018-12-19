package com.grobo.timetablesem2;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("TIMETABLE");

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);

        if (isFirstRun) {

            startActivity(new Intent(MainActivity.this, FirstActivity.class));
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();

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
    protected void onResume() {
        super.onResume();
        showTimetable();
    }
}
