package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class I05_04_PaymentItem {
	public LinearLayout i05_04_paymentitem1=null;
	public TextView i05_04_text_payment=null;
	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;
		
		i05_04_paymentitem1 = new LinearLayout(context);
		i05_04_paymentitem1.setBackgroundColor(Color.WHITE);
		{
			tempHeight=60;
			paddingWidth = ((int) (((float) 25 * (float) viewWidth) / (float) 480));
			LinearLayout linear=new LinearLayout(context);
			linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, tempHeight));
			linear.setPadding(paddingWidth, 0, 0, 0);	
			linear.setGravity(Gravity.CENTER_VERTICAL);
			i05_04_paymentitem1.addView(linear); 
			{
				i05_04_text_payment=new TextView(context);
				i05_04_text_payment.setTextColor(Color.BLACK);
				linear.addView(i05_04_text_payment);
			}
		}
		return i05_04_paymentitem1;
	}
}
