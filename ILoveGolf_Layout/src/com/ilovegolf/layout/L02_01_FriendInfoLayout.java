package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ilovegolf.R;

public class L02_01_FriendInfoLayout {
	public LinearLayout l02_01_layout_friendinfo = null;
	public LinearLayout l02_01_layout_temp = null;
	public ImageView l02_01_img_pic = null;
	public TextView l02_01_text_name = null;
	public View l02_01_btn_favorite = null;
	public View l02_01_btn_close = null;
	public TextView l02_01_text_address = null;
	public TextView l02_01_text_msg = null;
	public View l02_01_btn_sendMessage = null;
	public RelativeLayout relative=null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingleft = 0;
		int paddingtop = 0;
		int paddingright = 0;
		int paddingbottom = 0;

		l02_01_layout_friendinfo = new LinearLayout(context);
		l02_01_layout_friendinfo.setBackgroundColor(Color.alpha(0));
		l02_01_layout_friendinfo.setOrientation(LinearLayout.VERTICAL);
		l02_01_layout_friendinfo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		{
			l02_01_layout_temp = new LinearLayout(context);
			LinearLayout.LayoutParams tempparam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			tempparam.weight = 1;
			l02_01_layout_temp.setLayoutParams(tempparam);
			l02_01_layout_temp.setBackgroundColor(Color.alpha(0));
			l02_01_layout_friendinfo.addView(l02_01_layout_temp);

			tempHeight = ((int) (((float) 226 * (float) viewHight) / (float) 800));
			relative = new RelativeLayout(context);
			relative.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			l02_01_layout_friendinfo.addView(relative);
			{
				LinearLayout base = new LinearLayout(context);
				base.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				relative.addView(base);
				{
					paddingleft = ((int) (((float) 22 * (float) viewWidth) / (float) 480));
					paddingtop = ((int) (((float) 14 * (float) viewHight) / (float) 800));
					tempWidth = ((int) (((float) 111 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 111 * (float) viewHight) / (float) 800));
					l02_01_img_pic = new ImageView(context);
					LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
					param.setMargins(paddingleft, paddingtop, 0, 0);
					l02_01_img_pic.setLayoutParams(param);
					l02_01_img_pic.setBackgroundColor(Color.WHITE);
					base.addView(l02_01_img_pic);
				}
				LinearLayout linear = new LinearLayout(context);
				linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				linear.setBackgroundResource(R.drawable.profile_bg);
				linear.setOrientation(LinearLayout.VERTICAL);
				relative.addView(linear);

				{
					tempHeight = ((int) (((float) 49 * (float) viewHight) / (float) 800));
					LinearLayout linear2 = new LinearLayout(context);
					linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
					linear.addView(linear2);
					{

						paddingleft = ((int) (((float) 168 * (float) viewWidth) / (float) 480));
						paddingtop = ((int) (((float) 29 * (float) viewHight) / (float) 800));
						l02_01_text_name = new TextView(context);
						LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
						param3.setMargins(paddingleft, paddingtop, 0, 0);
						param3.weight = 1;
						l02_01_text_name.setLayoutParams(param3);
						l02_01_text_name.setText("골프여신");
						l02_01_text_name.setTextColor(Color.YELLOW);
						linear2.addView(l02_01_text_name);

						paddingtop = ((int) (((float) 15 * (float) viewHight) / (float) 800));
						tempWidth = ((int) (((float) 29 * (float) viewWidth) / (float) 480));
						tempHeight = ((int) (((float) 29 * (float) viewHight) / (float) 800));
						l02_01_btn_favorite = new View(context);
						LinearLayout.LayoutParams param5 = new LinearLayout.LayoutParams(tempWidth, tempHeight);
						param5.setMargins(0, paddingtop, 0, 0);
						l02_01_btn_favorite.setLayoutParams(param5);
						l02_01_btn_favorite.setBackgroundResource(R.drawable.btn_profile_favorite);
						linear2.addView(l02_01_btn_favorite);

						paddingleft = ((int) (((float) 10 * (float) viewWidth) / (float) 480));
						paddingright = ((int) (((float) 22 * (float) viewWidth) / (float) 480));
						l02_01_btn_close = new View(context);
						param5 = new LinearLayout.LayoutParams(tempWidth, tempHeight);
						param5.setMargins(paddingleft, paddingtop, paddingright, 0);
						l02_01_btn_close.setLayoutParams(param5);
						l02_01_btn_close.setBackgroundResource(R.drawable.btn_profile_close_off);
						linear2.addView(l02_01_btn_close);
					}
					paddingleft = ((int) (((float) 168 * (float) viewWidth) / (float) 480));
					paddingtop = ((int) (((float) 5 * (float) viewHight) / (float) 800));
					paddingright = ((int) (((float) 42 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 20 * (float) viewHight) / (float) 800));
					l02_01_text_address = new TextView(context);
					LinearLayout.LayoutParams param6 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, tempHeight);
					param6.setMargins(paddingleft, paddingtop, paddingright, 0);
					l02_01_text_address.setLayoutParams(param6);
					l02_01_text_address.setTextColor(Color.WHITE);
					l02_01_text_address.setText("초급 여 37세 서울시 강서구");
					l02_01_text_address.setMaxLines(1);
					l02_01_text_address.setTextSize(13);
					linear.addView(l02_01_text_address);

					paddingleft = ((int) (((float) 170 * (float) viewWidth) / (float) 480));
					paddingtop = ((int) (((float) 9 * (float) viewHight) / (float) 800));
					paddingright = ((int) (((float) 44 * (float) viewWidth) / (float) 480));
					paddingbottom = ((int) (((float) 9 * (float) viewHight) / (float) 800));
					tempHeight = ((int) (((float) 35 * (float) viewHight) / (float) 800));
					l02_01_text_msg = new TextView(context);
					param6 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, tempHeight);
					param6.setMargins(paddingleft, paddingtop, paddingright, paddingbottom);
					l02_01_text_msg.setLayoutParams(param6);
					l02_01_text_msg.setTextColor(Color.WHITE);
					l02_01_text_msg.setGravity(Gravity.CENTER_VERTICAL);
					l02_01_text_msg.setTextSize(13);
					linear.addView(l02_01_text_msg);

					tempWidth = ((int) (((float) 297 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
					paddingleft = ((int) (((float) 90 * (float) viewWidth) / (float) 480));
					paddingtop = ((int) (((float) 36 * (float) viewHight) / (float) 800));
					l02_01_btn_sendMessage = new View(context);
					LinearLayout.LayoutParams param7 = new LinearLayout.LayoutParams(tempWidth, tempHeight);
					param7.setMargins(paddingleft, paddingtop, 0, 0);
					l02_01_btn_sendMessage.setLayoutParams(param7);
					l02_01_btn_sendMessage.setBackgroundResource(R.drawable.btn_call_you_off);
					linear.addView(l02_01_btn_sendMessage);
				}
			}
		}
		return l02_01_layout_friendinfo;
	}
}
