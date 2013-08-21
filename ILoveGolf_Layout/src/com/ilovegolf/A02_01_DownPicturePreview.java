package com.ilovegolf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.EventLog.Event;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.ilovegolf.layout.L02_01_DownPicturePreviewLayout;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A02_01_DownPicturePreview extends BaseActivity {
	L02_01_DownPicturePreviewLayout layout = null;
	String img = null;

	View btn_left = null;
	View btn_right = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		layout = new L02_01_DownPicturePreviewLayout();
		setContentView(layout.getLayout(context, display));

		Intent intent = getIntent();
		img = intent.getStringExtra("img");
		System.out.println("이미지이름???" + img);
		String filepath = intent.getStringExtra("file");

		btn_left = layout.l02_01_btn_left;
		btn_right = layout.l02_01_btn_right;

		btn_left.setOnTouchListener(onTouch);
		btn_right.setOnTouchListener(onTouch);

		btn_left.setOnClickListener(onClick);
		btn_right.setOnClickListener(onClick);

		ImageView img_pic = layout.l02_01_img_pic;
		prd("잠시만 기다려 주세요.");
		SyncTask task = new SyncTask(filepath, img_pic);
		task.execute();
	}

	public class SyncTask extends AsyncTask<Void, Void, Void> {
		String filepath = null;
		ImageView img_pic = null;
		boolean toastflag = false;

		public SyncTask(String filepath, ImageView img_pic) {
			this.filepath = filepath;
			this.img_pic = img_pic;
		}

		@Override
		protected Void doInBackground(Void... params) {
			File path = new File("/mnt/sdcard/golfpic/big");
			if (!path.isDirectory()) {
				path.mkdir();
			}

			if (StaticClass.ImageSoc instanceof SocketIO) {
				StaticClass.ImageSoc = null;
			}
			try {

				StaticClass.ImageSoc = new SocketIO(StaticClass.IP, 2008);
				String send = "BEGIN BIGIMAGE\r\n";
				send += img + "\r\n";
				send += "END\r\n";
				StaticClass.ImageSoc.sendMessage(send);

				byte[] b = new byte[5096];
				byte[] lengthb = new byte[1];
				StaticClass.ImageSoc.is.read(lengthb, 0, 1);
				String stringlength = new String(lengthb, "utf-8");
				if (!stringlength.equals("0")) {
					int length = Integer.parseInt(stringlength);
					byte[] sizeb = new byte[length];
					StaticClass.ImageSoc.is.read(sizeb, 0, length);
					String stringsize = new String(sizeb, "utf-8");
					int size = Integer.parseInt(stringsize);

					File file = new File("/mnt/sdcard/golfpic/big/" + filepath);
					FileOutputStream fos = new FileOutputStream(file);
					int len = 0;
					for (; size >= 0; size -= len) {
						len = StaticClass.ImageSoc.is.read(b);
						fos.write(b, 0, len);
					}

					// System.out.println("남은 크기 ::::::::::::::::"+size);
					// StaticClass.ImageSoc.is.read(b, 0, size);
					// fos.write(b);

					fos.close();

					StaticClass.ImageSoc.os.close();
					StaticClass.ImageSoc.close();
				} else {
					toastflag = true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (toastflag) {
				dismiss();
				StaticClass.alert = new AlertDialog.Builder(context).create();
				StaticClass.alert.setTitle("아이러브골프");
				StaticClass.alert.setMessage("이미지를 찾을 수 없습니다.");
				StaticClass.alert.setButton(AlertDialog.BUTTON_NEUTRAL, "확인", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				StaticClass.alert.show();
			} else {
				img_pic.setImageBitmap(BitmapFactory.decodeFile("/mnt/sdcard/golfpic/big/" + filepath));
				dismiss();
			}
		}
	}

	public OnTouchListener onTouch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (v == btn_left) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.btn_picture_preview_on);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.btn_picture_preview_off);
				}
			} else if (v == btn_right) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.btn_picture_next_on);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.btn_picture_next_off);
				}
			}
			return false;
		}
	};

	public OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};

}