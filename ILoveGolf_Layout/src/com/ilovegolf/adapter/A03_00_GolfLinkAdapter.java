package com.ilovegolf.adapter;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ilovegolf.A02_01_SelectAddress;
import com.ilovegolf.R;
import com.ilovegolf.layout.I03_00_FavoriteTitleItem;
import com.ilovegolf.layout.I03_00_LinkItem;
import com.ilovegolf.layout.I03_00_NeighborTitleItem;
import com.ilovegolf.struct.Link;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A03_00_GolfLinkAdapter extends BaseAdapter {
	ArrayList<Link> linkList = null;

	Context context;
	Display display;

	TextView text_name;
	TextView text_title;

	SharedPreferences sp = null;

	long update = 0;

	public A03_00_GolfLinkAdapter(Context context, Display display, ArrayList<Link> linkList, SharedPreferences sp) {
		this.context = context;
		this.display = display;
		this.linkList = linkList;
		this.sp = sp;
	}

	@Override
	public int getCount() {
		return linkList.size();
	}

	@Override
	public Object getItem(int id) {
		return linkList.get(id);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int id, View convertView, ViewGroup parent) {
		Link link = linkList.get(id);
		Log.e("name:", link.strName);
		if (link.strName.indexOf("#title#1") != -1) {
			I03_00_FavoriteTitleItem layout = new I03_00_FavoriteTitleItem();
			convertView = layout.getLayout(context, display);

		} else if (link.strName.indexOf("#title#2") != -1) {
			I03_00_NeighborTitleItem layout = new I03_00_NeighborTitleItem();
			convertView = layout.getLayout(context, display);

			final View btn_myarea = layout.i03_00_btn_myarea;
			final View btn_other = layout.i03_00_btn_otherarea;

			if (StaticClass.linkmyarea) {
				btn_myarea.setBackgroundResource(R.drawable.btn_my_area);
				btn_other.setBackgroundResource(R.drawable.btn_other_area);
			} else {
				btn_myarea.setBackgroundResource(R.drawable.btn_my_area_off);
				btn_other.setBackgroundResource(R.drawable.btn_other_area_on);
			}

			btn_myarea.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					btn_myarea.setBackgroundResource(R.drawable.btn_my_area);
					btn_other.setBackgroundResource(R.drawable.btn_other_area);
					StaticClass.linkmyarea = true;

					StaticClass.other_address_si = sp.getString("myAddress_si", "");
					StaticClass.other_address_gu = sp.getString("myAddress_gu", "");
					StaticClass.other_address_dong = sp.getString("myAddress_dong", "");
					
					Message hanMessage = new Message();
					hanMessage.arg1 = StaticClass.REFRESH;
					StaticClass.handler.sendMessage(hanMessage);
				}
			});

			btn_other.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					btn_myarea.setBackgroundResource(R.drawable.btn_my_area_off);
					btn_other.setBackgroundResource(R.drawable.btn_other_area_on);

					Intent intent = new Intent(context.getApplicationContext(), A02_01_SelectAddress.class);
					intent.putExtra("friend", false);
					context.startActivity(intent);
					((Activity) context).finish();
				}
			});

			btn_myarea.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (!StaticClass.linkmyarea) {
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							btn_myarea.setBackgroundResource(R.drawable.btn_my_area);
						}

						if (event.getAction() == MotionEvent.ACTION_CANCEL) {
							btn_myarea.setBackgroundResource(R.drawable.btn_my_area_off);
						}
					}
					return false;
				}
			});

			btn_other.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (StaticClass.linkmyarea) {
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							btn_other.setBackgroundResource(R.drawable.btn_other_area_on);
						}

						if (event.getAction() == MotionEvent.ACTION_CANCEL) {
							btn_other.setBackgroundResource(R.drawable.btn_other_area);
						}
					}
					return false;
				}
			});

		} else {
			I03_00_LinkItem layout = new I03_00_LinkItem();
			convertView = layout.getLayout(context, display);
			
			convertView.setTag(layout.line);

			TextView text_name = layout.i03_00_text_name;
			TextView text_address = layout.i03_00_text_address;

			text_name.setText(link.strName);
			String[] address=link.strAddress_text.split("µ¿ ");
			text_address.setText(address[0]+"µ¿");

			if (link.favorite) {
				layout.line.setBackgroundResource(R.drawable.golf_position_fav_list);
			}

		}
		return convertView;
	}

}
