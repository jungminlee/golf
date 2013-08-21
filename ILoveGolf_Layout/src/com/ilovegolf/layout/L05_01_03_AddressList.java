package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;

public class L05_01_03_AddressList{
	public ListView l05_01_03_list_address=null;
	
	public View getLayout(Context context, Display display) {
		
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;


		LinearLayout linear=new LinearLayout(context);
		linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		linear.setGravity(Gravity.CENTER);
		{
			tempWidth = ((int) (((float) 300 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 600 * (float) viewHight) / (float) 800));
			l05_01_03_list_address=new ListView(context);
			l05_01_03_list_address.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
			l05_01_03_list_address.setBackgroundColor(Color.WHITE);
			linear.addView(l05_01_03_list_address);
		}
		return linear;
	}
}
