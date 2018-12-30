package com.grobo.timetablesem2;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class JsonLoader extends AsyncTaskLoader<List<SingleDay>> {

    private String jsonString;
    private String mBranchPreference;
    private String mDayPreference;
    private String mBranchPre;

    public JsonLoader(Context context, String dayPreference) {
        super(context);
        jsonString = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getString("jsonString", "");
        mBranchPreference = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getString("branchPreference", "");
        mBranchPre = context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getString("branchPre", "");
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
        List<SingleDay> singleDayList = QueryUtils.extractTimetable(jsonString, mBranchPre, mBranchPreference, mDayPreference);
        return  singleDayList;
    }
}
