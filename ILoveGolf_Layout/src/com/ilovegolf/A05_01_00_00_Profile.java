package com.ilovegolf;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ilovegolf.adapter.A05_01_ProfileAdapter;
import com.ilovegolf.layout.L05_01_00_ProfileLayout;
import com.ilovegolf.struct.ProfileItem;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A05_01_00_00_Profile extends BaseActivity {
	L05_01_00_ProfileLayout layout = null;

	SharedPreferences sp = null;
	Editor edit = null;

	ImageView btn_changePic = null;

	TextView btn_name = null;
	TextView btn_sex = null;
	TextView btn_grade = null;
	TextView btn_message = null;
	TextView btn_address = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new L05_01_00_ProfileLayout();
		setContentView(layout.getLayout(context, display));

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		sp = getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();

		TextView text_myphone = layout.l05_01_text_myphone;
		text_myphone.setText(sp.getString("myPhone", ""));

		btn_changePic = layout.l05_01_img_pic;
		System.out.println("이미지 지금 있나??????????" + sp.getString("myImage", ""));

		if (sp.getString("myImage", "").length() != 0) {
			System.out.println("이미지 바꾸러 들어옵니까????");
			btn_changePic.setImageBitmap(BitmapFactory.decodeFile("/mnt/sdcard/golfpic/profile"));
		}
		btn_changePic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK);
				intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				startActivityForResult(intent, 2);

			}
		});

		btn_name = layout.i05_01_text_name;
		btn_sex = layout.i05_01_text_sex;
		btn_grade = layout.i05_01_text_grade;
		btn_message = layout.i05_01_text_message;
		btn_address = layout.i05_01_text_address;

		btn_name.setText(sp.getString("myName", ""));
		String sex = sp.getString("mySex", "M");
		if (sex.equals("M"))
			btn_sex.setText("남자");
		else if (sex.equals("W"))
			btn_sex.setText("여자");
		String grade = sp.getString("myGrade", "A1");
		if (grade.equals("A1"))
			btn_grade.setText("초급");
		else if (grade.equals("B1"))
			btn_grade.setText("중급");
		else if (grade.equals("C1"))
			btn_grade.setText("고급");
		else if (grade.equals("D1"))
			btn_grade.setText("프로");
		btn_message.setText(sp.getString("myMessage", ""));
		btn_address.setText(sp.getString("myAddress_si", "") + " " + sp.getString("myAddress_gu", "") + " " + sp.getString("myAddress_dong", ""));

		btn_name.setOnClickListener(onClick);
		btn_sex.setOnClickListener(onClick);
		btn_grade.setOnClickListener(onClick);
		btn_message.setOnClickListener(onClick);
		btn_address.setOnClickListener(onClick);

		btn_name.setOnTouchListener(onTouch);
		btn_sex.setOnTouchListener(onTouch);
		btn_grade.setOnTouchListener(onTouch);
		btn_message.setOnTouchListener(onTouch);
		btn_address.setOnTouchListener(onTouch);
	}

	OnTouchListener onTouch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (v == btn_name) {
					v.setBackgroundResource(R.drawable.golf_setup_profile_name_bg_on);
				} else if (v == btn_sex) {
					v.setBackgroundResource(R.drawable.golf_setup_profile_sex_bg_on);
				} else if (v == btn_grade) {
					v.setBackgroundResource(R.drawable.golf_setup_profile_class_bg_on);
				} else if (v == btn_message) {
					v.setBackgroundResource(R.drawable.golf_setup_profile_mass_bg_on);
				} else if (v == btn_address) {
					v.setBackgroundResource(R.drawable.golf_setup_profile_add_bg_on);
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				if (v == btn_name) {
					v.setBackgroundResource(R.drawable.golf_setup_profile_name_bg_off);
				} else if (v == btn_sex) {
					v.setBackgroundResource(R.drawable.golf_setup_profile_sex_bg_off);
				} else if (v == btn_grade) {
					v.setBackgroundResource(R.drawable.golf_setup_profile_class_bg_off);
				} else if (v == btn_message) {
					v.setBackgroundResource(R.drawable.golf_setup_profile_mass_bg_off);
				} else if (v == btn_address) {
					v.setBackgroundResource(R.drawable.golf_setup_profile_add_bg_off);
				}
			}
			return false;
		}
	};

	OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v == btn_name) {
				Intent intent = new Intent(getBaseContext(), A05_01_01_ModifyName.class);
				startActivity(intent);
				finish();
			} else if (v == btn_sex) {
				Intent intent = new Intent(getBaseContext(), A05_01_04_ModifySex.class);
				startActivity(intent);
				finish();
			} else if (v == btn_grade) {
				Intent intent = new Intent(getBaseContext(), A05_01_05_ModifyGrade.class);
				startActivity(intent);
				finish();
			} else if (v == btn_message) {
				Intent intent = new Intent(getBaseContext(), A05_01_02_ModifyMessage.class);
				startActivity(intent);
				finish();
			} else if (v == btn_address) {
				Intent intent = new Intent(getBaseContext(), A05_01_03_ModifyAdress.class);
				startActivity(intent);
				finish();
			}
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == 2) {
				Uri currImageURI = data.getData();
				String path = getPath(currImageURI);
				// btn_changePic.setImageBitmap(BitmapFactory.decodeFile(path));

				Intent intent = new Intent(getBaseContext(), A05_01_00_01_ModifyPicture.class);
				intent.putExtra("path", path);
				startActivity(intent);
				finish();
			}
		}
	}

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			finish();
		}
		return true;
	}
}
