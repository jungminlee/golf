package com.ilovegolf.layout;

import com.ilovegolf.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class L05_04_paymentLayout extends B00_01_BarLayout{
	public ListView l05_04_list_payment=null;
	public View l05_04_btn_pay=null;
	@Override
	public View getLayout(Context context, Display display) {
		super.getLayout(context, display);
		
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingWidth = 0;
		int paddingHeight = 0;

		b00_01_text_bar.setBackgroundResource(R.drawable.golf_payment_bar);
		
		l05_04_list_payment=new ListView(context);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		param.weight = 1;
		l05_04_list_payment.setLayoutParams(param);
		b00_01_linear_content.addView(l05_04_list_payment);
		

//		paddingWidth = ((int) (((float) 25 * (float) viewWidth) / (float) 480));
//		paddingHeight = ((int) (((float) 25 * (float) viewHight) / (float) 800));
//		LinearLayout linear2=new LinearLayout(context);
//		linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//		linear2.setPadding(paddingWidth, paddingHeight, paddingWidth, paddingHeight);
//		linear2.setGravity(Gravity.CENTER_HORIZONTAL);
//		b00_01_linear_content.addView(linear2);
//		{
//			tempWidth = ((int) (((float) 108 * (float) viewWidth) / (float) 480));
//			tempHeight = ((int) (((float) 49 * (float) viewHight) / (float) 800));
//			l05_04_btn_pay=new View(context);
//			l05_04_btn_pay.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
//			l05_04_btn_pay.setBackgroundResource(R.drawable.btn_payment_off);
//			linear2.addView(l05_04_btn_pay);
//		}
		return b00_01_barlayout;
	}
}
