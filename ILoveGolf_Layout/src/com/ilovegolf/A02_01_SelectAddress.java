package com.ilovegolf;

import java.io.IOException;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ilovegolf.layout.L05_01_03_AddressList;
import com.ilovegolf.layout.L05_01_03_ModifyAddressLayout;
import com.ilovegolf.struct.InputAddress;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A02_01_SelectAddress extends BaseActivity {
	L05_01_03_ModifyAddressLayout layout = null;
	L05_01_03_AddressList layout_addresslist = null;

	View btn_ok = null;

	TextView combo_si = null;
	TextView combo_gu = null;
	TextView combo_dong = null;
	ArrayAdapter<String> silistAdapter = null;
	ArrayAdapter<String> gulistAdapter = null;
	ArrayAdapter<String> donglistAdapter = null;

	long update = 0;

	String id_si;
	String id_gu;
	String id_dong;

	SharedPreferences sp = null;
	Editor edit = null;

	ArrayList<String> si_list;
	ArrayList<String> gu_list;
	ArrayList<String> dong_list;

	boolean friend = true;

	ListView list = null;

	RelativeLayout layout_address = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new L05_01_03_ModifyAddressLayout();
		setContentView(layout.getLayout(context, display));

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		Intent intent = getIntent();
		friend = intent.getBooleanExtra("friend", true);

		sp = getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();

		layout_address = layout.b00_01_barlayout;

		layout.b00_01_text_bar.setBackgroundResource(R.drawable.golf_other_map_bar);
		btn_ok = layout.l05_01_03_btn_ok;

		combo_si = layout.l05_01_03_combo_si;
		combo_gu = layout.l05_01_03_combo_gu;
		combo_dong = layout.l05_01_03_combo_dong;

		id_si = StaticClass.other_address_si;
		id_gu = StaticClass.other_address_gu;
		id_dong = StaticClass.other_address_dong;

		combo_si.setText(id_si);
		combo_gu.setText(id_gu);
		combo_dong.setText(id_dong);

		si_list = new ArrayList<String>();
		gu_list = new ArrayList<String>();
		dong_list = new ArrayList<String>();

		StaticClass.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.arg1 == StaticClass.ADDRESS_GU) {
					dismiss();
					gu_list.clear();
					InputAddress add = new InputAddress();
					add.strAddress_si = id_si;
					StaticClass.dbm.selectAddressDB(gu_list, 2, add);
					int size = gu_list.size();
					String siArray[] = new String[size];
					for (int i = 0; i < size; i++) {
						siArray[i] = gu_list.get(i);
					}
					new AlertDialog.Builder(context).setItems(siArray, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							try {
								id_dong = "";
								combo_dong.setText("");
								id_gu = gu_list.get(which);
								combo_gu.setText(id_gu);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}).show();
				} else if (msg.arg1 == StaticClass.ADDRESS_DONG) {
					dismiss();
					dong_list.clear();
					InputAddress add = new InputAddress();
					add.strAddress_si = id_si;
					add.strAddress_gu = id_gu;
					StaticClass.dbm.selectAddressDB(dong_list, 3, add);
					int size = dong_list.size();
					String siArray[] = new String[size];
					for (int i = 0; i < size; i++) {
						siArray[i] = dong_list.get(i);
					}
					new AlertDialog.Builder(context).setItems(siArray, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							try {
								id_dong = dong_list.get(which);
								combo_dong.setText(id_dong);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}).show();
				}
			}
		};
		combo_si.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				StaticClass.dbm.selectAddressDB(si_list, 1, null);
				int size = si_list.size();
				String siArray[] = new String[size];
				for (int i = 0; i < size; i++) {
					siArray[i] = si_list.get(i);
				}
				new AlertDialog.Builder(context).setItems(siArray, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						id_gu = "";
						combo_gu.setText("");
						id_dong = "";
						combo_dong.setText("");
						try {
							id_si = si_list.get(which);
							System.out.println(">AS>D>GAWGWA?AWF?AWF" + id_si);
							combo_si.setText(id_si);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).show();
			}
		});

		combo_gu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (id_si != null && id_si != "") {
					InputAddress add = new InputAddress();
					add.strAddress_si = id_si;
					if (!StaticClass.dbm.selectAddressDB(2, add)) {
						try {
							if (!StaticClass.DataSoc.isConnected())
								StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

							String send = "BEGIN ADDRESSGU\r\n";
							send += "Address_si:" + id_si + "\r\n";
							send += "END\r\n";
							StaticClass.DataSoc.sendMessage(send);

							prd("잠시만 기다려 주세요.");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							Log.e("error", e.getMessage() + "");
						}
					} else {
						Message hanMessage = new Message();
						hanMessage.arg1 = StaticClass.ADDRESS_GU;
						StaticClass.handler.sendMessage(hanMessage);
					}
				}
			}
		});

		combo_dong.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (id_gu != null && id_gu != "") {
					InputAddress add = new InputAddress();
					add.strAddress_si = id_si;
					add.strAddress_gu = id_gu;
					if (!StaticClass.dbm.selectAddressDB(3, add)) {
						System.out.println(":+:+:+:+:+:+:요청");
						try {
							if (!StaticClass.DataSoc.isConnected())
								StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

							String send = "BEGIN ADDRESSDONG\r\n";
							send += "Address_si:" + id_si + "\r\n";
							send += "Address_gu:" + id_gu + "\r\n";
							send += "END\r\n";
							StaticClass.DataSoc.sendMessage(send);

							prd("잠시만 기다려 주세요.");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							Log.e("error", e.getMessage() + "");

						}
					} else {
						Message hanMessage = new Message();
						hanMessage.arg1 = StaticClass.ADDRESS_DONG;
						StaticClass.handler.sendMessage(hanMessage);
					}
				}
			}
		});

		btn_ok.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.btn_ok_on);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.btn_ok_off);
				}
				return false;
			}
		});

		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ((id_si != null && id_gu != null && id_dong != null) && (id_si != "" && id_gu != "" && id_dong != "")) {
					edit.putString("id_si", id_si);
					edit.putString("id_gu", id_gu);
					edit.putString("id_dong", id_dong);
					edit.commit();
					if (friend) {
						StaticClass.friendmyarea = false;
					} else
						StaticClass.linkmyarea = false;

					StaticClass.other_address_si = id_si;
					StaticClass.other_address_gu = id_gu;
					StaticClass.other_address_dong = id_dong;

					StaticClass.refresh = true;

					if (friend) {
						Intent intent = new Intent(getBaseContext(), A02_00_Friend.class);
						startActivity(intent);
					} else {
						Intent intent = new Intent(getBaseContext(), A03_00_FindLink.class);
						startActivity(intent);
					}
					overridePendingTransition(0, 0);
					finish();
				}else {
					StaticClass.alert = new AlertDialog.Builder(context).create();
					StaticClass.alert.setTitle("아이러브골프");
					StaticClass.alert.setMessage("시, 동, 구를 모두 선택해야 합니다.");
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
			if (layout_address.getChildCount() > 1) {
				layout_address.removeViewAt(layout_address.getChildCount() - 1);
			} else {
				if (friend) {
					Intent intent = new Intent(getBaseContext(), A02_00_Friend.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(getBaseContext(), A03_00_FindLink.class);
					startActivity(intent);
				}
				overridePendingTransition(0, 0);
				finish();
			}
		}
		return true;
	}
}
