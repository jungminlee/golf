package com.ilovegolf.layout;

import com.ilovegolf.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class I05_02_AlarmSettingItem2 {
	public LinearLayout i05_02_alarmsettingitem2 = null;
	public CheckBox i05_02_check_alarm=null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		
		i05_02_alarmsettingitem2 = new LinearLayout(context);
		// i05_01_profileitem1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		{
			tempWidth = ((int) (((float) 436 * (float) viewWidth) / (float) 480));
			tempHeight = ((int) (((float) 48 * (float) viewHight) / (float) 800));
			LinearLayout linear = new LinearLayout(context);
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(new LayoutParams(tempWidth, tempHeight));
			param.weight = 1;
			linear.setLayoutParams(param);	
			linear.setOrientation(LinearLayout.VERTICAL);
			linear.setBackgroundResource(R.drawable.golf_setup_vibrate_bg);
			i05_02_alarmsettingitem2.addView(linear);
			{
				TextView text = new TextView(context);
				text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				text.setTextColor(Color.BLACK);
				text.setText("진동알림");
				linear.addView(text);
			}
			i05_02_check_alarm=new CheckBox(context);
			i05_02_check_alarm.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
			i05_02_check_alarm.setGravity(Gravity.CENTER_VERTICAL);
			i05_02_alarmsettingitem2.addView(i05_02_check_alarm);
		}
		return i05_02_alarmsettingitem2;
	}
}


