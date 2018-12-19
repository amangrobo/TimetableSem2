package com.grobo.timetablesem2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DayFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<SingleDay>>{

    private String mDay;
    private TimeTableAdapter mAdapter;
    private ListView listView;
    public String branchPreference;
    private static final String TIMETABLE_URL = "https://timetable-grobo.firebaseio.com/.json";

    public static DayFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt("dayNumber", page);
        DayFragment fragment = new DayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (getArguments().getInt("dayNumber")){
            case 1:
                mDay = "monday";
                break;
            case 2:
                mDay = "tuesday";
                break;
            case 3:
                mDay = "wednesday";
                break;
            case 4:
                mDay = "thursday";
                break;
            case 5:
                mDay = "friday";
                break;
            default:

        }

        branchPreference = getContext().getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("branchPreference", "").toLowerCase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timetable_list, container, false);

        listView = rootView.findViewById(R.id.timetable_list_view);

        List<SingleDay> singleDayList = new ArrayList<SingleDay>();

        mAdapter = new TimeTableAdapter(getActivity(), R.layout.single_day_item, singleDayList);

        listView.setAdapter(mAdapter);

        LoaderManager loaderManager = android.support.v4.app.LoaderManager.getInstance(this);
        loaderManager.initLoader(1, null, this);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public Loader<List<SingleDay>> onCreateLoader(int i, Bundle bundle) {

        return new TimetableLoader(getContext(), TIMETABLE_URL, branchPreference, mDay);
    }

    @Override
    public void onLoaderReset(Loader<List<SingleDay>> loader) {
        mAdapter.clear();
    }

    @Override
    public void onLoadFinished(Loader<List<SingleDay>> loader, List<SingleDay> singleDayList) {
        mAdapter.clear();

        if (singleDayList != null && !singleDayList.isEmpty()){
            mAdapter.addAll(singleDayList);
        }

    }

}
