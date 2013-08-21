package com.ilovegolf.layout;

import com.ilovegolf.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class I02_00_FavoriteTitleItem {
	LinearLayout i02_00_favoritetitleitem=null;
	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		i02_00_favoritetitleitem = new LinearLayout(context);
		{
			tempHeight = ((int) (((float) 47 * (float) viewHight) / (float) 800));
			LinearLayout linear = new LinearLayout(context);
			linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			linear.setGravity(Gravity.CENTER_VERTICAL);
			linear.setBackgroundResource(R.drawable.main_favorite_list_bar);
			i02_00_favoritetitleitem.addView(linear);
//			{
//				tempWidth = ((int) (((float) 15 * (float) viewWidth) / (float) 480));
//				View temp=new View(context);
//				temp.setLayoutParams(new LayoutParams(tempWidth, LayoutParams.MATCH_PARENT));
//				linear.addView(temp);
//				
//				tempHeight = ((int) (((float) 20 * (float) viewHight) / (float) 800));
//				tempWidth = ((int) (((float) 20 * (float) viewWidth) / (float) 480));
//				View icon=new View(context);				
//				icon.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
//				icon.setBackgroundResource(R.drawable.titlebar_ico_favorite);
//				linear.addView(icon);
//
//				paddingWidth = ((int) (((float) 5 * (float) viewWidth) / (float) 480));
//				TextView title=new TextView(context);
//				LinearLayout.LayoutParams param1=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//				param1.setMargins(paddingWidth, 0, 0, 0);
//				title.setLayoutParams(param1);
//				title.setText("¡Ò∞‹√£±‚");
//				title.setTextSize(10);
//				title.setTextColor(Color.WHITE);
//				linear.addView(title);
//			}
		}
		
		return i02_00_favoritetitleitem;
		
	}
}
