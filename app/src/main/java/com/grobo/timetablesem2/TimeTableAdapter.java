package com.grobo.timetablesem2;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TimeTableAdapter extends ArrayAdapter<SingleDay> {

    public TimeTableAdapter(Activity context, int resource, List<SingleDay> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.single_day_item, parent, false);
        }

        TextView timeTextView = (TextView) convertView.findViewById(R.id.time_view);
        TextView subjectTextView = (TextView) convertView.findViewById(R.id.subject_view);

        SingleDay singleTimetable = getItem(position);

        timeTextView.setText(singleTimetable.getTime());
        subjectTextView.setText(singleTimetable.getSubject());

        return convertView;
    }
}
