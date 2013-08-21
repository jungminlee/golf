package com.ilovegolf.layout;

import java.sql.Wrapper;

import com.ilovegolf.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class L05_01_00_01_ModifyPictureLayout extends B00_01_BarLayout {

	public ImageView l05_01_00_01_img_pic = null;
	public View l05_01_00_01_btn_ok = null;
	public View l05_01_00_01_btn_rocate = null;
	public View l05_01_00_01_btn_cancel = null;

	public View getLayout(Context context, Display display) {
		super.getLayout(context, display);

		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		b00_01_text_bar.setBackgroundResource(R.drawable.golf_setup_photo_bar);

		l05_01_00_01_img_pic = new ImageView(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.weight = 1;
		l05_01_00_01_img_pic.setLayoutParams(params);
		b00_01_linear_content.addView(l05_01_00_01_img_pic);

		tempHeight = ((int) (((float) 60 * (float) viewHight) / (float) 800));
		LinearLayout linear = new LinearLayout(context);
		linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
		linear.setBackgroundResource(R.drawable.golf_setup_photo_edit_bar);
		linear.setGravity(Gravity.CENTER_HORIZONTAL);
		b00_01_linear_content.addView(linear);
		{
			LinearLayout linear2 = new LinearLayout(context);
			linear2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
			linear2.setGravity(Gravity.CENTER_VERTICAL);
			linear.addView(linear2);
			{

				tempWidth = ((int) (((float) 71 * (float) viewWidth) / (float) 480));
				tempHeight = ((int) (((float) 42 * (float) viewHight) / (float) 800));
				paddingWidth = ((int) (((float) 10 * (float) viewWidth) / (float) 480));
				l05_01_00_01_btn_cancel = new View(context);
				params = new LinearLayout.LayoutParams(tempWidth, tempHeight);
				params.setMargins(0, 0, paddingWidth, 0);
				l05_01_00_01_btn_cancel.setLayoutParams(params);
				l05_01_00_01_btn_cancel.setBackgroundResource(R.drawable.btn_picture_cancel_off);
				linear2.addView(l05_01_00_01_btn_cancel);

				l05_01_00_01_btn_rocate = new View(context);
				l05_01_00_01_btn_rocate.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
				l05_01_00_01_btn_rocate.setBackgroundResource(R.drawable.btn_picture_rotation_off);
				linear2.addView(l05_01_00_01_btn_rocate);

				l05_01_00_01_btn_ok = new View(context);
				params = new LinearLayout.LayoutParams(tempWidth, tempHeight);
				params.setMargins(paddingWidth, 0, 0, 0);
				l05_01_00_01_btn_ok.setLayoutParams(params);
				l05_01_00_01_btn_ok.setBackgroundResource(R.drawable.btn_picture_save_off);
				linear2.addView(l05_01_00_01_btn_ok);
			}
		}

		return b00_01_barlayout;
	}

}
