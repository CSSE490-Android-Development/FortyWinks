package com.fernferret.android.fortywinks.test;

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
        a.setFollowups(2);
        a.setIntervalStart(3);
        a.setIntervalEnd(5);
        
        Parcel p = Parcel.obtain();
        a.writeToParcel(p, 0);
        p.setDataPosition(0);
        
        Alarm result = new Alarm(p);
        //assertEquals(result.getId(), a.getId());
        assertEquals(result.getHour(), a.getHour());
        assertEquals(result.getId(), a.getId());
        assertEquals(result.getId(), a.getId());
    }
    
    public String toString() {
        return super.toString();
    }
    }
    
}
