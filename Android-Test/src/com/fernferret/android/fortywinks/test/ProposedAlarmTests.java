package com.fernferret.android.fortywinks.test;

import com.fernferret.android.fortywinks.ProposedAlarm;

import junit.framework.TestCase;

public class ProposedAlarmTests extends TestCase {
	public void testProposedAlarmCanBeCreated() {
		ProposedAlarm pa = new ProposedAlarm(0, 0, 1, 90);
		assertNotNull(pa);
	}
	
	public void testTimePrintsCorrectly() {
		ProposedAlarm pa = new ProposedAlarm(1, 11, 1, 90);
		assertEquals(" 1:11 AM", pa.getPrettyTime());
		
		pa = new ProposedAlarm(13, 11, 1, 90);
		assertEquals(" 1:11 PM", pa.getPrettyTime());
		
		pa = new ProposedAlarm(0, 11, 1, 90);
		assertEquals("12:11 AM", pa.getPrettyTime());
		
		pa = new ProposedAlarm(12, 11, 1, 90);
		assertEquals("12:11 PM", pa.getPrettyTime());
	}
	
	public void testTimeTillAlarm() {
		ProposedAlarm pa = new ProposedAlarm(1, 11, 1, 90);
		assertEquals("1.5 Hours", pa.getPrettyCyclesTillAlarm());
		
		pa = new ProposedAlarm(13, 11, 1, 120);
		assertEquals("2 Hours", pa.getPrettyCyclesTillAlarm());
		
		pa = new ProposedAlarm(0, 11, 1, 60);
		assertEquals("1 Hour", pa.getPrettyCyclesTillAlarm());
		
		pa = new ProposedAlarm(12, 11, 1, 0);
		assertEquals("0 Hours", pa.getPrettyCyclesTillAlarm());
	}
}
