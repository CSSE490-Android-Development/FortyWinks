package com.fernferret.android.fortywinks;

import android.os.Parcel;
import android.os.Parcelable;

public class Alarm implements Parcelable {
    
    private int mId;
    private int mFollowups;
    private int mIntervalStart;
    private int mIntervalEnd;
    private int mDaysOfWeek = 0;
    private boolean mEnabled;
    
    public enum Day {
        SUNDAY    (1),
        MONDAY    (2),
        TUESDAY   (4),
        WEDNESDAY (8),
        THURSDAY (16),
        FRIDAY   (32),
        SATURDAY (64);
        
        private int mValue;
        
        Day (int val) { mValue = val; }
        int getValue() { return mValue; }
    }
    
    public Alarm(int id) {
        mId = id;
    }
    
    public int getId() { return mId; }
    
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

    @Override
    public int describeContents() { return 0; }
    
    public static final Parcelable.Creator<Alarm> CREATOR = new Parcelable.Creator<Alarm>() {
        
        public Alarm createFromParcel(Parcel in) {
            Alarm result = new Alarm(in.readInt());
            result.setFollowups(in.readInt());
            result.setIntervalStart(in.readInt());
            result.setIntervalEnd(in.readInt());
            result.setDaysOfWeek(in.readInt());
            result.setEnabled(in.readInt() == 1);
            return result;
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
        
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
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
                getFollowups() == a.getFollowups() &&
                getIntervalStart() == a.getIntervalStart() &&
                getIntervalEnd() == a.getIntervalEnd() &&
                getDaysOfWeek() == a.getDaysOfWeek() &&
                getEnabled() == a.getEnabled();
    }
}
