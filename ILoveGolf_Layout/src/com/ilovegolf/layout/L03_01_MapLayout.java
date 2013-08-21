package com.ilovegolf.layout;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilovegolf.R;

public class L03_01_MapLayout extends B00_01_BarLayout{
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
	
		b00_01_text_bar.setBackgroundResource(R.drawable.golf_map_bar);

		return b00_01_barlayout;
	}
}
