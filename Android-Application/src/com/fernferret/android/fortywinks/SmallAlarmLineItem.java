package com.fernferret.android.fortywinks;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SmallAlarmLineItem extends RelativeLayout {
	
	private Activity mContext;
	
	private ImageView mSpecialIcon;
	private TextView mAlarmTime;
	private TextView mRemainingAlarms;
	
	public SmallAlarmLineItem(Context context) {
		super(context);
		mContext = (Activity) context;		
		LayoutInflater layoutInflater = mContext.getLayoutInflater();
		layoutInflater.inflate(R.layout.small_alarm_line_item, this);
		mAlarmTime = (TextView) findViewById(R.id.small_alarm_line_item_time);
		mRemainingAlarms = (TextView) findViewById(R.id.small_alarm_line_item_remaining);
		mSpecialIcon = (ImageView) findViewById(R.id.small_alarm_line_item_icon);
	}
	
	public void setAlarmText(String time) {
		mAlarmTime.setText(time);
	}
	
	public void setQuikAlarm() {
		mSpecialIcon.setImageDrawable(getResources().getDrawable(R.drawable.quick));
		mSpecialIcon.setVisibility(View.VISIBLE);
	}
	
	public void setPowerNap() {
		mSpecialIcon.setImageDrawable(getResources().getDrawable(R.drawable.zs));
		mSpecialIcon.setVisibility(View.VISIBLE);
	}
	
	public void setRegularAlarm() {
		mSpecialIcon.setVisibility(View.INVISIBLE);
	}

	public void setRemainingAlarmsText(String string) {
		mRemainingAlarms.setText(string);
		mRemainingAlarms.setVisibility(View.VISIBLE);
	}
	
}
