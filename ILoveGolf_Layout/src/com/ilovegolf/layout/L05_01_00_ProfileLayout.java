package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ilovegolf.R;
import com.ilovegolf.R.drawable;

public class L05_01_00_ProfileLayout extends B00_01_BarLayout{
	public RelativeLayout l05_01_layout_profile = null;
	public ImageView l05_01_img_pic = null;
	public TextView l05_01_text_myphone = null;
	public ListView l05_01_list_changeprofile = null;

	public TextView i05_01_text_name = null;
	public TextView i05_01_text_sex = null;
	public TextView i05_01_text_grade = null;
	public TextView i05_01_text_message = null;
	public TextView i05_01_text_address = null;

	public View getLayout(Context context, Display display) {
		super.getLayout(context, display);
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;


		 b00_01_text_bar.setBackgroundResource(R.drawable.golf_profile_setup_bar);
		
		LinearLayout.LayoutParams params = null;
		// tempWidth = ((int) (((float) 65 * (float) viewWidth) / (float) 480));
		// tempHeight = ((int) (((float) 44 * (float) viewHight) / (float) 800));

		l05_01_layout_profile = new RelativeLayout(context);
		l05_01_layout_profile.setBackgroundColor(Color.WHITE);
		l05_01_layout_profile.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		b00_01_linear_content.addView(l05_01_layout_profile);
		{
			LinearLayout linear = new LinearLayout(context);
			linear.setOrientation(LinearLayout.VERTICAL);
			linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			l05_01_layout_profile.addView(linear);
			{
				tempWidth = ((int) (((float) 480 * (float) viewWidth) / (float) 480));
				tempHeight = ((int) (((float) 144 * (float) viewHight) / (float) 800));
				RelativeLayout relative = new RelativeLayout(context);
				relative.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
				linear.addView(relative);

				{
					LinearLayout linear2 = new LinearLayout(context);
					linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
					relative.addView(linear2);
					{
						paddingWidth = ((int) (((float) 22 * (float) viewWidth) / (float) 480));
						paddingHeight = ((int) (((float) 16 * (float) viewHight) / (float) 800));
						tempWidth = ((int) (((float) 111 * (float) viewWidth) / (float) 480));
						tempHeight = ((int) (((float) 111 * (float) viewHight) / (float) 800));
						l05_01_img_pic = new ImageView(context);
						params = new LinearLayout.LayoutParams(tempWidth, tempHeight);
						params.setMargins(paddingWidth, paddingHeight, 0, 0);
						l05_01_img_pic.setLayoutParams(params);
						linear2.addView(l05_01_img_pic);
					}

					paddingWidth = ((int) (((float) 170 * (float) viewWidth) / (float) 480));
					LinearLayout linear3 = new LinearLayout(context);
					linear3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
					linear3.setOrientation(LinearLayout.VERTICAL);
					linear3.setBackgroundResource(R.drawable.golf_friend_chat_bg);
					linear3.setPadding(paddingWidth, 0, 0, 0);
					linear3.setGravity(Gravity.CENTER_VERTICAL);
					relative.addView(linear3);
					{
						TextView text1 = new TextView(context);
						text1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
						text1.setText("전화번호");
						linear3.addView(text1);

						l05_01_text_myphone = new TextView(context);
						l05_01_text_myphone.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
						l05_01_text_myphone.setTextColor(Color.WHITE);
						linear3.addView(l05_01_text_myphone);
					}
				}
				paddingWidth = ((int) (((float) 20 * (float) viewWidth) / (float) 480));
				LinearLayout linear3 = new LinearLayout(context);
				linear3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				linear3.setPadding(0, paddingWidth, 0, 0);
				linear3.setBackgroundColor(Color.rgb(231, 234, 223));
				linear3.setOrientation(LinearLayout.VERTICAL);
				linear3.setGravity(Gravity.CENTER_HORIZONTAL);
				linear.addView(linear3);
				{
					tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
					paddingWidth = ((int) (((float) 140 * (float) viewWidth) / (float) 480));
					i05_01_text_name = new TextView(context);
					i05_01_text_name.setPadding(paddingWidth, 0, 0, 0);
					i05_01_text_name.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
					i05_01_text_name.setTextColor(Color.BLACK);
					i05_01_text_name.setGravity(Gravity.CENTER_VERTICAL);
					i05_01_text_name.setText("dkdkdkdk");
					i05_01_text_name.setBackgroundResource(R.drawable.golf_setup_profile_name_bg_off);
					linear3.addView(i05_01_text_name);
					
					tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
					paddingWidth = ((int) (((float) 140 * (float) viewWidth) / (float) 480));
					i05_01_text_sex = new TextView(context);
					i05_01_text_sex.setPadding(paddingWidth, 0, 0, 0);
					i05_01_text_sex.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
					i05_01_text_sex.setTextColor(Color.BLACK);
					i05_01_text_sex.setGravity(Gravity.CENTER_VERTICAL);
					i05_01_text_sex.setText("dkdkdkdk");
					i05_01_text_sex.setBackgroundResource(R.drawable.golf_setup_profile_sex_bg_off);
					linear3.addView(i05_01_text_sex);
					
					tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
					paddingWidth = ((int) (((float) 140 * (float) viewWidth) / (float) 480));
					i05_01_text_grade = new TextView(context);
					i05_01_text_grade.setPadding(paddingWidth, 0, 0, 0);
					i05_01_text_grade.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
					i05_01_text_grade.setTextColor(Color.BLACK);
					i05_01_text_grade.setGravity(Gravity.CENTER_VERTICAL);
					i05_01_text_grade.setText("dkdkdkdk");
					i05_01_text_grade.setBackgroundResource(R.drawable.golf_setup_profile_class_bg_off);
					linear3.addView(i05_01_text_grade);
					
					tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
					paddingWidth = ((int) (((float) 140 * (float) viewWidth) / (float) 480));
					i05_01_text_message = new TextView(context);
					i05_01_text_message.setPadding(paddingWidth, 0, 0, 0);
					i05_01_text_message.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
					i05_01_text_message.setTextColor(Color.BLACK);
					i05_01_text_message.setGravity(Gravity.CENTER_VERTICAL);
					i05_01_text_message.setText("dkdkdkdk");
					i05_01_text_message.setBackgroundResource(R.drawable.golf_setup_profile_mass_bg_off);
					linear3.addView(i05_01_text_message);
					
					tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
					paddingWidth = ((int) (((float) 140 * (float) viewWidth) / (float) 480));
					i05_01_text_address = new TextView(context);
					i05_01_text_address.setPadding(paddingWidth, 0, 0, 0);
					i05_01_text_address.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
					i05_01_text_address.setTextColor(Color.BLACK);
					i05_01_text_address.setGravity(Gravity.CENTER_VERTICAL);
					i05_01_text_address.setText("dkdkdkdk");
					i05_01_text_address.setBackgroundResource(R.drawable.golf_setup_profile_add_bg_off);
					linear3.addView(i05_01_text_address);
				}
			}
		}
		return b00_01_barlayout;
	}
}
