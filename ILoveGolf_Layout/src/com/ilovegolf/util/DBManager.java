package com.ilovegolf.util;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ilovegolf.struct.ChatRoom;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.struct.InputAddress;
import com.ilovegolf.struct.Link;
import com.ilovegolf.struct.RecvMessage;

public class DBManager {

	Context context = null;

	private DBHelper dbhepler;
	private SQLiteDatabase db;

	String table_friend = "friend";
	String friend_id = "friend_id";
	String friend_name = "friend_name";
	String friend_phone = "friend_phone";
	String friend_image = "friend_image";
	String friend_message = "friend_message";
	String friend_sex = "friend_sex";
	String friend_grade = "friend_grade";
	String friend_address_si = "friend_address_si";
	String friend_address_gu = "friend_address_gu";
	String friend_address_dong = "friend_address_dong";
	String friend_isfavorite = "friend_isfavorite";
	String friend_isflag = "friend_isflag";
	String friend_isblack = "friend_isblack";

	String table_link = "link";
	String link_id = "link_id";
	String link_name = "link_name";
	String link_tel = "link_tel";
	String link_address_si = "link_address_si";
	String link_address_gu = "link_address_gu";
	String link_address_dong = "link_address_dong";
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

	String table_address = "address";
	String address_si = "address_si";
	String address_gu = "address_gu";
	String address_dong = "address_dong";

	Cursor friendCursor = null;
	Cursor linkCursor = null;
	Cursor messageCursor = null;
	Cursor chatroomCursor = null;
	Cursor addressCursor = null;
	Cursor paymentCursor = null;

	String table_payment = "payment";
	String payment_date = "payment_date";

	public DBManager(Context context) {
		this.context = context;
		dbhepler = new DBHelper(this.context);
		db = dbhepler.getWritableDatabase();
	}

	public void paymentCursorCheck() {
		if (paymentCursor instanceof Cursor) {
			synchronized (paymentCursor) {
				paymentCursor.close();
			}
		}
	}

	public void addressCursorCheck() {
		if (addressCursor instanceof Cursor) {
			synchronized (addressCursor) {
				addressCursor.close();
			}
		}
	}

	public void chatroomCursorCheck() {
		if (chatroomCursor instanceof Cursor) {
			synchronized (chatroomCursor) {
				chatroomCursor.close();
			}
		}
	}

	public void friendCursorCheck() {
		if (friendCursor instanceof Cursor) {
			synchronized (friendCursor) {
				friendCursor.close();
			}
		}
	}

	public void linkCursorCheck() {
		if (linkCursor instanceof Cursor) {
			synchronized (linkCursor) {
				linkCursor.close();
			}
		}
	}

	public void messageCursorCheck() {
		if (messageCursor instanceof Cursor) {
			synchronized (linkCursor) {
				messageCursor.close();
			}
		}
	}

	public void deleteLinkDB() {
		db.execSQL("delete from link");
	}

	public void deleteFriendDB() {
		db.execSQL("delete from friend");
	}

	public void insertPaymentDB(String date) {
		db.execSQL("insert into " + table_payment + "(" + payment_date + ") values('" + date + "');");
	}

	public void insertAddressDB(String si) {
		db.execSQL("insert into " + table_address + " values('" + si + "', null, null)");
	}

	public void insertAddressDB(String si, String gu) {
		db.execSQL("insert into " + table_address + " values('" + si + "', '" + gu + "', null)");
	}

	public void insertAddressDB(String si, String gu, String dong) {
		db.execSQL("insert into " + table_address + " values('" + si + "', '" + gu + "', '" + dong + "')");
	}

	public void insertChatRoomDB(ChatRoom chatroom) {
		db.execSQL("insert into " + table_chatroom + " values('" + chatroom.strRoomID + "', '" + chatroom.strRoomName + "', '" + chatroom.strUpdateDate + "', '" + chatroom.strUpdateMessage + "')");
	}

	public void insertMessageDB(RecvMessage message) {
		db.execSQL("insert into " + table_message + "(" + message_peopleid + ", " + message_peoplename + ", " + message_msg + ", " + message_date + ", " + message_code + ") values('" + message.strPeopleID + "', '" + message.strPeopleName + "', '" + message.strContent + "', '" + message.strDate + "', " + message.iCode + ");");
	}

	public void deleteMessageDB(int iMessageID) {
		db.execSQL("delete from " + table_message + " where _id=" + iMessageID + "");
	}

	// public void deleteAddress(int flag, InputAddress add) {
	// if (flag == 1) {
	// db.execSQL("delete from " + table_address + " where " + address_si + "='" + add.strAddress_si + "' and " + address_gu + " is NULL and " + address_dong + " is NULL");
	// } else if (flag == 2) {
	// db.execSQL("delete from " + table_address + " where " + address_si + "='" + add.strAddress_si + "' and " + address_gu + " ='" + add.strAddress_gu + "' " + address_dong + " is NULL");
	// }
	// }

	public void deleteMessageDB_ALL(String sender) {
		db.execSQL("delete from " + table_message + " where " + message_peopleid + "='" + sender + "'");
	}

	public void deleteChatRoomDB(String roomid) {
		db.execSQL("delete from " + table_chatroom + " where " + chatroom_roomid + "='" + roomid + "'");
	}

	public void updateChatRoomDB(ChatRoom chatroom) {
		db.execSQL("update chatroom set " + chatroom_updatedate + "='" + chatroom.strUpdateDate + "', " + chatroom_updatemessage + "='" + chatroom.strUpdateMessage + "' where " + chatroom_roomid + "='" + chatroom.strRoomID + "'");
	}

	public void updateMessageDB(RecvMessage message) {
		db.execSQL("update message set " + message_msg + "='" + message.strContent + "', " + message_code + "=" + message.iCode + " where _id=" + message.iMessageID + "");
	}

	public void insertFriendDB(Friend friend) {
		db.execSQL("insert into friend values('" + friend.strID + "', '" + friend.strName + "', '" + friend.strPhone + "', '" + friend.strImage + "', '" + friend.strMessage + "', '" + friend.strSex + "', '" + friend.strGrade + "','" + friend.strAddressSi + "' ,'" + friend.strAddressGu + "' ,'" + friend.strAddressDong + "' ," + friend.iIsFavorite + ", " + friend.iIsFlag + ", " + friend.iIsBlack + ");");
	}

	public void insertLinkDB(Link link) {
		db.execSQL("insert into link values('" + link.strID + "', '" + link.strName + "', '" + link.strTel + "', '" + link.strlink_x + "', '" + link.strlink_y + "', '" + link.strAddress_si + "', '" + link.strAddress_gu + "', '" + link.strAddress_dong + "', '" + link.strAddress_text + "', '" + link.strlink_parking + "', '" + link.strlink_system + "', " + link.iIsFavorite + ", " + link.iIsFlag + ");");
	}

	public void updateFriendBlack(String id) {
		db.execSQL("update friend set " + friend_isblack + "=1 where " + friend_id + "='" + id + "'");
	}

	public void updateLinkDB(Link link) {
		System.out.println("update link set " + link_isfavorite + "=" + link.iIsFavorite + ", link_isflag=" + link.iIsFlag + ", " + link_address_si + "='" + link.strAddress_si + "', " + link_address_gu + "='" + link.strAddress_gu + "', " + link_address_dong + "='" + link.strAddress_dong + "', " + link_address_text + "='" + link.strAddress_text + "' where " + link_id + "='" + link.strID + "';");
		db.execSQL("update link set " + link_isfavorite + "=" + link.iIsFavorite + ", link_isflag=" + link.iIsFlag + ", " + link_address_si + "='" + link.strAddress_si + "', " + link_address_gu + "='" + link.strAddress_gu + "', " + link_address_dong + "='" + link.strAddress_dong + "', " + link_address_text + "='" + link.strAddress_text + "' where " + link_id + "='" + link.strID + "';");
	}

	public void updateFriendDB(Friend friend) {
		db.execSQL("update friend set " + friend_address_si + "='" + friend.strAddressSi + "'," + friend_address_gu + "='" + friend.strAddressGu + "'," + friend_address_dong + "='" + friend.strAddressDong + "' ," + friend_isfavorite + "=" + friend.iIsFavorite + ", friend_isflag=" + friend.iIsFlag + ", " + friend_image + "='" + friend.strImage + "', " + friend_message + "='" + friend.strMessage + "', " + friend_name + "='" + friend.strName + "', " + friend_sex + "='" + friend.strSex + "', " + friend_grade + "='" + friend.strGrade + "' where " + friend_id + "='" + friend.strID + "';");
	}

	public void selectPaymentDB(ArrayList<String> payList) {
		paymentCursorCheck();

		paymentCursor = db.rawQuery("select * from " + table_payment, null);
		while (paymentCursor.moveToNext()) {
			String pay = paymentCursor.getString(1);
			payList.add(pay);
		}
		paymentCursor.close();
	}

	public boolean selectFriendBlackDB(String id) {
		friendCursorCheck();
		friendCursor = db.rawQuery("select " + friend_isblack + " from " + table_friend + " where " + friend_id + "='" + id + "';", null);
		while (friendCursor.moveToNext()) {
			if (friendCursor.getInt(0) == 1) {
				System.out.println("블랙리스트");
				return true;
			} else
				return false;
		}
		return false;
	}

	public void selectAddressDB(ArrayList<String> addressList, int flag, InputAddress add) {
		String address = "";

		addressCursorCheck();

		if (flag == 1) {
			addressCursor = db.rawQuery("select DISTINCT " + address_si + " from address where " + address_si + "!='null' order by " + address_si + " asc", null);
		} else if (flag == 2) {
			addressCursor = db.rawQuery("select DISTINCT " + address_gu + " from address where " + address_si + "='" + add.strAddress_si + "' and " + address_gu + " is not null  order by " + address_gu + " asc", null);
		} else if (flag == 3) {
			addressCursor = db.rawQuery("select DISTINCT " + address_dong + " from address where " + address_si + "='" + add.strAddress_si + "' and " + address_gu + "='" + add.strAddress_gu + "' and " + address_dong + " is not null order by " + address_dong + " asc", null);
		}
		while (addressCursor.moveToNext()) {
			address = addressCursor.getString(0);
			System.out.println("address" + address);
			addressList.add(address);
		}
		addressCursor.close();
	}

	public boolean selectAddressDB(int flag, InputAddress add) {
		String address = "";
		addressCursorCheck();

		if (flag == 2) {
			addressCursor = db.rawQuery("select distinct * from address where " + address_si + "='" + add.strAddress_si + "';", null);
			System.out.println(addressCursor.getCount() + ":+:+:+:+:+:+:true");
			if (addressCursor.getCount() > 1) {
				// StaticClass.dbm.deleteAddress(1, add);
				return true;
			}
		} else if (flag == 3) {
			addressCursor = db.rawQuery("select distinct * from address where " + address_si + "='" + add.strAddress_si + "' and " + address_gu + "='" + add.strAddress_gu + "';", null);
			System.out.println(addressCursor.getCount() + ":+:+:+:+:+:+:true");
			if (addressCursor.getCount() > 1) {
				// StaticClass.dbm.deleteAddress(2, add);
				return true;
			}
		}
		addressCursor.close();
		System.out.println(":+:+:+:+:+:+:false");
		return false;
	}

	public void selectMessageDB(ArrayList<RecvMessage> messageList, String id) {
		RecvMessage message;

		messageCursorCheck();

		messageCursor = db.rawQuery("select * from message where " + message_peopleid + "='" + id + "';", null);
		// c.moveToFirst();
		while (messageCursor.moveToNext()) {
			message = new RecvMessage();
			message.iMessageID = messageCursor.getInt(0);
			message.strPeopleID = messageCursor.getString(1);
			message.strPeopleName = messageCursor.getString(2);
			message.strContent = messageCursor.getString(3);
			message.strDate = messageCursor.getString(4);
			message.iCode = messageCursor.getInt(5);
			messageList.add(message);
		}
		messageCursor.close();
	}

	public void selectFriendDB(ConcurrentLinkedQueue<Friend> idList) {
		Friend friend = null;
		friendCursorCheck();

		friendCursor = db.rawQuery("select * from friend where " + friend_image + " like '%*';", null);
		// c.moveToFirst();
		while (friendCursor.moveToNext()) {
			friend = new Friend();
			friend.strID = friendCursor.getString(0);
			friend.strName = friendCursor.getString(1);
			friend.strImage = friendCursor.getString(3);
			friend.strMessage = friendCursor.getString(4);
			friend.strSex = friendCursor.getString(5);
			friend.strGrade = friendCursor.getString(6);
			friend.strAddressSi = friendCursor.getString(7);
			friend.strAddressGu = friendCursor.getString(8);
			friend.strAddressDong = friendCursor.getString(9);
			friend.iIsFavorite = friendCursor.getInt(10);
			friend.iIsFlag = friendCursor.getInt(11);
			friend.iIsBlack = friendCursor.getInt(12);
			idList.offer(friend);
		}
		friendCursor.close();
	}

	public void selectChatRoomDB(ArrayList<ChatRoom> chatroomList) {
		ChatRoom chatroom;

		chatroomCursorCheck();

		chatroomCursor = db.rawQuery("select * from chatroom order by " + chatroom_updatedate + " desc;", null);
		while (chatroomCursor.moveToNext()) {
			chatroom = new ChatRoom();
			chatroom.strRoomID = chatroomCursor.getString(0);
			chatroom.strRoomName = chatroomCursor.getString(1);
			chatroom.strUpdateDate = chatroomCursor.getString(2);
			chatroom.strUpdateMessage = chatroomCursor.getString(3);
			chatroomList.add(chatroom);
		}
		chatroomCursor.close();
	}

	public boolean selectChatRoomDB(String roomid) {
		chatroomCursorCheck();

		chatroomCursor = db.rawQuery("select * from chatroom where " + chatroom_roomid + "='" + roomid + "';", null);

		System.out.println("chatroomCursor.getCount() " + chatroomCursor.getCount());
		if (chatroomCursor.getCount() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void selectNearFriendDB(ArrayList<Friend> friendlist, String si, String gu, String dong) {
		Friend friend;

		friendCursorCheck();

		friendCursor = db.rawQuery("select * from friend where " + friend_address_si + "='" + si + "' and " + friend_address_gu + "='" + gu + "' and " + friend_address_dong + "='" + dong + "';", null);
		// c.moveToFirst();
		while (friendCursor.moveToNext()) {
			friend = new Friend();
			friend.strID = friendCursor.getString(0);
			friend.strName = friendCursor.getString(1);
			friend.strImage = friendCursor.getString(3);
			friend.strMessage = friendCursor.getString(4);
			friend.strSex = friendCursor.getString(5);
			friend.strGrade = friendCursor.getString(6);
			friend.strAddressSi = friendCursor.getString(7);
			friend.strAddressGu = friendCursor.getString(8);
			friend.strAddressDong = friendCursor.getString(9);
			friend.iIsFavorite = friendCursor.getInt(10);
			friend.iIsFlag = friendCursor.getInt(11);
			friendlist.add(friend);
		}
		friendCursor.close();
	}

	public void selectFriendDB(ArrayList<Friend> friendlist) {
		Friend friend;

		friendCursorCheck();

		friendCursor = db.rawQuery("select * from friend;", null);
		// c.moveToFirst();
		while (friendCursor.moveToNext()) {
			friend = new Friend();
			friend.strID = friendCursor.getString(0);
			friend.strName = friendCursor.getString(1);
			friend.strImage = friendCursor.getString(3);
			friend.strMessage = friendCursor.getString(4);
			friend.strSex = friendCursor.getString(5);
			friend.strGrade = friendCursor.getString(6);
			friend.strAddressSi = friendCursor.getString(7);
			friend.strAddressGu = friendCursor.getString(8);
			friend.strAddressDong = friendCursor.getString(9);
			friend.iIsFavorite = friendCursor.getInt(10);
			friend.iIsFlag = friendCursor.getInt(11);
			friend.iIsBlack = friendCursor.getInt(12);
			friendlist.add(friend);
		}
		friendCursor.close();
	}

	public void selectLinkDB(ArrayList<Link> linklist, String si, String gu, String dong) {
		Link link;

		linkCursorCheck();

		linkCursor = db.rawQuery("select * from " + table_link + " where " + link_address_si + "='" + si + "' and " + link_address_gu + "='" + gu + "' and " + link_address_dong + "='" + dong + "';", null);
		// c.moveToFirst();

		while (linkCursor.moveToNext()) {
			link = new Link();
			link.strID = linkCursor.getString(0);
			link.strName = linkCursor.getString(1);
			link.strTel = linkCursor.getString(2);
			link.strlink_x = linkCursor.getString(3);
			link.strlink_y = linkCursor.getString(4);
			link.strAddress_si = linkCursor.getString(5);
			link.strAddress_gu = linkCursor.getString(6);
			link.strAddress_dong = linkCursor.getString(7);
			link.strAddress_text = linkCursor.getString(8);
			link.strlink_parking = linkCursor.getString(9);
			link.strlink_system = linkCursor.getString(10);
			link.iIsFavorite = linkCursor.getInt(11);
			link.iIsFlag = linkCursor.getInt(12);
			linklist.add(link);
		}
		linkCursor.close();
	}

	public int selectLinkDBAll() {
		linkCursorCheck();

		linkCursor = db.rawQuery("select * from link;", null);

		return linkCursor.getCount();
	}

	public void selectLinkDB(int flag, ArrayList<Link> linklist) {
		Link link;
		linkCursorCheck();

		linkCursor = db.rawQuery("select * from link where " + link_isfavorite + "=" + flag + ";", null);
		// c.moveToFirst();
		while (linkCursor.moveToNext()) {
			link = new Link();
			link.strID = linkCursor.getString(0);
			link.strName = linkCursor.getString(1);
			link.strTel = linkCursor.getString(2);
			link.strlink_x = linkCursor.getString(3);
			link.strlink_y = linkCursor.getString(4);
			link.strAddress_si = linkCursor.getString(5);
			link.strAddress_gu = linkCursor.getString(6);
			link.strAddress_dong = linkCursor.getString(7);
			link.strAddress_text = linkCursor.getString(8);
			link.strlink_parking = linkCursor.getString(9);
			link.strlink_system = linkCursor.getString(10);
			link.iIsFavorite = linkCursor.getInt(11);
			link.iIsFlag = linkCursor.getInt(12);
			link.favorite = true;
			linklist.add(link);
		}
		linkCursor.close();
	}

	public void selectFavoriteFriendDB(int flag, ArrayList<Friend> friendlist) {
		Friend friend;
		friendCursorCheck();

		friendCursor = db.rawQuery("select * from friend where " + friend_isfavorite + "=" + flag + ";", null);
		// c.moveToFirst();
		while (friendCursor.moveToNext()) {
			friend = new Friend();
			friend.strID = friendCursor.getString(0);
			friend.strName = friendCursor.getString(1);
			friend.strImage = friendCursor.getString(3);
			friend.strMessage = friendCursor.getString(4);
			friend.strSex = friendCursor.getString(5);
			friend.strGrade = friendCursor.getString(6);
			friend.strAddressSi = friendCursor.getString(7);
			friend.strAddressGu = friendCursor.getString(8);
			friend.strAddressDong = friendCursor.getString(9);
			friend.iIsFavorite = friendCursor.getInt(10);
			friend.iIsFlag = friendCursor.getInt(11);
			friend.favorite = true;
			friendlist.add(friend);
		}
		friendCursor.close();
	}

	public ConcurrentLinkedQueue<Friend> selectFriendDB(int flag) {
		ConcurrentLinkedQueue<Friend> friendlist = new ConcurrentLinkedQueue<Friend>();
		Friend friend;

		friendCursorCheck();
		friendCursor = db.rawQuery("select * from friend where " + friend_isflag + "=" + flag + ";", null);
		// c.moveToFirst();
		while (friendCursor.moveToNext()) {
			friend = new Friend();
			friend.strID = friendCursor.getString(0);
			friend.strName = friendCursor.getString(1);
			friend.strImage = friendCursor.getString(3);
			friend.strMessage = friendCursor.getString(4);
			friend.strSex = friendCursor.getString(5);
			friend.strGrade = friendCursor.getString(6);
			friend.strAddressSi = friendCursor.getString(7);
			friend.strAddressGu = friendCursor.getString(8);
			friend.strAddressDong = friendCursor.getString(9);
			friend.iIsFavorite = friendCursor.getInt(10);
			friend.iIsFlag = friendCursor.getInt(11);
			friend.iIsBlack = friendCursor.getInt(12);
			friendlist.add(friend);
		}

		friendCursor.close();
		return friendlist;

	}

	public Friend selectFriendDB(String id) {
		Friend friend = null;

		friendCursorCheck();

		friendCursor = db.rawQuery("select * from friend where " + friend_id + "='" + id + "';", null);

		if (friendCursor.moveToNext()) {
			friend = new Friend();
			friend.strID = friendCursor.getString(0);
			friend.strName = friendCursor.getString(1);
			friend.strImage = friendCursor.getString(3);
			friend.strMessage = friendCursor.getString(4);
			friend.strSex = friendCursor.getString(5);
			friend.strGrade = friendCursor.getString(6);
			friend.strAddressSi = friendCursor.getString(7);
			friend.strAddressGu = friendCursor.getString(8);
			friend.strAddressDong = friendCursor.getString(9);
			friend.iIsFavorite = friendCursor.getInt(10);
			friend.iIsFlag = friendCursor.getInt(11);
			friend.iIsBlack = friendCursor.getInt(12);
			Log.e("!!!!!!!!!!!!!", friend.iIsFlag + "");
		}
		friendCursor.close();
		System.out.println("~~~~~~~~~~~~~~~" + friend);
		return friend;
	}

	public boolean selectFriendDB1(String id) {
		friendCursorCheck();

		friendCursor = db.rawQuery("select * from friend where " + friend_id + "='" + id + "';", null);

		if (friendCursor.getCount() > 0)
			return true;
		return false;
	}

	public boolean selectLinkDB1(String id) {
		linkCursorCheck();

		linkCursor = db.rawQuery("select * from link where " + link_id + "='" + id + "';", null);

		if (linkCursor.getCount() > 0)
			return true;
		return false;
	}

	public Link selectLinkDB(String id) {
		Link link = null;

		friendCursorCheck();

		linkCursor = db.rawQuery("select * from " + table_link + " where " + link_id + "='" + id + "';", null);
		// c.moveToFirst();
		if (linkCursor.moveToNext()) {
			link = new Link();
			link.strID = linkCursor.getString(0);
			link.strName = linkCursor.getString(1);
			link.strTel = linkCursor.getString(2);
			link.strlink_x = linkCursor.getString(3);
			link.strlink_y = linkCursor.getString(4);
			link.strAddress_si = linkCursor.getString(5);
			link.strAddress_gu = linkCursor.getString(6);
			link.strAddress_dong = linkCursor.getString(7);
			link.strAddress_text = linkCursor.getString(8);
			link.strlink_parking = linkCursor.getString(9);
			link.strlink_system = linkCursor.getString(10);
			link.iIsFavorite = linkCursor.getInt(11);
			link.iIsFlag = linkCursor.getInt(12);
		}
		linkCursor.close();
		return link;
	}
}
