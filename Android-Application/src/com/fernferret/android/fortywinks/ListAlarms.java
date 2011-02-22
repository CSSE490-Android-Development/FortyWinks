package com.fernferret.android.fortywinks;

import android.app.Activity;
import android.os.Bundle;
/**
 * Provides a listing of all alarms using the AlarmLineItem view.
 * @author Eric Stokes
 *
 */
public class ListAlarms extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_alarms);
	}
}
