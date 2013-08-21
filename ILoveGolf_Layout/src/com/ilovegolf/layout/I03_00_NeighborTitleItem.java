package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilovegolf.R;

public class I03_00_NeighborTitleItem {
	LinearLayout i03_00_neighbortitleitem=null;
	public View i03_00_btn_myarea=null;
	public View i03_00_btn_otherarea=null;
	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		i03_00_neighbortitleitem = new LinearLayout(context);
		{
			tempHeight = ((int) (((float) 47 * (float) viewHight) / (float) 800));
			LinearLayout linear = new LinearLayout(context);
			linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			linear.setGravity(Gravity.CENTER_VERTICAL);
			linear.setBackgroundResource(R.drawable.golf_near_list_bar);
			i03_00_neighbortitleitem.addView(linear);
			{
				tempWidth = ((int) (((float) 15 * (float) viewWidth) / (float) 480));
				View temp=new View(context);
				temp.setLayoutParams(new LayoutParams(tempWidth, LayoutParams.MATCH_PARENT));
				linear.addView(temp);
				
				tempHeight = ((int) (((float) 20 * (float) viewHight) / (float) 800));
				tempWidth = ((int) (((float) 20 * (float) viewWidth) / (float) 480));
				View icon=new View(context);				
				icon.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
//				icon.setBackgroundResource(R.drawable.titlebar_ico_shop);
				linear.addView(icon);

				paddingWidth = ((int) (((float) 5 * (float) viewWidth) / (float) 480));
				TextView title=new TextView(context);
				LinearLayout.LayoutParams param1=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				param1.setMargins(paddingWidth, 0, 0, 0);
				param1.weight=1;
				title.setLayoutParams(param1);
//				title.setText("나와 가까이에 있는 골프장");
				title.setTextSize(10);
				title.setTextColor(Color.WHITE);
				linear.addView(title);
				
				paddingWidth = ((int) (((float) 15 * (float) viewWidth) / (float) 480));
				LinearLayout linear2=new LinearLayout(context);
				LinearLayout.LayoutParams param2=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				param2.setMargins(0, 0, paddingWidth, 0);
				linear2.setLayoutParams(param2);
				linear2.setGravity(Gravity.CENTER_VERTICAL);
				linear.addView(linear2);
				{
					paddingWidth = ((int) (((float) 5 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 40 * (float) viewHight) / (float) 800));
					tempWidth = ((int) (((float) 82 * (float) viewWidth) / (float) 480));
					i03_00_btn_myarea=new View(context);
					LinearLayout.LayoutParams param3=new LinearLayout.LayoutParams(tempWidth, tempHeight);
					param3.setMargins(0, 0, paddingWidth, 0);
					i03_00_btn_myarea.setLayoutParams(param3);
					i03_00_btn_myarea.setBackgroundResource(R.drawable.btn_my_area);
					linear2.addView(i03_00_btn_myarea);
					
					i03_00_btn_otherarea=new View(context);
					i03_00_btn_otherarea.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
					i03_00_btn_otherarea.setBackgroundResource(R.drawable.btn_other_area);
					linear2.addView(i03_00_btn_otherarea);
					
				}
			}
		}
		
		return i03_00_neighbortitleitem;
		
	}
}
