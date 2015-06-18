package com.example.joseph.gpa_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by Joseph on 6/18/2015.
 */
public class SettingsActivity extends PreferenceActivity{

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);


    }
}
