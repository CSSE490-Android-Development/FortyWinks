package com.fernferret.android.fortywinks;

import java.text.DecimalFormat;

import java.text.NumberFormat;
import java.util.Calendar;

import android.text.format.Time;



/**
 * Provides the means for creating potential alarms. These never get saved to the database, nor do they get created into alarms, unless they are selected. They are a very inexpensive way of showing the user potential alarm possibilities.
 * @author Eric Stokes
 *
 */
public class ProposedAlarm {
	
	public enum ProposedAlarmType {PowerNap, QuickAlarm};
	
	private int mHour;
	private int mMinute;
	private Time mTime;
	private int mIntervalNumber;
	private int mIntervalLength;
	private ProposedAlarmType mAlarmType;
	/**
	 * Creates a new proposed alarm that the user can turn into a quick alarm.
	 * @param hour Hour in 24 hour time format (0 - 23)
	 * @param minute Minute from 0 - 59
	 * @param intervalFromNow How many intervals is this from now
	 * @param intervalLength The length of one interval
	 */
	public ProposedAlarm(int hour, int minute, int intervalNumber, int intervalLength, ProposedAlarmType type) {
		mTime = new Time();
		mTime.setToNow();
		mTime.set(mTime.second, minute, hour, mTime.monthDay, mTime.month, mTime.year);
		mHour = hour;
		mMinute = minute;
		mIntervalNumber = intervalNumber;
		mIntervalLength = intervalLength;
		mAlarmType = type;
	}
	/**
	 * Returns the time in a very pretty manner: 12:56 PM or 1:28 AM
	 * @return A human readable time
	 */
	public String getPrettyTime() {
		String format = "%l:%M %p";
		return mTime.format(format).toUpperCase();
	}
	
	/**
	 * Returns a human readable representation of time the user will be asleep
	 * @return a human readable representation of time the user will be asleep
	 */
	public String getPrettyCyclesTillAlarm() {
		float timeInHours = ((float)mIntervalLength/(float)60.0) * mIntervalNumber;
		// Grammar Matters.
		if(timeInHours == 1) {
			return  "1 Hour";
		}
		if(timeInHours == Math.round(timeInHours)) {
			return (int)timeInHours + " Hours";
		}
		NumberFormat format = new DecimalFormat("0.0");
		return format.format(timeInHours) + " Hours";
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
	
	public ProposedAlarmType getProposedAlarmType() {
		return mAlarmType;
	}
	
	private void setTimeObject() {
		mTime.set(mTime.second, mMinute, mHour, mTime.monthDay, mTime.month, mTime.year);
	}
	/**
	 * Allows this Proposed alarm to be updated on the fly.  This is useful if users sit on pages too long and these values get out of sync.
	 * @param c The Calendar object that contains the new time.  This object's values will be overridden.
	 */
	public void updateCurrentTime(Calendar c) {
		mMinute = c.get(Calendar.MINUTE);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		setTimeObject();
	}
}
