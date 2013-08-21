package com.ilovegolf.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	String table_friend = "friend";
	String friend_id = "friend_id";
	String friend_name = "friend_name";
	String friend_phone = "friend_phone";
	String friend_image = "friend_image";
	String friend_message = "friend_message";
	String friend_sex = "friend_sex";
	String friend_grade = "friend_grade";
	String friend_address_si="friend_address_si";
	String friend_address_gu="friend_address_gu";
	String friend_address_dong="friend_address_dong";
	String friend_isfavorite = "friend_isfavorite";
	String friend_isflag = "friend_isflag";

	String table_link = "link";
	String link_id = "link_id";
	String link_name = "link_name";
	String link_tel = "link_tel";
	String link_address_si="link_address_si";
	String link_address_gu="link_address_gu";
	String link_address_dong="link_address_dong";
	String link_address_text = "link_address_text";
	String link_x = "link_x";
	String link_y = "link_y";
	String link_parking = "link_parking";
	String link_system = "link_system";
	String link_isfavorite = "link_isfavorite";
	String link_isflag = "link_isflag";

	String table_message = "message";
	String message_peopleid = "message_peopleid";
	String message_peoplename = "message_peoplename";
	String message_msg = "message_msg";
	String message_date = "message_date";
	String message_code = "message_code";
	String message_id = "message_id";

	String table_chatroom = "chatroom";
	String chatroom_roomid = "chatroom_roomid";
	String chatroom_roomname = "chatroom_roomname";
	String chatroom_updatedate = "chatroom_updatedate";
	String chatroom_updatemessage = "chatroom_updatemessage";
	
	String table_address="address";
	String address_si="address_si";
	String address_gu="address_gu";
	String address_dong="address_dong";
	
	String table_payment="payment";
	String payment_date="payment_date";
	

	public DBHelper(Context context) {
		super(context, "ILGDB", null, StaticClass.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table friend(" + friend_id + " text primary key, " + friend_name + " text, " + friend_phone + " text, " + friend_image + " text, " + friend_message + " text, " + friend_sex + " text, " + friend_grade + " text, "+friend_address_si+" text, "+friend_address_gu+" text, "+friend_address_dong+" text," + friend_isfavorite + " integer, " + friend_isflag + " integer);");
		db.execSQL("create table link(" + link_id + " text primary key, " + link_name + " text, " + link_tel + " text, "+link_x+" text, " + link_y + " text, "+link_address_si+" text, "+link_address_gu+" text, "+link_address_dong+" text, "+link_address_text+" text, "+link_parking+" text, "+link_system+" text, " + link_isfavorite + " integer, " + link_isflag + " integer);");
		db.execSQL("create table message(_id integer primary key autoincrement, " + message_peopleid + " text, " + message_peoplename + " text, " + message_msg + " text, " + message_date + " text, " + message_code + " int);");
		db.execSQL("create table chatroom(" + chatroom_roomid + " text primary key, " + chatroom_roomname + " text, " + chatroom_updatedate + " text, " + chatroom_updatemessage + " text);");
		db.execSQL("create table address("+address_si+" text, "+address_gu+" text, "+address_dong+" text);");
		db.execSQL("create table payment(_id integer primary key autoincrement, "+payment_date+" text);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists message");
		db.execSQL("drop table if exists chatroom");
		db.execSQL("drop table if exists friend");
		db.execSQL("drop table if exists link");
		db.execSQL("drop table if exists address");
		db.execSQL("drop table if exists payment");
		onCreate(db);
	}

}
