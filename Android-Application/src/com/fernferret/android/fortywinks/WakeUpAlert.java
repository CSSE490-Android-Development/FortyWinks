package com.fernferret.android.fortywinks;

import android.app.Activity;
import android.os.Bundle;
/**
 * Activity that will allow the user to dismiss alarms.
 * @author ericstokes
 *
 */
public class WakeUpAlert extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wake_up_alert);
	}
}
