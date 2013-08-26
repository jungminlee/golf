package com.ilovegolf;

import java.io.IOException;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ilovegolf.R.drawable;
import com.ilovegolf.adapter.A03_00_GolfLinkAdapter;
import com.ilovegolf.layout.L03_01_LinkInfoLayout;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.struct.Link;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;
import com.ilovegolf.util.TabActivity;

public class A03_00_FindLink extends TabActivity {

	ListView list_link = null;
	// ArrayList<Link> linkList = null;
	A03_00_GolfLinkAdapter linkAdapter = null;

	RelativeLayout layout_findlink = null;
	L03_01_LinkInfoLayout layout_LinkInfo = null;

	SharedPreferences sp = null;

	long update = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		sp = getSharedPreferences("ilovegolfPrefer", 0);

		bFriend = true;
		bFindLink = false;
		bTalkRoomList = true;
		bSetting = true;

		tab_FindLink.setBackgroundResource(drawable.main_topbar_menu_02_on);
		layout_findlink = layout.b00_00_TabLayout;

		LinearLayout relative_content = layout.b00_00_linear_content;
		LinearLayout linear = layout.linear;
		linear.removeView(relative_content);

		StaticClass.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				dismiss();
				if (msg.arg1 == StaticClass.REFRESH) {
					StaticClass.linkList.clear();
					synchronized (StaticClass.dbm) {
						Link link = new Link();
						link.strName = "#title#1";
						StaticClass.linkList.add(link);
						StaticClass.dbm.selectLinkDB(1, StaticClass.linkList);
						link = new Link();
						link.strName = "#title#2";
						StaticClass.linkList.add(link);
						StaticClass.dbm.selectLinkDB(StaticClass.linkList, StaticClass.other_address_si, StaticClass.other_address_gu, StaticClass.other_address_dong);
					}
					linkAdapter.notifyDataSetChanged();

				}
			}
		};

		
		
		list_link = layout.b00_00_list_content;
		list_link.setDrawSelectorOnTop(true);
		list_link.setSelector(R.drawable.friend_selector);
		linkAdapter = new A03_00_GolfLinkAdapter(context, display, StaticClass.linkList, sp);
		list_link.setAdapter(linkAdapter);

		if(StaticClass.refresh) {
			StaticClass.linkList.clear();
			synchronized (StaticClass.dbm) {
				Link link = new Link();
				link.strName = "#title#1";
				StaticClass.linkList.add(link);
				StaticClass.dbm.selectLinkDB(1, StaticClass.linkList);
				link = new Link();
				link.strName = "#title#2";
				StaticClass.linkList.add(link);
				StaticClass.dbm.selectLinkDB(StaticClass.linkList, StaticClass.other_address_si, StaticClass.other_address_gu, StaticClass.other_address_dong);
			}
			linkAdapter.notifyDataSetChanged();
			StaticClass.refresh=false;
		}
		
		try {
			long time = System.currentTimeMillis();
			if (time - update > 300000) {
				update = time;
				if (StaticClass.DataSoc == null || !StaticClass.DataSoc.isConnected() || StaticClass.DataSoc.isClosed()) {
					StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);
				}

				String send = "BEGIN SELECTLINK\r\n";
				send += "My_ID:" + sp.getString("myID", "") + "\r\n";

				if (StaticClass.linkmyarea) {
					StaticClass.other_address_si = sp.getString("myAddress_si", "");
					StaticClass.other_address_gu = sp.getString("myAddress_gu", "");
					StaticClass.other_address_dong = sp.getString("myAddress_dong", "");
					send += "Member_Address_si:" + StaticClass.other_address_si + "\r\n";
					send += "Member_Address_gu:" + StaticClass.other_address_gu + "\r\n";
					send += "Member_Address_dong:" + StaticClass.other_address_dong + "\r\n";
				} else {
					send += "Member_Address_si:" + StaticClass.other_address_si + "\r\n";
					send += "Member_Address_gu:" + StaticClass.other_address_gu + "\r\n";
					send += "Member_Address_dong:" + StaticClass.other_address_dong + "\r\n";
				}

				send += "END\r\n";
				StaticClass.DataSoc.sendMessage(send);

				update = System.currentTimeMillis();

			} else {
				StaticClass.hanmessage = new Message();
				StaticClass.hanmessage.arg1 = StaticClass.REFRESH;
				StaticClass.handler.sendMessage(StaticClass.hanmessage);
			}
		} catch (IOException e) {
			StaticClass.DataSoc = null;
		} catch (Exception e) {
			Log.e("error", e.getMessage() + "");

		}

		list_link.setOnItemClickListener(new OnItemClickListener() {
			Link link = null;
			View btn_favorite = null;

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int id, long arg3) {
				link = StaticClass.linkList.get(id);
				if (link.strName.indexOf("#title#") == -1) {

					layout_LinkInfo = new L03_01_LinkInfoLayout();
					layout_findlink.addView(layout_LinkInfo.getLayout(context, display), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

					
					TextView text_linkinfo_name = layout_LinkInfo.l03_01_text_name;
					TextView text_linkinfo_msg = layout_LinkInfo.l03_01_text_msg;
					TextView text_linkinfo_address = layout_LinkInfo.l03_01_text_address;

					text_linkinfo_name.setText(link.strName);
					text_linkinfo_msg.setText("시스템 수 : "+link.strlink_system+" 주차가능 여부 : "+link.strlink_parking);
					text_linkinfo_address.setText(link.strAddress_text);

					btn_favorite = layout_LinkInfo.l03_01_btn_favorite;
					View btn_linkinfo_close = layout_LinkInfo.l03_01_btn_close;
					View btn_linkinfo_call = layout_LinkInfo.l03_01_btn_call;
					View btn_linkinfo_map = layout_LinkInfo.l03_01_btn_map;

					LinearLayout layout_temp = layout_LinkInfo.l03_01_layout_temp;

					layout_LinkInfo.relative.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {

						}
					});

					layout_temp.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							layout_findlink.removeViewAt(layout_findlink.getChildCount() - 1);

						}
					});

					btn_linkinfo_close.setOnTouchListener(new OnTouchListener() {
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							if (event.getAction() == MotionEvent.ACTION_DOWN) {
								v.setBackgroundResource(R.drawable.btn_profile_close);
							} else if (event.getAction() == MotionEvent.ACTION_UP) {
								v.setBackgroundResource(R.drawable.btn_profile_close_off);
							}
							return false;
						}
					});

					btn_favorite.setOnTouchListener(new OnTouchListener() {
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							if (event.getAction() == MotionEvent.ACTION_DOWN) {
								if (link.iIsFavorite == 0)
									v.setBackgroundResource(R.drawable.btn_profile_favorite);
								else if (link.iIsFavorite == 1)
									v.setBackgroundResource(R.drawable.btn_profile_favorite_off);
							} else if (event.getAction() == MotionEvent.ACTION_UP) {
								if (link.iIsFavorite == 1)
									v.setBackgroundResource(R.drawable.btn_profile_favorite);
								else if (link.iIsFavorite == 0)
									v.setBackgroundResource(R.drawable.btn_profile_favorite_off);
							}
							return false;
						}
					});

					btn_linkinfo_call.setOnTouchListener(new OnTouchListener() {
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							if (event.getAction() == MotionEvent.ACTION_DOWN) {
								v.setBackgroundResource(R.drawable.btn_phone_on);
							}

							if (event.getAction() == MotionEvent.ACTION_UP) {
								v.setBackgroundResource(R.drawable.btn_phone_off);
							}
							return false;
						}
					});

					btn_linkinfo_map.setOnTouchListener(new OnTouchListener() {
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							if (event.getAction() == MotionEvent.ACTION_DOWN) {
								v.setBackgroundResource(R.drawable.btn_position_golf_on);
							}

							if (event.getAction() == MotionEvent.ACTION_UP) {
								v.setBackgroundResource(R.drawable.btn_position_golf_off);
							}
							return false;
						}
					});

					btn_linkinfo_close.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							v.setBackgroundResource(R.drawable.btn_profile_close);
							layout_findlink.removeViewAt(layout_findlink.getChildCount() - 1);
						}
					});

					btn_linkinfo_call.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(Intent.ACTION_CALL);
							intent.setData(Uri.parse("tel:" + link.strTel));
							context.startActivity(intent);
						}
					});

					btn_linkinfo_map.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(getBaseContext(), A03_01_MapView.class);
							intent.putExtra("x", link.strlink_x);
							intent.putExtra("y", link.strlink_y);
							startActivity(intent);
						}
					});

					if (link.iIsFavorite == 0)
						btn_favorite.setBackgroundResource(R.drawable.btn_profile_favorite_off);

					btn_favorite.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (link.iIsFavorite == 1) {
								link.iIsFavorite = 0;
								btn_favorite.setBackgroundResource(R.drawable.btn_profile_favorite_off);
							} else {
								link.iIsFavorite = 1;
								btn_favorite.setBackgroundResource(R.drawable.btn_profile_favorite);
							}
							synchronized (StaticClass.dbm) {
								StaticClass.dbm.updateLinkDB(link);

							}

							StaticClass.hanmessage = new Message();
							StaticClass.hanmessage.arg1 = StaticClass.REFRESH;
							StaticClass.handler.sendMessage(StaticClass.hanmessage);
						}
					});

				}
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			if (layout_findlink.getChildCount() > 1) {
				layout_findlink.removeViewAt(layout_findlink.getChildCount() - 1);

			} else {
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
		}
		return true;
	}
}
