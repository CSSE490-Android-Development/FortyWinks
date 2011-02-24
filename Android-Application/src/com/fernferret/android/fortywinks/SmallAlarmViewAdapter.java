package com.fernferret.android.fortywinks;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class SmallAlarmViewAdapter extends ArrayAdapter<Alarm>{

	private Context mContext;
	
	public SmallAlarmViewAdapter(Context context, int resource, int textViewResourceId, List<Alarm> objects) {
		super(context, resource, textViewResourceId, objects);
		mContext = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SmallAlarmLineItem alarmView = null;
		if(convertView == null) {
			alarmView = new SmallAlarmLineItem(mContext);
			alarmView.hideRemainingAlarmsText();
			Log.v("40W", "Creating New View for SmallAlarmViewAdapter");
		} else {
			alarmView = (SmallAlarmLineItem) convertView;
			alarmView.hideRemainingAlarmsText();
			Log.v("40W", "Recycling Old View for SmallAlarmViewAdapter");
		}
		Alarm a = getItem(position);
		alarmView.setAlarmText(a.toString());
		if(a.getRemainingActiveAlarms() != 0) {
			alarmView.setRemainingAlarmsText("(" + a.getRemainingActiveAlarms() + ")");
		}
		if(a.isPowerNap()) {
			alarmView.setPowerNap();
		} else if(a.isQuikAlarm()) {
			alarmView.setQuikAlarm();
		} else {
			alarmView.setRegularAlarm();
		}
		
		return alarmView;
	}
}
