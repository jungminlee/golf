package com.ilovegolf;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ListView;

import com.android.vending.billing.IInAppBillingService;
import com.example.android.trivialdrivesample.util.IabHelper;
import com.example.android.trivialdrivesample.util.IabResult;
import com.example.android.trivialdrivesample.util.Inventory;
import com.example.android.trivialdrivesample.util.Purchase;
import com.ilovegolf.adapter.A05_04_PaymentAdapter;
import com.ilovegolf.layout.L05_04_paymentLayout;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class A05_04_Payment extends BaseActivity {
	L05_04_paymentLayout layout = null;

	ArrayList<String> paymentList = null;
	ListView list_payment = null;
	A05_04_PaymentAdapter paymentlistAdapter = null;
	ArrayList skuList = new ArrayList();

	SimpleDateFormat format1 = new SimpleDateFormat("yyyy", Locale.KOREA);
	SimpleDateFormat format2 = new SimpleDateFormat("MM", Locale.KOREA);
	SimpleDateFormat format3 = new SimpleDateFormat("dd", Locale.KOREA);

	IInAppBillingService mService;
	ServiceConnection mServiceConn;

	String Itemprice;

	IabHelper mHelper;

	static final String SKU_GOLF = "test11";

	boolean pay = false;

	SharedPreferences sp = null;
	Editor edit = null;

	Long today_year;
	Long today_month;
	Long today_day;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new L05_04_paymentLayout();
		setContentView(layout.getLayout(context, display));

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		sp = context.getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();

		String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtTghp0ZCfVaQqGCqUqkiSNvTdXd3RXprO+4aTFf5VP/pK06mqgOT/RjXWKSQksYfjMLJ5shoq8fGJq69hgJ6ldWWpzYGahZ3Sr3XSuAZng4yaNAKASwxhTyM1lHMlx7ZP92izwBVfQsGY8mjnJjluFYFiRB1FRJ0I3QVyK/9XqcHdhweEUSIUjydHtHNVyPyPXCwAYptrUdDAcQCV52F27/MI8QThCT//9hfu3w3PBbW6/Rk/qFnyZkZIEovZt5Dg8F8AjTlZqeQexDEuSHaq/QzA8rn79d0QQtchkd18JuzcICN+gdSPRQ4M+QzAVz94BpDwQ+hjkub8OUbEaO2awIDAQAB";

		if (sp.getInt("myIsAcount", 0) == 1) {
			System.out.println("?SD?FAS?DF?AF?ASDF?SDF?");
			pay = true;
			Date today = new Date();
			today_year = Long.parseLong(format1.format(today));
			today_month = Long.parseLong(format2.format(today));
			Long acount_year = sp.getLong("AcountYear", 0);
			Long acount_month = sp.getLong("AcountMonth", 0);
			Long acount_day = sp.getLong("AcountDay", 0);
			System.out.println("today_year:"+today_year+"///today_month:"+today_month+"///acount_year:"+acount_year+"///acount_month:"+acount_month+"///acount_day:"+acount_day);
			
		}
//			if (today_year - acount_year > 1) {
//				if (today_month - acount_month > 1) {
//					int cnt = (int) ((today_year - acount_year) * 10 + (today_month - acount_month));
//					for (int i = 0; i < cnt; i++) {
//						acount_month++;
//						if (acount_month > 12) {
//							acount_year++;
//							acount_month -= 12;
//						}
//						String s = "[" + acount_year + "." + acount_month + "." + acount_day + "] 9500원 결제";
//						StaticClass.dbm.insertPaymentDB(s);
//					}
//				} else {
//					int cnt = (int) ((today_year - acount_year) * 10);
//					for (int i = 0; i < cnt; i++) {
//						acount_month++;
//						if (acount_month > 12) {
//							acount_year++;
//							acount_month -= 12;
//						}
//						String s = "[" + acount_year + "." + acount_month + "." + acount_day + "] 9500원 결제";
//						StaticClass.dbm.insertPaymentDB(s);
//					}
//				}
//			} else {
//				if (today_month - acount_month > 1) {
//					int cnt = (int) ((today_month - acount_month));
//					for (int i = 0; i < cnt; i++) {
//						acount_month++;
//						if (acount_month > 12) {
//							acount_month -= 12;
//						}
//						String s = "[" + acount_year + "." + acount_month + "." + acount_day + "] 9500원 결제";
//						StaticClass.dbm.insertPaymentDB(s);
//					}
//				}
//			}
//
//			synchronized (StaticClass.dbm) {
//				paymentList.clear();
//				StaticClass.dbm.selectPaymentDB(paymentList);
//			}
//			paymentlistAdapter.notifyDataSetChanged();
//
//			edit.putLong("AcountYear", acount_year);
//			edit.putLong("AcountMonth", acount_month);
//			edit.commit();
//		}

		paymentList = new ArrayList<String>();
		synchronized (StaticClass.dbm) {
			StaticClass.dbm.selectPaymentDB(paymentList);
		}
		list_payment = layout.l05_04_list_payment;
		paymentlistAdapter = new A05_04_PaymentAdapter(context, display, paymentList);
		list_payment.setAdapter(paymentlistAdapter);
		list_payment.setSelector(new ColorDrawable(Color.alpha(0)));
		
		mHelper = new IabHelper(context, base64EncodedPublicKey);

		mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
			public void onIabSetupFinished(IabResult result) {
				if (!result.isSuccess()) {
					System.out.println("inappbilling 연결 실패");
					return;
				}
				mHelper.queryInventoryAsync(mGotInventoryListener);

			}
		});
		
		View btn_pay = layout.l05_04_btn_pay;
		btn_pay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String payload = "";
				if (!pay) {
					mHelper.launchPurchaseFlow((Activity) context, SKU_GOLF, 1001, mPurchaseFinishedListener, payload);
				} else {
					StaticClass.alert = new AlertDialog.Builder(context).create();
					StaticClass.alert.setTitle("아이러브골프");
					StaticClass.alert.setMessage("이미 결제 하셨습니다.");
					StaticClass.alert.setButton(AlertDialog.BUTTON_NEUTRAL, "확인", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(getBaseContext(), A05_00_Setting.class);
							startActivity(intent);
							overridePendingTransition(0, 0);
							finish();
						}
					});
					StaticClass.alert.show();
				}

			}
		});

		btn_pay.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.btn_payment_on);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.btn_payment_off);
				}
				return false;
			}
		});
	}

	IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
		public void onIabPurchaseFinished(IabResult result, Purchase purchase) {

			if (purchase != null) {
				if (purchase.getSku().equals(SKU_GOLF)) {
					if (result.getResponse() == RESULT_OK) {
						pay = true;
						System.out.println("payyyyyyyyyyyyyy:" + pay);
					} else {
						Log.e("fail??", "실패한것맞니22222222");
						pay = false;
						System.out.println("payyyyyyyyyyyyyy:" + pay);
					}
				}
			}
		}
	};

	IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
		public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
			if (result.isFailure()) {
				return;
			}
			
			Purchase premiumPurchase = inventory.getPurchase(SKU_GOLF);
			Log.e("!@$!WAF@#", "ASDFASDFASDF");
			if (premiumPurchase != null) {
				Log.e("!@$!WAF@#", premiumPurchase.getPurchaseState() + "");
				if (premiumPurchase.getPurchaseState() == 0) {
				} else if (premiumPurchase.getPurchaseState() == 2) {
					// 마지막 결제내역 삭제
					Log.e("!@$!WAF@#", "결제내역삭제");
					pay = false;
					edit.putInt("myIsAcount", 0);
					edit.commit();

					Long acount_year = sp.getLong("AcountYear", 0);
					Long acount_month = sp.getLong("AcountMonth", 0);
					Long acount_day = sp.getLong("AcountDay", 0);

					synchronized (StaticClass.dbm) {
						StaticClass.dbm.insertPaymentDB("[" + acount_year + "." + acount_month + "." + acount_day + "] 환불");
						paymentList.clear();
						StaticClass.dbm.selectPaymentDB(paymentList);
					}
					paymentlistAdapter.notifyDataSetChanged();

					try {
						if (!StaticClass.DataSoc.isConnected())
							StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

						String send = "BEGIN UPDATEMEMBERACOUNT\r\n";
						send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
						send += "Member_isAcount:0\r\n";
						send += "END\r\n";
						StaticClass.DataSoc.sendMessage(send);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						Log.e("error", e.getMessage() + "");

					}
				}
			}
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (!mHelper.handleActivityResult(requestCode, resultCode, data))
			super.onActivityResult(requestCode, resultCode, data);
		else {
			if (requestCode == 1001) {
				if (resultCode == RESULT_OK) {
					Date date = new Date();
					
					pay = true;
					edit.putInt("myIsAcount", 1);
					edit.putLong("AcountYear", Long.parseLong(format1.format(date)));
					edit.putLong("AcountMonth", Long.parseLong(format2.format(date)));
					edit.putLong("AcountDay", Long.parseLong(format3.format(date)));
					edit.commit();
					
					synchronized (StaticClass.dbm) {
						StaticClass.dbm.insertPaymentDB("[" + format1.format(date) + "." + format2.format(date) + "." + format3.format(date) + "] 9500원 결제");
						StaticClass.dbm.selectPaymentDB(paymentList);
					}
					paymentlistAdapter.notifyDataSetChanged();

					try {
						if (!StaticClass.DataSoc.isConnected())
							StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);

						String send = "BEGIN UPDATEMEMBERACOUNT\r\n";
						send += "Member_ID:" + sp.getString("myID", "") + "\r\n";
						send += "Member_isAcount:1\r\n";
						send += "END\r\n";
						StaticClass.DataSoc.sendMessage(send);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						Log.e("error", e.getMessage() + "");

					}
				} else {
					pay = false;
					Log.e("fail??", "실패한것맞니11111111");
				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mServiceConn != null)
			unbindService(mServiceConn);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			finish();
		}
		return true;
	}
	
	
}
