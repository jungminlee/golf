package com.ilovegolf.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.ilovegolf.A02_00_Friend;
import com.ilovegolf.A03_00_FindLink;
import com.ilovegolf.A04_00_ChattingRoom;
import com.ilovegolf.A04_00_RecvMessageList;
import com.ilovegolf.A05_00_Setting;
import com.ilovegolf.R;
import com.ilovegolf.layout.B00_00_TabLayout;

public class TabActivity extends BaseActivity {
	protected View tab_Friend = null;
	protected View tab_FindLink = null;
	protected View tab_TalkRoomList = null;
	protected View tab_Setting = null;

	protected boolean bFriend = true;
	protected boolean bFindLink = true;
	protected boolean bTalkRoomList = true;
	protected boolean bSetting = true;

	protected B00_00_TabLayout layout=null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout=new B00_00_TabLayout();
		setContentView(layout.getLayout(context, display));

		tab_Friend = layout.b00_00_tab_friend;
		tab_FindLink = layout.b00_00_tab_findlink;
		tab_TalkRoomList = layout.b00_00_tab_talkroomlist;
		tab_Setting = layout.b00_00_tab_setting;

		tab_Friend.setOnTouchListener(onTouch);
		tab_FindLink.setOnTouchListener(onTouch);
		tab_TalkRoomList.setOnTouchListener(onTouch);
		tab_Setting.setOnTouchListener(onTouch);

		tab_Friend.setOnClickListener(onClick);
		tab_FindLink.setOnClickListener(onClick);
		tab_TalkRoomList.setOnClickListener(onClick);
		tab_Setting.setOnClickListener(onClick);
	}

	OnTouchListener onTouch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (v == tab_Friend && bFriend) {
					v.setBackgroundResource(R.drawable.main_topbar_menu_01_on);
				} else if (v == tab_FindLink && bFindLink) {
					v.setBackgroundResource(R.drawable.main_topbar_menu_02_on);
				} else if (v == tab_TalkRoomList && bTalkRoomList) {
					v.setBackgroundResource(R.drawable.main_topbar_menu_03_on);
				} else if (v == tab_Setting && bSetting) {
					v.setBackgroundResource(R.drawable.main_topbar_menu_04_on);
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				if (v == tab_Friend && bFriend) {
					v.setBackgroundResource(R.drawable.main_topbar_menu_01_off);
				} else if (v == tab_FindLink && bFindLink) {
					v.setBackgroundResource(R.drawable.main_topbar_menu_02_off);
				} else if (v == tab_TalkRoomList && bTalkRoomList) {
					v.setBackgroundResource(R.drawable.main_topbar_menu_03_off);
				} else if (v == tab_Setting && bSetting) {
					v.setBackgroundResource(R.drawable.main_topbar_menu_04_off);
				}
			}
			return false;
		}
	};

	OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == tab_Friend && bFriend) {
				Intent intent=new Intent(getBaseContext(), A02_00_Friend.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			} else if(v==tab_FindLink && bFindLink) {
				Intent intent=new Intent(getBaseContext(), A03_00_FindLink.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			} else if(v==tab_TalkRoomList && bTalkRoomList) {
				Intent intent=new Intent(getBaseContext(), A04_00_ChattingRoom.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			} else if(v==tab_Setting && bSetting) {
				Intent intent=new Intent(getBaseContext(), A05_00_Setting.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		}
	};
}
