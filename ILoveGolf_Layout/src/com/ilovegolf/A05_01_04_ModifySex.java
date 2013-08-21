package com.ilovegolf;

import java.io.IOException;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilovegolf.layout.L05_01_04_ModifyLayout2;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A05_01_04_ModifySex extends BaseActivity {
	L05_01_04_ModifyLayout2 layout = null;

	SharedPreferences sp = null;
	Editor edit = null;

	String sex = "";

	View btn_woman = null;
	View btn_man = null;
	View btn_ok = null;

	int viewHight;
	int viewWidth;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new L05_01_04_ModifyLayout2();
		setContentView(layout.getLayout(context, display));

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		viewHight = display.getHeight();
		viewWidth = display.getWidth();

		sp = getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();

		TextView text_sub = layout.l05_01_01_text_subject;
		text_sub.setText("성별을 선택하세요");

		LinearLayout linear = layout.linear3;

		int tempWidth = ((int) (((float) 64 * (float) viewWidth) / (float) 480));
		int tempHeight = ((int) (((float) 63 * (float) viewHight) / (float) 800));
		btn_woman = new TextView(context);
		btn_woman.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
		btn_woman.setBackgroundResource(R.drawable.btn_setup_woman);
		linear.addView(btn_woman);

		int paddingWidth = ((int) (((float) 25 * (float) viewWidth) / (float) 480));
		btn_man = new TextView(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
		params.setMargins(paddingWidth, 0, 0, 0);
		btn_man.setLayoutParams(params);
		btn_man.setBackgroundResource(R.drawable.btn_setup_man);
		linear.addView(btn_man);

		if (sp.getString("mySex", "").equals("M")) {
			sex = "M";
			btn_man.setBackgroundResource(R.drawable.btn_setup_man_on);
			btn_woman.setBackgroundResource(R.drawable.btn_setup_woman);
		} else if (sp.getString("mySex", "").equals("W")) {
			sex = "W";
			btn_woman.setBackgroundResource(R.drawable.btn_setup_woman_on);
			btn_man.setBackgroundResource(R.drawable.btn_setup_man);
		}

		btn_ok = layout.l05_01_01_btn_ok;

		btn_woman.setOnTouchListener(onTouch);
		btn_man.setOnTouchListener(onTouch);
		btn_ok.setOnTouchListener(onTouch);

		btn_woman.setOnClickListener(onClick);
		btn_man.setOnClickListener(onClick);
		btn_ok.setOnClickListener(onClick);
	}

	OnTouchListener onTouch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (v == btn_woman) {
					v.setBackgroundResource(R.drawable.btn_setup_woman_on);
				} else if (v == btn_man) {
					v.setBackgroundResource(R.drawable.btn_setup_man_on);
				} else if (v == btn_ok) {
					v.setBackgroundResource(R.drawable.btn_ok_on);
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				if (v == btn_woman) {
					v.setBackgroundResource(R.drawable.btn_setup_woman);
				} else if (v == btn_man) {
					v.setBackgroundResource(R.drawable.btn_setup_man);
				} else if (v == btn_ok) {
					v.setBackgroundResource(R.drawable.btn_ok_off);
				}
			}
			return false;
		}
	};

	OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == btn_woman) {
				sex = "W";
				btn_woman.setBackgroundResource(R.drawable.btn_setup_woman_on);
				btn_man.setBackgroundResource(R.drawable.btn_setup_man);
			} else if (v == btn_man) {
				sex = "M";
				btn_man.setBackgroundResource(R.drawable.btn_setup_man_on);
				btn_woman.setBackgroundResource(R.drawable.btn_setup_woman);
			} else if (v == btn_ok) {
				edit.putString("mySex", sex);
				edit.commit();

				try {
					if (!StaticClass.DataSoc.isConnected())
						StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

					String send = "BEGIN UPDATEMEMBERSEX\r\n";
					send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
					send += "Member_Sex:" + sp.getString("mySex", "") + "\r\n";
					send += "END\r\n";
					StaticClass.DataSoc.sendMessage(send);

					Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
					startActivity(intent);
					overridePendingTransition(0, 0);
					finish();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					Log.e("error", e.getMessage() + "");

				}
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
			startActivity(intent);
			overridePendingTransition(0, 0);
			finish();
		}
		return true;
	}
}
