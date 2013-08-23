package com.ilovegolf;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Date;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Message;
import android.util.Log;

import com.ilovegolf.struct.ChatRoom;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.struct.InputAddress;
import com.ilovegolf.struct.Link;
import com.ilovegolf.struct.RecvMessage;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;
import com.ilovegolf.util.StringHashMap;

public class T00_00_ReceiveThread extends Thread {

	public int login = 0;
	public int refresh = 1;
	public int join = 2;

	SharedPreferences sp = null;
	Editor edit = null;

	Message hanMessage = null;

	Context context = null;

	boolean threadflag = true;

	public T00_00_ReceiveThread(Context context) {
		this.context = context;
		sp = context.getSharedPreferences("ilovegolfPrefer", 0);
		edit = sp.edit();
	}

	@Override
	public void run() {

		while (threadflag) {
			String message = null;
			try {
				if (StaticClass.DataSoc == null) {
					StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);
				} else if (!StaticClass.DataSoc.isConnected() || StaticClass.DataSoc.isClosed()) {
					StaticClass.DataSoc = new SocketIO(StaticClass.IP, StaticClass.PORT);
				}
				if ((message = StaticClass.DataSoc.getMessage()) != null) {
					Log.e("recvmsg", message + "");
					if (message.indexOf("ADDRESSGUFINISH") != -1) {
						hanMessage = new Message();
						hanMessage.arg1 = StaticClass.ADDRESS_GU;
						StaticClass.handler.sendMessage(hanMessage);

					} else if (message.indexOf("ADDRESSDONGFINISH") != -1) {
						hanMessage = new Message();
						hanMessage.arg1 = StaticClass.ADDRESS_DONG;
						StaticClass.handler.sendMessage(hanMessage);

						edit.putBoolean("sidown", true);
						edit.commit();

					} else if (message.indexOf("ADDRESSSIFINISH") != -1) {
						edit.putBoolean("sidown", true);
						edit.commit();
					} else if (message.indexOf("FINISH") != -1) {
						hanMessage = new Message();
						hanMessage.arg1 = StaticClass.REFRESH;
						StaticClass.handler.sendMessage(hanMessage);
					}

					StringHashMap msg = StaticClass.DataSoc.MessageSplit(message);
					if (message.indexOf("PING") != -1) {
						String send = "BEGIN PING\r\n";
						send += "KEY:ALIVE\r\n";
						send += "END\r\n";
						StaticClass.DataSoc.sendMessage(send);
						Log.e("recv", "recvPING");
					} else if (message.indexOf("ADDRESSSI") != -1) {
						if (!sp.getBoolean("sidown", false)) {
							synchronized (StaticClass.dbm) {
								String si = msg.getString("Address_si");
								System.out.println("recvSISISISIS" + si);
								StaticClass.dbm.insertAddressDB(si);
							}
						}
					} else if (message.indexOf("ADDRESSGU") != -1) {
						InputAddress add = new InputAddress();
						add.strAddress_si = msg.getString("Address_si");
						synchronized (StaticClass.dbm) {
							StaticClass.dbm.insertAddressDB(add.strAddress_si, msg.getString("Address_gu"));
						}
					} else if (message.indexOf("ADDRESSDONG") != -1) {
						InputAddress add = new InputAddress();
						add.strAddress_si = msg.getString("Address_si");
						add.strAddress_gu = msg.getString("Address_gu");
						synchronized (StaticClass.dbm) {
							StaticClass.dbm.insertAddressDB(msg.getString("Address_si"), msg.getString("Address_gu"), msg.getString("Address_dong"));
						}
					} else if (message.indexOf("INSERTMEMBERACK") != -1) {
						if (msg.getString("CODE").equals("400")) {
							hanMessage = new Message();
							hanMessage.arg1 = 3;
							StaticClass.handler.sendMessage(hanMessage);
						} else if (msg.getString("CODE").equals("100")) {
							hanMessage = new Message();
							hanMessage.arg1 = 2;
							StaticClass.handler.sendMessage(hanMessage);
						}
					} else if (message.indexOf("LOGINACK") != -1) {
						if (msg.containsKey("CODE")) {
							if (msg.getString("CODE").equals(StaticClass.Fail)) {
								hanMessage = new Message();
								hanMessage.arg1 = StaticClass.LOGIN_FAIL;
								StaticClass.handler.sendMessage(hanMessage);
							}
						} else {
							edit.putString("myName", msg.getString("Member_Name"));
							edit.putString("myImage", msg.getString("Member_Image"));
							edit.putString("myPhone", msg.getString("Member_Phone"));
							edit.putString("myMessage", msg.getString("Member_Message"));
							edit.putInt("myIsAlarm", Integer.parseInt(msg.getString("Member_isAlarm")));
							edit.putInt("myIsVibrate", Integer.parseInt(msg.getString("Member_isVibrate")));
							edit.putInt("myIsRing", Integer.parseInt(msg.getString("Member_isRing")));
							edit.putInt("myIsFind", Integer.parseInt(msg.getString("Member_isFind")));
							edit.putString("mySex", msg.getString("Member_Sex"));
							edit.putString("myGrade", msg.getString("Member_Grade"));
							edit.putString("myAddress_si", msg.getString("Member_Address_si"));
							edit.putString("myAddress_gu", msg.getString("Member_Address_gu"));
							edit.putString("myAddress_dong", msg.getString("Member_Address_dong"));
							edit.putInt("myIsAcount", Integer.parseInt(msg.getString("Member_isAcount")));
							// edit.putLong("myAcountDate", Long.parseLong(msg.getString("Member_AcountDate")));
							edit.commit();

							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.LOGIN_SUCC;
							StaticClass.handler.sendMessage(hanMessage);
						}
					} else if (message.indexOf("SELECTMEMBERACK") != -1) {
						Friend friend = new Friend();
						friend.strID = msg.getString("Friend_ID");
						friend.strName = msg.getString("Friend_Name");
						friend.strPhone = msg.getString("Friend_Phone");
						friend.strImage = msg.getString("Friend_Image");
						friend.strMessage = msg.getString("Friend_Message");
						friend.strSex = msg.getString("Friend_Sex");
						friend.strGrade = msg.getString("Friend_Grade");
						friend.strAddressSi = msg.getString("Friend_Address_si");
						friend.strAddressGu = msg.getString("Friend_Address_gu");
						friend.strAddressDong = msg.getString("Friend_Address_dong");

						synchronized (StaticClass.dbm) {
							if (StaticClass.dbm.selectFriendDB1(friend.strID)) {
								Friend check = StaticClass.dbm.selectFriendDB(friend.strID);
								if (friend.strImage != null) {
									System.out.println("check.strImage" + check.strImage + "///////friend.strImage" + friend.strImage);
									if (!check.strImage.equals(friend.strImage))
										friend.strImage += "*";
								}
								System.out.println("^_^_^_^^_^_^_^_^_^_^_^_^_^" + check.iIsFavorite);
								friend.iIsFavorite = check.iIsFavorite;
								friend.iIsFlag = check.iIsFlag;
								StaticClass.dbm.updateFriendDB(friend);
								System.out.println("있으니까 갱신할꺼야");
							} else {
								friend.strImage += "*";
								StaticClass.dbm.insertFriendDB(friend);
								System.out.println("없으니까 입력함");

							}
						}

					} else if (message.indexOf("SELECTLINKACK") != -1) {
						Link link = new Link();
						link.strID = msg.getString("Link_ID");
						link.strName = msg.getString("Link_Name");
						link.strTel = msg.getString("Link_Tel");
						link.strAddress_si = msg.getString("Link_Address_si");
						link.strAddress_gu = msg.getString("Link_Address_gu");
						link.strAddress_dong = msg.getString("Link_Address_dong");
						link.strAddress_text = msg.getString("Link_Address_text");
						link.strlink_x = msg.getString("Link_X");
						link.strlink_y = msg.getString("Link_Y");
						link.strlink_system = msg.getString("Link_System");
						link.strlink_parking = msg.getString("Link_Parking");
						synchronized (StaticClass.dbm) {
							if (StaticClass.dbm.selectLinkDB1(link.strID)) {
								Link check = StaticClass.dbm.selectLinkDB(link.strID);
								link.iIsFavorite = check.iIsFavorite;
								StaticClass.dbm.updateLinkDB(link);
							} else {
								StaticClass.dbm.insertLinkDB(link);
							}
						}
					} else if (message.indexOf("SENDMESSAGEACK") != -1) {
						hanMessage = new Message();
						hanMessage.arg1 = 6;
						StaticClass.handler.sendMessage(hanMessage);
					} else if (message.indexOf("OLDSENDMESSAGE") != -1) {
						String messageid = msg.getString("Message_ID");
						String sender = msg.getString("Sender_ID");

						if (!StaticClass.dbm.selectFriendBlackDB(sender)) {
							RecvMessage recv = new RecvMessage();
							recv.strPeopleID = sender;
							recv.strPeopleName = msg.getString("Sender_Name");
							recv.strContent = msg.getString("Message_content");
							recv.strDate = msg.getString("Message_date");
							recv.iCode = Integer.parseInt(msg.getString("Message_code"));

							ChatRoom chatroom = new ChatRoom();
							chatroom.strRoomID = recv.strPeopleID;
							chatroom.strRoomName = recv.strPeopleName;
							chatroom.strUpdateDate = recv.strDate.replace("^", ":");
							chatroom.strUpdateMessage = recv.strContent;

							synchronized (StaticClass.dbm) {
								Friend friend = StaticClass.dbm.selectFriendDB(chatroom.strRoomID);
								friend.iIsFlag = 3;
								StaticClass.dbm.updateFriendDB(friend);

								StaticClass.dbm.insertMessageDB(recv);
								if (!StaticClass.dbm.selectChatRoomDB(chatroom.strRoomID)) {
									StaticClass.dbm.insertChatRoomDB(chatroom);
								} else {
									StaticClass.dbm.updateChatRoomDB(chatroom);
								}
							}

							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.MESSAGE_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);

						}
						String send = "BEGIN SENDMESSAGEACK\r\n";
						send += "Message_ID:" + messageid + "\r\n";
						send += "END\r\n";
						StaticClass.DataSoc.sendMessage(send);

					} else if (message.indexOf("SENDMESSAGE") != -1) {
						String messageid = msg.getString("Message_ID");

						String sender = msg.getString("Sender_ID");

						System.out.println("확인해본당");
						if (!StaticClass.dbm.selectFriendBlackDB(sender)) {
							System.out.println("차단안했음");


							RecvMessage recv = new RecvMessage();
							recv.strPeopleID = sender;
							recv.strPeopleName = msg.getString("Sender_Name");
							recv.strContent = msg.getString("Message_content");
							recv.strDate = msg.getString("Message_date");
							recv.iCode = Integer.parseInt(msg.getString("Message_code"));

							ChatRoom chatroom = new ChatRoom();
							chatroom.strRoomID = recv.strPeopleID;
							chatroom.strRoomName = recv.strPeopleName;
							chatroom.strUpdateDate = recv.strDate.replace("^", ":");
							;
							chatroom.strUpdateMessage = recv.strContent;

							synchronized (StaticClass.dbm) {
								Friend friend = StaticClass.dbm.selectFriendDB(chatroom.strRoomID);
								friend.iIsFlag = 3;
								StaticClass.dbm.updateFriendDB(friend);

								StaticClass.dbm.insertMessageDB(recv);
								if (!StaticClass.dbm.selectChatRoomDB(chatroom.strRoomID)) {
									StaticClass.dbm.insertChatRoomDB(chatroom);
								} else {
									StaticClass.dbm.updateChatRoomDB(chatroom);
								}
							}

							if (!recv.strPeopleID.equals(StaticClass.chatmember) && sp.getInt("myIsAlarm", 0) == 1) {
								final String gcmMsg = "메세지가 도착했습니다";
								final String gcmTitle = "아이러브골프";
								NotificationManager mNotiManager = (NotificationManager) context.getSystemService("notification");
								Notification noti = new Notification(R.drawable.noti, gcmMsg, System.currentTimeMillis());
								if (sp.getInt("myIsVibrate", 0) == 1) {
									if (sp.getInt("myIsRing", 0) == 1)
										noti.defaults |= (Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
									else
										noti.defaults |= Notification.DEFAULT_VIBRATE;
								} else
									noti.defaults = 0;

								noti.flags |= Notification.FLAG_AUTO_CANCEL;
								// noti.flags = Notification.FLAG_ONLY_ALERT_ONCE;
								Intent intent = new Intent(context, A04_00_RecvMessageList.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.putExtra("id", recv.strPeopleID);
								PendingIntent content = PendingIntent.getActivity(context, 0, intent, 0);
								noti.setLatestEventInfo(context, gcmTitle, gcmMsg, content);
								mNotiManager.notify(1, noti);

							}

							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.MESSAGE_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);
						}
						String send = "BEGIN SENDMESSAGEACK\r\n";
						send += "Member_ID:" + sp.getString("MyID", "");
						send += "Message_ID:" + messageid + "\r\n";
						send += "END\r\n";
						StaticClass.DataSoc.sendMessage(send);

					} else if (message.indexOf("CREATECHATROOM") != -1) {
						String sender = msg.getString("Sender_ID");

						if (!StaticClass.dbm.selectFriendBlackDB(sender)) {
							ChatRoom chatroom = new ChatRoom();
							synchronized (StaticClass.dbm) {
								String friendid = sender;
								if (StaticClass.dbm.selectFriendDB1(friendid)) {
									Friend friend = StaticClass.dbm.selectFriendDB(msg.getString("Sender_ID"));
									chatroom.strRoomID = friend.strID;
									chatroom.strRoomName = friend.strName;
									chatroom.strUpdateDate = StaticClass.format.format(new Date()).replace("^", ":");
								} else {
									Friend friend = new Friend();
									friend.strID = friendid;
									friend.strName = msg.getString("Sender_Name");
									friend.strMessage = msg.getString("Sender_Message");
									friend.strImage = msg.getString("Sender_Image");
									friend.strSex = msg.getString("Sender_Sex");
									friend.strGrade = msg.getString("Sender_Grade");
									// 시, 동, 구 추가
									StaticClass.dbm.insertFriendDB(friend);
								}
							}
							synchronized (StaticClass.dbm) {
								if (!StaticClass.dbm.selectChatRoomDB(chatroom.strRoomID)) {
									StaticClass.dbm.insertChatRoomDB(chatroom);
								}
							}

							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);
						}

						String send = "BEGIN CREATECHATROOMACK\r\n";
						send += "Sender_ID:" + sender + "\r\n";
						send += "Recver_ID:" + sp.getString("myID", "") + "\r\n";
						send += "END\r\n";
						StaticClass.DataSoc.sendMessage(send);

					} else if (message.indexOf("OLDSENDREQUEST") != -1) {

						String sender = msg.getString("Sender_ID");

						if (!StaticClass.dbm.selectFriendBlackDB(sender)) {
							Friend friend = null;
							synchronized (StaticClass.dbm) {
								friend = StaticClass.dbm.selectFriendDB(sender);
							}

							friend.iIsFlag = 2;

							StaticClass.dbm.updateFriendDB(friend);
							ChatRoom chatroom = new ChatRoom();
							chatroom.strRoomID = friend.strID;
							chatroom.strUpdateDate = StaticClass.format.format(new Date()).replace("^", ":");
							StaticClass.dbm.updateChatRoomDB(chatroom);

							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);

							String send = "BEGIN SENDREQUESTACK\r\n";
							send += "Sender_ID:" + friend.strID + "\r\n";
							send += "Recver_ID:" + sp.getString("myID", "") + "\r\n";
							send += "END\r\n";
							StaticClass.DataSoc.sendMessage(send);

							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);
						}
					} else if (message.indexOf("SENDREQUEST") != -1) {
						String sender = msg.getString("Sender_ID");

						if (!StaticClass.dbm.selectFriendBlackDB(sender)) {
							Friend friend = null;
							synchronized (StaticClass.dbm) {
								friend = StaticClass.dbm.selectFriendDB(sender);
							}

							friend.iIsFlag = 2;

							StaticClass.dbm.updateFriendDB(friend);

							ChatRoom chatroom = new ChatRoom();
							chatroom.strRoomID = friend.strID;
							chatroom.strUpdateDate = StaticClass.format.format(new Date()).replace("^", ":");
							StaticClass.dbm.updateChatRoomDB(chatroom);

							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);

							String send = "BEGIN SENDREQUESTACK\r\n";
							send += "Sender_ID:" + friend.strID + "\r\n";
							send += "Recver_ID:" + sp.getString("myID", "") + "\r\n";
							send += "END\r\n";
							StaticClass.DataSoc.sendMessage(send);

							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);

							if (!friend.strID.equals(StaticClass.chatmember) && sp.getInt("myIsAlarm", 0) == 1) {
								final String gcmMsg = "골프요청이 도착했습니다";
								final String gcmTitle = "아이러브골프";
								NotificationManager mNotiManager = (NotificationManager) context.getSystemService("notification");
								Notification noti = new Notification(R.drawable.noti, gcmMsg, System.currentTimeMillis());
								if (sp.getInt("myIsVibrate", 0) == 1) {
									if (sp.getInt("myIsRing", 0) == 1)
										noti.defaults |= (Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
									else
										noti.defaults |= Notification.DEFAULT_VIBRATE;
								} else
									noti.defaults = 0;

								noti.flags |= Notification.FLAG_AUTO_CANCEL;
								// noti.flags = Notification.FLAG_ONLY_ALERT_ONCE;
								Intent intent = new Intent(context, A04_00_ChattingRoom.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								PendingIntent content = PendingIntent.getActivity(context, 0, intent, 0);
								noti.setLatestEventInfo(context, gcmTitle, gcmMsg, content);
								mNotiManager.notify(1, noti);

							}
						}
					} else if (message.indexOf("OLDACCEPTREQUEST") != -1) {
						String sender = msg.getString("Sender_ID");

						if (!StaticClass.dbm.selectFriendBlackDB(sender)) {
							Friend friend = null;
							synchronized (StaticClass.dbm) {
								friend = StaticClass.dbm.selectFriendDB(sender);
							}
							friend.iIsFlag = 3;
							synchronized (StaticClass.dbm) {
								StaticClass.dbm.updateFriendDB(friend);
							}
							ChatRoom chatroom = new ChatRoom();
							chatroom.strRoomID = friend.strID;
							chatroom.strUpdateDate = StaticClass.format.format(new Date()).replace("^", ":");
							StaticClass.dbm.updateChatRoomDB(chatroom);

							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);

							String send = "BEGIN ACCEPTREQUESTACK\r\n";
							send += "Sender_ID:" + friend.strID + "\r\n";
							send += "Recver_ID:" + sp.getString("myID", "") + "\r\n";
							send += "END\r\n";
							StaticClass.DataSoc.sendMessage(send);

							Log.e("???????", "두번오나???");
							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);
						}
					} else if (message.indexOf("ACCEPTREQUEST") != -1) {
						String sender = msg.getString("Sender_ID");

						if (!StaticClass.dbm.selectFriendBlackDB(sender)) {
							Friend friend = null;
							synchronized (StaticClass.dbm) {
								friend = StaticClass.dbm.selectFriendDB(sender);
							}
							friend.iIsFlag = 3;
							synchronized (StaticClass.dbm) {
								StaticClass.dbm.updateFriendDB(friend);
							}
							ChatRoom chatroom = new ChatRoom();
							chatroom.strRoomID = friend.strID;
							chatroom.strUpdateDate = StaticClass.format.format(new Date()).replace("^", ":");
							StaticClass.dbm.updateChatRoomDB(chatroom);

							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);

							String send = "BEGIN ACCEPTREQUESTACK\r\n";
							send += "Sender_ID:" + friend.strID + "\r\n";
							send += "Recver_ID:" + sp.getString("myID", "") + "\r\n";
							send += "END\r\n";
							StaticClass.DataSoc.sendMessage(send);

							Log.e("???????", "두번오나???");
							hanMessage = new Message();
							hanMessage.arg1 = StaticClass.CHATROOM_REFRESH;
							StaticClass.handler.sendMessage(hanMessage);

							if (!friend.strID.equals(StaticClass.chatmember) && sp.getInt("myIsAlarm", 0) == 1) {
								final String gcmMsg = "골프요청 수락하셨습니다";
								final String gcmTitle = "아이러브골프";
								NotificationManager mNotiManager = (NotificationManager) context.getSystemService("notification");
								Notification noti = new Notification(R.drawable.noti, gcmMsg, System.currentTimeMillis());
								if (sp.getInt("myIsVibrate", 0) == 1) {
									if (sp.getInt("myIsRing", 0) == 1)
										noti.defaults |= (Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
									else
										noti.defaults |= Notification.DEFAULT_VIBRATE;
								} else
									noti.defaults = 0;

								noti.flags |= Notification.FLAG_AUTO_CANCEL;
								// noti.flags = Notification.FLAG_ONLY_ALERT_ONCE;
								Intent intent = new Intent(context, A04_00_ChattingRoom.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								PendingIntent content = PendingIntent.getActivity(context, 0, intent, 0);
								noti.setLatestEventInfo(context, gcmTitle, gcmMsg, content);
								mNotiManager.notify(1, noti);

							}
						}
					}
				}
			} catch (IOException e) {
				StaticClass.DataSoc = null;
			} catch (Exception e) {
				Log.e("error", e.getMessage() + "");

			}
		}
	}
}
