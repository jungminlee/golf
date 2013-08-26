package com.ilovegolf;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.struct.Link;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.DBManager;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A00_00_Loading extends BaseActivity {

	TextView text_stat = null;
	SharedPreferences sp = null;
	Editor edit = null;
	String PROJECT_ID = "524396637806";
	String id = null;
	int code = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		super.onCreate(savedInstanceState);
		setContentView(R.layout.l00_00_loading);

		sp = getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();

		text_stat = (TextView) findViewById(R.id.l00_00_text_stat);

		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		edit.putString("myRegID", GCMRegistrar.getRegistrationId(this));
		edit.commit();

		if (sp.getString("myRegID", "").equals("")) {
			GCMRegistrar.register(this, PROJECT_ID);
			edit.putString("myRegID", GCMRegistrar.getRegistrationId(this));
			edit.commit();
		}

		text_stat.setText("GCMIntentService");
		Intent intent = getIntent();
		id = intent.getStringExtra("id");
		String stringcode=intent.getStringExtra("code");
		if (id != null ) {
			StaticClass.ispush = true;
		}else if(stringcode!=null) {
			StaticClass.ispush = true;
			code=Integer.parseInt(intent.getStringExtra("code"));
		}

		StaticClass.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				System.out.println("isjoin::::::::::" + StaticClass.isjoin);
				if (msg.arg1 == StaticClass.LOGIN_SUCC) {
					try {
						String send = "BEGIN SELECTMEMBER\r\n";
						send += "My_ID:" + sp.getString("myID", "") + "\r\n";
						send += "END\r\n";

						send += "BEGIN SELECTLINK\r\n";
						send += "My_ID:" + sp.getString("myID", "") + "\r\n";
						send += "Member_Address_si:" + sp.getString("myAddress_si", "") + "\r\n";
						send += "Member_Address_gu:" + sp.getString("myAddress_gu", "") + "\r\n";
						send += "Member_Address_dong:" + sp.getString("myAddress_dong", "") + "\r\n";
						send += "END\r\n";

						if (StaticClass.DataSoc == null || !StaticClass.DataSoc.isConnected() || StaticClass.DataSoc.isClosed())
							StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

						StaticClass.DataSoc.sendMessage(send);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						Log.e("error", e.getMessage() + "");
					}
				} else if (msg.arg1 == StaticClass.LOGIN_FAIL) {
					StaticClass.isjoin = true;
					Intent intent = new Intent(getBaseContext(), A05_01_01_ModifyName.class);
					startActivity(intent);
					finish();
				} else if (msg.arg1 == StaticClass.REFRESH) {
					synchronized (StaticClass.dbm) {
						StaticClass.friendList = new ArrayList<Friend>();
						Friend friend = new Friend();
						friend.strName = "#title#1";
						StaticClass.friendList.add(friend);
						StaticClass.dbm.selectFavoriteFriendDB(1, StaticClass.friendList);
						friend = new Friend();
						friend.strName = "#title#2";
						StaticClass.friendList.add(friend);
						StaticClass.dbm.selectNearFriendDB(StaticClass.friendList, StaticClass.other_address_si, StaticClass.other_address_gu, StaticClass.other_address_dong);

						StaticClass.linkList = new ArrayList<Link>();
						Link link = new Link();
						link.strName = "#title#1";
						StaticClass.linkList.add(link);
						StaticClass.dbm.selectLinkDB(1, StaticClass.linkList);
						link = new Link();
						link.strName = "#title#2";
						StaticClass.linkList.add(link);
						StaticClass.dbm.selectLinkDB(StaticClass.linkList, StaticClass.other_address_si, StaticClass.other_address_gu, StaticClass.other_address_dong);
					}

					if (StaticClass.ispush) {
						Intent intent = null;
						if(code==0) {
							intent = new Intent(getBaseContext(), A04_00_RecvMessageList.class);
							intent.putExtra("id", id);
						}else if(code==1) {
							intent = new Intent(getBaseContext(), A04_00_ChattingRoom.class);
						}
						startActivity(intent);
						finish();
					} else {
						Intent intent = new Intent(getBaseContext(), A02_00_Friend.class);
						startActivity(intent);
						finish();
					}
				}
			}
		};

		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		if (tm.getLine1Number() == null) {
			edit.putString("myID", "00000000000" + tm.getDeviceId());
			edit.putString("myPhone", "00000000000");
		} else {
			edit.putString("myPhone", tm.getLine1Number());
			edit.putString("myID", tm.getLine1Number() + tm.getDeviceId());
		}
		edit.commit();

		try {
			text_stat.setText("Connection Socket...");

			if (StaticClass.DataSoc == null || !StaticClass.DataSoc.isConnected() || StaticClass.DataSoc.isClosed()) {
				StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);
			}
			if (StaticClass.dbm instanceof DBManager) {
				StaticClass.dbm = null;
			}
			StaticClass.dbm = new DBManager(context);

			text_stat.setText("Thread Start...");
			if (StaticClass.thread instanceof T00_00_ReceiveThread)
				StaticClass.thread.threadflag = false;
			StaticClass.thread = new T00_00_ReceiveThread(context);
			StaticClass.thread.setDaemon(true);
			StaticClass.thread.start();

			text_stat.setText("Server Login...");
			String send = "BEGIN LOGIN\r\n";
			send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
			send += "RegID:" + sp.getString("myRegID", "") + "\r\n";
			send += "END\r\n";
			StaticClass.DataSoc.sendMessage(send);
		} catch (IOException e) {
			StaticClass.DataSoc = null;
		} catch (Exception e) {
			Log.e("error", e.getMessage() + "");

		}

	}
}
