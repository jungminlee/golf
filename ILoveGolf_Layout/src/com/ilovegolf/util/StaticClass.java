package com.ilovegolf.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import com.ilovegolf.T00_00_ReceiveThread;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.struct.Link;

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class StaticClass {

	public static boolean datalogin = false;
	public static boolean isjoin = false;
	public static boolean friendmyarea = true;
	public static boolean linkmyarea = true;
	public static boolean ispush = false;
	public static boolean imagethreadisalive=false;
	public static boolean refresh=false;
	public static boolean sidown=false;

	public static String other_address_si = "";
	public static String other_address_gu = "";
	public static String other_address_dong = "";
	public static int si_id;
	public static int gu_id;
	public static int dong_id;
	
	public static ArrayList<Friend> friendList = null;
	public static ArrayList<Link> linkList = null;
	public static HashMap<String, ImageView> favoritelist = new HashMap<String, ImageView>();

	public static final int PORT = 7000;
	// 서버
	 public static final String IP = "175.126.145.248";

	// 회사
//	public static final String IP = "192.168.69.2";

	// 집
	// public static final String IP="110.35.177.144";
	public static BitmapFactory.Options getBitmapOption(int displayWidth, int displayHeight, String imgFilePath) throws Exception, OutOfMemoryError {

		// 읽어들일 이미지의 사이즈를 구한다.

		BitmapFactory.Options options = new BitmapFactory.Options();

		options.inPreferredConfig = Config.RGB_565;

		options.inJustDecodeBounds = true;

		BitmapFactory.decodeFile(imgFilePath, options);

		// 화면 사이즈에 가장 근접하는 이미지의 리스케일 사이즈를 구한다.
		// 리스케일의 사이즈는 짝수로 지정한다. (이미지 손실을 최소화하기 위함.)

		float widthScale = options.outWidth / displayWidth;

		float heightScale = options.outHeight / displayHeight;

		float scale = widthScale > heightScale ? widthScale : heightScale;
		if (scale >= 16) {
			options.inSampleSize = 16;
		} else if (scale >= 8) {
			options.inSampleSize = 8;
		} else if (scale >= 6) {
			options.inSampleSize = 6;
		} else if (scale >= 4) {
			options.inSampleSize = 4;
		} else if (scale >= 2) {
			options.inSampleSize = 2;
		} else {
			options.inSampleSize = 1;
		}
		options.inJustDecodeBounds = false;
		return options;
	}

	public static final int DB_VERSION = 132;
	public static String Result = "";
	public static final String Succ = "100";
	public static final String Fail = "400";

	public static SocketIO DataSoc = null;
	public static SocketIO ImageSoc = null;
	public static T00_00_ReceiveThread thread = null;
	public static DBManager dbm = null;
	public static Handler handler = null;
	public static AlertDialog alert = null;
	public static Message hanmessage = null;
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH^mm", Locale.KOREA);
	public static SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmm", Locale.KOREA);

	public static final int LOGIN_SUCC = 0;
	public static final int LOGIN_FAIL = 1;
	public static final int JOIN_SUCC = 2;
	public static final int JOIN_FAIL = 3;
	public static final int REFRESH = 4;
	public static final int MESSAGE_REFRESH = 5;
	public static final int REQUEST = 6;
	public static final int CHATROOM_REFRESH = 7;
	public static final int ADDRESS_GU = 8;
	public static final int ADDRESS_DONG = 9;
	public static String chatmember = "";
}
