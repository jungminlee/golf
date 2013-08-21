package com.ilovegolf;

import java.io.IOException;

import android.R.color;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilovegolf.layout.L05_01_04_ModifyLayout2;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A05_01_05_ModifyGrade extends BaseActivity {
	L05_01_04_ModifyLayout2 layout = null;

	SharedPreferences sp = null;
	Editor edit = null;

	String grade = "";

	View btn_g1 = null;
	View btn_g2 = null;
	View btn_g3 = null;
	View btn_g4 = null;
	View btn_ok = null;

	int viewHight;
	int viewWidth;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new L05_01_04_ModifyLayout2();
		setContentView(layout.getLayout(context, display));

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		viewHight = display.getHeight();
		viewWidth = display.getWidth();

		sp = getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();

		TextView text_sub = layout.l05_01_01_text_subject;
		text_sub.setText("등급을 선택하세요");

		LinearLayout linear = layout.linear3;

		int tempWidth = ((int) (((float) 64 * (float) viewWidth) / (float) 480));
		int tempHeight = ((int) (((float) 63 * (float) viewHight) / (float) 800));
		int paddingWidth = ((int) (((float) 25 * (float) viewWidth) / (float) 480));
		TextView text;

		LinearLayout temp = new LinearLayout(context);
		temp.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		temp.setOrientation(LinearLayout.VERTICAL);
		temp.setGravity(Gravity.CENTER_HORIZONTAL);
		linear.addView(temp);
		{
			btn_g1 = new View(context);
			btn_g1.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
			btn_g1.setBackgroundResource(R.drawable.btn_setup_bronze);
			temp.addView(btn_g1);

			text = new TextView(context);
			text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			text.setTextColor(Color.BLACK);
			text.setTextSize(13);
			text.setText("초급");
			temp.addView(text);
		}

		temp = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		params.setMargins(paddingWidth, 0, 0, 0);
		temp.setLayoutParams(params);
		temp.setOrientation(LinearLayout.VERTICAL);
		temp.setGravity(Gravity.CENTER_HORIZONTAL);
		linear.addView(temp);
		{
			btn_g2 = new View(context);
			btn_g2.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
			btn_g2.setBackgroundResource(R.drawable.btn_setup_silver);
			temp.addView(btn_g2);

			text = new TextView(context);
			text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			text.setTextColor(Color.BLACK);
			text.setTextSize(13);
			text.setText("중급");
			temp.addView(text);
		}

		temp = new LinearLayout(context);
		temp.setLayoutParams(params);
		temp.setOrientation(LinearLayout.VERTICAL);
		temp.setGravity(Gravity.CENTER_HORIZONTAL);
		linear.addView(temp);
		{
			btn_g3 = new View(context);
			btn_g3.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
			btn_g3.setBackgroundResource(R.drawable.btn_setup_gold);
			temp.addView(btn_g3);

			text = new TextView(context);
			text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			text.setTextColor(Color.BLACK);
			text.setTextSize(13);
			text.setText("고급");
			temp.addView(text);
		}

		temp = new LinearLayout(context);
		temp.setLayoutParams(params);
		temp.setOrientation(LinearLayout.VERTICAL);
		temp.setGravity(Gravity.CENTER_HORIZONTAL);
		linear.addView(temp);
		{
			btn_g4 = new View(context);
			btn_g4.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
			btn_g4.setBackgroundResource(R.drawable.btn_setup_pro);
			temp.addView(btn_g4);

			text = new TextView(context);
			text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			text.setTextColor(Color.BLACK);
			text.setTextSize(13);
			text.setText("프로");
			temp.addView(text);
		}

		if (sp.getString("myGrade", "").equals("A1")) {
			grade = "A1";
			btn_g1.setBackgroundResource(R.drawable.btn_setup_bronze_on);
		} else if (sp.getString("myGrade", "").equals("B1")) {
			grade = "B1";
			btn_g2.setBackgroundResource(R.drawable.btn_setup_silver_on);
		} else if (sp.getString("myGrade", "").equals("C1")) {
			grade = "C1";
			btn_g3.setBackgroundResource(R.drawable.btn_setup_gold_on);
		} else if (sp.getString("myGrade", "").equals("D1")) {
			grade = "D1";
			btn_g4.setBackgroundResource(R.drawable.btn_setup_pro_on);
		}

		btn_ok = layout.l05_01_01_btn_ok;

		btn_g1.setOnTouchListener(onTouch);
		btn_g2.setOnTouchListener(onTouch);
		btn_g3.setOnTouchListener(onTouch);
		btn_g4.setOnTouchListener(onTouch);
		btn_ok.setOnTouchListener(onTouch);

		btn_g1.setOnClickListener(onClick);
		btn_g2.setOnClickListener(onClick);
		btn_g3.setOnClickListener(onClick);
		btn_g4.setOnClickListener(onClick);
		btn_ok.setOnClickListener(onClick);
	}

	OnTouchListener onTouch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (v == btn_g1) {
					v.setBackgroundResource(R.drawable.btn_setup_bronze_on);
				} else if (v == btn_g2) {
					v.setBackgroundResource(R.drawable.btn_setup_silver_on);
				} else if (v == btn_g3) {
					v.setBackgroundResource(R.drawable.btn_setup_gold_on);
				} else if (v == btn_g4) {
					v.setBackgroundResource(R.drawable.btn_setup_pro_on);
				} else if (v == btn_ok) {
					v.setBackgroundResource(R.drawable.btn_ok_on);
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				if (v == btn_g1) {
					v.setBackgroundResource(R.drawable.btn_setup_bronze);
				} else if (v == btn_g2) {
					v.setBackgroundResource(R.drawable.btn_setup_silver);
				} else if (v == btn_g3) {
					v.setBackgroundResource(R.drawable.btn_setup_gold);
				} else if (v == btn_g4) {
					v.setBackgroundResource(R.drawable.btn_setup_pro);
				} else if (v == btn_ok) {
					v.setBackgroundResource(R.drawable.btn_ok_off);
				}
			}
			return false;
		}
	};

	OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == btn_g1) {
				grade = "A1";
				btn_g1.setBackgroundResource(R.drawable.btn_setup_bronze_on);
				btn_g2.setBackgroundResource(R.drawable.btn_setup_silver);
				btn_g3.setBackgroundResource(R.drawable.btn_setup_gold);
				btn_g4.setBackgroundResource(R.drawable.btn_setup_pro);
			} else if (v == btn_g2) {
				grade = "B1";
				btn_g1.setBackgroundResource(R.drawable.btn_setup_bronze);
				btn_g2.setBackgroundResource(R.drawable.btn_setup_silver_on);
				btn_g3.setBackgroundResource(R.drawable.btn_setup_gold);
				btn_g4.setBackgroundResource(R.drawable.btn_setup_pro);
			} else if (v == btn_g3) {
				grade = "C1";
				btn_g1.setBackgroundResource(R.drawable.btn_setup_bronze);
				btn_g2.setBackgroundResource(R.drawable.btn_setup_silver);
				btn_g3.setBackgroundResource(R.drawable.btn_setup_gold_on);
				btn_g4.setBackgroundResource(R.drawable.btn_setup_pro);
			} else if (v == btn_g4) {
				grade = "D1";
				btn_g1.setBackgroundResource(R.drawable.btn_setup_bronze);
				btn_g2.setBackgroundResource(R.drawable.btn_setup_silver);
				btn_g3.setBackgroundResource(R.drawable.btn_setup_gold);
				btn_g4.setBackgroundResource(R.drawable.btn_setup_pro_on);
			} else if (v == btn_ok) {
				edit.putString("myGrade", grade);
				edit.commit();

				try {
					if (!StaticClass.DataSoc.isConnected())
						StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

					String send = "BEGIN UPDATEMEMBERGRADE\r\n";
					send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
					send += "Member_Grade:" + sp.getString("myGrade", "") + "\r\n";
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

			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
			startActivity(intent);
			overridePendingTransition(0, 0);
			finish();
		}
		return true;
	}

}
