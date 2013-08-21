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

public class I03_00_LinkItem {
	RelativeLayout i03_00_peopleitem = null;
	public ImageView i03_00_img_pic = null;
	public TextView i03_00_text_name = null;
	public TextView i03_00_text_address = null;
	public LinearLayout line = null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;
		int marginWidth = 0;

		i03_00_peopleitem = new RelativeLayout(context);
		{
			tempHeight = ((int) (((float) 81 * (float) viewHight) / (float) 800));
			LinearLayout linear = new LinearLayout(context);
			linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			linear.setGravity(Gravity.CENTER_VERTICAL);
			i03_00_peopleitem.addView(linear);
			{
				paddingWidth = ((int) (((float) 22 * (float) viewWidth) / (float) 480));
				tempHeight = ((int) (((float) 58 * (float) viewHight) / (float) 800));
				tempWidth = ((int) (((float) 58 * (float) viewWidth) / (float) 480));
				i03_00_img_pic = new ImageView(context);
				LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(tempWidth, tempHeight);
				param.setMargins(paddingWidth, 0, 0, 0);
				i03_00_img_pic.setLayoutParams(param);
				i03_00_img_pic.setBackgroundResource(R.drawable.golf_position_list_img);
				linear.addView(i03_00_img_pic);
			}

			tempHeight = ((int) (((float) 81 * (float) viewHight) / (float) 800));
			line = new LinearLayout(context);
			line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			line.setOrientation(LinearLayout.VERTICAL);
			line.setBackgroundResource(R.drawable.golf_position_list_bg);
			i03_00_peopleitem.addView(line);
			{
				tempHeight = ((int) (((float) 38 * (float) viewHight) / (float) 800));
				paddingWidth = ((int) (((float) 120 * (float) viewWidth) / (float) 480));
				i03_00_text_name = new TextView(context);
				LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, tempHeight);
				param.setMargins(paddingWidth, 0, 0, 0);
				i03_00_text_name.setLayoutParams(param);
				i03_00_text_name.setTextColor(Color.BLACK);
				i03_00_text_name.setGravity(Gravity.CENTER_VERTICAL);
				line.addView(i03_00_text_name);

				tempHeight = ((int) (((float) 42 * (float) viewHight) / (float) 800));
				paddingWidth = ((int) (((float) 120 * (float) viewWidth) / (float) 480));
				i03_00_text_address = new TextView(context);
				LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, tempHeight);
				param2.setMargins(paddingWidth, 0, 0, 0);
				i03_00_text_address.setLayoutParams(param2);
				i03_00_text_address.setTextColor(Color.BLACK);
				i03_00_text_address.setText("서울시 구로구 구로3동 170-10호 대륭포스트타워 7차 509호");
				i03_00_text_address.setGravity(Gravity.CENTER_VERTICAL);
				line.addView(i03_00_text_address);

			}

		}

		return i03_00_peopleitem;

	}
}
