package com.ilovegolf.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;

public class BaseActivity extends Activity {

	protected Context context = null;
	protected Display display = null;

	protected ProgressDialog prd = null;
	protected String kill = null;
	protected long prdTime = 0;
	protected DialogTimer prdCheckTime = null;
	protected boolean isERROR = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	}

	protected ProgressDialog prd(String string) {
		return prd(string, true);
	}

	protected ProgressDialog prd(String string, boolean isTimeCheck) {
		prdTime = System.currentTimeMillis();
		dismiss();
		if (prdCheckTime instanceof DialogTimer) {
			prdCheckTime.cancel();
			prdCheckTime = null;
		}
		prd = ProgressDialog.show(context, "아이러브골프", string);
		prd.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					dialog.dismiss();
				}
				return true;
			}
		});
		if (isTimeCheck) {
			prdCheckTime = new DialogTimer(20000, 20000) {
				@Override
				public void onTick(long millisUntilFinished) {
				}

				@Override
				public void onFinish() {
					try {
						if (mylong == prdTime) {
							if (prd instanceof ProgressDialog) {
								dismiss();
								isERROR = true;
								prdCheckTime = null;
								StaticClass.alert = new AlertDialog.Builder(context).create();
								StaticClass.alert.setTitle("아이러브골프");
								StaticClass.alert.setMessage("서버와 연결이 원활하지 않습니다.\n다시 시도해 주세요.");
								StaticClass.alert.setButton(AlertDialog.BUTTON_NEUTRAL, "확인", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
									}
								});
								StaticClass.alert.show();
//								if (StaticClass.DataSoc instanceof SocketIO) {
//									StaticClass.DataSoc.socketAlive = false;
//									StaticClass.DataSoc.close();
//									StaticClass.DataSoc = null;
//								} else if (StaticClass.ChatSoc instanceof SocketIO) {
//									StaticClass.ChatSoc.socketAlive = false;
//									StaticClass.ChatSoc.close();
//									StaticClass.ChatSoc = null;
//								}
							}
						}
					} catch (Exception e) {
					}

				}
			};
			prdCheckTime.setWhat(null, prdTime);
			prdCheckTime.start();
		}

		return prd;
	}// prd(String string)

	protected boolean dismiss() {
		if (prd instanceof ProgressDialog) {
			prd.dismiss();
			prd = null;
			return true;
		}
		return false;
	}// dismiss
}
