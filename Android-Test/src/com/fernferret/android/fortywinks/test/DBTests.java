package com.fernferret.android.fortywinks.test;

import java.util.List;

import android.test.ActivityInstrumentationTestCase2;

import com.fernferret.android.fortywinks.Alarm;
import com.fernferret.android.fortywinks.DBAdapter;
import com.fernferret.android.fortywinks.FortyWinks;

public class DBTests extends ActivityInstrumentationTestCase2<FortyWinks> {
    
    private DBAdapter mDba;

    public DBTests() {
        super("com.fernferret.android.fortywinks", FortyWinks.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mDba = new DBAdapter(this.getActivity());
        mDba.smokeDatabase();
    }
    
    public void testPreconditions() {
        assertNotNull(mDba);
    }
    
    private void testBasicEquality(Alarm a, Alarm b) {
        assertEquals(a.getDaysOfWeek(), b.getDaysOfWeek());
        assertEquals(a.getEnabled(), b.getEnabled());
        assertEquals(a.getHour(), b.getHour());
        assertEquals(a.getMinute(), b.getMinute());
        assertEquals(a.getNumFollowups(), b.getNumFollowups());
        assertEquals(a.getIntervalStart(), b.getIntervalStart());
        assertEquals(a.getIntervalEnd(), b.getIntervalEnd());
    }
    
    public void testSaveSimpleAlarm() {
        Alarm a = new Alarm(-1);
        
        a.setDaysOfWeek(1);
        a.setEnabled(false);
        a.setHour(4);
        a.setMinute(6);
        a.setNumFollowups(7);
        a.setIntervalStart(3);
        a.setIntervalEnd(3);
        
        mDba.saveAlarm(a);
        assertFalse(a.getId() == -1);
        Alarm result = mDba.getAlarm(a.getId());
        testBasicEquality(a, result);
    }
    
    public void testSavePowerNap() {
        Alarm a = new Alarm(-1);
        
        a.setDaysOfWeek(1);
        a.setEnabled(false);
        a.setHour(4);
        a.setMinute(6);
        a.setIsPowerNap(true);
        
        mDba.saveAlarm(a);
        assertFalse(a.getId() == -1);
        
        Alarm result = mDba.getPowerNap();
        assertNotNull(result);
        testBasicEquality(a, result);
    }
    
    public void testOverridePowerNap() {
        Alarm a = new Alarm(-1);
        
        a.setDaysOfWeek(1);
        a.setEnabled(false);
        a.setHour(4);
        a.setMinute(6);
        a.setIsPowerNap(true);
        
        Alarm b = new Alarm(-1);
        
        b.setDaysOfWeek(2);
        b.setEnabled(false);
        b.setHour(5);
        b.setMinute(7);
        b.setIsPowerNap(true);
        
        mDba.saveAlarm(a);
        assertFalse(a.getId() == -1);
        
        mDba.saveAlarm(b);
        assertFalse(b.getId() == -1);
        
        assertFalse(mDba.alarmExists(a.getId()));
        
        Alarm result = mDba.getPowerNap();
        assertNotNull(result);
        testBasicEquality(b, result);
    }
    
    public void testSetQuikAlarms() {
        Alarm a = new Alarm(-1);
        
        a.setDaysOfWeek(1);
        a.setEnabled(false);
        a.setHour(4);
        a.setMinute(6);
        a.setIsQuikAlarm(true);
        
        Alarm b = new Alarm(-1);
        
        b.setDaysOfWeek(2);
        b.setEnabled(false);
        b.setHour(4);
        b.setMinute(7);
        b.setIsQuikAlarm(true);
        
        Alarm c = new Alarm(-1);
        
        b.setDaysOfWeek(2);
        b.setEnabled(false);
        b.setHour(4);
        b.setMinute(5);
        b.setIsQuikAlarm(true);
        
        mDba.saveAlarm(a);
        assertFalse(a.getId() == -1);
        
        mDba.saveAlarm(b);
        assertFalse(b.getId() == -1);
        
        List<Alarm> quikAlarms = mDba.getQuickAlarmsAndAlarms();
        //testBasicEquality(quikAlarms.get(0), c);
        assertEquals(quikAlarms.get(0).getId(), c.getId());
        //testBasicEquality(quikAlarms.get(1), a);
        assertEquals(quikAlarms.get(1).getId(), a.getId());
        //testBasicEquality(quikAlarms.get(2), b);
        assertEquals(quikAlarms.get(2).getId(), b.getId());
        
    }
}