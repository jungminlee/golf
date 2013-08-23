package com.ilovegolf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ilovegolf.R.drawable;
import com.ilovegolf.adapter.A02_00_FriendAdapter;
import com.ilovegolf.layout.L02_01_FriendInfoLayout;
import com.ilovegolf.struct.ChatRoom;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;
import com.ilovegolf.util.TabActivity;

public class A02_00_Friend extends TabActivity {

	ListView list_friend = null;
	// ArrayList<Friend> friendList = null;
	A02_00_FriendAdapter friendAdapter = null;

	RelativeLayout layout_Friend = null;
	L02_01_FriendInfoLayout layout_FriendInfo = null;

	SharedPreferences sp = null;
	Editor edit = null;

	long update = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		sp = getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();

		bFriend = false;
		bFindLink = true;
		bTalkRoomList = true;
		bSetting = true;

		tab_Friend.setBackgroundResource(drawable.main_topbar_menu_01_on);
		layout_Friend = layout.b00_00_TabLayout;

		LinearLayout relative_content = layout.b00_00_linear_content;
		LinearLayout linear = layout.linear;
		linear.removeView(relative_content);

		StaticClass.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.arg1 == StaticClass.REQUEST) {
					dismiss();
					StaticClass.alert = new AlertDialog.Builder(context).create();
					StaticClass.alert.setTitle("아이러브골프");
					StaticClass.alert.setMessage("골프 요청 완료 하였습니다.");
					StaticClass.alert.setButton(AlertDialog.BUTTON_NEUTRAL, "확인", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					StaticClass.alert.show();
				} else if (msg.arg1 == StaticClass.REFRESH) {
					// dismiss();
					synchronized (StaticClass.dbm) {
						StaticClass.friendList.clear();
						Friend friend = new Friend();
						friend.strName = "#title#1";
						StaticClass.friendList.add(friend);
						StaticClass.dbm.selectFavoriteFriendDB(1, StaticClass.friendList);
						friend = new Friend();
						friend.strName = "#title#2";
						StaticClass.friendList.add(friend);
						System.out.println("::::::::::::::::::::::" + StaticClass.other_address_si + StaticClass.other_address_gu + StaticClass.other_address_dong);

						StaticClass.dbm.selectNearFriendDB(StaticClass.friendList, StaticClass.other_address_si, StaticClass.other_address_gu, StaticClass.other_address_dong);

						friendAdapter.notifyDataSetChanged();
					}
				}
			}
		};

		// StaticClass.friendList = new ArrayList<Friend>();
		list_friend = layout.b00_00_list_content;
		friendAdapter = new A02_00_FriendAdapter(context, display, StaticClass.friendList, sp);
		list_friend.setAdapter(friendAdapter);
		list_friend.setDrawSelectorOnTop(true);
		list_friend.setSelector(R.drawable.friend_selector);

		try {
			long time = System.currentTimeMillis();
			if (time - update > 300000) {
				update = time;
				if (StaticClass.DataSoc == null || !StaticClass.DataSoc.isConnected() || StaticClass.DataSoc.isClosed()) {
					StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);
				}

				String send = "BEGIN SELECTMEMBER\r\n";
				send += "My_ID:" + sp.getString("myID", "") + "\r\n";

				if (StaticClass.friendmyarea) {
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
		list_friend.setOnItemClickListener(new OnItemClickListener() {
			Friend friend = null;
			View btn_favorite = null;

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int id, long arg3) {
				friend = StaticClass.friendList.get(id);
				if (friend.strName.indexOf("#title#") == -1) {
					layout_FriendInfo = new L02_01_FriendInfoLayout();
					layout_Friend.addView(layout_FriendInfo.getLayout(context, display), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

					String str = "";
					if (friend.strSex.equals("M"))
						str += "(남)\n";
					else
						str += "(여)\n";

					TextView text_name = layout_FriendInfo.l02_01_text_name;
					text_name.setText(friend.strName + str);

					str = "";
					if (friend.strGrade.equals("A1"))
						str += "\n초급";
					else if (friend.strGrade.equals("B1"))
						str += "\n중급";
					else if (friend.strGrade.equals("C1"))
						str += "\n고급";
					else if (friend.strGrade.equals("D1"))
						str += "\n프로";
					TextView text_address = layout_FriendInfo.l02_01_text_address;

					text_address.setText(friend.strAddressSi + " " + friend.strAddressGu + " " + friend.strAddressDong + str);

					TextView text_message = layout_FriendInfo.l02_01_text_msg;

					layout_FriendInfo.relative.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
						}
					});

					if (friend.strMessage.equals("null"))
						text_message.setText(" ");
					else
						text_message.setText(friend.strMessage);

					ImageView img_pic = layout_FriendInfo.l02_01_img_pic;

					System.out.println("friend.strImage::::::"+friend.strImage);
					if (friend.strImage!=null && !friend.strImage.equals(" ") && !friend.strImage.equals("") && !friend.strImage.equals("null"))
						img_pic.setImageBitmap(BitmapFactory.decodeFile("/mnt/sdcard/golfpic/" + friend.strID));
					else 
						img_pic.setImageResource(R.drawable.profile_img);
					View btn_close = layout_FriendInfo.l02_01_btn_close;
					btn_favorite = layout_FriendInfo.l02_01_btn_favorite;
					View btn_sendMessage = layout_FriendInfo.l02_01_btn_sendMessage;

					LinearLayout layout_temp = layout_FriendInfo.l02_01_layout_temp;
					layout_temp.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							layout_Friend.removeViewAt(layout_Friend.getChildCount() - 1);
						}
					});

					btn_close.setOnTouchListener(new OnTouchListener() {
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
								if (friend.iIsFavorite == 0)
									v.setBackgroundResource(R.drawable.btn_profile_favorite);
								else if (friend.iIsFavorite == 1)
									v.setBackgroundResource(R.drawable.btn_profile_favorite_off);
							} else if (event.getAction() == MotionEvent.ACTION_UP) {
								if (friend.iIsFavorite == 1)
									v.setBackgroundResource(R.drawable.btn_profile_favorite);
								else if (friend.iIsFavorite == 0)
									v.setBackgroundResource(R.drawable.btn_profile_favorite_off);
							}
							return false;
						}
					});

					img_pic.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							Intent intent = new Intent(getBaseContext(), A02_01_DownPicturePreview.class);
							intent.putExtra("img", friend.strImage);
							intent.putExtra("file", friend.strID);
							startActivity(intent);
						}
					});

					btn_close.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							layout_Friend.removeViewAt(layout_Friend.getChildCount() - 1);
						}
					});

					btn_sendMessage.setOnTouchListener(new OnTouchListener() {
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							if (event.getAction() == MotionEvent.ACTION_DOWN) {
								v.setBackgroundResource(R.drawable.btn_call_you_on);
							}

							if (event.getAction() == MotionEvent.ACTION_UP) {
								v.setBackgroundResource(R.drawable.btn_call_you_off);
							}
							return false;
						}
					});
					btn_sendMessage.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							long today = Long.parseLong(StaticClass.format2.format(new Date()));
							if (sp.getInt("myIsAcount", 0) == 1) {
								if (!StaticClass.dbm.selectChatRoomDB(friend.strID)) {
									StaticClass.alert = new AlertDialog.Builder(context).create();
									StaticClass.alert.setTitle("아이러브골프");
									StaticClass.alert.setMessage(friend.strName + "님께 골프친구 요청 하시겠습니까?");
									StaticClass.alert.setButton(AlertDialog.BUTTON_NEGATIVE, "취소", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
										}
									});

									StaticClass.alert.setButton(AlertDialog.BUTTON_POSITIVE, "확인", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
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

												if (friend.iIsFlag == 0) {
													send += "BEGIN SENDREQUEST\r\n";
													send += "Sender_ID:" + sp.getString("myID", "") + "\r\n";
													send += "Recver_ID:" + friend.strID + "\r\n";
													send += "END\r\n";

													friend.iIsFlag = 1;
													StaticClass.dbm.updateFriendDB(friend);
												}

												StaticClass.DataSoc.sendMessage(send);

												prd("잠시만 기댜려 주세요.");

												ChatRoom chatroom = new ChatRoom();
												chatroom.strRoomID = friend.strID;
												chatroom.strRoomName = friend.strName;
												chatroom.strUpdateDate=StaticClass.format.format(new Date()).replace("^", ":");
												
												if (!StaticClass.dbm.selectChatRoomDB(friend.strID)) {
													StaticClass.dbm.insertChatRoomDB(chatroom);
												}
											} catch (IOException e) {
												StaticClass.DataSoc = null;
											} catch (Exception e) {
												Log.e("error", e.getMessage() + "");

											}
										}
									});

									StaticClass.alert.show();
								} else if (friend.iIsFlag == 3) {
									ChatRoom chatroom = new ChatRoom();
									chatroom.strRoomID = friend.strID;
									chatroom.strRoomName = friend.strName;
									if(StaticClass.dbm.selectChatRoomDB(friend.strID)) {
										Intent intent = new Intent(context.getApplicationContext(), A04_00_RecvMessageList.class);
										intent.putExtra("id", chatroom.strRoomID);
										context.startActivity(intent);
										finish();
									}else {
										StaticClass.dbm.insertChatRoomDB(chatroom);
									}
								} else {
									StaticClass.alert = new AlertDialog.Builder(context).create();
									StaticClass.alert.setTitle("아이러브골프");
									StaticClass.alert.setMessage("이미 요청 하셨습니다.");
									StaticClass.alert.setButton(AlertDialog.BUTTON_NEUTRAL, "확인", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
										}
									});
									StaticClass.alert.show();
								}
							} else {

								StaticClass.alert = new AlertDialog.Builder(context).create();
								StaticClass.alert.setTitle("아이러브골프");
								StaticClass.alert.setMessage("결제 후 이용 가능한 서비스 입니다.\n결제하시겠습니까?");
								StaticClass.alert.setButton(AlertDialog.BUTTON_NEGATIVE, "취소", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
									}

								});

								StaticClass.alert.setButton(AlertDialog.BUTTON_POSITIVE, "확인", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										Intent intent = new Intent(getBaseContext(), A05_04_Payment.class);
										startActivity(intent);
										finish();
									}
								});

								StaticClass.alert.show();
							}
						}
					});

					if (friend.iIsFavorite == 0)
						btn_favorite.setBackgroundResource(R.drawable.btn_profile_favorite_off);

					btn_favorite.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (friend.iIsFavorite == 1) {
								friend.iIsFavorite = 0;
								btn_favorite.setBackgroundResource(R.drawable.btn_profile_favorite_off);
							} else {
								friend.iIsFavorite = 1;
								btn_favorite.setBackgroundResource(R.drawable.btn_profile_favorite);
							}
							synchronized (StaticClass.dbm) {
								StaticClass.dbm.updateFriendDB(friend);
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
			if (layout_Friend.getChildCount() > 1) {
				layout_Friend.removeViewAt(layout_Friend.getChildCount() - 1);
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
