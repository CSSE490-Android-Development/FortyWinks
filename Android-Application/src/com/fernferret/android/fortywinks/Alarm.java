package com.fernferret.android.fortywinks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.os.Parcel;
import android.os.Parcelable;

public class Alarm implements Parcelable {
    
    private int mId;
    private int mHour;
    private int mMinute;
    private int mThreshold;
    private int mFollowups;
    private int mIntervalStart;
    private int mIntervalEnd;
    private int mDaysOfWeek = 0;
    private boolean mEnabled;
    
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
    
    public Alarm() {
        this(-1);
    }
    
    public Alarm(int id) {
        mId = id;
    }
    
    public Alarm(Parcel in) {
        this(in.readInt());
        setHour(in.readInt());
        setMinute(in.readInt());
        setThreshold(in.readInt());
        setFollowups(in.readInt());
        setIntervalStart(in.readInt());
        setIntervalEnd(in.readInt());
        setDaysOfWeek(in.readInt());
        setEnabled(in.readInt() == 1);
    }
    
    public int getId() { return mId; }
    public void setId(int id) { mId = id; }
    
    public int getHour() { return mHour; }
    public void setHour(int hour){ mHour = hour; }
    
    public int getMinute() { return mMinute; }
    public void setMinute(int minute) { mMinute = minute; }
    
    public int getThreshold() { return mThreshold; }
    public void setThreshold(int threshold) { mThreshold = threshold; }
    
    public int getFollowups() { return mFollowups; }
    public void setFollowups(int followups) { mFollowups = followups; }
    
    public int getIntervalStart() { return mIntervalStart; }
    public void setIntervalStart(int intervalStart) { mIntervalStart = intervalStart; }
    
    public int getIntervalEnd() { return mIntervalEnd; }
    public void setIntervalEnd(int intervalEnd) { mIntervalEnd = intervalEnd; }
    
    public int getDaysOfWeek() { return mDaysOfWeek; }
    public void setDaysOfWeek(int daysOfWeek) { mDaysOfWeek = daysOfWeek; }
    
    public boolean getEnabled() { return mEnabled; }
    public void setEnabled(boolean enabled) { mEnabled = enabled; }
    
    public void enableDay(Day day) {
        setDaysOfWeek(getDaysOfWeek() | day.getValue());
    }
    
    public void disableDay(Day day) {
        setDaysOfWeek(getDaysOfWeek() & ~day.getValue());
    }
    
    public boolean isDayEnabled(Day day) {
        int mask = day.getValue();
        return (getDaysOfWeek() & mask) == mask;
    }
    
    public ArrayList<Day> getEnabledDays() {
    	ArrayList<Alarm.Day> days = new ArrayList<Alarm.Day>();
    	for(Day d : Day.values()) {
    		if(isDayEnabled(d)) {
    			days.add(d);
    		}
    	}
    	return days;
    }
    
    private boolean isCalendarDayEnabled(int day) {
        return isDayEnabled(Day.fromCalendarDay(day));
    }
    
    public void makeOneTimeAlarm() {
        setDaysOfWeek(0);
    }
    
    public boolean isOneTimeAlarm() {
        return getDaysOfWeek() == 0;
    }
    
    public long getNextAlarmTime() {
        GregorianCalendar now = new GregorianCalendar(); // now
        GregorianCalendar t = new GregorianCalendar(); // time to check against
        t.set(Calendar.HOUR_OF_DAY, mHour);
        t.set(Calendar.MINUTE, mMinute);
        
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
        dest.writeInt(getFollowups());
        dest.writeInt(getIntervalStart());
        dest.writeInt(getIntervalEnd());
        dest.writeInt(getDaysOfWeek());
        dest.writeInt(getEnabled() ? 1 : 0);
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
                getFollowups() == a.getFollowups() &&
                getIntervalStart() == a.getIntervalStart() &&
                getIntervalEnd() == a.getIntervalEnd() &&
                getDaysOfWeek() == a.getDaysOfWeek() &&
                getEnabled() == a.getEnabled();
    }
    
    public String toString() {
        return super.toString();
    }
}
