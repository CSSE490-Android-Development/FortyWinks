package com.fernferret.android.fortywinks.test;

import junit.framework.TestCase;

import com.fernferret.android.fortywinks.Alarm;

public class AlarmTests extends TestCase {

    public void testAlarmCanBeCreated() {
        Alarm a = new Alarm(1);
        assertNotNull("Alarm was NULL", a);
    }
}
