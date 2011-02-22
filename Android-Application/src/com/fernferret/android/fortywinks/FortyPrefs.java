package com.fernferret.android.fortywinks;

import android.os.Bundle;
import android.preference.PreferenceActivity;
/**
 * PreferencesActivity that loads and inflates the XML resource for app prefs.
 * @author Eric Stokes
 *
 */
public class FortyPrefs extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.options);
	}
}
