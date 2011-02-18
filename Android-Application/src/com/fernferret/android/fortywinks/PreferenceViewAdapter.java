package com.fernferret.android.fortywinks;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class PreferenceViewAdapter extends ArrayAdapter<Alarm> {
	
	private Context mContext;
	
	public PreferenceViewAdapter(Context context, int resource, int textViewResourceId, List<Alarm> objects) {
		super(context, resource, textViewResourceId, objects);
		mContext = context;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		PreferenceView prefView = null;
		if (convertView == null) {
			prefView = new PreferenceView(mContext);
		} else {
			prefView = (PreferenceView) convertView;
		}
		Alarm currentAlarm = getItem(position);
		//currentRow.setLeftText(currentAlarm.);
		
		return prefView;
	}
}
