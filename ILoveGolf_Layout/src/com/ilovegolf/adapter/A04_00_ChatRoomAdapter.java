package com.ilovegolf.adapter;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilovegolf.A04_00_RecvMessageList;
import com.ilovegolf.R;
import com.ilovegolf.layout.I04_00_ChatroomItem;
import com.ilovegolf.struct.ChatRoom;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.util.DBManager;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A04_00_ChatRoomAdapter extends BaseAdapter {
	Context context = null;
	Display display = null;

	ArrayList<ChatRoom> chatroomList = null;

	ChatRoom chatroom = null;
	Friend friend = null;

	String myid = "";

	public A04_00_ChatRoomAdapter(Context context, Display display, ArrayList<ChatRoom> chatroomList, String myid) {
		this.context = context;
		this.display = display;
		this.chatroomList = chatroomList;
		this.myid = myid;
	}

	@Override
	public int getCount() {
		return chatroomList.size();
	}

	@Override
	public Object getItem(int id) {
		return chatroomList.get(id);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int id, View convertView, ViewGroup parent) {
		chatroom = chatroomList.get(id);
		friend = StaticClass.dbm.selectFriendDB(chatroom.strRoomID);

		I04_00_ChatroomItem layout = new I04_00_ChatroomItem();
		convertView = layout.getLayout(context, display);

		ImageView img_pic = layout.i04_00_img_pic;
		img_pic.setImageBitmap(BitmapFactory.decodeFile("/mnt/sdcard/golfpic/" + chatroom.strRoomID));

		TextView text_name = layout.i04_00_text_name;
		TextView text_date = layout.i04_00_text_date;
		TextView text_message = layout.i04_00_text_message;
		View btn_state = layout.i04_00_btn_state;
		if (friend.iIsFlag == 1) {
			btn_state.setBackgroundResource(R.drawable.ico_standby);
		} else if (friend.iIsFlag == 2) {
			btn_state.setBackgroundResource(R.drawable.btn_ok_you_off);
		} else if (friend.iIsFlag == 3) {
			btn_state.setBackgroundResource(R.drawable.btn_talk_me_off);
		}

		btn_state.setTag(id);
		btn_state.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int id=(Integer) v.getTag();
				chatroom = chatroomList.get(id);
				friend = StaticClass.dbm.selectFriendDB(chatroom.strRoomID);
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (friend.iIsFlag == 2)
						v.setBackgroundResource(R.drawable.btn_ok_you_on);
					else if (friend.iIsFlag == 3)
						v.setBackgroundResource(R.drawable.btn_talk_me_on);
				}

				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (friend.iIsFlag == 2)
						v.setBackgroundResource(R.drawable.btn_ok_you_off);
					else if (friend.iIsFlag == 3)
						v.setBackgroundResource(R.drawable.btn_talk_me_off);
				}

				return false;
			}
		});

		btn_state.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int id=(Integer) v.getTag();
				chatroom = chatroomList.get(id);
				friend = StaticClass.dbm.selectFriendDB(chatroom.strRoomID);
				if (friend.iIsFlag == 2) {
					v.setBackgroundResource(R.drawable.btn_ok_you_off);
					friend.iIsFlag = 3;
					StaticClass.dbm.updateFriendDB(friend);

					try {
						if (!StaticClass.DataSoc.isConnected())
							StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

						String send = "BEGIN ACCEPTREQUEST\r\n";
						send += "Sender_ID:" + myid + "\r\n";
						send += "Recver_ID:" + chatroom.strRoomID + "\r\n";
						send += "END\r\n";
						StaticClass.DataSoc.sendMessage(send);

						Message hanMessage = new Message();
						hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
						StaticClass.handler.sendMessage(hanMessage);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						
					}
					
				}else if(friend.iIsFlag==3) {
					Intent intent = new Intent(context.getApplicationContext(), A04_00_RecvMessageList.class);
					intent.putExtra("id", chatroom.strRoomID);
					context.startActivity(intent);
					((Activity) context).finish();
					

					Message hanMessage = new Message();
					hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
					StaticClass.handler.sendMessage(hanMessage);
				}
			}
		});

		text_name.setText(chatroom.strRoomName);
		text_date.setText(chatroom.strUpdateDate);
		text_message.setText(chatroom.strUpdateMessage);
		return convertView;
	}
}
