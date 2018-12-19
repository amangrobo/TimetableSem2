package com.grobo.timetablesem2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    private String branchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        getSupportActionBar().hide();

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

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putString("branchPreference", branchString).apply();
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
