package com.fernferret.android.fortywinks;

import java.util.Set;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * An alarm that fires off only once.
 * 
 * @author Eric Stokes
 * 
 */
public class SingleAlarm extends BroadcastReceiver {
	
	private Context mContext;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Set<String> Categories = intent.getCategories();
		int AlarmID = intent.getIntExtra("ALARM_ID", 0);
		Log.d("40W", "40W: Alarm ID: " + AlarmID + ", Categories: " + Categories);
		Log.w("40W", "40W: Recieved wakup intent");
		Intent wakeup = new Intent(context, WakeUpAlert.class);
		wakeup.putExtra("alarm_message", "test");
		wakeup.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(wakeup);
		Log.w("40W", "40W: Song done!");
	}
	
}
