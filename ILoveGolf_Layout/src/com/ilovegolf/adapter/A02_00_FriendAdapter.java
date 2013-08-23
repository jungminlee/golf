package com.ilovegolf.adapter;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilovegolf.A02_01_SelectAddress;
import com.ilovegolf.R;
import com.ilovegolf.T00_01_ImageRecvThread;
import com.ilovegolf.layout.I02_00_FavoriteTitleItem;
import com.ilovegolf.layout.I02_00_NeighborTitleItem;
import com.ilovegolf.layout.I02_00_PeopleItem;
import com.ilovegolf.struct.DownImage;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.util.StaticClass;

public class A02_00_FriendAdapter extends BaseAdapter {
	ArrayList<Friend> friendList = null;
	Context context;
	Display display = null;
	public static ConcurrentHashMap<String, DownImage> imagedownlist = null;
	T00_01_ImageRecvThread thread = null;
	SharedPreferences sp = null;

	long update = 0;

	public A02_00_FriendAdapter(Context context, Display display, ArrayList<Friend> friendList, SharedPreferences sp) {
		this.context = context;
		this.friendList = friendList;
		this.display = display;
		imagedownlist = new ConcurrentHashMap<String, DownImage>();
		this.sp = sp;
	}

	@Override
	public int getCount() {
		return friendList.size();
	}

	@Override
	public Object getItem(int id) {
		return friendList.get(id);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int id, View convertView, ViewGroup parent) {

		final Friend friend = friendList.get(id);
		if (friend.strName.indexOf("#title#1") != -1) {
			I02_00_FavoriteTitleItem layout = new I02_00_FavoriteTitleItem();
			convertView = layout.getLayout(context, display);
		} else if (friend.strName.indexOf("#title#2") != -1) {
			I02_00_NeighborTitleItem layout = new I02_00_NeighborTitleItem();
			convertView = layout.getLayout(context, display);

			final View btn_myarea = layout.i02_00_btn_myarea;
			final View btn_other = layout.i02_00_btn_otherarea;

			if (StaticClass.friendmyarea) {
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
					StaticClass.friendmyarea = true;

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
					Intent intent = new Intent(context.getApplicationContext(), A02_01_SelectAddress.class);
					intent.putExtra("friend", true);
					context.startActivity(intent);
					((Activity) context).finish();
				}
			});

			btn_myarea.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (!StaticClass.friendmyarea) {
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
					if (StaticClass.friendmyarea) {
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
			final I02_00_PeopleItem layout = new I02_00_PeopleItem();
			convertView = layout.getLayout(context, display);
			convertView.setTag(layout.line);
			ImageView img_pic = layout.i02_00_img_pic;

			String str = "";
			
			if (friend.strSex.equals("M"))
				str += "(남)\n";
			else
				str += "(여)\n";
			
			if (friend.strGrade.equals("A1"))
				str += "초급";
			else if (friend.strGrade.equals("B1"))
				str += "중급";
			else if (friend.strGrade.equals("C1"))
				str += "고급";
			else if (friend.strGrade.equals("D1"))
				str += "프로";


			TextView text_name = layout.i02_00_text_name;
			text_name.setText(friend.strName + str);

			TextView text_message = layout.i02_00_text_message;
			if (friend.strMessage.equals("null"))
				text_message.setText(" ");
			else
				text_message.setText(friend.strMessage);

			if (friend.favorite) {
				if (!friend.strMessage.equals("null"))
					text_message.setBackgroundResource(R.drawable.favorie_say);
				else
					text_message.setBackgroundColor(Color.alpha(0));
			} else {
				if (!friend.strMessage.equals("null"))
					text_message.setBackgroundResource(R.drawable.normal_say);
				else
					text_message.setBackgroundColor(Color.alpha(0));

				layout.line.setBackgroundResource(R.drawable.main_friend_list_bg);
			}

			boolean address = (StaticClass.other_address_si.equals(friend.strAddressSi) && StaticClass.other_address_gu.equals(friend.strAddressGu) && StaticClass.other_address_dong.equals(friend.strAddressDong));
			if (friend.favorite && address) {
				StaticClass.favoritelist.put(friend.strID, img_pic);
				if (friend.strImage.indexOf("*") != -1)
					friend.strImage = friend.strImage.substring(0, friend.strImage.length() - 1);
				else
					img_pic.setImageBitmap(BitmapFactory.decodeFile("/mnt/sdcard/golfpic/" + friend.strID));

			} else {
				System.out.println("싱크태스크~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				SyncTask task = new SyncTask(friend, img_pic, id);
				task.execute();
			}
		}
		return convertView;
	}

	public class SyncTask extends AsyncTask<Void, Void, Void> {
		Friend friend = null;
		ImageView img_pic = null;
		boolean bflag = false;
		boolean isimage = false;
		DownImage downimage = new DownImage();

		public SyncTask(Friend friend, ImageView img_pic, int id) {
			this.friend = friend;
			this.img_pic = img_pic;
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				System.out.println("~~~~~~~~~~~~~~~~~~image~~~~~~~~~" + friend.strImage);
				if (!friend.strImage.equals("null*") && friend.strImage != null && !friend.strImage.equals("*")) {
					if (friend.strImage.indexOf("*") != -1) {
						System.out.println("-------------------" + friend.strImage);
						friend.strImage = friend.strImage.substring(0, friend.strImage.length() - 1);
						synchronized (StaticClass.dbm) {
							StaticClass.dbm.updateFriendDB(friend);
						}
						downimage.strId = friend.strID;
						downimage.strImage = friend.strImage;
						downimage.bFlag = false;
						imagedownlist.put(friend.strID, downimage);
						System.out.println("put image 리스트에 넣었씀당");
						bflag = true;
					}
					isimage = true;
				}
				System.out.println("id+++++++++++++++++++" + friend.strID);
				if (!StaticClass.imagethreadisalive && bflag) {
					StaticClass.imagethreadisalive = true;
					thread = new T00_01_ImageRecvThread();
					thread.setDaemon(true);
					thread.start();
					System.out.println("imagerecv START!!!!!!!!!!!!");
				}

				System.out.println("bflag>>>>>>>>>>>>>>" + friend.strID + ">>>>>>>" + bflag);
				while (bflag) {
					if (imagedownlist.get(friend.strID).bFlag)
						break;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (NullPointerException e) {
				Log.e("error", e.getMessage() + "");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			System.out.println(friend.strImage + "A?DF?AS?GDASDGG");
			if (isimage && !friend.strImage.equals("") && friend.strImage != null && !friend.strImage.equals("null")) {
				if (bflag)
					imagedownlist.remove(friend.strID);
				if (StaticClass.favoritelist.containsKey(friend.strID)) {
					StaticClass.favoritelist.get(friend.strID).setImageBitmap(BitmapFactory.decodeFile("/mnt/sdcard/golfpic/" + friend.strID));
					StaticClass.favoritelist.remove(friend.strID);
				}
				img_pic.setImageBitmap(BitmapFactory.decodeFile("/mnt/sdcard/golfpic/" + friend.strID));
			} else {
				img_pic.setImageResource(R.drawable.list_profile_box);
			}
		}
	}
}
