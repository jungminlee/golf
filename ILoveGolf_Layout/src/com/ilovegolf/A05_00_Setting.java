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
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ilovegolf.adapter.A05_00_SettingMenuAdapter;
import com.ilovegolf.util.StaticClass;
import com.ilovegolf.util.TabActivity;

public class A05_00_Setting extends TabActivity {
	View tab_Friend = null;
	View tab_FindGolf = null;
	View tab_TalkList = null;

	ArrayList<String> menuList = null;
	ListView list_menulist = null;
	A05_00_SettingMenuAdapter menuListAdapter = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		bFriend = true;
		bFindLink = true;
		bTalkRoomList = true;
		bSetting = false;

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		tab_Setting.setBackgroundResource(R.drawable.main_topbar_menu_04_on);
		
		RelativeLayout relative_content=layout.b00_00_relative_content;
		LinearLayout linear=layout.linear;
		linear.removeView(relative_content);

		menuList = new ArrayList<String>();
		menuList.add("프로필 변경");
		menuList.add("알림 설정");
		menuList.add("문의 하기");
		menuList.add("결제 내역");

		menuListAdapter = new A05_00_SettingMenuAdapter(context, display, menuList);
		list_menulist = layout.b00_00_list_content;
		list_menulist.setAdapter(menuListAdapter);

		list_menulist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int id, long arg3) {
				if (id == 0) {
					Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
					startActivity(intent);
				} else if (id == 1) {
					Intent intent = new Intent(getBaseContext(), A05_02_AlarmSetting.class);
					startActivity(intent);
				} else if (id == 2) {
					Uri uri=Uri.parse("mailto:ljm4843@dalbitsoft.com");
					Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
					intent.putExtra(intent.EXTRA_SUBJECT, "[아이러브골프]문의메일");
					startActivity(intent);
				} else if (id == 3) {
					Intent intent = new Intent(getBaseContext(), A05_04_Payment.class);
					startActivity(intent);
					finish();
				}
			}
		});
		
	}
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
