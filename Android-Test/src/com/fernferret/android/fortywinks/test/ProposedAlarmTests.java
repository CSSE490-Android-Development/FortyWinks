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
}
