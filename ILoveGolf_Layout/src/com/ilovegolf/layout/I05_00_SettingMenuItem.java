package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class I05_00_SettingMenuItem {
	public LinearLayout i05_00_settingmenuitem = null;
	public TextView i05_00_text_subject = null;
	public LinearLayout linear=null;
	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		i05_00_settingmenuitem = new LinearLayout(context);
		// i05_00_profileitem1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		i05_00_settingmenuitem.setOrientation(LinearLayout.VERTICAL);
		{
			tempHeight = ((int) (((float) 81 * (float) viewHight) / (float) 800));
			linear = new LinearLayout(context);
			linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			linear.setOrientation(LinearLayout.VERTICAL);
			i05_00_settingmenuitem.addView(linear);
			{
				paddingWidth = ((int) (((float) 90 * (float) viewWidth) / (float) 480));
				i05_00_text_subject = new TextView(context);
				LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				params.setMargins(paddingWidth, 0, 0, 0);
				i05_00_text_subject.setLayoutParams(params);
				i05_00_text_subject.setGravity(Gravity.CENTER_VERTICAL);
				i05_00_text_subject.setTextColor(Color.BLACK);
				linear.addView(i05_00_text_subject);
			}
		}

		return i05_00_settingmenuitem;
	}
}
