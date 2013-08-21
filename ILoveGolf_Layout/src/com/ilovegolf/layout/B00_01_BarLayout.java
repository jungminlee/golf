package com.ilovegolf.layout;

import com.ilovegolf.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class B00_01_BarLayout {
	public RelativeLayout b00_01_barlayout=null;
	public TextView b00_01_text_bar=null;
	public LinearLayout b00_01_linear_content=null;
	
	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int tempWidthPadding=0;
		// tempWidth = ((int) (((float) 65 * (float) viewWidth) / (float) 480));
		// tempHeight = ((int) (((float) 44 * (float) viewHight) / (float) 800));

		b00_01_barlayout = new RelativeLayout(context);
		b00_01_barlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		{
			LinearLayout linear=new LinearLayout(context);
			linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			linear.setOrientation(LinearLayout.VERTICAL);
			b00_01_barlayout.addView(linear);
			{
				tempWidthPadding=((int) (((float) 25 * (float) viewWidth) / (float) 480));
				tempHeight = ((int) (((float) 47 * (float) viewHight) / (float) 800));
				b00_01_text_bar=new TextView(context);
				b00_01_text_bar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
				b00_01_text_bar.setBackgroundResource(R.drawable.golf_profile_setup_bar);
				b00_01_text_bar.setPadding(tempWidthPadding, 0, 0, 0);
				b00_01_text_bar.setGravity(Gravity.CENTER_VERTICAL);
				b00_01_text_bar.setTextColor(Color.WHITE);
				linear.addView(b00_01_text_bar);
				
				b00_01_linear_content=new LinearLayout(context);
				b00_01_linear_content.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				b00_01_linear_content.setOrientation(LinearLayout.VERTICAL);
				b00_01_linear_content.setBackgroundColor(Color.rgb(231, 234, 223));
				linear.addView(b00_01_linear_content);
			}
		}
		return b00_01_barlayout;
	}
}
