package com.fernferret.android.fortywinks;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * A Special view that has 2 text components that can be re-aligned.
 * @author Eric Stokes
 *
 */
public class PreferenceView extends TableLayout {
	
	private Activity mContext;
	private TextView mLeftTextView;
	private TextView mRightTextView;
	
	/**
	 * Creates a new Preference view with a context and a set of attributes. This is useful for creating within XML.
	 * @param context The context to create the view in.
	 * @param attributes The attributes to pass to this view.
	 */
	public PreferenceView(Context context, AttributeSet attributes) {
		// This was the reason for the NullPointerException
	    super(context, attributes);
	}
	
	/**
	 * Creates a new Preference view with a context.  This is useful for createing within code.
	 * @param context The context to create the view in.
	 */
	public PreferenceView(Context context) {
		super(context);
		mContext = (Activity) context;
		
		LayoutInflater layoutInflater = mContext.getLayoutInflater();
		layoutInflater.inflate(R.layout.preference_view, this);
		
		mLeftTextView = (TextView) findViewById(R.id.preference_view_left_text);
		mRightTextView = (TextView) findViewById(R.id.preference_view_right_text);
	}
	
	/**
	 * Creates a new PreferenceView with pre-set strings
	 * @param context The context to create the view in.
	 * @param left The text on the left side
	 * @param right The text on the Right side
	 */
	public PreferenceView(Context context, String left, String right) {
		this(context);
		
		mLeftTextView.setText(left);
		mRightTextView.setText(right);
	}
	
	public void setLeftText(String text) {
		mLeftTextView.setText(text);
	}
	/**
	 * Set the alignment properties of the Right text box.  Use Gravity.[XMLATTRIBUTENAME] as the input.
	 * @param gravity The gravity to apply to the Right text box.
	 */
	public void setLeftAlign(int gravity) {
		mRightTextView.setGravity(gravity);
	}
	
	public void setRightText(String text) {
		mRightTextView.setText(text);
	}
	/**
	 * Set the alignment properties of the Right text box.  Use Gravity.[XMLATTRIBUTENAME] as the input.
	 * @param gravity The gravity to apply to the Right text box.
	 */
	public void setRightAlign(int gravity) {
		mRightTextView.setGravity(gravity);
	}
	
	
	
}
