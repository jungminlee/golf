package com.ilovegolf.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class SocketIO {
	Socket socket;
	public InputStream is;
	public OutputStream os;
	public Boolean socketAlive = true;
	private ConcurrentLinkedQueue<String> messageList = null;
	public boolean chatlogin = false;

	ByteArrayOutputStream tempStrBuffer = null;
	ByteArrayOutputStream buffer = null;

	int init = 0;

	public SocketIO(Socket s) throws IOException {
		this.socket = s;

		is = socket.getInputStream();
		os = socket.getOutputStream();
		messageList = new ConcurrentLinkedQueue<String>();
	}

	public void Init() {
		if (init == 0) {
			tempStrBuffer = new ByteArrayOutputStream(2048);
			buffer = new ByteArrayOutputStream(2048);
			init++;
		}
	}

	public SocketIO(String host, int port) throws IOException {
		this(new Socket(host, port));
	}

	public InetAddress getInetAddress() {
		return socket.getInetAddress();
	}

	public boolean isBound() {
		return socket.isBound();
	}

	public boolean isConnected() {
		return socket.isConnected();
	}

	public boolean isClosed() {
		return socket.isClosed();
	}

	public boolean sendMessage(String send) throws IOException {
		os.write(send.getBytes("utf-8"), 0, send.getBytes("utf-8").length);
		os.flush();
		return true;
	}
	protected Drawable getDrawable(int displayWidth, int displayHeight, String imgFilePath) {
		File file = new File(imgFilePath);
		try {
			if (file.isFile()) {
				FileInputStream is = new FileInputStream(file);
				Drawable draw = Drawable.createFromResourceStream(null, null, is, "picture", StaticClass.getBitmapOption(displayWidth, displayHeight, imgFilePath));
				is.close();
				is = null;
				return draw;
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	public boolean sendImage(String myID) throws IOException {
		File copyfile = new File("/mnt/sdcard/golfpic/profile");
		FileInputStream fis = new FileInputStream(copyfile);

		byte[] buf = new byte[2048];
		String send = "BEGIN UPDATEMEMBERIMANGE\r\n";
		send += "Member_ID:" + myID + "\r\n";
		send += "Member_Image:" + myID + System.currentTimeMillis() + "\r\n";
		send += "Path:y\r\n";
		send += "Size:" + copyfile.length() + "\r\n";
		send += "END\r\n";
		sendMessage(send);
		System.out.println(send);

		while (fis.read(buf) > -1) {
			os.write(buf);
			os.flush();
		}
		os.close();
		fis.close();
		return true;
	}

	public void readMessage(byte[] b, int len) throws IOException {

		Init();

		if (b != null) {
			if (tempStrBuffer.toByteArray().length != 0) {
				buffer.write(tempStrBuffer.toByteArray(), 0, tempStrBuffer.toByteArray().length);
				tempStrBuffer.reset();
			}
			buffer.write(b, 0, len);
			String str = new String(buffer.toByteArray(), "UTF-8");
			String endstr = str.substring(str.indexOf("BEGIN "), str.lastIndexOf("END\r\n") + "END\r\n".length());
			if (!str.endsWith("END\r\n")&&buffer.toByteArray().length!=0) {
				// tempStrBuffer.write(buffer.toByteArray(), str.lastIndexOf("END\r\n") + "END\r\n".length(), buffer.toByteArray().length - (str.lastIndexOf("END\r\n") + "END\r\n".length()));
				tempStrBuffer.write(buffer.toByteArray(), endstr.getBytes("UTF-8").length, buffer.toByteArray().length - endstr.getBytes().length);
			}

			// str = str.substring(str.indexOf("BEGIN "), str.lastIndexOf("END\r\n") + "END\r\n".length());
			buffer.reset();

			String[] msg = endstr.split("END\r\n");

			for (String cutmsg : msg) {
				messageList.add(cutmsg);
			}
		}
	}

	public boolean isReadMessage() throws IOException {
		byte[] b = new byte[1024];
		int len;
		if ((len = is.read(b)) > 0) {
			readMessage(b, len);
			return true;
		}
		return false;
	}

	public String getMessage() throws IOException {
		if (messageList.isEmpty()) {
			if (isReadMessage())
				return messageList.poll();
			else
				return null;
		}
		return messageList.poll();
	}

	public void close() throws IOException {
		if (os instanceof OutputStream)
			os.close();
		if (is instanceof InputStream)
			is.close();
		if (socket instanceof Socket && socket.isConnected())
			socket.close();
	}

	protected void finalize() throws Throwable {
		close();
		super.finalize();
	}

	public StringHashMap MessageSplit(String fullMessage) {
		// String[] endcut = fullMessage.split("END\r\n");
		String[] arr = fullMessage.split("\r\n");
		StringHashMap message = new StringHashMap();
		for (int i = 1; i < arr.length; i++) {
			String[] key_value = arr[i].split(":");
			if (key_value.length > 1) {
				message.put(key_value[0], key_value[1]);
			}
		}
		return message;
	}
}
