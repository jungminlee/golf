
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

public class L05_00_SettingLayout extends B00_01_BarLayout {
	public TextView l05_00_btn_profile = null;
	public TextView l05_00_btn_alarm = null;
	public TextView l05_00_btn_qna = null;
	public TextView l05_00_btn_pay = null;
	public TextView l05_00_btn_notice = null;

	public View getLayout(Context context, Display display) {
		super.getLayout(context, display);

		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempHeight = 0;
		int paddingWidth = 0;

		b00_01_text_bar.setBackgroundResource(R.drawable.golf_profile_setup_bar);
		
		tempHeight = ((int) (((float) 81 * (float) viewHight) / (float) 800));
		paddingWidth = ((int) (((float) 90 * (float) viewWidth) / (float) 480));
		l05_00_btn_profile = new TextView(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, tempHeight);
		l05_00_btn_profile.setLayoutParams(params);
		l05_00_btn_profile.setGravity(Gravity.CENTER_VERTICAL);
		l05_00_btn_profile.setTextColor(Color.BLACK);
		l05_00_btn_profile.setPadding(paddingWidth, 0, 0, 0);
		l05_00_btn_profile.setBackgroundResource(R.drawable.golf_setup_profile_line);
		l05_00_btn_profile.setText("프로필 변경");
		b00_01_linear_content.addView(l05_00_btn_profile);

		l05_00_btn_alarm = new TextView(context);
		l05_00_btn_alarm.setLayoutParams(params);
		l05_00_btn_alarm.setGravity(Gravity.CENTER_VERTICAL);
		l05_00_btn_alarm.setTextColor(Color.BLACK);
		l05_00_btn_alarm.setPadding(paddingWidth, 0, 0, 0);
		l05_00_btn_alarm.setBackgroundResource(R.drawable.golf_setup_alam_line);
		l05_00_btn_alarm.setText("알림 설정");
		b00_01_linear_content.addView(l05_00_btn_alarm);

		l05_00_btn_qna = new TextView(context);
		l05_00_btn_qna.setLayoutParams(params);
		l05_00_btn_qna.setGravity(Gravity.CENTER_VERTICAL);
		l05_00_btn_qna.setTextColor(Color.BLACK);
		l05_00_btn_qna.setPadding(paddingWidth, 0, 0, 0);
		l05_00_btn_qna.setBackgroundResource(R.drawable.golf_setup_qna_line);
		l05_00_btn_qna.setText("문의 하기");
		b00_01_linear_content.addView(l05_00_btn_qna);

		l05_00_btn_pay = new TextView(context);
		l05_00_btn_pay.setLayoutParams(params);
		l05_00_btn_pay.setGravity(Gravity.CENTER_VERTICAL);
		l05_00_btn_pay.setTextColor(Color.BLACK);
		l05_00_btn_pay.setPadding(paddingWidth, 0, 0, 0);
		l05_00_btn_pay.setBackgroundResource(R.drawable.golf_setup_pay_line);
		l05_00_btn_pay.setText("결제 내역");
		b00_01_linear_content.addView(l05_00_btn_pay);

		l05_00_btn_notice = new TextView(context);
		l05_00_btn_notice.setLayoutParams(params);
		l05_00_btn_notice.setGravity(Gravity.CENTER_VERTICAL);
		l05_00_btn_notice.setTextColor(Color.BLACK);
		l05_00_btn_notice.setPadding(paddingWidth, 0, 0, 0);
		l05_00_btn_notice.setBackgroundResource(R.drawable.golf_setup_info_line_off);
		l05_00_btn_notice.setText("이용 안내");
		b00_01_linear_content.addView(l05_00_btn_notice);
		
		return b00_01_barlayout;
	}
}
