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
		} else {
			alarmView = (SmallAlarmLineItem) convertView;
		}
		Alarm a = getItem(position);
		alarmView.setAlarmText(a.toString());
		
		if(a.isPowerNap()) {
			alarmView.setPowerNap();
			Log.d("40W", "Set as PowerNap");
		} else if(a.isQuikAlarm()) {
			alarmView.setQuikAlarm();
			Log.d("40W", "Set as QuikAlarm");
		} else {
			alarmView.setRegularAlarm();
			Log.d("40W", "Set as Regular Alarm");
		}
		
		return alarmView;
	}
}
