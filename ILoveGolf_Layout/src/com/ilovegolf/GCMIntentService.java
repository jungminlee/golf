package com.ilovegolf;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.ilovegolf.util.StaticClass;

public class GCMIntentService extends GCMBaseIntentService {

	NotificationManager mNotiManager = null;

	SharedPreferences sp = null;

	@Override
	protected void onError(Context context, String error) {
		Log.e("@@@@", "onError : " + error);
	}

	@Override
	protected void onRegistered(Context context, String regID) {
		Log.e("@@@@", "onError : " + regID);
	}

	@Override
	protected void onUnregistered(Context context, String regID) {
		Log.e("@@@@", "onError : " + regID);
	}

	@Override
	protected void onMessage(Context context, Intent message) {
		if (message == null)
			return;
		final String gcmMsg = message.getStringExtra("msg");
		final String gcmTitle = message.getStringExtra("title");

		mNotiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Notification noti = new Notification(R.drawable.noti, gcmMsg, System.currentTimeMillis());
		Log.e("notinoti", "================2222222222222");
		sp = context.getSharedPreferences("ilovegolfPrefer", 0);
		if (sp.getInt("myIsVibrate", 0) == 1) {
			if (sp.getInt("myIsRing", 0) == 1)
				noti.defaults |= (Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
			else
				noti.defaults |= Notification.DEFAULT_VIBRATE;
		} else
			noti.defaults = 0;

		noti.flags |= Notification.FLAG_AUTO_CANCEL;
		// noti.flags = Notification.FLAG_ONLY_ALERT_ONCE;

		Intent intent=null;
		intent = new Intent(GCMIntentService.this, A00_00_Loading.class);
		intent.putExtra("id", message.getStringExtra("id"));
		intent.putExtra("code", message.getStringExtra("code"));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent content = PendingIntent.getActivity(GCMIntentService.this, 0, intent, 0);
		noti.setLatestEventInfo(GCMIntentService.this, gcmTitle, gcmMsg, content);
		mNotiManager.notify(1, noti);

		Log.e("@@@@", "@@@@");
	}
}
