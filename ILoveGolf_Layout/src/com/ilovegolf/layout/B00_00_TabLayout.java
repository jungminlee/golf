package com.ilovegolf.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ilovegolf.R;

public class B00_00_TabLayout {

	public RelativeLayout b00_00_TabLayout = null;
	public View b00_00_tab_friend = null;
	public View b00_00_tab_findlink = null;
	public View b00_00_tab_talkroomlist = null;
	public View b00_00_tab_setting = null;
	public LinearLayout b00_00_linear_content = null;
	public ListView b00_00_list_content = null;
	public ImageView b00_00_img_pic = null;
	public LinearLayout linear=null;

	public View getLayout(Context context, Display display) {
		int viewHight = display.getHeight();
		int viewWidth = display.getWidth();
		int tempWidth = 0;
		int tempHeight = 0;
		int paddingtop = 0;
		int paddingbottom = 0;
		int paddingleft = 0;
		int paddingright = 0;

		// tempWidth = ((int) (((float) 65 * (float) viewWidth) / (float) 480));
		// tempHeight = ((int) (((float) 44 * (float) viewHight) / (float) 800));

		b00_00_TabLayout = new RelativeLayout(context);
		b00_00_TabLayout.setBackgroundColor(Color.WHITE);
		b00_00_TabLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		{
			linear = new LinearLayout(context);
			linear.setOrientation(LinearLayout.VERTICAL);
			linear.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			b00_00_TabLayout.addView(linear);
			{
				LinearLayout linear2 = new LinearLayout(context);
				linear2.setOrientation(LinearLayout.HORIZONTAL);
				linear2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				linear.addView(linear2);
				{

					tempWidth = ((int) (((float) 120 * (float) viewWidth) / (float) 480));
					tempHeight = ((int) (((float) 80 * (float) viewHight) / (float) 800));

					b00_00_tab_friend = new View(context);
					b00_00_tab_friend.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
					b00_00_tab_friend.setBackgroundResource(R.drawable.main_topbar_menu_01_off);
					linear2.addView(b00_00_tab_friend);

					b00_00_tab_findlink = new View(context);
					b00_00_tab_findlink.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
					b00_00_tab_findlink.setBackgroundResource(R.drawable.main_topbar_menu_02_off);
					linear2.addView(b00_00_tab_findlink);

					b00_00_tab_talkroomlist = new View(context);
					b00_00_tab_talkroomlist.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
					b00_00_tab_talkroomlist.setBackgroundResource(R.drawable.main_topbar_menu_03_off);
					linear2.addView(b00_00_tab_talkroomlist);

					b00_00_tab_setting = new View(context);
					b00_00_tab_setting.setLayoutParams(new LayoutParams(tempWidth, tempHeight));
					b00_00_tab_setting.setBackgroundResource(R.drawable.main_topbar_menu_04_off);
					linear2.addView(b00_00_tab_setting);
				}

				b00_00_linear_content = new LinearLayout(context);
				b00_00_linear_content.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				b00_00_linear_content.setOrientation(LinearLayout.VERTICAL);
				linear.addView(b00_00_linear_content);
				{
				}

				b00_00_list_content = new ListView(context);
				b00_00_list_content.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				b00_00_list_content.setDivider(null);
				linear.addView(b00_00_list_content);
			}
		}
		return b00_00_TabLayout;
	}
}
