package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ilovegolf.R;


public class L04_01_RecvMessageListLayout extends B00_01_BarLayout{
	LinearLayout l04_01_layout_recvmessage = null;
	public ListView l04_01_list_message = null;
	public EditText l04_01_edit_message = null;
	public View l04_01_btn_send = null;
	public ImageView l04_01_img_mypic = null;


	public View getLayout(Context context, Display display) {
		super.getLayout(context, display);
		
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingLeft = 0;
		int paddingTop = 0;
		int paddingRight = 0;
		int paddingBottom = 0;

		LinearLayout.LayoutParams params = null;


		b00_01_text_bar.setBackgroundResource(R.drawable.golf_chat_bar);
		
		l04_01_layout_recvmessage = new LinearLayout(context);
		l04_01_layout_recvmessage.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		l04_01_layout_recvmessage.setOrientation(LinearLayout.VERTICAL);
		l04_01_layout_recvmessage.setBackgroundColor(Color.WHITE);
		b00_01_linear_content.addView(l04_01_layout_recvmessage);
		{
			l04_01_list_message = new ListView(context);
			params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			params.weight = 1;
			l04_01_list_message.setLayoutParams(params);
			l04_01_list_message.setDivider(null);
			l04_01_layout_recvmessage.addView(l04_01_list_message);

			LinearLayout linear5 = new LinearLayout(context);
			linear5.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			linear5.setGravity(Gravity.CENTER);
			linear5.setBackgroundResource(R.drawable.talk_input_bg);
			l04_01_layout_recvmessage.addView(linear5);

			{
				tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
				paddingLeft = ((int) (((float) 5 * (float) viewWidth) / (float) 480));
				paddingTop = ((int) (((float) 5 * (float) viewHight) / (float) 800));
				l04_01_edit_message = new EditText(context);
				params = new LinearLayout.LayoutParams(100, LayoutParams.WRAP_CONTENT);
				params.weight = 1;
				params.setMargins(paddingLeft, paddingTop, paddingLeft, paddingTop);
				l04_01_edit_message.setLayoutParams(params);
				l04_01_edit_message.setMinHeight(tempHeight);
				l04_01_edit_message.setBackgroundResource(R.drawable.talk_input);
				l04_01_edit_message.setMaxHeight(tempHeight);
				linear5.addView(l04_01_edit_message);

				tempWidth = ((int) (((float) 57 * (float) viewWidth) / (float) 480));
				tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
				l04_01_btn_send = new View(context);
				params = new LinearLayout.LayoutParams(tempWidth, tempHeight);
				params.setMargins(0, paddingTop, paddingLeft, paddingTop);
				l04_01_btn_send.setLayoutParams(params);
				l04_01_btn_send.setBackgroundResource(R.drawable.btn_chat_send_off);
				linear5.addView(l04_01_btn_send);
			}
		}
		return b00_01_barlayout;
	}
}
