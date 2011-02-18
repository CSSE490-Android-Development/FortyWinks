package com.fernferret.android.fortywinks;

import android.os.Parcel;
import android.os.Parcelable;

public class Alarm implements Parcelable {
    
    private int mId;
    private int mFollowups;
    private int mIntervalStart;
    private int mIntervalEnd;
    private int mDaysOfWeek;
    private boolean mEnabled;
    
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

}
