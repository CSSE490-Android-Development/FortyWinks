package com.fernferret.android.fortywinks;

import java.text.DecimalFormat;

import java.text.NumberFormat;
import java.util.Calendar;

import android.text.format.DateFormat;



/**
 * Provides the means for creating potential alarms. These never get saved to the database, nor do they get created into alarms, unless they are selected. They are a very inexpensive way of showing the user potential alarm possibilities.
 * @author Eric Stokes
 *
 */
public class ProposedAlarm {
	
	public enum ProposedAlarmType {PowerNap, QuickAlarm};
	
	private Calendar mTime;
	private int mIntervalNumber;
	private int mIntervalLength;
	private ProposedAlarmType mAlarmType;
	/**
	 * Creates a new proposed alarm that the user can turn into a PowerNap alarm.
	 * @param hour Hour in 24 hour time format (0 - 23) that this alarm will go off
	 * @param minute Minute from 0 - 59 that this alarm will go off 
	 * @param intervalNumber How many intervals is this from now
	 * @param intervalLength The length of one interval
	 */
	public ProposedAlarm(int hour, int minute, int intervalNumber, int intervalLength, ProposedAlarmType type) {
		mTime = Calendar.getInstance();
		mTime.set(Calendar.HOUR, hour);
		mTime.set(Calendar.MINUTE, minute);
		mIntervalNumber = intervalNumber;
		mIntervalLength = intervalLength;
		mAlarmType = type;
	}
	/**
	 * Creates a new proposed alarm that is set from a given number of hours and mins
	 * @param hours Number of hours until this alarm goes off (in addition to the minutes)
	 * @param minutes Number of minutes until this alarm goes off (in addition to the hours)
	 * @param intervalNumber How many intervals is this from now
	 * @param intervalLength The length of one interval
	 */
	public ProposedAlarm(int hours, int minutes, int intervalNumber, int intervalLength) {
		mTime = Calendar.getInstance();
		mTime.add(Calendar.HOUR, hours);
		mTime.add(Calendar.MINUTE, minutes);
		
		mIntervalNumber = intervalNumber;
		mIntervalLength = intervalLength;
		mAlarmType = ProposedAlarmType.QuickAlarm;
	}
	/**
	 * Returns the time in a very pretty manner: 12:56 PM or 1:28 AM
	 * @return A human readable time
	 */
	public String getPrettyTime() {
		return DateFormat.format("h:mm aa", mTime).toString();
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
		return mTime.get(Calendar.HOUR);
	}
	
	public int getMinute() {
		return mTime.get(Calendar.MINUTE);
	}
	
	public void setHour(int hour) {
		mTime.set(Calendar.HOUR, hour);
	}
	
	public void setMinute(int minute) {
		mTime.set(Calendar.MINUTE, minute);
	}
	
	public ProposedAlarmType getProposedAlarmType() {
		return mAlarmType;
	}
	/**
	 * Allows this Proposed alarm to be updated on the fly.  This is useful if users sit on pages too long and these values get out of sync.
	 * @param c The Calendar object that contains the new time.  This object's values will be overridden.
	 */
	public void updateCurrentTime(Calendar c) {
		mTime = c;
	}
}
