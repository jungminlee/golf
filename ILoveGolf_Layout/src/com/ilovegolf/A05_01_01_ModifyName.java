package com.ilovegolf;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Paint.Join;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.TextView;

import com.ilovegolf.layout.L05_01_01_ModifyLayout;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A05_01_01_ModifyName extends BaseActivity {
	L05_01_01_ModifyLayout layout = null;
	EditText edit_context = null;

	SharedPreferences sp = null;
	Editor edit = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new L05_01_01_ModifyLayout();
		setContentView(layout.getLayout(context, display));

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		sp = getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();

		if (StaticClass.isjoin) {
			layout.b00_01_text_bar.setBackgroundResource(R.drawable.golf_join_bar);
		}

		TextView text_subject = layout.l05_01_01_text_subject;
		text_subject.setText("�̸��� �Է��ϼ���.");

		edit_context = layout.l05_01_01_edit_context;
		edit_context.setText(sp.getString("myName", ""));

		View btn_remove = layout.l05_01_01_btn_remove;
		btn_remove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				edit_context.setText("");
			}
		});

		View btn_ok = layout.l05_01_01_btn_ok;
		
		btn_ok.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()==MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.btn_ok_on);
				}else if(event.getAction()==MotionEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.btn_ok_off);
				}
				return false;
			}
		});
		
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				edit.putString("myName", edit_context.getText().toString());
				edit.commit();

				if (!StaticClass.isjoin) {
					try {
						if (!StaticClass.DataSoc.isConnected())
							StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

						String send = "BEGIN UPDATEMEMBERNAME\r\n";
						send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
						send += "Member_Name:" + sp.getString("myName", "") + "\r\n";
						// send += "Member_Message:" + StaticClass.myMessage + "\r\n";
						// send += "Member_Address_si:" + StaticClass.myAddress_si + "\r\n";
						// send += "Member_Address_gu:" + StaticClass.myAddress_gu + "\r\n";
						// send += "Member_Address_dong:" + StaticClass.myAddress_dong + "\r\n";
						send += "END\r\n";
						StaticClass.DataSoc.sendMessage(send);

						Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
						startActivity(intent);
						finish();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						Log.e("error", e.getMessage() + "");

					}
				} else {
					Intent intent = new Intent(getBaseContext(), A05_01_03_ModifyAdress.class);
					startActivity(intent);
					overridePendingTransition(0, 0);
					finish();
				}

			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			if (!StaticClass.isjoin) {
				Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}else {
				StaticClass.alert = new AlertDialog.Builder(context).create();
				StaticClass.alert.setTitle("���̷������");
				StaticClass.alert.setMessage("���� �Ͻðڽ��ϱ�?");
				StaticClass.alert.setButton(AlertDialog.BUTTON_NEGATIVE, "���", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

				StaticClass.alert.setButton(AlertDialog.BUTTON_POSITIVE, "Ȯ��", new DialogInterface.OnClickListener() {
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
