package com.grobo.timetablesem2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DayFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<SingleDay>>{

    private String mDay;
    private TimeTableAdapter mAdapter;
    private ListView listView;

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
            case 6:
                mDay="saturday";
                break;
            case 7:
                mDay="sunday";
                break;
            default:

        }

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
        loaderManager.initLoader(2, null, this);

        final EditText message = (EditText) rootView.findViewById(R.id.task1);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        message.setText(prefs.getString(mDay, ""));
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setCursorVisible(true);
            }
        });


        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.edit().putString(mDay, s.toString()).apply();
            }
        });

        return rootView;
    }


    @Override
    public void onDestroyView() {
        mAdapter.clear();
        super.onDestroyView();
    }

    @NonNull
    @Override
    public Loader<List<SingleDay>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new JsonLoader(getContext(), mDay);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<SingleDay>> loader, List<SingleDay> singleDayList) {
        mAdapter.clear();
        if (singleDayList != null && !singleDayList.isEmpty()){
            mAdapter.addAll(singleDayList);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<SingleDay>> loader) {
        mAdapter.clear();
    }
}
