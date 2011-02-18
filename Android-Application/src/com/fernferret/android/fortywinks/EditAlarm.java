package com.fernferret.android.fortywinks;

import android.app.Activity;
import android.os.Bundle;

public class EditAlarm extends Activity {
    
    PreferenceView mThreshold;
    PreferenceView mFollowups;
    PreferenceView mInterval;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_alarm);
        
        mThreshold = (PreferenceView)findViewById(R.id.threshold);
        mFollowups = (PreferenceView)findViewById(R.id.followups);
        mInterval = (PreferenceView)findViewById(R.id.interval);
        
        mThreshold.setLeftText("Threshold");
        mFollowups.setLeftText("Followups");
        mInterval.setLeftText("Interval");
    }
}
