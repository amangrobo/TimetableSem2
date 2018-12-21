package com.grobo.timetablesem2;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class JsonLoader extends AsyncTaskLoader<List<SingleDay>> {

    String jsonString;
    String mBranchPreference;
    String mDayPreference;

    public JsonLoader(Context context, String dayPreference) {
        super(context);
        jsonString = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getString("jsonString", "");
        mBranchPreference = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getString("branchPreference", "");
        mDayPreference = dayPreference;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<SingleDay> loadInBackground() {
        if (jsonString == null) {
            return null;
        }
        List<SingleDay> singleDayList = QueryUtils.extractTimetable(jsonString, mBranchPreference, mDayPreference);
        return  singleDayList;
    }
}
