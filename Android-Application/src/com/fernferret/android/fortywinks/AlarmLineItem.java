package com.fernferret.android.fortywinks;

import java.util.Calendar;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * Custom view that allows listing ov all Alarms that are currently active (excluding QuickAlarms)
 * @author Eric Stokes
 *
 */
public class AlarmLineItem extends RelativeLayout {
	
	private CheckBox mEnabled;
	private TextView mTime;
	private TextView mDaysEnabled;
	private TextView mThreshold;
	
	private String mDisplayTime;
	private String mDisplayDaysEnabled;
	private boolean mIsProjectComplete;
	private String mDisplayThreshold;
	
	private int mID;
	
	public AlarmLineItem(Context context) {
		super(context);
		
		mEnabled = (CheckBox) findViewById(R.id.alarm_line_item_enabled);
		mTime = (TextView) findViewById(R.id.alarm_line_item_time);
		mDaysEnabled = (TextView) findViewById(R.id.alarm_line_item_days);
		mThreshold = (TextView) findViewById(R.id.alarm_line_item_threshold);
	}
	
	public void setAll(Alarm a) {
		mID = a.getId();
		mEnabled.setEnabled(a.isEnabled());
		long currentTime = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentTime);
		calendar.set(Calendar.MINUTE, a.getMinute());
		calendar.set(Calendar.HOUR_OF_DAY, a.getHour());
		mTime.setText(calendar.get(Calendar.HOUR) + ":" +  calendar.get(Calendar.MINUTE) + " " + calendar.get(Calendar.AM_PM));
		String daysString = "";
		for(Alarm.Day d : a.getEnabledDays()) {
			daysString += d.toString().substring(0, 2) + " ";
		}
		mDaysEnabled.setText(daysString);
		mThreshold.setText(a.getThreshold());
	}
	
}
