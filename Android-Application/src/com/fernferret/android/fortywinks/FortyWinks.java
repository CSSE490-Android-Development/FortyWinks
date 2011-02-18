package com.fernferret.android.fortywinks;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
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
	TextView mBigTime;
	TextView mTimeLeftToSleep;
	TextView mRemainingCycles;
	
	// Buttons
	Button mDoSleepButton;
	
	// ListViews
	ListView mQuickAlarmList;
	
	// Drawer
	SlidingDrawer mDrawer;
	
	ArrayAdapter<PreferenceView> mQuickAlarmAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Load Resources
		mResources = getResources();
		
		// Load UI Elements
		mBigTime = (TextView) findViewById(R.id.main_big_time);
		mTimeLeftToSleep = (TextView) findViewById(R.id.main_time_left_to_sleep);
		mRemainingCycles = (TextView) findViewById(R.id.main_remaining_cycles);
		
		mDoSleepButton = (Button) findViewById(R.id.main_do_sleep_button);
		
		mQuickAlarmList = (ListView) findViewById(R.id.main_quick_alarms_list);
		
		mDrawer = (SlidingDrawer) findViewById(R.id.main_drawer);
		
		ArrayList<PreferenceView> listItems = new ArrayList<PreferenceView>();
		for(int i = 0; i < 20; i++) {
			listItems.add(new PreferenceView(this, "Left" + i, "Right"));
		}
		mQuickAlarmAdapter = new ArrayAdapter<PreferenceView>(this, R.layout.preference_view, R.id.preference_view_left_text, listItems);
		
		
		// Set Listeners
		mDrawer.setOnDrawerOpenListener(mDrawerOpenListener);
		mDrawer.setOnDrawerCloseListener(mDrawerCloseListener);
		mDoSleepButton.setOnClickListener(mOnButtonClickListener);
		mQuickAlarmList.setOnItemClickListener(mListViewListener);
		
	}
	private View.OnClickListener mOnButtonClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			
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
		switch(item.getItemId()) {
			case R.id.menu_options:
				Intent options = new Intent(this, FortyPrefs.class);
				startActivity(options);
				break;
		}
		return true;
	}
	
}