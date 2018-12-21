package com.grobo.timetablesem2;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class TimetableLoader extends AsyncTaskLoader<String> {

    private static final String LOG_TAG = TimetableLoader.class.getName();
    private String mUrl;

    public TimetableLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        String jsonResponse = QueryUtils.doEverything(mUrl);
        return  jsonResponse;
    }

}
