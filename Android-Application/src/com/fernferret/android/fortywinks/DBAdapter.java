package com.fernferret.android.fortywinks;

import java.util.List;

public interface DBAdapter {

    public boolean alarmExists(int id);
    
    public void deleteAlarm(Alarm a);

    public void deleteAlarm(int id);

    public Alarm saveAlarm(Alarm a);

    public Alarm getAlarm(int id);

    public Alarm getPowerNap();

    public List<Alarm> getQuikAlarmsAndAlarms();

    public List<Alarm> getFullAlarmList();

    public List<Alarm> getFullAlarmList(int numItems);
    
    public void updateFullAlarmList(List<Alarm> alarms);
    
    public void setAlarmActive(Alarm a);
    
    public void setAlarmActive(int id);
    
    void resetDatabase();

}