package com.fernferret.android.fortywinks;

import android.text.format.Time;

public class ProposedAlarm {
	
	private int mHour;
	private int mMinute;
	private Time mTime;
	private int mIntervalNumber;
	private int mIntervalLength;
	/**
	 * Creates a new proposed alarm that the user can turn into a quick alarm.
	 * @param hour Hour in 24 hour time format (0 - 23)
	 * @param minute Minute from 0 - 59
	 * @param intervalFromNow How many intervals is this from now
	 * @param intervalLength The length of one interval
	 */
	public ProposedAlarm(int hour, int minute, int intervalNumber, int intervalLength) {
		mTime = new Time();
		mTime.setToNow();
		mTime.set(mTime.second, minute, hour, mTime.monthDay, mTime.month, mTime.year);
		mHour = hour;
		mMinute = minute;
		mIntervalNumber = intervalNumber;
		mIntervalLength = intervalLength;
	}
	
	public String getPrettyTime() {
		String format = "%l:%M %p";
		return mTime.format(format).toUpperCase();
	}
	
	public String getPrettyCyclesTillAlarm() {
		float timeInHours = ((float)mIntervalLength/(float)60.0) * mIntervalNumber;
		// Grammar Matters.
		if(timeInHours == 1) {
			return  "1 Hour";
		}
		if(timeInHours == Math.round(timeInHours)) {
			return (int)timeInHours + " Hours";
		}
		return timeInHours + " Hours";
	}
	
	public int getHour() {
		return mHour;
	}
	
	public int getMinute() {
		return mMinute;
	}
	
	public void setHour(int hour) {
		mHour = hour;
		setTimeObject();
	}
	
	public void setMinute(int minute) {
		mMinute = minute;
		setTimeObject();
	}
	
	private void setTimeObject() {
		mTime.set(mTime.second, mMinute, mHour, mTime.monthDay, mTime.month, mTime.year);
	}
}
