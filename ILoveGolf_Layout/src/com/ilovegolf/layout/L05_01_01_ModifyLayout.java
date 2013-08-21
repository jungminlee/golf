package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilovegolf.R;

public class L05_01_01_ModifyLayout extends B00_01_BarLayout {
	public TextView l05_01_01_text_subject = null;
	public EditText l05_01_01_edit_context = null;
	public View l05_01_01_btn_remove = null;
	public View l05_01_01_btn_ok = null;

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
			l05_01_01_text_subject = new TextView(context);
			l05_01_01_text_subject.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			l05_01_01_text_subject.setTextColor(Color.BLACK);
			linear2.addView(l05_01_01_text_subject);
		}
		tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
		tempHeight = ((int) (((float) 46 * (float) viewHight) / (float) 800));
		LinearLayout linear3 = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tempWidth, tempHeight);
		params.setMargins(25, 25, 25, 25);
		linear3.setLayoutParams(params);
		linear3.setBackgroundResource(R.drawable.setup_input);
		linear3.setGravity(Gravity.CENTER_VERTICAL);
		b00_01_linear_content.addView(linear3);
		{
			l05_01_01_edit_context = new EditText(context);
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
			param.weight = 1;
			l05_01_01_edit_context.setLayoutParams(param);
			l05_01_01_edit_context.setTextSize(15);
			l05_01_01_edit_context.setBackgroundColor(Color.alpha(0));
			l05_01_01_edit_context.setGravity(Gravity.CENTER_VERTICAL);
			linear3.addView(l05_01_01_edit_context);

			tempWidth = ((int) (((float) 29 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 29 * (float) viewHight) / (float) 800));
			l05_01_01_btn_remove = new View(context);
			params = new LinearLayout.LayoutParams(tempWidth, tempHeight);
			params.setMargins(0, 0, 10, 0);
			l05_01_01_btn_remove.setLayoutParams(params);
			l05_01_01_btn_remove.setBackgroundResource(R.drawable.btn_clear_off);
			linear3.addView(l05_01_01_btn_remove);

		}

		LinearLayout linear4 = new LinearLayout(context);
		linear4.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		linear4.setPadding(paddingWidth, paddingHeight, paddingWidth, paddingHeight);
		linear4.setGravity(Gravity.CENTER_HORIZONTAL);
		b00_01_linear_content.addView(linear4);
		{
			tempWidth = ((int) (((float) 107 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
			l05_01_01_btn_ok = new View(context);
			l05_01_01_btn_ok.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
			l05_01_01_btn_ok.setBackgroundResource(R.drawable.btn_ok_off);
			linear4.addView(l05_01_01_btn_ok);
		}
		return b00_01_barlayout;
	}
}
