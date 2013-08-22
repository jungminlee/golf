package com.ilovegolf;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ilovegolf.adapter.A05_00_SettingMenuAdapter;
import com.ilovegolf.layout.L05_00_SettingLayout;
import com.ilovegolf.util.StaticClass;
import com.ilovegolf.util.TabActivity;

public class A05_00_Setting extends TabActivity {
	L05_00_SettingLayout settinglayout = null;

	ArrayList<String> menuList = null;
	ListView list_menulist = null;
	A05_00_SettingMenuAdapter menuListAdapter = null;

	TextView btn_profile = null;
	TextView btn_alarm = null;
	TextView btn_qna = null;
	TextView btn_pay = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settinglayout = new L05_00_SettingLayout();
		layout.b00_00_linear_content.addView(settinglayout.getLayout(context, display));

		bFriend = true;
		bFindLink = true;
		bTalkRoomList = true;
		bSetting = false;
		
		tab_Setting.setBackgroundResource(R.drawable.main_topbar_menu_04_on);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());


		ListView list_content = layout.b00_00_list_content;
		LinearLayout linear = layout.linear;
		linear.removeView(list_content);

		btn_profile = settinglayout.l05_00_btn_profile;
		btn_alarm = settinglayout.l05_00_btn_alarm;
		btn_qna = settinglayout.l05_00_btn_qna;
		btn_pay = settinglayout.l05_00_btn_pay;

		btn_profile.setOnClickListener(onClick);
		btn_alarm.setOnClickListener(onClick);
		btn_qna.setOnClickListener(onClick);
		btn_pay.setOnClickListener(onClick);

		btn_profile.setOnTouchListener(onTouch);
		btn_alarm.setOnTouchListener(onTouch);
		btn_qna.setOnTouchListener(onTouch);
		btn_pay.setOnTouchListener(onTouch);
	}

	OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == btn_profile) {
				Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
				startActivity(intent);
			}else if (v == btn_alarm) {
				Intent intent = new Intent(getBaseContext(), A05_02_AlarmSetting.class);
				startActivity(intent);
			}else if (v == btn_qna) {
				Uri uri = Uri.parse("mailto:ljm4843@dalbitsoft.com");
				Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
				intent.putExtra(intent.EXTRA_SUBJECT, "[아이러브골프]문의메일");
				startActivity(intent);
			}else if (v == btn_pay) {
				Intent intent = new Intent(getBaseContext(), A05_04_Payment.class);
				startActivity(intent);
			}
		}
	};

	OnTouchListener onTouch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (v == btn_profile) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					v.setBackgroundResource(R.drawable.golf_setup_profile_line_on);
				else if (event.getAction() == MotionEvent.ACTION_UP)
					v.setBackgroundResource(R.drawable.golf_setup_profile_line);
			}else if (v == btn_alarm) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					v.setBackgroundResource(R.drawable.golf_setup_alam_line_on);
				else if (event.getAction() == MotionEvent.ACTION_UP)
					v.setBackgroundResource(R.drawable.golf_setup_alam_line);
			}else if (v == btn_qna) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					v.setBackgroundResource(R.drawable.golf_setup_qna_line_on);
				else if (event.getAction() == MotionEvent.ACTION_UP)
					v.setBackgroundResource(R.drawable.golf_setup_qna_line);
			}else if (v == btn_pay) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					v.setBackgroundResource(R.drawable.golf_setup_pay_line_on);
				else if (event.getAction() == MotionEvent.ACTION_UP)
					v.setBackgroundResource(R.drawable.golf_setup_pay_line);
			}
			return false;
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			StaticClass.alert = new AlertDialog.Builder(context).create();
			StaticClass.alert.setTitle("아이러브골프");
			StaticClass.alert.setMessage("종료 하시겠습니까?");
			StaticClass.alert.setButton(AlertDialog.BUTTON_NEGATIVE, "취소", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});

			StaticClass.alert.setButton(AlertDialog.BUTTON_POSITIVE, "확인", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});

			StaticClass.alert.show();
		}
		return true;
	}
}
