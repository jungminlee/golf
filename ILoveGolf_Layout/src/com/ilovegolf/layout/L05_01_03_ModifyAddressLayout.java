package com.ilovegolf.layout;

import com.ilovegolf.R;
import com.ilovegolf.util.StaticClass;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class L05_01_03_ModifyAddressLayout extends B00_01_BarLayout {
	public TextView l05_01_03_text_subject = null;
	public TextView l05_01_03_combo_si = null;
	public TextView l05_01_03_combo_gu = null;
	public TextView l05_01_03_combo_dong = null;
	public View l05_01_03_btn_ok = null;

	public View getLayout(Context context, Display display) {
		super.getLayout(context, display);

		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		// b00_01_text_bar.setText("프로필 변경");

		paddingWidth = ((int) (((float) 25 * (float) viewWidth) / (float) 480));
		paddingHeight = ((int) (((float) 25 * (float) viewHight) / (float) 800));
		LinearLayout linear2 = new LinearLayout(context);
		linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		linear2.setPadding(paddingWidth, paddingHeight, paddingWidth, paddingHeight);
		b00_01_linear_content.addView(linear2);

		{
			l05_01_03_text_subject = new TextView(context);
			l05_01_03_text_subject.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			l05_01_03_text_subject.setText("주소를 선택하세요.");
			l05_01_03_text_subject.setTextColor(Color.BLACK);
			linear2.addView(l05_01_03_text_subject);
		}

		LinearLayout linear3 = new LinearLayout(context);
		linear3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		linear3.setOrientation(LinearLayout.VERTICAL);
		linear3.setGravity(Gravity.CENTER_HORIZONTAL);
		b00_01_linear_content.addView(linear3);
		{
			paddingWidth = ((int) (((float) 15 * (float) viewWidth) / (float) 480));
			paddingHeight = ((int) (((float) 20 * (float) viewHight) / (float) 800));
			tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 46 * (float) viewHight) / (float) 800));
			l05_01_03_combo_si = new TextView(context);
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(tempWidth, tempHeight);
			params.setMargins(0, paddingHeight, 0, 0);
			l05_01_03_combo_si.setLayoutParams(params);
			l05_01_03_combo_si.setPadding(paddingWidth, 0, 0, 0);
			l05_01_03_combo_si.setBackgroundResource(R.drawable.address_select);
			l05_01_03_combo_si.setGravity(Gravity.CENTER_VERTICAL);
			linear3.addView(l05_01_03_combo_si);

			l05_01_03_combo_gu = new TextView(context);
			l05_01_03_combo_gu.setLayoutParams(params);
			l05_01_03_combo_gu.setPadding(paddingWidth, 0, 0, 0);
			l05_01_03_combo_gu.setBackgroundResource(R.drawable.address_select);
			l05_01_03_combo_gu.setGravity(Gravity.CENTER_VERTICAL);
			linear3.addView(l05_01_03_combo_gu);

			l05_01_03_combo_dong = new TextView(context);
			l05_01_03_combo_dong.setLayoutParams(params);
			l05_01_03_combo_dong.setPadding(paddingWidth, 0, 0, 0);
			l05_01_03_combo_dong.setBackgroundResource(R.drawable.address_select);
			l05_01_03_combo_dong.setGravity(Gravity.CENTER_VERTICAL);
			linear3.addView(l05_01_03_combo_dong);

			LinearLayout linear4 = new LinearLayout(context);
			linear4.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			linear4.setPadding(paddingWidth, paddingHeight, paddingWidth, paddingHeight);
			linear4.setGravity(Gravity.CENTER_HORIZONTAL);
			linear3.addView(linear4);
			{
				tempWidth = ((int) (((float) 107 * (float) viewWidth) / (float) 480));
				tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
				l05_01_03_btn_ok = new View(context);
				l05_01_03_btn_ok.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
				l05_01_03_btn_ok.setBackgroundResource(R.drawable.btn_ok_off);
				linear4.addView(l05_01_03_btn_ok);
			}
		}

		return b00_01_barlayout;
	}
}
