package com.ilovegolf.layout;

import com.ilovegolf.R;

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

public class L03_01_LinkInfoLayout {
	public LinearLayout l03_01_layout_linkinfo = null;
	public LinearLayout l03_01_layout_temp = null;
	public ImageView l03_01_img_pic = null;
	public TextView l03_01_text_name = null;
	public View l03_01_btn_favorite = null;
	public TextView l03_01_text_msg = null;
	public TextView l03_01_text_address = null;
	public View l03_01_btn_close = null;
	public View l03_01_btn_map = null;
	public View l03_01_btn_call = null;
	public RelativeLayout relative = null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingleft = 0;
		int paddingtop = 0;
		int paddingright = 0;
		int paddingbottom = 0;

		l03_01_layout_linkinfo = new LinearLayout(context);
		l03_01_layout_linkinfo.setBackgroundColor(Color.alpha(0));
		l03_01_layout_linkinfo.setOrientation(LinearLayout.VERTICAL);
		l03_01_layout_linkinfo.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		{
			l03_01_layout_temp = new LinearLayout(context);
			LinearLayout.LayoutParams tempparam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			tempparam.weight = 1;
			l03_01_layout_temp.setLayoutParams(tempparam);
			l03_01_layout_temp.setBackgroundColor(Color.alpha(0));
			l03_01_layout_linkinfo.addView(l03_01_layout_temp);

			tempHeight = ((int) (((float) 226 * (float) viewHight) / (float) 800));
			System.out.println(":::::::::::" + tempHeight);
			relative = new RelativeLayout(context);
			relative.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			l03_01_layout_linkinfo.addView(relative);
			{
//				LinearLayout base = new LinearLayout(context);
//				base.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//				relative.addView(base);
//				{
//					paddingleft = ((int) (((float) 22 * (float) viewWidth) / (float) 480));
//					paddingtop = ((int) (((float) 14 * (float) viewHight) / (float) 800));
//					tempWidth = ((int) (((float) 111 * (float) viewWidth) / (float) 480));
//					tempHeight = ((int) (((float) 111 * (float) viewHight) / (float) 800));
//					l03_01_img_pic = new ImageView(context);
//					LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
//					param.setMargins(paddingleft, paddingtop, 0, 0);
//					l03_01_img_pic.setLayoutParams(param);
//					l03_01_img_pic.setBackgroundResource(R.drawable.golf_position_profile_img);
//					base.addView(l03_01_img_pic);
//				}
				LinearLayout linear = new LinearLayout(context);
				linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				linear.setBackgroundResource(R.drawable.position_profile_bg);
				linear.setOrientation(LinearLayout.VERTICAL);
				relative.addView(linear);

				{
					tempHeight = ((int) (((float) 50 * (float) viewHight) / (float) 800));
					LinearLayout linear2 = new LinearLayout(context);
					linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
					linear.addView(linear2);
					{

						paddingleft = ((int) (((float) 40 * (float) viewWidth) / (float) 480));
						paddingtop = ((int) (((float) 29 * (float) viewHight) / (float) 800));
						l03_01_text_name = new TextView(context);
						LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
						param3.setMargins(paddingleft, paddingtop, 0, 0);
						param3.weight = 1;
						l03_01_text_name.setLayoutParams(param3);
						l03_01_text_name.setTextColor(Color.YELLOW);
						linear2.addView(l03_01_text_name);

						paddingtop = ((int) (((float) 15 * (float) viewHight) / (float) 800));
						tempWidth = ((int) (((float) 29 * (float) viewWidth) / (float) 480));
						tempHeight = ((int) (((float) 29 * (float) viewHight) / (float) 800));
						l03_01_btn_favorite = new View(context);
						LinearLayout.LayoutParams param5 = new LinearLayout.LayoutParams(tempWidth, tempHeight);
						param5.setMargins(0, paddingtop, 0, 0);
						l03_01_btn_favorite.setLayoutParams(param5);
						l03_01_btn_favorite.setBackgroundResource(R.drawable.btn_profile_favorite);
						linear2.addView(l03_01_btn_favorite);

						paddingleft = ((int) (((float) 10 * (float) viewWidth) / (float) 480));
						paddingright = ((int) (((float) 22 * (float) viewWidth) / (float) 480));
						l03_01_btn_close = new View(context);
						param5 = new LinearLayout.LayoutParams(tempWidth, tempHeight);
						param5.setMargins(paddingleft, paddingtop, paddingright, 0);
						l03_01_btn_close.setLayoutParams(param5);
						l03_01_btn_close.setBackgroundResource(R.drawable.btn_profile_close_off);
						linear2.addView(l03_01_btn_close);
					}
					paddingleft = ((int) (((float) 40 * (float) viewWidth) / (float) 480));
					paddingtop = ((int) (((float) 5 * (float) viewHight) / (float) 800));
					paddingright = ((int) (((float) 42 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 45 * (float) viewHight) / (float) 800));
					l03_01_text_address = new TextView(context);
					LinearLayout.LayoutParams param6 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, tempHeight);
					param6.setMargins(paddingleft, paddingtop, paddingright, 0);
					l03_01_text_address.setLayoutParams(param6);
					l03_01_text_address.setTextColor(Color.WHITE);
					l03_01_text_address.setText("서울시 구로구 구로3동 170-10호\n대륭 포스트타워 7차 509호");
					// l03_01_text_address.setMaxLines(1);
					linear.addView(l03_01_text_address);

					paddingleft = ((int) (((float) 40 * (float) viewWidth) / (float) 480));
					paddingtop = ((int) (((float) 5 * (float) viewHight) / (float) 800));
					paddingright = ((int) (((float) 44 * (float) viewWidth) / (float) 480));
					paddingbottom = ((int) (((float) 5 * (float) viewHight) / (float) 800));
					tempHeight = ((int) (((float) 25 * (float) viewHight) / (float) 800));
					l03_01_text_msg = new TextView(context);
					param6 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, tempHeight);
					param6.setMargins(paddingleft, paddingtop, paddingright, paddingbottom);
					l03_01_text_msg.setLayoutParams(param6);
					l03_01_text_msg.setTextColor(Color.WHITE);
					l03_01_text_msg.setGravity(Gravity.CENTER_VERTICAL);
					l03_01_text_msg.setMaxLines(1);
					l03_01_text_msg.setTextSize(13);
					linear.addView(l03_01_text_msg);

					paddingtop = ((int) (((float) 30 * (float) viewHight) / (float) 800));
					paddingleft = ((int) (((float) 82 * (float) viewWidth) / (float) 480));
					LinearLayout linear6 = new LinearLayout(context);
					LinearLayout.LayoutParams param7 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					param7.setMargins(paddingleft, paddingtop, 0, 0);
					linear6.setLayoutParams(param7);
					linear.addView(linear6);
					{
						tempWidth = ((int) (((float) 147 * (float) viewWidth) / (float) 480));
						tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
						l03_01_btn_map = new View(context);
						param7 = new LinearLayout.LayoutParams(tempWidth, tempHeight);
						l03_01_btn_map.setLayoutParams(param7);
						l03_01_btn_map.setBackgroundResource(R.drawable.btn_position_golf_off);
						linear6.addView(l03_01_btn_map);

						paddingleft = ((int) (((float) 21 * (float) viewWidth) / (float) 480));
						l03_01_btn_call = new View(context);
						param7 = new LinearLayout.LayoutParams(tempWidth, tempHeight);
						param7.setMargins(paddingleft, 0, 0, 0);
						l03_01_btn_call.setLayoutParams(param7);
						l03_01_btn_call.setBackgroundResource(R.drawable.btn_phone_off);
						linear6.addView(l03_01_btn_call);
					}
				}
			}
		}
		return l03_01_layout_linkinfo;
	}
}
