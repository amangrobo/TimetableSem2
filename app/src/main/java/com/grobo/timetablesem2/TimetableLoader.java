package com.grobo.timetablesem2;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class TimetableLoader extends AsyncTaskLoader<String> {

    private static final String LOG_TAG = TimetableLoader.class.getName();
    private String mUrl;
    private static final String TIMETABLE_URL = "https://timetable-grobo.firebaseio.com/";

    public TimetableLoader(Context context, String branchPre, String branch) {
        super(context);
        mUrl = TIMETABLE_URL + branchPre + "/" + branch + "/.json/";
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {

        String jsonResponse = QueryUtils.doEverything(mUrl);
        return  jsonResponse;
    }

}
