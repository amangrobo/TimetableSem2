package com.grobo.timetablesem2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DaysFragmentAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 7;
    private String tabTitles[] = new String[] { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY","SATURDAY","SUNDAY" };
    private Context mContext;

    public DaysFragmentAdapter(Context context, FragmentManager fm){
        super(fm);
        mContext = context;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return DayFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
