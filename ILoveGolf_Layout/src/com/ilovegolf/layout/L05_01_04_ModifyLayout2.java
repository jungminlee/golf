package com.ilovegolf.layout;

import com.ilovegolf.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class L05_01_04_ModifyLayout2 extends B00_01_BarLayout {
	public TextView l05_01_01_text_subject = null;
	public View l05_01_01_btn_ok = null;
	public LinearLayout linear3 = null;

	public View getLayout(Context context, Display display) {
		super.getLayout(context, display);

		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		// b00_01_text_bar.setText("프로필 변경");

		LinearLayout linear2 = new LinearLayout(context);
		linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		linear2.setGravity(Gravity.CENTER_HORIZONTAL);
		linear2.setOrientation(LinearLayout.VERTICAL);
		b00_01_linear_content.addView(linear2);

		{
			paddingWidth = ((int) (((float) 25 * (float) viewWidth) / (float) 480));
			paddingHeight = ((int) (((float) 25 * (float) viewHight) / (float) 800));
			l05_01_01_text_subject = new TextView(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			params.setMargins(paddingWidth, paddingHeight, 0, 0);
			l05_01_01_text_subject.setLayoutParams(params);
			l05_01_01_text_subject.setTextColor(Color.BLACK);
			linear2.addView(l05_01_01_text_subject);

			paddingHeight = ((int) (((float) 40 * (float) viewHight) / (float) 800));
			tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 129 * (float) viewHight) / (float) 800));
			LinearLayout linear4 = new LinearLayout(context);
			params = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
			params.setMargins(0, paddingWidth, 0, 0);
			linear4.setLayoutParams(params);
			linear4.setOrientation(LinearLayout.VERTICAL);
			linear4.setGravity(Gravity.CENTER_HORIZONTAL);
			linear4.setBackgroundResource(R.drawable.golf_setup_profile_sex_bg);
			linear2.addView(linear4);
			{
				linear3 = new LinearLayout(context);
				linear3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
				linear3.setGravity(Gravity.CENTER_VERTICAL);
				linear4.addView(linear3);
			}

			paddingHeight = ((int) (((float) 40 * (float) viewHight) / (float) 800));
			tempWidth = ((int) (((float) 107 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
			l05_01_01_btn_ok = new View(context);
			params = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
			params.setMargins(0, paddingWidth, 0, 0);
			l05_01_01_btn_ok.setLayoutParams(params);
			l05_01_01_btn_ok.setBackgroundResource(R.drawable.btn_ok_off);
			linear2.addView(l05_01_01_btn_ok);
		}
		return b00_01_barlayout;
	}
}
