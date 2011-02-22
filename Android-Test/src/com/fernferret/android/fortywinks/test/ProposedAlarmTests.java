package com.fernferret.android.fortywinks.test;

import java.util.Calendar;

import junit.framework.TestCase;

import android.text.format.DateFormat;

import com.fernferret.android.fortywinks.ProposedAlarm;

public class ProposedAlarmTests extends TestCase {
	public void testProposedAlarmCanBeCreated() {
		ProposedAlarm pa = new ProposedAlarm(0, 0, 1, 90);
		assertNotNull(pa);
	}
	
	public void testTimePrintsCorrectly() {
		ProposedAlarm pa = new ProposedAlarm(1, 11, 1, 90, ProposedAlarm.ProposedAlarmType.QuickAlarm);
		assertEquals("1:11 am", pa.getPrettyTime());
		
		pa = new ProposedAlarm(13, 11, 1, 90, ProposedAlarm.ProposedAlarmType.QuickAlarm);
		assertEquals("1:11 pm", pa.getPrettyTime());
		
		pa = new ProposedAlarm(0, 11, 1, 90, ProposedAlarm.ProposedAlarmType.QuickAlarm);
		assertEquals("12:11 am", pa.getPrettyTime());
		
		pa = new ProposedAlarm(12, 11, 1, 90, ProposedAlarm.ProposedAlarmType.QuickAlarm);
		assertEquals("12:11 pm", pa.getPrettyTime());
	}
	
	public void testQuickAlarmTimes() {
		Calendar time = Calendar.getInstance();
		ProposedAlarm pa = new ProposedAlarm(1, 0, 4, 5);
		time.add(Calendar.HOUR, 1);
		assertEquals(DateFormat.format("h:mm aa", time).toString(), pa.getPrettyTime());
		
		time = Calendar.getInstance();
		time.add(Calendar.MINUTE, 10);
		pa = new ProposedAlarm(0, 10, 4, 5);
		
		assertEquals(DateFormat.format("h:mm aa", time).toString(), pa.getPrettyTime());
		
		time = Calendar.getInstance();
		
		pa = new ProposedAlarm(1, 15, 4, 5);
		time.add(Calendar.HOUR, 1);
		time.add(Calendar.MINUTE, 15);
		
		assertEquals(DateFormat.format("h:mm aa", time).toString(), pa.getPrettyTime());
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
	
	public void testAddingTimesWorks() {
		ProposedAlarm pa = new ProposedAlarm(1, 11, 1, 90);
		assertEquals("1.5 Hours", pa.getPrettyCyclesTillAlarm());
		pa.setTimeTill(1, 15);
		Calendar time = Calendar.getInstance();
		time.add(Calendar.HOUR, 1);
		time.add(Calendar.MINUTE, 15);
		assertEquals(DateFormat.format("h:mm aa", time).toString(), pa.getPrettyTime());
	}
}
