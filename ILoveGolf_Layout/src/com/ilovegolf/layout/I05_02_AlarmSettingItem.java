package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class I05_02_AlarmSettingItem {
	public LinearLayout i05_02_alarmsettingitem1 = null;
	public CheckBox i05_02_check_alarm=null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		paddingWidth = ((int) (((float) 25 * (float) viewWidth) / (float) 480));
		paddingHeight = ((int) (((float) 25 * (float) viewHight) / (float) 800));
		i05_02_alarmsettingitem1 = new LinearLayout(context);
		i05_02_alarmsettingitem1.setPadding(paddingWidth, paddingHeight, paddingWidth, paddingHeight);	
		// i05_01_profileitem1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		{
			LinearLayout linear = new LinearLayout(context);
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
			param.weight = 1;
			linear.setLayoutParams(param);	
			linear.setOrientation(LinearLayout.VERTICAL);
			i05_02_alarmsettingitem1.addView(linear);
			{
				TextView text = new TextView(context);
				text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				text.setTextColor(Color.BLACK);
				text.setText("골프요청도착알림");
				linear.addView(text);

				text = new TextView(context);
				text.setPadding(0, paddingHeight, 0, 0);	
				text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				text.setTextSize(13);
				text.setText("아이러브골프를 실행하지 않을 때 푸시 알림을 받습니다.");
				linear.addView(text);
			}
			i05_02_check_alarm=new CheckBox(context);
			i05_02_check_alarm.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
			i05_02_check_alarm.setGravity(Gravity.CENTER_VERTICAL);
			i05_02_alarmsettingitem1.addView(i05_02_check_alarm);
		}
		return i05_02_alarmsettingitem1;
	}
}
