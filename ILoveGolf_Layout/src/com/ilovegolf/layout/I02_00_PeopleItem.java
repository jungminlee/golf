package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ilovegolf.R;

public class I02_00_PeopleItem {
	RelativeLayout i02_00_peopleitem = null;
	public static ImageView i02_00_img_pic = null;
	public static TextView i02_00_text_name = null;
	public static TextView i02_00_text_message = null;
	public LinearLayout line = null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;
		int marginWidth = 0;

		i02_00_peopleitem = new RelativeLayout(context);
		{
			tempHeight = ((int) (((float) 81 * (float) viewHight) / (float) 800));
			LinearLayout linear = new LinearLayout(context);
			linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			linear.setGravity(Gravity.CENTER_VERTICAL);
			i02_00_peopleitem.addView(linear);
			{
				paddingWidth = ((int) (((float) 22 * (float) viewWidth) / (float) 480));
				tempHeight = ((int) (((float) 58 * (float) viewHight) / (float) 800));
				tempWidth = ((int) (((float) 58 * (float) viewWidth) / (float) 480));
				i02_00_img_pic = new ImageView(context);
				LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(tempWidth, tempHeight);
				param.setMargins(paddingWidth, 0, 0, 0);
				i02_00_img_pic.setLayoutParams(param);
				i02_00_img_pic.setBackgroundColor(Color.WHITE);
				linear.addView(i02_00_img_pic);
			}

			tempHeight = ((int) (((float) 81 * (float) viewHight) / (float) 800));
			line = new LinearLayout(context);
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			line.setGravity(Gravity.CENTER_VERTICAL);
			line.setBackgroundResource(R.drawable.list_profile_fav_line);
			i02_00_peopleitem.addView(line);
			{
				paddingWidth = ((int) (((float) 85 * (float) viewWidth) / (float) 480));
				marginWidth = ((int) (((float) 30 * (float) viewWidth) / (float) 480));
				i02_00_text_name = new TextView(context);
				LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				param.setMargins(paddingWidth, 0, marginWidth, 0);
				i02_00_text_name.setLayoutParams(param);
				i02_00_text_name.setTextColor(Color.BLACK);
				i02_00_text_name.setText("중급 남 37세 서울시 구로구\n" + Html.fromHtml("<b>" + "내가 프로야!" + "</b>"));
//				i02_00_text_name.setTextSize(13);
				line.addView(i02_00_text_name);

				paddingWidth = ((int) (((float) 15 * (float) viewWidth) / (float) 480));
				LinearLayout linear2 = new LinearLayout(context);
				LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				param2.setMargins(0, 0, marginWidth, 0);
				param2.weight=1;
				linear2.setLayoutParams(param2);
				linear2.setGravity(Gravity.RIGHT);
				line.addView(linear2);

				{
					i02_00_text_message = new TextView(context);
					i02_00_text_message.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					i02_00_text_message.setTextColor(Color.BLACK);
					i02_00_text_message.setText("내가 쫌 쳐!");
					i02_00_text_message.setTextSize(13);
					i02_00_text_message.setGravity(Gravity.CENTER_VERTICAL);
					linear2.addView(i02_00_text_message);
				}
			}

		}

		return i02_00_peopleitem;

	}
}
