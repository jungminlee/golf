package com.ilovegolf.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilovegolf.layout.I04_00_MessageItem;
import com.ilovegolf.layout.I04_00_MessageItem3;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.struct.RecvMessage;
import com.ilovegolf.util.StaticClass;

public class A04_00_RecvMessageAdapter extends BaseAdapter {
	ArrayList<RecvMessage> RecvMessageList = null;

	Context context;
	Display display;

	RecvMessage message = null;

	// send message iCode=1
	I04_00_MessageItem3 layout1 = null;

	// accept message iCode=3
	I04_00_MessageItem layout3 = null;

	SharedPreferences sp = null;

	public A04_00_RecvMessageAdapter(Context context, Display display, ArrayList<RecvMessage> RecvMessageList) {
		this.context = context;
		this.display = display;
		this.RecvMessageList = RecvMessageList;

		sp = context.getSharedPreferences("ilovegolfPrefer", 0);
	}

	@Override
	public int getCount() {
		return RecvMessageList.size();
	}

	@Override
	public Object getItem(int id) {
		return RecvMessageList.get(id);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int id, View convertView, ViewGroup parent) {
		message = RecvMessageList.get(id);
		if (message.iCode == 1) {
			layout1 = new I04_00_MessageItem3();
			convertView = layout1.getLayout(context, display);

			TextView text_date = layout1.i04_00_text_date;
			TextView text_msg = layout1.i04_00_text_message;

			text_date.setText(message.strDate);
			text_msg.setText(message.strContent);
		} else if (message.iCode == 3) {
			layout3 = new I04_00_MessageItem();
			Friend friend;
			synchronized (StaticClass.dbm) {
				friend = StaticClass.dbm.selectFriendDB(message.strPeopleID);
			}
			convertView = layout3.getLayout(context, display);

			ImageView img_pic = layout3.i04_00_img_pic;
			if (!friend.strImage.equals(""))
				img_pic.setImageBitmap(BitmapFactory.decodeFile("/mnt/sdcard/golfpic/" + message.strPeopleID));

			TextView text_name = layout3.i04_00_text_name;
			TextView text_date = layout3.i04_00_text_date;
			TextView text_msg = layout3.i04_00_text_message;

			text_name.setText(message.strPeopleName);
			String date=message.strDate.replace("^", ":");
			text_date.setText(date);
			text_msg.setText(message.strContent);

		}
		return convertView;
	}
}
