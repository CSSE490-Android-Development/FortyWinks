package com.fernferret.android.fortywinks;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;

public class AddQuikAlarm extends Activity {
	
	private TimePicker mTimePicker;
	private RadioGroup mTypePicker;
	private TextView mHelperText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_quik_alarm);
		
		mTypePicker = (RadioGroup) findViewById(R.id.new_quik_alarm_type_radio_group);
		mTimePicker = (TimePicker) findViewById(R.id.new_quik_alarm_time_picker);
		mHelperText = (TextView) findViewById(R.id.new_quik_alarm_help_text);
		
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
			mTimePicker.setIs24HourView(true);
			mHelperText.setText(R.string.help_time_for);
		} else {
			mTimePicker.setIs24HourView(false);
			mHelperText.setText(R.string.help_time_until);
		}
	}
	
}
