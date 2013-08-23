package com.ilovegolf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.ilovegolf.adapter.A04_00_RecvMessageAdapter;
import com.ilovegolf.layout.L04_01_RecvMessageListLayout;
import com.ilovegolf.struct.ChatRoom;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.struct.RecvMessage;
import com.ilovegolf.util.BarActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A04_00_RecvMessageList extends BarActivity {
	L04_01_RecvMessageListLayout layout = null;

	ArrayList<RecvMessage> messageList = null;
	ListView list_message = null;
	A04_00_RecvMessageAdapter messageListAdapter = null;

	String id = "";
	EditText edit_message = null;
	SharedPreferences sp = null;

	Friend friend = null;
	ImageView img_pic = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		layout = new L04_01_RecvMessageListLayout();
		setContentView(layout.getLayout(context, display));

		sp = getSharedPreferences("ilovegolfPrefer", 0);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		StaticClass.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				dismiss();
				if (msg.arg1 == StaticClass.MESSAGE_REFRESH) {
					messageList.clear();
					synchronized (StaticClass.dbm) {
						StaticClass.dbm.selectMessageDB(messageList, id);
					}
					messageListAdapter.notifyDataSetChanged();
					list_message.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
				}
			}
		};

		Intent intent = getIntent();
		id = intent.getStringExtra("id");
		StaticClass.chatmember = id;
		synchronized (StaticClass.dbm) {
			friend = StaticClass.dbm.selectFriendDB(id);

			layout.b00_01_text_bar.setText("						(" + friend.strName + ")");
			messageList = new ArrayList<RecvMessage>();
			StaticClass.dbm.selectMessageDB(messageList, id);
		}
		list_message = layout.l04_01_list_message;
		list_message.setSelector(new ColorDrawable(Color.alpha(0)));
		messageListAdapter = new A04_00_RecvMessageAdapter(context, display, messageList);
		list_message.setAdapter(messageListAdapter);
		list_message.setSelectionFromTop(messageListAdapter.getCount(), 0);

		edit_message = layout.l04_01_edit_message;
		edit_message.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				list_message.setSelectionFromTop(messageListAdapter.getCount(), 0);
			}
		});

		View btn_send = layout.l04_01_btn_send;
		btn_send.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					v.setBackgroundResource(R.drawable.btn_chat_send_on);
				else if (event.getAction() == MotionEvent.ACTION_UP)
					v.setBackgroundResource(R.drawable.btn_chat_send_off);
				return false;
			}
		});
		btn_send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!edit_message.getText().toString().equals("")) {
					String msg = edit_message.getText().toString();
					edit_message.setText("");
					String date = StaticClass.format.format(new Date());

					try {
						if (StaticClass.DataSoc == null || !StaticClass.DataSoc.isConnected() || StaticClass.DataSoc.isClosed()) {
							StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);
						}
						
						String send = "BEGIN CREATECHATROOM\r\n";
						send += "Sender_ID:" + sp.getString("myID", "") + "\r\n";
						send += "Sender_Name:" + sp.getString("myName", "") + "\r\n";
						send += "Sender_Image:" + sp.getString("myImage", "") + "\r\n";
						send += "Recver_ID:" + friend.strID + "\r\n";
						send += "END\r\n";
						
						
						send = "BEGIN SENDMESSAGE\r\n";
						send += "Sender_ID:" + sp.getString("myID", "") + "\r\n";
						send += "Sender_Name:" + sp.getString("myName", "") + "\r\n";
						send += "Sender_Image:" + sp.getString("myImage", "") + "\r\n";
						send += "Recver_ID:" + friend.strID + "\r\n";
						send += "Message_content:" + msg + "\r\n";
						send += "Message_date:" + date + "\r\n";
						send += "Message_code:3\r\n";
						send += "END\r\n";

						Log.e("send", send + "");
						StaticClass.DataSoc.sendMessage(send);

						date=date.replace("^", ":");
						RecvMessage message = new RecvMessage();
						message.strPeopleID = friend.strID;
						message.strContent = msg;
						message.strDate = date;
						message.iCode = 1;

						ChatRoom chatroom = new ChatRoom();
						chatroom.strRoomID = friend.strID;
						chatroom.strUpdateDate = date;
						chatroom.strUpdateMessage = msg;

						synchronized (StaticClass.dbm) {
							StaticClass.dbm.updateChatRoomDB(chatroom);
							StaticClass.dbm.insertMessageDB(message);

							messageList.clear();
							StaticClass.dbm.selectMessageDB(messageList, id);
						}
						messageListAdapter.notifyDataSetChanged();
						list_message.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
					} catch (IOException e) {
						StaticClass.DataSoc = null;
						edit_message.setText(msg);
					} catch (Exception e) {
						Log.e("error", e.getMessage() + "");

					}

				} else {
					StaticClass.alert = new AlertDialog.Builder(context).create();
					StaticClass.alert.setTitle("아이러브골프");
					StaticClass.alert.setMessage("메세지를 입력해 주세요.");
					StaticClass.alert.setButton(AlertDialog.BUTTON_NEUTRAL, "확인", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					StaticClass.alert.show();
				}
			}
		});
	}

     

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			StaticClass.chatmember = "";
			Intent intent = new Intent(this, A04_00_ChattingRoom.class);
			context.startActivity(intent);
			overridePendingTransition(0, 0);
			finish();
			return true;
		}
		return false;
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"차단하기");
        return true;
    }
     
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
		case 1:
			synchronized (StaticClass.dbm) {
				StaticClass.dbm.updateFriendBlack(id);
				System.out.println("차단 되었습니다");
			}
			return true;
		}
        return false;
    }
}
