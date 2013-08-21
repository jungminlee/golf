package com.ilovegolf.layout;

import com.ilovegolf.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class I04_00_MessageItem3 {
	public LinearLayout i04_00_Messageitem3 = null;
	public TextView i04_00_text_date = null;
	public TextView i04_00_text_message = null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingLeft = 0;
		int paddingRight = 0;
		int paddingHeight = 0;

		i04_00_Messageitem3 = new LinearLayout(context);
		i04_00_Messageitem3.setBackgroundColor(Color.WHITE);
		{
			paddingLeft = ((int) (((float) 60 * (float) viewWidth) / (float) 480));
			paddingRight = ((int) (((float) 10 * (float) viewWidth) / (float) 480));
			paddingHeight = ((int) (((float) 10 * (float) viewHight) / (float) 800));
			LinearLayout linear = new LinearLayout(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(paddingLeft, paddingHeight, paddingRight, paddingHeight);
			linear.setLayoutParams(params);
			linear.setOrientation(LinearLayout.VERTICAL);
			i04_00_Messageitem3.addView(linear);
			{
				LinearLayout linear2 = new LinearLayout(context);
				linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				// linear2.setGravity(Gravity.CENTER);
				linear.addView(linear2);
				{
					tempWidth = ((int) (((float) 80 * (float) viewWidth) / (float) 480));
					LinearLayout linear3 = new LinearLayout(context);
					params = new LinearLayout.LayoutParams(tempWidth, LayoutParams.MATCH_PARENT);
					params.weight = 1;
					linear3.setLayoutParams(params);
					linear3.setGravity(Gravity.RIGHT);
					linear2.addView(linear3);
					{
						i04_00_text_date = new TextView(context);
						i04_00_text_date.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
						i04_00_text_date.setTextColor(Color.BLACK);
						i04_00_text_date.setGravity(Gravity.BOTTOM);
						i04_00_text_date.setTextSize(13);
						linear3.addView(i04_00_text_date);
					}

					tempWidth = ((int) (((float) 320 * (float) viewWidth) / (float) 480));
					i04_00_text_message = new TextView(context);
					params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					params.weight = 1;
					i04_00_text_message.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					i04_00_text_message.setTextColor(Color.BLACK);
					i04_00_text_message.setMaxWidth(tempWidth);
					i04_00_text_message.setBackgroundResource(R.drawable.normal_say_you);
					linear2.addView(i04_00_text_message);
				}
			}

		}
		return i04_00_Messageitem3;
	}
}
