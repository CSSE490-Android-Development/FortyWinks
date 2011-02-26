package com.fernferret.android.fortywinks;

import java.util.Calendar;

import com.fernferret.android.fortywinks.ProposedAlarm.ProposedAlarmType;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;

public class AddQuikAlarm extends Activity {
	
	private TimePicker mTimePicker;
	private RadioGroup mTypePicker;
	private TextView mHelperText;
	
	private Button mSubmit;
	private Button mCancel;
	
	private ProposedAlarm mProposedAlarm;
	
	private SharedPreferences mSettings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_quik_alarm);
		
		mSettings = PreferenceManager.getDefaultSharedPreferences(this);
		
		mTypePicker = (RadioGroup) findViewById(R.id.new_quik_alarm_type_radio_group);
		mTimePicker = (TimePicker) findViewById(R.id.new_quik_alarm_time_picker);
		mHelperText = (TextView) findViewById(R.id.new_quik_alarm_help_text);
		
		mSubmit = (Button) findViewById(R.id.new_quik_alarm_add_alarm_button);
		mCancel = (Button) findViewById(R.id.new_quik_alarm_cancel_button);
		mSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent result = new Intent();
				int numberOfFollowups;
				int timeTillSleep;
				
				try {
					numberOfFollowups = Integer.parseInt(mSettings.getString(getString(R.string.key_followup_alarms), "4"));
				} catch (NumberFormatException e) {
					numberOfFollowups = 4;
				}
				
				try {
				    timeTillSleep = Integer.parseInt(mSettings.getString(getString(R.string.key_time_till_sleep), "14"));
				} catch (NumberFormatException e) {
				    timeTillSleep = 14;
				}
				
				int lengthBetweenFollowup;
				try {
					lengthBetweenFollowup = Integer.parseInt(mSettings.getString(getString(R.string.key_followup_interval), "5"));
				} catch (NumberFormatException e) {
					lengthBetweenFollowup = 5;
				}
				if(mTypePicker.getCheckedRadioButtonId() == R.id.new_quik_alarm_radio_time_until) {
					mProposedAlarm = new ProposedAlarm(mTimePicker.getCurrentHour(), mTimePicker.getCurrentMinute(), numberOfFollowups, lengthBetweenFollowup, timeTillSleep);
				} else {
					mProposedAlarm = new ProposedAlarm(mTimePicker.getCurrentHour(), mTimePicker.getCurrentMinute(), numberOfFollowups, lengthBetweenFollowup, timeTillSleep, ProposedAlarmType.QuickAlarm);
				}
				
				Alarm transport = new Alarm(mProposedAlarm);
				result.putExtra("ALARM", transport);
				setResult(Activity.RESULT_OK, result);
			}
		});
		mCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		setTimeClockFormat();
		mTypePicker.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				setTimeClockFormat();
			}
		});
		
	}
	
	private void setTimeClockFormat() {
		if(mTypePicker.getCheckedRadioButtonId() == R.id.new_quik_alarm_radio_time_until) {
			mTimePicker.setCurrentHour(0);
			mTimePicker.setCurrentMinute(26);
			mTimePicker.setIs24HourView(true);
			mHelperText.setText(R.string.help_time_until);
		} else {
			Calendar c = Calendar.getInstance();
			c.get(Calendar.HOUR);
			mTimePicker.setCurrentHour(c.get(Calendar.HOUR));
			mTimePicker.setCurrentMinute(c.get(Calendar.MINUTE));
			mTimePicker.setIs24HourView(false);
			mHelperText.setText(R.string.help_time_for);
		}
	}
	
}
