package com.ilovegolf.layout;

import java.lang.annotation.ElementType;

import com.ilovegolf.R;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class I04_00_ChatroomItem {
	public LinearLayout i04_00_chatroomitem = null;
	public ImageView i04_00_img_pic = null;
	public TextView i04_00_text_name = null;
	public TextView i04_00_text_date = null;
	public TextView i04_00_text_message = null;
	public View i04_00_btn_state = null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		i04_00_chatroomitem = new LinearLayout(context);
		// i05_01_profileitem1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		{
			tempHeight = ((int) (((float) 81 * (float) viewHight) / (float) 800));
			RelativeLayout relative = new RelativeLayout(context);
			relative.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			i04_00_chatroomitem.addView(relative);
			{
				LinearLayout base = new LinearLayout(context);
				base.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				base.setGravity(Gravity.CENTER_VERTICAL);
				relative.addView(base);
				{
					paddingWidth = ((int) (((float) 24 * (float) viewWidth) / (float) 480));
					tempWidth = ((int) (((float) 54 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 54 * (float) viewHight) / (float) 800));
					i04_00_img_pic = new ImageView(context);
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tempWidth, tempHeight);
					params.setMargins(paddingWidth, 0, 0, 0);
					i04_00_img_pic.setBackgroundColor(Color.WHITE);
					i04_00_img_pic.setLayoutParams(params);
					base.addView(i04_00_img_pic);
				}
				LinearLayout linear = new LinearLayout(context);
				linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				linear.setBackgroundResource(R.drawable.main_friend_list_bg);
				relative.addView(linear);
				{
					paddingWidth = ((int) (((float) 83 * (float) viewWidth) / (float) 480));
					LinearLayout linear2 = new LinearLayout(context);
					LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
					param.setMargins(paddingWidth, 0, 0, 0);
					linear2.setLayoutParams(param);
					linear2.setGravity(Gravity.CENTER_VERTICAL);
					linear.addView(linear2);
					{
						paddingWidth = ((int) (((float) 15 * (float) viewWidth) / (float) 480));
						LinearLayout linear3 = new LinearLayout(context);
						param = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
						param.setMargins(0, 0, paddingWidth, 0);
						param.weight = 1;
						linear3.setLayoutParams(param);
						linear3.setOrientation(LinearLayout.VERTICAL);
						linear2.addView(linear3);
						{
							paddingWidth = ((int) (((float) 8 * (float) viewWidth) / (float) 480));
							LinearLayout linear4 = new LinearLayout(context);
							param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
							param.setMargins(paddingWidth, 0, 0, 0);
							linear4.setLayoutParams(param);
							linear3.addView(linear4);
							{
								i04_00_text_name = new TextView(context);
								param = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
								param.weight = 1;
								i04_00_text_name.setLayoutParams(param);
								i04_00_text_name.setTextColor(Color.BLACK);
								i04_00_text_name.setText("name");
								linear4.addView(i04_00_text_name);

								i04_00_text_date = new TextView(context);
								i04_00_text_date.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
								i04_00_text_date.setTextColor(Color.BLACK);
								i04_00_text_date.setText("date");
								i04_00_text_date.setTextSize(13);
								linear4.addView(i04_00_text_date);
							}
							i04_00_text_message = new TextView(context);
							i04_00_text_message.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
							i04_00_text_message.setTextColor(Color.BLACK);
							i04_00_text_message.setText("message");
							i04_00_text_message.setMaxLines(1);
							i04_00_text_message.setEllipsize(TruncateAt.END);
							i04_00_text_message.setBackgroundResource(R.drawable.favorie_say);
							linear3.addView(i04_00_text_message);

						}
						tempWidth = ((int) (((float) 88 * (float) viewWidth) / (float) 480));
						tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
						paddingWidth = ((int) (((float) 15 * (float) viewWidth) / (float) 480));
						i04_00_btn_state = new View(context);
						param = new LinearLayout.LayoutParams(tempWidth, tempHeight);
						param.setMargins(paddingWidth, 0, paddingWidth, 0);
						i04_00_btn_state.setLayoutParams(param);
						i04_00_btn_state.setBackgroundResource(R.drawable.ico_standby);
						i04_00_btn_state.setPadding(paddingWidth, 0, paddingWidth, 0);
						linear2.addView(i04_00_btn_state);
					}
				}
			}
		}
		return i04_00_chatroomitem;
	}
}
