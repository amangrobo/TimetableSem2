package com.grobo.timetablesem2;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class TimetableLoader extends AsyncTaskLoader<List<SingleDay>> {

    private static final String LOG_TAG = TimetableLoader.class.getName();
    private String mUrl;
    private String mBranchPreference;
    private String mDayPreference;

    public TimetableLoader(Context context, String url, String branchPreference, String dayPreference) {
        super(context);
        mUrl = url;
        mBranchPreference = branchPreference;
        mDayPreference = dayPreference;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<SingleDay> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<SingleDay> listTimetable = QueryUtils.doEverything(mUrl, mBranchPreference, mDayPreference);
        return  listTimetable;
    }

}
