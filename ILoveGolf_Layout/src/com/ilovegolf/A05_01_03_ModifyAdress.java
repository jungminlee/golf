package com.ilovegolf;

import java.io.IOException;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;
import com.ilovegolf.layout.L05_01_03_AddressList;
import com.ilovegolf.layout.L05_01_03_ModifyAddressLayout;
import com.ilovegolf.struct.InputAddress;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A05_01_03_ModifyAdress extends BaseActivity {
	L05_01_03_ModifyAddressLayout layout = null;
	L05_01_03_AddressList layout_addresslist = null;
	View btn_ok = null;

	TextView combo_si = null;
	TextView combo_gu = null;
	TextView combo_dong = null;
	ArrayAdapter<String> silistAdapter = null;
	ArrayAdapter<String> gulistAdapter = null;
	ArrayAdapter<String> donglistAdapter = null;

	ArrayList<String> si_list = null;
	ArrayList<String> gu_list = null;
	ArrayList<String> dong_list = null;

	String tempAddress_si = "";
	String tempAddress_gu = "";
	String tempAddress_dong = "";

	SharedPreferences sp = null;
	Editor edit = null;

	String regID = "";

	ListView list = null;

	RelativeLayout layout_address = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new L05_01_03_ModifyAddressLayout();
		setContentView(layout.getLayout(context, display));

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		regID = GCMRegistrar.getRegistrationId(this);
		sp = getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();

		layout_address = layout.b00_01_barlayout;

		StaticClass.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {

			}
		};

		if (StaticClass.isjoin) {
			layout.b00_01_text_bar.setBackgroundResource(R.drawable.golf_join_bar);
		}

		btn_ok = layout.l05_01_03_btn_ok;

		combo_si = layout.l05_01_03_combo_si;
		combo_gu = layout.l05_01_03_combo_gu;
		combo_dong = layout.l05_01_03_combo_dong;

		tempAddress_si = sp.getString("myAddress_si", "");
		tempAddress_gu = sp.getString("myAddress_gu", "");
		tempAddress_dong = sp.getString("myAddress_dong", "");
		
		combo_si.setText(tempAddress_si);
		combo_gu.setText(tempAddress_gu);
		combo_dong.setText(tempAddress_dong);

		gu_list = new ArrayList<String>();
		dong_list = new ArrayList<String>();

		si_list = new ArrayList<String>();

		StaticClass.handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.arg1 == 2) {
					StaticClass.isjoin = false;
					dismiss();
					Intent intent = new Intent(getBaseContext(), A00_00_Loading.class);
					startActivity(intent);
					finish();
				}
				if (msg.arg1 == StaticClass.ADDRESS_GU) {
					dismiss();
					gu_list.clear();
					InputAddress add = new InputAddress();
					add.strAddress_si = tempAddress_si;
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
								tempAddress_dong = "";
								combo_dong.setText("");
								tempAddress_gu = gu_list.get(which);
								combo_gu.setText(tempAddress_gu);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}).show();
				} else if (msg.arg1 == StaticClass.ADDRESS_DONG) {
					dismiss();
					dong_list.clear();
					InputAddress add = new InputAddress();
					add.strAddress_si = tempAddress_si;
					add.strAddress_gu = tempAddress_gu;
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
								tempAddress_dong = dong_list.get(which);
								combo_dong.setText(tempAddress_dong);
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
						tempAddress_gu = "";
						combo_gu.setText("");
						tempAddress_dong = "";
						combo_dong.setText("");
						try {
							tempAddress_si = si_list.get(which);
							System.out.println(">AS>D>GAWGWA?AWF?AWF" + tempAddress_si);
							combo_si.setText(tempAddress_si);
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
				if(tempAddress_si!=null && tempAddress_si!="")  {
					InputAddress add = new InputAddress();
					add.strAddress_si = tempAddress_si;
					if (!StaticClass.dbm.selectAddressDB(2, add)) {
						try {
							if (!StaticClass.DataSoc.isConnected())
								StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

							String send = "BEGIN ADDRESSGU\r\n";
							send += "Address_si:" + tempAddress_si + "\r\n";
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
				if(tempAddress_gu!=null && tempAddress_gu!="") {
					InputAddress add = new InputAddress();
					add.strAddress_si = tempAddress_si;
					add.strAddress_gu = tempAddress_gu;
					if (!StaticClass.dbm.selectAddressDB(3, add)) {
						System.out.println(":+:+:+:+:+:+:요청");
						try {
							if (!StaticClass.DataSoc.isConnected())
								StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

							String send = "BEGIN ADDRESSDONG\r\n";
							send += "Address_si:" + tempAddress_si + "\r\n";
							send += "Address_gu:" + tempAddress_gu + "\r\n";
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
				if ((tempAddress_si != null && tempAddress_gu != null && tempAddress_dong != null) && (tempAddress_si != "" && tempAddress_gu != "" && tempAddress_dong != "")) {
					edit.putString("myAddress_si", tempAddress_si);
					edit.putString("myAddress_gu", tempAddress_gu);
					edit.putString("myAddress_dong", tempAddress_dong);
					edit.commit();

					if (!StaticClass.isjoin) {
						try {
							if (!StaticClass.DataSoc.isConnected())
								StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

							String send = "BEGIN UPDATEMEMBERADDRESS\r\n";
							send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
							send += "Member_Address_si:" + sp.getString("myAddress_si", "") + "\r\n";
							send += "Member_Address_gu:" + sp.getString("myAddress_gu", "") + "\r\n";
							send += "Member_Address_dong:" + sp.getString("myAddress_dong", "") + "\r\n";
							send += "END\r\n";
							StaticClass.DataSoc.sendMessage(send);

							Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
							startActivity(intent);
							overridePendingTransition(0, 0);
							finish();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							Log.e("error", e.getMessage() + "");

						}

					} else {
						try {
							if (!StaticClass.DataSoc.isConnected())
								StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

							String send = "BEGIN INSERTMEMBER\r\n";
							send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
							send += "Member_Name:" + sp.getString("myName", "") + "\r\n";
							send += "Member_Phone:" + sp.getString("myPhone", "") + "\r\n";
							send += "Member_Address_si:" + sp.getString("myAddress_si", "") + "\r\n";
							send += "Member_Address_gu:" + sp.getString("myAddress_gu", "") + "\r\n";
							send += "Member_Address_dong:" + sp.getString("myAddress_dong", "") + "\r\n";
							send += "RegID:" + regID + "\r\n";
							send += "END\r\n";
							StaticClass.DataSoc.sendMessage(send);

							prd("잠시만 기다려 주세요.");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							Log.e("error", e.getMessage() + "");

						}

					}
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			if (layout_address.getChildCount() > 1) {
				layout_address.removeViewAt(layout_address.getChildCount() - 1);
			} else {
				if (!StaticClass.isjoin) {
					Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
					startActivity(intent);
					overridePendingTransition(0, 0);
					finish();
				} else {
					Intent intent = new Intent(getBaseContext(), A05_01_01_ModifyName.class);
					startActivity(intent);
					overridePendingTransition(0, 0);
					finish();
				}
			}
		}
		return true;
	}
}
