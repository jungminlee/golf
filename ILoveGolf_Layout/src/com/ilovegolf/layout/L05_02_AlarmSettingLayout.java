package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilovegolf.R;
import com.ilovegolf.util.BarActivity;

public class L05_02_AlarmSettingLayout extends B00_01_BarLayout {
	public LinearLayout l05_02_layout_alarmsetting = null;
	public View l05_02_check_popup = null;
	public View l05_02_check_alarm = null;
	public View l05_02_check_ring = null;
	public View l05_02_check_vibrate = null;

	public View getLayout(Context context, Display display) {
		super.getLayout(context, display);
		
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;
		TextView text = null;
		LinearLayout linear = null;
	
		b00_01_text_bar.setBackgroundResource(R.drawable.golf_setup_alam_bar);
		
		l05_02_layout_alarmsetting = new LinearLayout(context);
		l05_02_layout_alarmsetting.setBackgroundColor(Color.WHITE);
		l05_02_layout_alarmsetting.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		l05_02_layout_alarmsetting.setOrientation(LinearLayout.VERTICAL);
		l05_02_layout_alarmsetting.setGravity(Gravity.CENTER_HORIZONTAL);
		l05_02_layout_alarmsetting.setBackgroundColor(Color.rgb(231, 234, 223));
		b00_01_linear_content.addView(l05_02_layout_alarmsetting);
		{
			paddingHeight = ((int) (((float) 0 * (float) viewHight) / (float) 800));
			tempHeight = ((int) (((float) 0 * (float) viewHight) / (float) 800));
			
//			paddingHeight = ((int) (((float) 20 * (float) viewHight) / (float) 800));
			paddingWidth = ((int) (((float) 10 * (float) viewHight) / (float) 480));
			tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
//			tempHeight = ((int) (((float) 94 * (float) viewHight) / (float) 800));
			linear = new LinearLayout(context);
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
			param.setMargins(0, paddingHeight, 0, 0);
			linear.setLayoutParams(param);
			linear.setBackgroundResource(R.drawable.golf_setup_alam_bg);
			linear.setOrientation(LinearLayout.VERTICAL);
			l05_02_layout_alarmsetting.addView(linear);
			{
				tempHeight = ((int) (((float) 47 * (float) viewHight) / (float) 800));
				LinearLayout linear2 = new LinearLayout(context);
				linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
				linear2.setGravity(Gravity.CENTER_VERTICAL);
				linear.addView(linear2);
				{
					text = new TextView(context);
					param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					param.weight = 1;
					param.setMargins(paddingHeight, 0, 0, 0);
					text.setLayoutParams(param);
					text.setTextColor(Color.BLACK);
					text.setText("골프요청도착 알림팝업");
					linear2.addView(text);

					LinearLayout temp = new LinearLayout(context);
					param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
					param.setMargins(0, 0, paddingWidth, 0);
					temp.setLayoutParams(param);
					temp.setGravity(Gravity.CENTER_VERTICAL);
					linear2.addView(temp);
					{
						tempWidth = ((int) (((float) 30 * (float) viewWidth) / (float) 480));
						tempHeight = ((int) (((float) 29 * (float) viewHight) / (float) 800));
						l05_02_check_popup = new View(context);
						param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
						l05_02_check_popup.setLayoutParams(param);
						l05_02_check_popup.setBackgroundResource(R.drawable.golf_setup_alam_off);
						temp.addView(l05_02_check_popup);
					}
				}

				text = new TextView(context);
				text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				text.setPadding(paddingHeight, 0, 0, 0);
				text.setGravity(Gravity.CENTER_VERTICAL);
				text.setTextSize(13);
				text.setText("골프요청이 도착하면 팝업으로 알려줍니다.");
				linear.addView(text);
			}
			paddingHeight = ((int) (((float) 20 * (float) viewHight) / (float) 800));
			tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 94 * (float) viewHight) / (float) 800));
			linear = new LinearLayout(context);
			param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
			param.setMargins(0, paddingHeight, 0, 0);
			linear.setLayoutParams(param);
			linear.setBackgroundResource(R.drawable.golf_setup_alam_bg);
			linear.setOrientation(LinearLayout.VERTICAL);
			l05_02_layout_alarmsetting.addView(linear);
			{
				tempHeight = ((int) (((float) 47 * (float) viewHight) / (float) 800));
				LinearLayout linear2 = new LinearLayout(context);
				linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
				linear2.setGravity(Gravity.CENTER_VERTICAL);
				linear.addView(linear2);
				{
					text = new TextView(context);
					param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					param.weight = 1;
					param.setMargins(paddingHeight, 0, 0, 0);
					text.setLayoutParams(param);
					text.setTextColor(Color.BLACK);
					text.setText("골프요청도착 알림");
					linear2.addView(text);

					LinearLayout temp = new LinearLayout(context);
					param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
					param.setMargins(0, 0, paddingWidth, 0);
					temp.setLayoutParams(param);
					temp.setGravity(Gravity.CENTER_VERTICAL);
					linear2.addView(temp);
					{
						tempWidth = ((int) (((float) 30 * (float) viewWidth) / (float) 480));
						tempHeight = ((int) (((float) 29 * (float) viewHight) / (float) 800));
						l05_02_check_alarm = new View(context);
						param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
						l05_02_check_alarm.setLayoutParams(param);
						l05_02_check_alarm.setBackgroundResource(R.drawable.golf_setup_alam_off);
						temp.addView(l05_02_check_alarm);
					}
				}

				text = new TextView(context);
				text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				text.setPadding(paddingHeight, 0, 0, 0);
				text.setGravity(Gravity.CENTER_VERTICAL);
				text.setTextSize(13);
				text.setText("아이러브골프를 실행하지 않을 때 푸시 알림을 받습니다.");
				linear.addView(text);
			}
			paddingHeight = ((int) (((float) 20 * (float) viewHight) / (float) 800));
			tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 94 * (float) viewHight) / (float) 800));
			linear = new LinearLayout(context);
			param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
			param.setMargins(0, paddingHeight, 0, 0);
			linear.setLayoutParams(param);
			linear.setBackgroundResource(R.drawable.golf_setup_alam_bg);
			linear.setOrientation(LinearLayout.VERTICAL);
			l05_02_layout_alarmsetting.addView(linear);
			{
				tempHeight = ((int) (((float) 47 * (float) viewHight) / (float) 800));
				LinearLayout linear2 = new LinearLayout(context);
				linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
				linear2.setGravity(Gravity.CENTER_VERTICAL);
				linear.addView(linear2);
				{
					text = new TextView(context);
					param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					param.weight = 1;
					param.setMargins(paddingHeight, 0, 0, 0);
					text.setLayoutParams(param);
					text.setTextColor(Color.BLACK);
					text.setText("골프요청도착 알림");
					linear2.addView(text);

					LinearLayout temp = new LinearLayout(context);
					param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
					param.setMargins(0, 0, paddingWidth, 0);
					temp.setLayoutParams(param);
					temp.setGravity(Gravity.CENTER_VERTICAL);
					linear2.addView(temp);
					{
						tempWidth = ((int) (((float) 30 * (float) viewWidth) / (float) 480));
						tempHeight = ((int) (((float) 29 * (float) viewHight) / (float) 800));
						l05_02_check_ring = new View(context);
						param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
						l05_02_check_ring.setLayoutParams(param);
						l05_02_check_ring.setBackgroundResource(R.drawable.golf_setup_sound_off);
						temp.addView(l05_02_check_ring);
					}
				}

				text = new TextView(context);
				text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				text.setPadding(paddingHeight, 0, 0, 0);
				text.setGravity(Gravity.CENTER_VERTICAL);
				text.setTextSize(13);
				text.setText("소리알림");
				linear.addView(text);
			}

			tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
			linear = new LinearLayout(context);
			param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
			param.setMargins(0, paddingHeight, 0, 0);
			linear.setLayoutParams(param);
			linear.setBackgroundResource(R.drawable.golf_setup_vibrate_bg);
			linear.setGravity(Gravity.CENTER_VERTICAL);
			l05_02_layout_alarmsetting.addView(linear);
			{
				text = new TextView(context);
				param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				param.weight = 1;
				param.setMargins(paddingHeight, 0, 0, 0);
				text.setLayoutParams(param);
				text.setTextColor(Color.BLACK);
				text.setText("진동알림");
				linear.addView(text);

				LinearLayout temp = new LinearLayout(context);
				param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
				param.setMargins(0, 0, paddingWidth, 0);
				temp.setLayoutParams(param);
				temp.setGravity(Gravity.CENTER_VERTICAL);
				linear.addView(temp);
				{
					tempWidth = ((int) (((float) 30 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 29 * (float) viewHight) / (float) 800));
					l05_02_check_vibrate = new View(context);
					param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
					l05_02_check_vibrate.setLayoutParams(param);
					l05_02_check_vibrate.setBackgroundResource(R.drawable.golf_setup_alam_off);
					temp.addView(l05_02_check_vibrate);
				}
			}
		}
		return b00_01_barlayout;
	}
}
