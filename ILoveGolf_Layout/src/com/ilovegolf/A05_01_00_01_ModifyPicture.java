package com.ilovegolf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.ilovegolf.layout.L05_01_00_01_ModifyPictureLayout;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A05_01_00_01_ModifyPicture extends BaseActivity {
	L05_01_00_01_ModifyPictureLayout layout = null;
	String file = null;

	View btn_ok = null;
	View btn_rocate = null;
	View btn_cancel = null;

	ImageView img_pic = null;

	Bitmap bit;

	private int currentAngle = 0;
	private static final int ROTATE_VALUE = 90;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		layout = new L05_01_00_01_ModifyPictureLayout();
		setContentView(layout.getLayout(context, display));

		Intent intent = getIntent();
		file = intent.getStringExtra("path");

		btn_ok = layout.l05_01_00_01_btn_ok;
		btn_rocate = layout.l05_01_00_01_btn_rocate;
		btn_cancel = layout.l05_01_00_01_btn_cancel;

		img_pic = layout.l05_01_00_01_img_pic;

		File path = new File("/mnt/sdcard/golfpic");
		if (!path.isDirectory()) {
			path.mkdir();
		}

		try {
			FileInputStream fis = new FileInputStream(file);
			bit = BitmapFactory.decodeStream(fis, null, StaticClass.getBitmapOption(300, 300, file));
			img_pic.setImageBitmap(bit);
		} catch (OutOfMemoryError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			Log.e("error", e.getMessage() + "");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				prd("잠시만 기다려 주세요.");
				new SyncTask(bit, context) {
					protected void onPostExecute(Void result) {
						dismiss();

						Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
						startActivity(intent);
						overridePendingTransition(0, 0);
						finish();
					};
				}.execute();

			}
		});
		btn_rocate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Matrix matrix = new Matrix();
				// currentAngle = ROTATE_VALUE + (currentAngle % 360);

				System.out.println(":::::::::::::::::" + currentAngle);
				matrix.postRotate(ROTATE_VALUE); // 회전

				bit = Bitmap.createBitmap(bit, 0, 0, bit.getWidth(), bit.getHeight(), matrix, true);
				img_pic.setImageBitmap(bit);
			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});

		btn_cancel.setOnTouchListener(onTouch);
		btn_rocate.setOnTouchListener(onTouch);
		btn_ok.setOnTouchListener(onTouch);

	}

	public OnTouchListener onTouch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (v == btn_cancel) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.btn_picture_cancel_on);
				} else if (event.getAction() == KeyEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.btn_picture_cancel_off);
				}
			} else if (v == btn_rocate) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.btn_picture_rotation_on);
				} else if (event.getAction() == KeyEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.btn_picture_rotation_off);
				}
			} else if (v == btn_ok) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.btn_picture_save_on);
				} else if (event.getAction() == KeyEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.btn_picture_save_off);
				}
			}
			return false;
		}
	};

	public class SyncTask extends AsyncTask<Void, Void, Void> {

		Bitmap bit = null;
		SharedPreferences sp = null;
		Editor edit = null;

		public SyncTask(Bitmap bit, Context context) {
			this.bit = bit;
			sp = context.getSharedPreferences("ilovegolfPrefer", 0);
			edit = sp.edit();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				File copyfile = new File("/mnt/sdcard/golfpic/profile");
				FileOutputStream fos;
				fos = new FileOutputStream(copyfile);

				bit.compress(CompressFormat.PNG, 100, fos);
				fos.close();

				StaticClass.ImageSoc = new SocketIO(StaticClass.IP, 2008);
				StaticClass.ImageSoc.sendImage(sp.getString("myID", ""));
				StaticClass.ImageSoc.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			edit.putString("myImage", "y");
			edit.commit();

			return null;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			if (!StaticClass.isjoin) {
				Intent intent = new Intent(getBaseContext(), A05_01_00_00_Profile.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		}
		return true;
	}
}