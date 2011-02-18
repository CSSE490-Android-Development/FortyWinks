package com.fernferret.android.fortywinks;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class FortyPrefs extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.options);
	}
}
