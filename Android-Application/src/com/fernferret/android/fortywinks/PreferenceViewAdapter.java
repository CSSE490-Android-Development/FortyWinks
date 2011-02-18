package com.fernferret.android.fortywinks;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class PreferenceViewAdapter extends ArrayAdapter<ProposedAlarm> {
	
	private Context mContext;
	
	public PreferenceViewAdapter(Context context, int resource, int textViewResourceId, List<ProposedAlarm> objects) {
		super(context, resource, textViewResourceId, objects);
		mContext = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		PreferenceView prefView = null;
		if (convertView == null) {
			prefView = new PreferenceView(mContext);
			prefView.setRightAlign(Gravity.LEFT);
			prefView.setLeftAlign(Gravity.LEFT);
		} else {
			prefView = (PreferenceView) convertView;
		}
		ProposedAlarm currentItem = getItem(position);
		prefView.setLeftText(currentItem.getPrettyTime());
		prefView.setRightText(currentItem.getPrettyCyclesTillAlarm());
		return prefView;
	}
}
