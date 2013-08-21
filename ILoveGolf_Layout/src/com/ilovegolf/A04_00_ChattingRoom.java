package com.ilovegolf;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.ilovegolf.adapter.A04_00_ChatRoomAdapter;
import com.ilovegolf.struct.ChatRoom;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.util.StaticClass;
import com.ilovegolf.util.TabActivity;

public class A04_00_ChattingRoom extends TabActivity {
	ArrayList<ChatRoom> chatroomList = null;
	ListView list_chatroom = null;
	A04_00_ChatRoomAdapter chatroomAdapter = null;

	SharedPreferences sp = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		bFriend = true;
		bFindLink = true;
		bTalkRoomList = false;
		bSetting = true;

		RelativeLayout relative_content = layout.b00_00_relative_content;
		LinearLayout linear = layout.linear;
		linear.removeView(relative_content);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		sp = getSharedPreferences("ilovegolfPrefer", 0);
		tab_TalkRoomList.setBackgroundResource(R.drawable.main_topbar_menu_03_on);

		StaticClass.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				dismiss();
				if (msg.arg1 == StaticClass.MESSAGE_REFRESH || msg.arg1 == StaticClass.CHATROOM_REFRESH) {
					chatroomList.clear();
					synchronized (StaticClass.dbm) {
						StaticClass.dbm.selectChatRoomDB(chatroomList);
					}
					chatroomAdapter.notifyDataSetChanged();
				}
			}
		};

		chatroomList = new ArrayList<ChatRoom>();
		synchronized (StaticClass.dbm) {
			StaticClass.dbm.selectChatRoomDB(chatroomList);
		}
		list_chatroom = layout.b00_00_list_content;
		list_chatroom.setSelector(new ColorDrawable(Color.alpha(0)));
		chatroomAdapter = new A04_00_ChatRoomAdapter(context, display, chatroomList, sp.getString("myID", ""));
		list_chatroom.setAdapter(chatroomAdapter);
		list_chatroom.setSelectionFromTop(chatroomAdapter.getCount(), 0);


		list_chatroom.setOnItemLongClickListener(new OnItemLongClickListener() {

			ChatRoom chatroom = null;

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View v, int id, long arg3) {
				chatroom = chatroomList.get(id);

				StaticClass.alert = new AlertDialog.Builder(context).create();
				StaticClass.alert.setTitle("아이러브골프");
				StaticClass.alert.setMessage("채팅방에서 나가시겠습니까?\n나가기를 하면 모든 대화내용이 삭제됩니다.");
				StaticClass.alert.setButton(AlertDialog.BUTTON_NEGATIVE, "취소", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

				StaticClass.alert.setButton(AlertDialog.BUTTON_POSITIVE, "확인", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						synchronized (StaticClass.dbm) {
							StaticClass.dbm.deleteChatRoomDB(chatroom.strRoomID);
							StaticClass.dbm.deleteMessageDB_ALL(chatroom.strRoomID);

							chatroomList.clear();
							StaticClass.dbm.selectChatRoomDB(chatroomList);
						}
						chatroomAdapter.notifyDataSetChanged();
					}
				});

				StaticClass.alert.show();
				return false;
			}

		});
	}

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
