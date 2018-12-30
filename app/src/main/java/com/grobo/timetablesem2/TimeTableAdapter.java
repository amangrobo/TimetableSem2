package com.grobo.timetablesem2;

import android.app.Activity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class TimeTableAdapter extends ArrayAdapter<SingleDay> {

    String branchPre;

    public TimeTableAdapter(Activity context, int resource, List<SingleDay> objects) {
        super(context, resource, objects);
        branchPre = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("branchPre", "");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.single_day_item, parent, false);
        }

        TextView timeTextView = (TextView) convertView.findViewById(R.id.time_view);
        TextView subjectTextView = (TextView) convertView.findViewById(R.id.subject_view);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) subjectTextView.getLayoutParams();

        if (branchPre.compareTo("1701") == 0){
            params.weight = 2.0f;
            subjectTextView.setLayoutParams(params);
        }

        SingleDay singleTimetable = getItem(position);

        timeTextView.setText(singleTimetable.getTime());
        subjectTextView.setText(singleTimetable.getSubject());

        return convertView;
    }
}
