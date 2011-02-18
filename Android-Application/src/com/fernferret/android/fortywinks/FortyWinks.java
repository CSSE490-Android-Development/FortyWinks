package com.fernferret.android.fortywinks;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;

public class FortyWinks extends Activity {
	
	// General Class Variables
	Resources mResources;
	
	// TextViews
	private TextView mBigTime;
	private TextView mTimeLeftToSleep;
	private TextView mRemainingCycles;
	
	// Buttons
	private Button mDoSleepButton;
	
	// ListViews
	private ListView mQuickAlarmList;
	
	// Drawer
	private SlidingDrawer mDrawer;
	
	private PreferenceViewAdapter mQuickAlarmAdapter;
	
	private ArrayList<ProposedAlarm> mQuickProposedAlarms;
	
	
	// Important values used in generating the drawer items
	int mNumberOfAlarms;
	int mCycleTime;
	SharedPreferences mSettings;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Load Resources
		mResources = getResources();
		mSettings = PreferenceManager.getDefaultSharedPreferences(this);
		
		// Load UI Elements
		mBigTime = (TextView) findViewById(R.id.main_big_time);
		mTimeLeftToSleep = (TextView) findViewById(R.id.main_time_left_to_sleep);
		mRemainingCycles = (TextView) findViewById(R.id.main_remaining_cycles);
		
		mDoSleepButton = (Button) findViewById(R.id.main_do_sleep_button);
		
		mQuickAlarmList = (ListView) findViewById(R.id.main_quick_alarms_list);
		
		mDrawer = (SlidingDrawer) findViewById(R.id.main_drawer);
		
		try {
			mNumberOfAlarms = Integer.parseInt(mSettings.getString(getString(R.string.key_number_quick_alarms), "6"));
		} catch (NumberFormatException e) {
			mNumberOfAlarms = 6;
		}
		
		try {
			mCycleTime = Integer.parseInt(mSettings.getString(getString(R.string.key_cycle_time), "90"));
		} catch (NumberFormatException e) {
			// Someone entered NaN for their default value of REM cycle, let's use 90, the industry standard!
			mCycleTime = 90;
		}
		generateAlarms();
		mQuickAlarmAdapter = new PreferenceViewAdapter(this, R.layout.preference_view, R.id.preference_view_left_text, mQuickProposedAlarms);
		
		mQuickAlarmList.setAdapter(mQuickAlarmAdapter);
		// Set Listeners
		mDrawer.setOnDrawerOpenListener(mDrawerOpenListener);
		mDrawer.setOnDrawerCloseListener(mDrawerCloseListener);
		mDrawer.setOnClickListener(mOnDrawerClickListener);
		mDoSleepButton.setOnClickListener(mOnButtonClickListener);
		mQuickAlarmList.setOnItemClickListener(mListViewListener);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		
	}
	
	private void generateAlarms() {
		ArrayList<ProposedAlarm> listItems = new ArrayList<ProposedAlarm>();
		long currentTime = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentTime);
		
		for (int i = 1; i <= mNumberOfAlarms; i++) {
			calendar.add(Calendar.MINUTE, mCycleTime);
			listItems.add(new ProposedAlarm(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), i, mCycleTime));
		}
		mQuickProposedAlarms = listItems;
	}
	private void refreshQuickAlarms() {
		Log.w("40W", "40W - Refreshing Alarms");
		long currentTime = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentTime);
		for(ProposedAlarm a : mQuickProposedAlarms) {
			calendar.add(Calendar.MINUTE, mCycleTime);
			a.updateCurrentTime(calendar);
		}
		mQuickAlarmAdapter.notifyDataSetChanged();
	}
	
	private View.OnClickListener mOnButtonClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			
		}
	};
	
	private View.OnClickListener mOnDrawerClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			refreshQuickAlarms();
		}
	};
	private OnItemClickListener mListViewListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		}
	};
	private OnDrawerOpenListener mDrawerOpenListener = new OnDrawerOpenListener() {
		@Override
		public void onDrawerOpened() {
			refreshQuickAlarms();
			ImageView button = (ImageView) findViewById(R.id.main_drawer_button);
			button.setImageDrawable(mResources.getDrawable(R.drawable.drawer_open));
		}
	};
	
	
	
	private OnDrawerCloseListener mDrawerCloseListener = new OnDrawerCloseListener() {
		@Override
		public void onDrawerClosed() {
			ImageView button = (ImageView) findViewById(R.id.main_drawer_button);
			button.setImageDrawable(mResources.getDrawable(R.drawable.drawer_closed));
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_options:
				Intent options = new Intent(this, FortyPrefs.class);
				startActivity(options);
				break;
		}
		return true;
	}
	
}