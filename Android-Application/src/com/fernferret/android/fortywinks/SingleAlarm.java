package com.fernferret.android.fortywinks;

import java.util.Set;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * An alarm that fires off only once.
 * 
 * @author Eric Stokes
 * 
 */
public class SingleAlarm extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		Log.i("40W", "Recieved: " + extras);
		
		Set<String> Categories = intent.getCategories();
		int AlarmID = intent.getIntExtra(context.getString(R.string.intent_alarm_id), 0);
		int AlarmMgrID = intent.getIntExtra(context.getString(R.string.intent_alarm_mgr_id), 0);
		int RepeatingAlarmID = intent.getIntExtra(context.getString(R.string.intent_alarm_followup_number), 0);
		String soundURI = intent.getStringExtra(context.getString(R.string.intent_alarm_sound));
		boolean lastAlarm = intent.getBooleanExtra(context.getString(R.string.intent_alarm_last), false);
		
		Log.d("40W", "40W: Alarm ID: " + AlarmID + ", Categories: " + Categories + ", Alarm URI: " + soundURI + ", Last Alarm:" + lastAlarm + ", Repeating ID: " + RepeatingAlarmID + ", AlarmManagerID: " + AlarmMgrID);
		Log.w("40W", "40W: Recieved wakup intent");
		Intent wakeup = new Intent(context, WakeUpAlert.class);
		wakeup.putExtra(context.getString(R.string.intent_alarm_sound), soundURI);
		wakeup.putExtra(context.getString(R.string.intent_alarm_id), AlarmID);
		wakeup.putExtra(context.getString(R.string.intent_alarm_last), lastAlarm);
		wakeup.putExtra(context.getString(R.string.intent_alarm_mgr_id), AlarmMgrID);
		wakeup.putExtra(context.getString(R.string.intent_alarm_followup_number), RepeatingAlarmID);
		wakeup.putExtra("alarm_message", "test");
		wakeup.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(wakeup);
		Log.w("40W", "40W: Song done!");
	}
	
}
