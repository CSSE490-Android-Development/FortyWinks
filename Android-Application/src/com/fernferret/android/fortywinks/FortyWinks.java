package com.fernferret.android.fortywinks;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FortyWinks extends Activity {
	
	// Load UI Resources
	// TextViews
	TextView mBigTime;
	TextView mTimeLeftToSleep;
	TextView mRemainingCycles;
	
	// Buttons
	Button mDoSleepButton;
	
	// ListViews
	ListView mQuickAlarmList;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
    }
}