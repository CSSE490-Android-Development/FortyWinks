package com.fernferret.android.fortywinks;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.widget.Toast;

public class SingleAlarm extends BroadcastReceiver {
	
	PowerManager.WakeLock mWakeLock;
	KeyguardManager mKeyguardManager;
	KeyguardLock mKeyguardLock;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "You should get up!", Toast.LENGTH_SHORT).show();
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
		mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
		mKeyguardLock = mKeyguardManager.newKeyguardLock(Context.KEYGUARD_SERVICE);
		mWakeLock.acquire();
		mKeyguardLock.disableKeyguard();
		MediaPlayer mp = MediaPlayer.create(context, R.raw.woah);
	    mp.start();
	    while(mp.isPlaying());
	    mKeyguardLock.reenableKeyguard();
	    mWakeLock.release();
	}
	
}
