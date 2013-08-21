package com.ilovegolf;

import java.io.IOException;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.ilovegolf.layout.L05_02_AlarmSettingLayout;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A05_02_AlarmSetting extends BaseActivity {
	L05_02_AlarmSettingLayout layout = null;

	SharedPreferences sp = null;
	Editor edit = null;

	View check_alarm = null;
	View check_ring = null;
	View check_vibrate = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new L05_02_AlarmSettingLayout();
		setContentView(layout.getLayout(context, display));

		sp = context.getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		check_alarm = layout.l05_02_check_alarm;
		if (sp.getInt("myIsAlarm", 0) == 1) {
			check_alarm.setBackgroundResource(R.drawable.golf_setup_alam_on);
		} else {
			check_alarm.setBackgroundResource(R.drawable.golf_setup_alam_off);
		}

		check_ring = layout.l05_02_check_ring;
		if (sp.getInt("myIsRing", 0) == 1) {
			check_ring.setBackgroundResource(R.drawable.golf_setup_sound_on);
		} else {
			check_ring.setBackgroundResource(R.drawable.golf_setup_sound_off);
		}

		check_vibrate = layout.l05_02_check_vibrate;
		if (sp.getInt("myIsVibrate", 0) == 1) {
			check_vibrate.setBackgroundResource(R.drawable.golf_setup_alam_on);
		} else {
			check_vibrate.setBackgroundResource(R.drawable.golf_setup_alam_off);
		}
		check_alarm.setOnClickListener(onClick);
		check_ring.setOnClickListener(onClick);
		check_vibrate.setOnClickListener(onClick);

	}

	OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == check_alarm) {

				try {
					String send = "BEGIN UPDATEMEMBERALARM\r\n";
					send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
					if (sp.getInt("myIsAlarm", 0) == 1) {
						edit.putInt("myIsAlarm", 0);
						edit.putInt("myIsRing", 0);
						edit.putInt("myIsVibrate", 0);

						check_alarm.setBackgroundResource(R.drawable.golf_setup_alam_off);
						check_ring.setBackgroundResource(R.drawable.golf_setup_sound_off);
						check_vibrate.setBackgroundResource(R.drawable.golf_setup_alam_off);

						send += "Member_isAlarm:0\r\n";
					} else {
						edit.putInt("myIsAlarm", 1);

						send += "Member_isAlarm:1\r\n";

						check_alarm.setBackgroundResource(R.drawable.golf_setup_alam_on);
					}
					send += "END\r\n";

					edit.commit();
					if (!StaticClass.DataSoc.isConnected())
						StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);
					StaticClass.DataSoc.sendMessage(send);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					Log.e("error", e.getMessage() + "");

				}
			} else if (v == check_ring) {

				try {
					String send = "BEGIN UPDATEMEMBERRING\r\n";
					send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
					if (sp.getInt("myIsRing", 0) == 0) {
						if (sp.getInt("myIsAlarm", 0) == 1) {
							edit.putInt("myIsRing", 1);
							send += "Member_isRing:1\r\n";

							check_ring.setBackgroundResource(R.drawable.golf_setup_sound_on);
						}
					} else {
						edit.putInt("myIsRing", 0);
						send += "Member_isRing:0\r\n";

						check_ring.setBackgroundResource(R.drawable.golf_setup_sound_off);
					}
					send += "END\r\n";
					edit.commit();
					if (!StaticClass.DataSoc.isConnected())
						StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);
					StaticClass.DataSoc.sendMessage(send);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					Log.e("error", e.getMessage() + "");

				}
			} else if (v == check_vibrate) {

				try {
					String send = "BEGIN UPDATEMEMBERVIBRATE\r\n";
					send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
					if (sp.getInt("myIsVibrate", 0) == 0) {
						if (sp.getInt("myIsAlarm", 0) == 1) {
							edit.putInt("myIsVibrate", 1);
							send += "Member_isVibrate:1\r\n";
							check_vibrate.setBackgroundResource(R.drawable.golf_setup_alam_on);
						}
					} else {
						edit.putInt("myIsVibrate", 0);
						send += "Member_isVibrate:0\r\n";
						check_vibrate.setBackgroundResource(R.drawable.golf_setup_alam_off);
					}
					send += "END\r\n";
					edit.commit();
					if (!StaticClass.DataSoc.isConnected())
						StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);
					StaticClass.DataSoc.sendMessage(send);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					Log.e("error", e.getMessage() + "");

				}
			}
		}
	};
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			finish();
		}
		return true;
	}
}
