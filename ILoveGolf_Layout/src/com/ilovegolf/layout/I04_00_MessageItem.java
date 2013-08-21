package com.ilovegolf.layout;

import com.ilovegolf.R;
import com.ilovegolf.R.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class I04_00_MessageItem {
	public LinearLayout i04_00_Messageitem1 = null;
	public ImageView i04_00_img_pic = null;
	public TextView i04_00_text_name = null;
	public TextView i04_00_text_date = null;
	public TextView i04_00_text_message = null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		i04_00_Messageitem1 = new LinearLayout(context);
		// i05_01_profileitem1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		{
			tempHeight = ((int) (((float) 81 * (float) viewWidth) / (float) 800));
			RelativeLayout relative = new RelativeLayout(context);
			relative.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			relative.setMinimumHeight(tempHeight);
			relative.setBackgroundColor(Color.WHITE);
			i04_00_Messageitem1.addView(relative);
			{
				LinearLayout linear = new LinearLayout(context);
				linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				relative.addView(linear);
				{
					paddingWidth = ((int) (((float) 22 * (float) viewWidth) / (float) 480));
					paddingHeight= ((int) (((float) 13 * (float) viewHight) / (float) 800));
					tempWidth = ((int) (((float) 54 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 54 * (float) viewHight) / (float) 800));
					LinearLayout linear5 = new LinearLayout(context);
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tempHeight, tempHeight);
					params.setMargins(paddingWidth, paddingHeight, 0, 0);
					linear5.setLayoutParams(params);
					linear5.setGravity(Gravity.CENTER_VERTICAL);
					linear.addView(linear5);
					{
						i04_00_img_pic = new ImageView(context);
						i04_00_img_pic.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
						i04_00_img_pic.setBackgroundColor(Color.WHITE);
						linear5.addView(i04_00_img_pic);
					}
				}
				
				tempWidth = ((int) (((float) 83 * (float) viewWidth) / (float) 480));
				tempHeight = ((int) (((float) 81 * (float) viewHight) / (float) 800));
				LinearLayout frame = new LinearLayout(context);
				frame.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
				frame.setBackgroundResource(R.drawable.talk_list_line);
				relative.addView(frame);

				LinearLayout base = new LinearLayout(context);
				base.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				relative.addView(base);

				{
					paddingWidth = ((int) (((float) 83 * (float) viewWidth) / (float) 480));
					paddingHeight = ((int) (((float) 20 * (float) viewWidth) / (float) 480));
					LinearLayout linear2 = new LinearLayout(context);
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					params.setMargins(paddingWidth, 0, paddingHeight, 0);
					linear2.setLayoutParams(params);
					linear2.setOrientation(LinearLayout.VERTICAL);
					base.addView(linear2);
					{
						paddingWidth = ((int) (((float) 3 * (float) viewWidth) / (float) 480));
						i04_00_text_name = new TextView(context);
						params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
						params.setMargins(0, 0, paddingWidth, 0);
						i04_00_text_name.setLayoutParams(params);
						i04_00_text_name.setTextColor(Color.BLACK);
						linear2.addView(i04_00_text_name);

						LinearLayout linear3 = new LinearLayout(context);
						linear3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
						linear2.addView(linear3);
						{
							tempWidth = ((int) (((float) 304 * (float) viewWidth) / (float) 480));
							i04_00_text_message = new TextView(context);
							i04_00_text_message.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
							i04_00_text_message.setBackgroundResource(R.drawable.favorie_say);
							i04_00_text_message.setTextColor(Color.BLACK);
							i04_00_text_message.setMaxWidth(tempWidth);
							i04_00_text_message.setGravity(Gravity.CENTER_VERTICAL);
							linear3.addView(i04_00_text_message);

							LinearLayout linear4 = new LinearLayout(context);
							params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
							params.weight = 1;
							linear3.setLayoutParams(params);
							linear3.addView(linear4);
							{
								tempWidth = ((int) (((float) 70 * (float) viewWidth) / (float) 480));
								i04_00_text_date = new TextView(context);
								i04_00_text_date.setLayoutParams(new LayoutParams(tempWidth, LayoutParams.MATCH_PARENT));
								i04_00_text_date.setTextColor(Color.BLACK);
								i04_00_text_date.setTextSize(13);
								i04_00_text_date.setGravity(Gravity.BOTTOM);
								linear3.addView(i04_00_text_date);
							}
						}

					}
				}
			}
		}
		return i04_00_Messageitem1;
	}
}
