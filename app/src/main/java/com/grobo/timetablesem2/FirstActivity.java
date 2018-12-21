package com.grobo.timetablesem2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<String>{

    private String branchString;
    private static final String TIMETABLE_URL = "https://timetable-grobo.firebaseio.com/.json";
    private View progressContainer;
    private View rollContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        getSupportActionBar().hide();
        progressContainer = findViewById(R.id.progress_container);
        rollContainer = findViewById(R.id.roll_container);

        progressContainer.setVisibility(View.INVISIBLE);

    }

    public void showTimetable(View v) {
        EditText rollNumberInput = (EditText) findViewById(R.id.roll_number_input);
        String rollNumberString = rollNumberInput.getText().toString();

        StringBuffer alpha = new StringBuffer();
        for (int i = 0; i < rollNumberString.length(); i++) {
            if(Character.isAlphabetic(rollNumberString.charAt(i)))
                alpha.append(rollNumberString.charAt(i));
        }

        branchString = alpha.toString().toLowerCase().trim();

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putString("branchPreference", branchString).commit();
        rollContainer.setVisibility(View.INVISIBLE);
        progressContainer.setVisibility(View.VISIBLE);
        updateJsonData();

    }

    private void updateJsonData(){
        LoaderManager loaderManager = android.support.v4.app.LoaderManager.getInstance(this);
        loaderManager.initLoader(1, null, this);
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {

        return new TimetableLoader(this, TIMETABLE_URL);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String jsonResponse) {

        if (jsonResponse != null && !jsonResponse.isEmpty()){
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putString("jsonString", jsonResponse).apply();
        }

        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }

}
