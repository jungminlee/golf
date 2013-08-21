package com.ilovegolf.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilovegolf.R;
import com.ilovegolf.layout.I05_01_ProfileItem;
import com.ilovegolf.struct.ProfileItem;
import com.ilovegolf.util.StringHashMap;

public class A05_01_ProfileAdapter extends BaseAdapter {
	ArrayList<ProfileItem> ChangeProfileList = null;

	Context context;
	Display display;

	public A05_01_ProfileAdapter(Context context, Display display, ArrayList<ProfileItem> ChangeProfileList) {
		this.context = context;
		this.display = display;
		this.ChangeProfileList = ChangeProfileList;
	}

	@Override
	public int getCount() {
		return ChangeProfileList.size();
	}

	@Override
	public Object getItem(int id) {
		return ChangeProfileList.get(id);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int id, View convertView, ViewGroup parent) {

		ProfileItem profile = ChangeProfileList.get(id);
		
		I05_01_ProfileItem layout = new I05_01_ProfileItem();
		convertView = layout.getLayout(context, display);

		TextView text_content = layout.i05_01_text_content;

		text_content.setText(profile.strContent);
		
		if(id==1) {
			LinearLayout linear=layout.linear;
			linear.setBackgroundResource(R.drawable.golf_setup_profile_sex_bg_off);
		} else if(id==2) {
			LinearLayout linear=layout.linear;
			linear.setBackgroundResource(R.drawable.golf_setup_profile_class_bg_off);
		} else if(id==3) {
			LinearLayout linear=layout.linear;
			linear.setBackgroundResource(R.drawable.golf_setup_profile_mass_bg_off);
		} else if(id==4) {
			LinearLayout linear=layout.linear;
			linear.setBackgroundResource(R.drawable.golf_setup_profile_add_bg_off);
		} 
		
		return convertView;
	}

}
