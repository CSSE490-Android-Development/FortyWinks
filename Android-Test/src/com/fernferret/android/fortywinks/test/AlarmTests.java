package com.fernferret.android.fortywinks.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;
import android.os.Parcel;

import com.fernferret.android.fortywinks.Alarm;

public class AlarmTests extends TestCase {

    public void testAlarmCanBeCreated() {
        Alarm a = new Alarm(1);
        assertNotNull("Alarm was NULL", a);
    }
    
    public void testAlarmSetsInitialDaysOfWeek() {
        Alarm a = new Alarm(1);
        assertEquals(a.getDaysOfWeek(), 0);
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
    }
    
    public void testAlarmSetDay() {
        Alarm a = new Alarm(1);
        a.enableDay(Alarm.Day.SUNDAY);
        assertTrue(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.MONDAY);
        assertTrue(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.TUESDAY);
        assertTrue(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.WEDNESDAY);
        assertTrue(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.THURSDAY);
        assertTrue(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.FRIDAY);
        assertTrue(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.SATURDAY);
        assertTrue(a.isDayEnabled(Alarm.Day.SATURDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
    }
    
    public void testAlarmUnsetDay() {
        Alarm a = new Alarm(1);
        a.enableDay(Alarm.Day.SUNDAY);
        a.disableDay(Alarm.Day.SUNDAY);
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.MONDAY);
        a.disableDay(Alarm.Day.MONDAY);
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.TUESDAY);
        a.disableDay(Alarm.Day.TUESDAY);
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.WEDNESDAY);
        a.disableDay(Alarm.Day.WEDNESDAY);
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.THURSDAY);
        a.disableDay(Alarm.Day.THURSDAY);
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.FRIDAY);
        a.disableDay(Alarm.Day.FRIDAY);
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
        
        a = new Alarm(1);
        a.enableDay(Alarm.Day.SATURDAY);
        a.disableDay(Alarm.Day.SATURDAY);
        assertFalse(a.isDayEnabled(Alarm.Day.SUNDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.MONDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.TUESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.WEDNESDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.THURSDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.FRIDAY));
        assertFalse(a.isDayEnabled(Alarm.Day.SATURDAY));
    }
    
    public void testParclablePackagingWorks() {
        Alarm a = new Alarm(1);
        a.enableDay(Alarm.Day.WEDNESDAY);
        a.setHour(4);
        a.setMinute(20);
        a.setNumFollowups(2);
        a.setIntervalStart(3);
        a.setIntervalEnd(5);
        
        /* Fake list of ids from the database */
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        
        a.populateFollowups(ids);
        
        Parcel p = Parcel.obtain();
        a.writeToParcel(p, 0);
        p.setDataPosition(0);
        
        Alarm result = new Alarm(p);
        assertEquals(a.getId(), result.getId());
        assertEquals(a.getHour(), result.getHour());
        assertEquals(a.getMinute(), result.getMinute());
        assertEquals(a.getThreshold(), result.getThreshold());
        assertEquals(a.getIntervalStart(), result.getIntervalStart());
        assertEquals(a.getIntervalEnd(), result.getIntervalEnd());
        assertEquals(a.getDaysOfWeek(), result.getDaysOfWeek());
        assertEquals(a.getEnabled(), result.getEnabled());
        assertEquals(a.isPowerNap(), result.isPowerNap());
        assertEquals(a.isQuikAlarm(), result.isQuikAlarm());
        assertEquals(a, result);
        
        /* Make sure all of the followups actually got mapped right */
        HashMap<Integer, Long> originalFollowups = a.getFollowups();
        HashMap<Integer, Long> resultFollowups = result.getFollowups();
        for (int id : originalFollowups.keySet()) {
            assertEquals(originalFollowups.get(id), resultFollowups.get(id));
        }
    }
}
