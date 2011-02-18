package com.fernferret.android.fortywinks;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PreferenceView extends TableLayout {
	
	private Activity mContext;
	private TextView mLeftTextView;
	private TextView mRightTextView;
	private TableRow mTableRow;
	
	public PreferenceView(Context context) {
		super(context);
		mContext = (Activity) context;
		
		LayoutInflater layoutInflater = mContext.getLayoutInflater();
		layoutInflater.inflate(R.layout.preference_view, this);
		
		mTableRow = (TableRow) findViewById(R.id.preference_view_row);
		mLeftTextView = (TextView) findViewById(R.id.preference_view_left_text);
		mRightTextView = (TextView) findViewById(R.id.preference_view_right_text);
		

		
		//mTableRow.setOnClickListener(mOnRowClickListener);
	}
	
	public PreferenceView(Context context, String left, String right) {
		this(context);
		
		mLeftTextView.setText(left);
		mRightTextView.setText(right);
	}
	
//	private View.OnClickListener mOnRowClickListener = new View.OnClickListener() {
//		@Override
//		public void onClick(View v) {
//			Log.w("40W", "40W - I was clicked!!!");
//		}
//	};
	
	public void setLeftText(String text) {
		mLeftTextView.setText(text);
	}
	
	public void setLeftAlign(int g) {
		mRightTextView.setGravity(g);
	}
	
	public void setRightText(String text) {
		mRightTextView.setText(text);
	}
	
	public void setRightAlign(int g) {
		mRightTextView.setGravity(g);
	}
	
	
	
}
