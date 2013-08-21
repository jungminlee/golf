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

public class I05_01_ProfileItem {
	public LinearLayout i05_01_profileitem1 = null;
	public TextView i05_01_text_subject = null;
	public TextView i05_01_text_content = null;
	public LinearLayout linear=null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		
		i05_01_profileitem1 = new LinearLayout(context);
		// i05_01_profileitem1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		i05_01_profileitem1.setOrientation(LinearLayout.VERTICAL);
		{
			tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
			linear = new LinearLayout(context);
			linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			linear.setGravity(Gravity.CENTER_VERTICAL);
			linear.setBackgroundResource(R.drawable.golf_setup_profile_name_bg_off);
			i05_01_profileitem1.addView(linear);
			{
				paddingWidth = ((int) (((float) 140 * (float) viewWidth) / (float) 480));
				i05_01_text_content = new TextView(context);
				LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				params.setMargins(paddingWidth, 0, 0, 0);
				i05_01_text_content.setLayoutParams(params);
				i05_01_text_content.setTextColor(Color.BLACK);
				linear.addView(i05_01_text_content);
			}
		}

		return i05_01_profileitem1;
	}
}
