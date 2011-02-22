package com.fernferret.android.fortywinks;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

/**
 * An alarm that fires off only once.
 * 
 * @author Eric Stokes
 * 
 */
public class SingleAlarm extends BroadcastReceiver {
	
	private Context mContext;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.w("40W", "40W: Recieved wakup intent");
		Intent wakeup = new Intent(context, WakeUpAlert.class);
		wakeup.putExtra("alarm_message", "test");
		wakeup.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(wakeup);
		
		// Toast.makeText(context, "You should get up!", Toast.LENGTH_SHORT).show();
		// PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		// mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
		// mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
		// mKeyguardLock = mKeyguardManager.newKeyguardLock(Context.KEYGUARD_SERVICE);
		// mWakeLock.acquire();
		// mKeyguardLock.disableKeyguard();
		// MediaPlayer mp = MediaPlayer.create(context, R.raw.woah);
		// mp.setLooping(true);
		// mp.start();
		// while (mp.isPlaying()) {
		// Log.w("40W", "40W: Song is playing!");
		// mKeyguardLock.reenableKeyguard();
		// mWakeLock.release();
		// }
		Log.w("40W", "40W: Song done!");
	}
	
}
