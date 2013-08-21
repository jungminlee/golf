package com.ilovegolf.adapter;

import java.util.ArrayList;

import com.ilovegolf.R;
import com.ilovegolf.layout.I05_00_SettingMenuItem;
import com.ilovegolf.layout.I05_01_ProfileItem;
import com.ilovegolf.struct.ProfileItem;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class A05_00_SettingMenuAdapter extends BaseAdapter {
	ArrayList<String> menuList = null;

	Context context;
	Display display;

	I05_00_SettingMenuItem layout = null;

	public A05_00_SettingMenuAdapter(Context context, Display display, ArrayList<String> menuList) {
		this.context = context;
		this.display = display;
		this.menuList = menuList;
	}

	@Override
	public int getCount() {
		return menuList.size();
	}

	@Override
	public Object getItem(int id) {
		return menuList.get(id);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int id, View convertView, ViewGroup parent) {
		layout = new I05_00_SettingMenuItem();
		convertView = layout.getLayout(context, display);
		TextView text = layout.i05_00_text_subject;
		LinearLayout linear=layout.linear;
		if (id == 0) {
			linear.setBackgroundResource(R.drawable.golf_setup_profile_line);
			convertView.setTag(id);
			text.setText(menuList.get(id));
		} else if (id == 1) {
			linear.setBackgroundResource(R.drawable.golf_setup_alam_line);
			convertView.setTag(id);
			text.setText(menuList.get(id));
		} else if (id == 2) {
			linear.setBackgroundResource(R.drawable.golf_setup_qna_line);
			convertView.setTag(id);
			text.setText(menuList.get(id));
		} else if (id == 3) {
			linear.setBackgroundResource(R.drawable.golf_setup_pay_line);
			convertView.setTag(id);
			text.setText(menuList.get(id));
		}
		return convertView;
	}
}
