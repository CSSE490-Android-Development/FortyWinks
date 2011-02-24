package com.fernferret.android.fortywinks;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
/**
 * Activity that will allow the user to dismiss alarms.
 * @author ericstokes
 *
 */
public class WakeUpAlert extends Activity {
	PowerManager.WakeLock mWakeLock;
	KeyguardManager mKeyguardManager;
	KeyguardLock mKeyguardLock;
	MediaPlayer mp;
	Button mClose;
	
	int mUserVolume;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wake_up_alert);
		
		// If this is the last alarm in this package of alarm...
		if(getIntent().getBooleanExtra("ALARM_LAST", false)) {
			SQLiteAdapter dbAdapter = new SQLiteAdapter(this);
			dbAdapter.deleteAlarm(getIntent().getIntExtra("ALARM_ID", 0));
		}
		Uri soundLocation = null;
		try {
			soundLocation = Uri.parse(getIntent().getStringExtra("ALARM_SOUND"));
		} catch (Exception e) {
			soundLocation = Settings.System.DEFAULT_ALARM_ALERT_URI;
		}
		
		Toast.makeText(this, "You should get up!", Toast.LENGTH_SHORT).show();
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
		mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		mKeyguardLock = mKeyguardManager.newKeyguardLock(Context.KEYGUARD_SERVICE);
		mWakeLock.acquire();
		mClose = (Button) findViewById(R.id.wake_up_alert_close);
		mClose.setOnClickListener(mCloseClickListener);
		mKeyguardLock.disableKeyguard();
		Log.d("40W", "40W - Preparing to play a sound...(" + soundLocation + ")");
		mp = MediaPlayer.create(this, soundLocation);
		//mp = MediaPlayer.create(this, R.raw.woah);
		AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		
		int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		mUserVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		//mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume, 0);
		mp.setLooping(true);
		mp.start();
	}
	
	private View.OnClickListener mCloseClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(mp.isPlaying()) {
				mp.stop();
				mp.release();
			}
			AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,mUserVolume, 0);
			mKeyguardLock.reenableKeyguard();
			mWakeLock.release();
			finish();
		}
	};
}
