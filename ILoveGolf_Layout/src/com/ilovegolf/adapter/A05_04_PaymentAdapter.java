package com.ilovegolf.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ilovegolf.layout.I05_04_PaymentItem;

public class A05_04_PaymentAdapter extends BaseAdapter{
	ArrayList<String> PaymentList = null;
	
	Context context;
	Display display;
	
	public A05_04_PaymentAdapter(Context context,Display display, ArrayList<String> PaymentList) {
		this.context = context;
		this.display=display;
		this.PaymentList = PaymentList;
	}
	
	@Override
	public int getCount() {
		return PaymentList.size();
	}

	@Override
	public Object getItem(int id) {
		return PaymentList.get(id);
	}

	
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int id, View convertView, ViewGroup parent) {
		if (convertView == null) {
				I05_04_PaymentItem layout=new I05_04_PaymentItem();
				convertView=layout.getLayout(context, display);
				
				TextView text_payment=layout.i05_04_text_payment;
				text_payment.setText(PaymentList.get(id));
				
		}
		return convertView;
	}

}
