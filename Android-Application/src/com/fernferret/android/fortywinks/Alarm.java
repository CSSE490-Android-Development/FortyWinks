package com.fernferret.android.fortywinks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.fernferret.android.fortywinks.ProposedAlarm.ProposedAlarmType;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;

/**
 * An implementation of a very versatile Alarm.  This alarm will support single runs, 
 * and indefinitely scheduled runs, in addition to thresholds, followups and advanced intervals.
 * @author Jimmy Theis
 */

public class Alarm implements Parcelable {
    
    private int mId;
    private int mHour;
    private int mMinute;
    private int mThreshold;
    private int mNumFollowups;
    private int mIntervalStart;
    private int mIntervalEnd;
    private int mDaysOfWeek = 0;
    private boolean mEnabled;
    private boolean mIsPowerNap;
    private boolean mIsQuikAlarm;
    private HashMap<Integer, Long> mFollowups;
    
    /**
     * The Day Enum allows very easy access from any class to the concept of a named day
     * @author Jimmy Theis
     */
    public enum Day {
        SUNDAY    (1, Calendar.SUNDAY),
        MONDAY    (2, Calendar.MONDAY),
        TUESDAY   (4, Calendar.TUESDAY),
        WEDNESDAY (8, Calendar.WEDNESDAY),
        THURSDAY (16, Calendar.THURSDAY),
        FRIDAY   (32, Calendar.FRIDAY),
        SATURDAY (64, Calendar.SATURDAY);
        
        private int mValue;
        private int mCalendarDay;
        
        /**
         * Constructs a new Day Enum
         * @param val A value given to the day to represent enabled or disabled.
         * @param day The day of the month that this day is on. This is done to avoid multiple day conflicts.
         */
        Day (int val, int day) { 
            mValue = val; 
            mCalendarDay = day;
        }
        
        static Day fromCalendarDay(int day) {
            switch (day) {
                case Calendar.SUNDAY:
                    return SUNDAY;
                case Calendar.MONDAY:
                    return MONDAY;
                case Calendar.TUESDAY:
                    return TUESDAY;
                case Calendar.WEDNESDAY:
                    return WEDNESDAY;
                case Calendar.THURSDAY:
                    return THURSDAY;
                case Calendar.FRIDAY:
                    return FRIDAY;
                default:
                    return SATURDAY;     
            }
        }
        
        int getValue() { return mValue; }
        int getDay() { return mCalendarDay; }
    }
    
    /**
     * Constructs a new (unsaved) Alarm
     */
    public Alarm() {
        this(-1);
    }
    
    /**
     * Constructs an alarm with the given id
     * @param id The id to construct the point with.
     */
    public Alarm(int id) {
        mId = id;
    }
    
    /**
     * Creates an alarm from a Proposed alarm.  This will be a one time power nap with no repeating.
     * @param a The Proposed alarm to scrape and create a real alarm from.
     */
    public Alarm(ProposedAlarm a) {
    	this();
    	setHour(a.getHour());
    	setMinute(a.getMinute());
    	setIntervalStart(a.getIntervalLength());
    	setIntervalEnd(a.getIntervalLength());
    	setNumFollowups(a.getNumberOfIntervals());
    	if(a.getProposedAlarmType() == ProposedAlarmType.PowerNap) {
    		setIsPowerNap(true);
    	} else if(a.getProposedAlarmType() == ProposedAlarmType.QuickAlarm) {
    		setIsQuikAlarm(true);
    	}
    }
    
    /**
     * Constructs a new Alarm with a Parcel. This is required due to alarms being Parcelable so they can be transported.
     * @param in The Parcel that is to be unpacked to an alarm.
     */
    public Alarm(Parcel in) {
        this(in.readInt());
        setHour(in.readInt());
        setMinute(in.readInt());
        setThreshold(in.readInt());
        setNumFollowups(in.readInt());
        setIntervalStart(in.readInt());
        setIntervalEnd(in.readInt());
        setDaysOfWeek(in.readInt());
        setEnabled(in.readInt() == 1);
        setIsPowerNap(in.readInt() == 1);
        setIsQuikAlarm(in.readInt() == 1);
        
        /* The rest of the data in the parcel must be our followups map */
        if (in.dataAvail() > 0) {
            HashMap<Integer, Long> followups = new HashMap<Integer, Long>();
            
            while (in.dataAvail() > 0) {
                followups.put(in.readInt(), in.readLong());
            }
            
            setFollowups(followups);
        }
    }
    
    public int getId() { return mId; }
    public void setId(int id) { mId = id; }
    
    public int getHour() { return mHour; }
    public void setHour(int hour){ mHour = hour; }
    
    public int getMinute() { return mMinute; }
    public void setMinute(int minute) { mMinute = minute; }
    
    public int getThreshold() { return mThreshold; }
    public void setThreshold(int threshold) { mThreshold = threshold; }
    
    public int getNumFollowups() { return mNumFollowups; }
    public void setNumFollowups(int followups) { mNumFollowups = followups; }
    
    public int getIntervalStart() { return mIntervalStart; }
    public void setIntervalStart(int intervalStart) { mIntervalStart = intervalStart; }
    
    public int getIntervalEnd() { return mIntervalEnd; }
    public void setIntervalEnd(int intervalEnd) { mIntervalEnd = intervalEnd; }
    
    public int getDaysOfWeek() { return mDaysOfWeek; }
    public void setDaysOfWeek(int daysOfWeek) { mDaysOfWeek = daysOfWeek; }
    
    public boolean getEnabled() { return mEnabled; }
    public void setEnabled(boolean enabled) { mEnabled = enabled; }
    
    public boolean isPowerNap() { return mIsPowerNap; }
    public void setIsPowerNap(boolean isPowerNap) { mIsPowerNap = isPowerNap; }
    
    public boolean isQuikAlarm() { return mIsQuikAlarm; }
    public void setIsQuikAlarm(boolean isQuikAlarm) { mIsQuikAlarm = isQuikAlarm; }
    
    /**
     * Get a clone of the current followups map
     * @return
     */
    @SuppressWarnings("unchecked")
    public HashMap<Integer, Long> getFollowups() { return mFollowups == null ? new HashMap<Integer, Long>() : (HashMap<Integer, Long>) mFollowups.clone(); }
    
    /**
     * Set the current followups map. This method handles the cloning.
     * @param followups
     */
    @SuppressWarnings("unchecked")
    public void setFollowups(HashMap<Integer, Long> followups) { mFollowups = (HashMap<Integer, Long>) followups.clone(); }
    
    
    /**
     * Enables the given day for this alarm.
     * @param day The day to enable the alarm for
     */
    public void enableDay(Day day) {
        setDaysOfWeek(getDaysOfWeek() | day.getValue());
    }
    
    /**
     * Disables the given day for this alarm.
     * @param day The day to disable the alarm for
     */    
    public void disableDay(Day day) {
        setDaysOfWeek(getDaysOfWeek() & ~day.getValue());
    }
    
    /**
     * Returns true if the alarm will ring on the given day
     * @param day The day to check alarm status for
     * @return True if the alarm will ring on this day, false if not.
     */
    public boolean isDayEnabled(Day day) {
        int mask = day.getValue();
        return (getDaysOfWeek() & mask) == mask;
    }
    
    /**
     * Gets an ArrayList of Days that are enabled. The max will obviously be 7 for the size of this list.
     * @return ArrayList of Days that are enabled.
     */
    public List<Day> getEnabledDays() {
    	ArrayList<Alarm.Day> days = new ArrayList<Alarm.Day>();
    	for(Day d : Day.values()) {
    		if(isDayEnabled(d)) {
    			days.add(d);
    		}
    	}
    	return days;
    }
    
    /**
     * Checks to see if the date given is enabled for the current alarm. This is required to determine future alarm status and scheduling.
     * @param day The day
     * @return True if this particular calendar day will activate this alarm, false if not
     */
    private boolean isCalendarDayEnabled(int day) {
        return isDayEnabled(Day.fromCalendarDay(day));
    }
    
    /**
     * Populate the actual times for this alarm's followups
     * @param ids A list of unique IDs from the database to assign to followups, whose length matches numFollowups
     */
    public void populateFollowups(int[] ids) {
        HashMap<Integer, Long> followups = getFollowups();
        Random r = new Random();
        
        Calendar nextAlarmTime = new GregorianCalendar();
        nextAlarmTime.setTimeInMillis(getNextAlarmTime());
        
        int offsetRange = getIntervalEnd() - getIntervalStart();
        
        for (int id : ids) {
            nextAlarmTime.add(Calendar.MINUTE, offsetRange <= 0 ? getIntervalStart() : r.nextInt(offsetRange) + getIntervalStart());
            followups.put(id, nextAlarmTime.getTimeInMillis());
        }
        
        setFollowups(followups);
    }
    
    /**
     * Removes all repeating values of this alarm.  When the alarm cannot repeat, it will go off on the next time it sees its given time.
     */
    public void makeOneTimeAlarm() {
        setDaysOfWeek(0);
    }
    
    /**
     * Returns true if this alarm will only ring once.
     * @return True if this alarm will only ring once, false if it has future activations scheduled.
     */
    public boolean isOneTimeAlarm() {
        return getDaysOfWeek() == 0;
    }
    
    /**
     * Returns the next alarm time for this alarm in Milliseconds. This method is what reminds me that Jimmy is truly a genius. Seriously.
     * @return The next  activation time for this alarm in Milliseconds
     */
    public long getNextAlarmTime() {
        GregorianCalendar now = new GregorianCalendar(); // now
        now.set(Calendar.SECOND, 0); // ...except truncate to whole minutes
        
        GregorianCalendar t = new GregorianCalendar(); // time to check against
        t.set(Calendar.HOUR_OF_DAY, mHour);
        t.set(Calendar.MINUTE, mMinute);
        t.set(Calendar.SECOND, 0); // truncate here too
        
        /* First we look at one-time instances */
        if (isOneTimeAlarm()) {
            
            if (now.before(t)) {
                
                /* We haven't reached that time today */
                return t.getTimeInMillis();
                
            } else {
                
                /* Alarm will go off tomorrow at said time */
                t.add(Calendar.DAY_OF_YEAR, 1);
                return t.getTimeInMillis();
            }
            
        } else {
            
            /* Are we past the time for today? */
            if (now.after(t)) {
                
                /* If so, increment our proposed day by one */
                t.add(Calendar.DAY_OF_YEAR, 1);
            }

            /* Now walk through the days until we find one that's enabled */
            while (!isCalendarDayEnabled(t.get(Calendar.DAY_OF_WEEK))) {
                t.add(Calendar.DAY_OF_YEAR, 1);
            }

            /* And return it */
            return t.getTimeInMillis();
        }
    }

    @Override
    public int describeContents() { return 0; }
    
    public static final Parcelable.Creator<Alarm> CREATOR = new Parcelable.Creator<Alarm>() {
        
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
        
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeInt(getHour());
        dest.writeInt(getMinute());
        dest.writeInt(getThreshold());
        dest.writeInt(getNumFollowups());
        dest.writeInt(getIntervalStart());
        dest.writeInt(getIntervalEnd());
        dest.writeInt(getDaysOfWeek());
        dest.writeInt(getEnabled() ? 1 : 0);
        dest.writeInt(isPowerNap() ? 1 : 0);
        dest.writeInt(isQuikAlarm() ? 1 : 0);
        
        /* Write the contents of our followups map to the rest of parcel */
        HashMap<Integer, Long> followups = getFollowups();
        if (followups != null) {
            for (int id : followups.keySet()) {
                dest.writeInt(id);
                dest.writeLong(followups.get(id));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Alarm)) {
            return super.equals(o);
        }
        Alarm a = (Alarm) o;
        return getId() == a.getId() &&
                getHour() == a.getHour() &&
                getMinute() == a.getMinute() &&
                getNumFollowups() == a.getNumFollowups() &&
                getIntervalStart() == a.getIntervalStart() &&
                getIntervalEnd() == a.getIntervalEnd() &&
                getDaysOfWeek() == a.getDaysOfWeek() &&
                getEnabled() == a.getEnabled() &&
                isQuikAlarm() == a.isQuikAlarm() &&
                isPowerNap() == a.isPowerNap();
    }
    
    public String toString() {
    	Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, mHour);
		c.set(Calendar.MINUTE, mMinute);
		return DateFormat.format("h:mm aa", c).toString();
    }
}
