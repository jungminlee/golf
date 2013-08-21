package com.ilovegolf.layout;

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

public class L02_01_DownPicturePreviewLayout extends B00_01_BarLayout {
	
	public ImageView l02_01_img_pic=null;
	public View l02_01_btn_left=null;
	public TextView l02_01_text_page=null;
	public View l02_01_btn_right =null;
	
	public View getLayout(Context context, Display display) {
		super.getLayout(context, display);

		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		b00_01_text_bar.setBackgroundResource(R.drawable.golf_setup_photo_bar);

		l02_01_img_pic=new ImageView(context);
		LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.weight=1;
		l02_01_img_pic.setLayoutParams(params);
		b00_01_linear_content.addView(l02_01_img_pic);
	
//		tempHeight = ((int) (((float) 60 * (float) viewHight) / (float) 800));		
		tempHeight = ((int) (((float) 0 * (float) viewHight) / (float) 800));
		LinearLayout linear=new LinearLayout(context);
		linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
		linear.setBackgroundResource(R.drawable.golf_setup_photo_edit_bar);
		linear.setGravity(Gravity.CENTER_VERTICAL);
		b00_01_linear_content.addView(linear);
		{

			tempWidth= ((int) (((float) 71 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 42 * (float) viewHight) / (float) 800));
			paddingWidth= ((int) (((float) 20 * (float) viewWidth) / (float) 480));
			l02_01_btn_left=new View(context);
			params=new LinearLayout.LayoutParams(tempWidth, tempHeight);
			params.setMargins(paddingWidth, 0, 0, 0);
			l02_01_btn_left.setLayoutParams(params);
			l02_01_btn_left.setBackgroundResource(R.drawable.btn_picture_preview_off);
			linear.addView(l02_01_btn_left);
			
			l02_01_text_page=new TextView(context);
			params=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			params.weight=1;
			l02_01_text_page.setLayoutParams(params);
			l02_01_text_page.setText("1/1");
			l02_01_text_page.setGravity(Gravity.CENTER);
			linear.addView(l02_01_text_page);
			
			l02_01_btn_right=new View(context);
			params=new LinearLayout.LayoutParams(tempWidth, tempHeight);
			params.setMargins(0, 0, paddingWidth, 0);
			l02_01_btn_right.setLayoutParams(params);
			l02_01_btn_right.setBackgroundResource(R.drawable.btn_picture_next_off);
			linear.addView(l02_01_btn_right);
		}
	
		return b00_01_barlayout;
	}

}
