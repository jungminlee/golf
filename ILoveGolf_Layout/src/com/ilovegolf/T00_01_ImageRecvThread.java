package com.ilovegolf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.util.Log;

import com.ilovegolf.adapter.A02_00_FriendAdapter;
import com.ilovegolf.struct.DownImage;
import com.ilovegolf.struct.Friend;
import com.ilovegolf.util.SocketIO;
import com.ilovegolf.util.StaticClass;

public class T00_01_ImageRecvThread extends Thread {
	File file = null;
	String i;

	@Override
	public void run() {
		File path = new File("/mnt/sdcard/golfpic");
		if (!path.isDirectory()) {
			path.mkdir();
		}
		try {

			while (!A02_00_FriendAdapter.imagedownlist.isEmpty()) {

				Iterator<String> iter = A02_00_FriendAdapter.imagedownlist.keySet().iterator();
				while (iter.hasNext()) {
					i = iter.next();
					DownImage downimage = A02_00_FriendAdapter.imagedownlist.get(i);

					System.out.println("누구꺼받아???" + i);
					try {
						if (downimage.bFlag == false) {

							System.out.println("누구꺼받아??????????" + downimage.bFlag);

							StaticClass.ImageSoc = new SocketIO(StaticClass.IP, 2008);

							String send = "BEGIN SMALLIMAGE\r\n";
							send += downimage.strImage + "\r\n";
							send += "END\r\n";
							StaticClass.ImageSoc.sendMessage(send);

							byte[] b = new byte[5096];
							byte[] lengthb = new byte[1];
							StaticClass.ImageSoc.is.read(lengthb, 0, 1);
							String stringlength = new String(lengthb, "utf-8");
							int length = Integer.parseInt(stringlength);
							byte[] sizeb = new byte[length];
							StaticClass.ImageSoc.is.read(sizeb, 0, length);
							String stringsize = new String(sizeb, "utf-8");
							int size = Integer.parseInt(stringsize);

							File file = new File("/mnt/sdcard/golfpic/" + downimage.strId);
							FileOutputStream fos = new FileOutputStream(file);
							int len = 0;
							for (; size >= 0; size -= len) {
								System.out.println("남은 크기 ::::::::::::::::" + size);
								len = StaticClass.ImageSoc.is.read(b);
								fos.write(b, 0, len);
							}

							fos.close();
							downimage.bFlag = true;
							StaticClass.ImageSoc.close();
						}
					} catch (NullPointerException e) {
						Log.e("error", e.getMessage()+"");
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StaticClass.imagethreadisalive=false;
	}
}
