package com.ilovegolf;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ilovegolf.layout.L03_01_MapLayout;
import com.ilovegolf.util.BaseActivity;
import com.ilovegolf.util.StaticClass;

public class A03_01_MapView extends BaseActivity {
	L03_01_MapLayout layout = null;
	Geocoder coder = null;
	String x = "";
	String y = "";
	List<Address> addr = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new L03_01_MapLayout();
		// setContentView(R.layout.l03_01_mapview);
		setContentView(layout.getLayout(context, display));

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

		Intent intent = getIntent();
		x = intent.getStringExtra("y");
		y = intent.getStringExtra("x");

		try {
			double slat = Double.parseDouble(x);
			double slon = Double.parseDouble(y);

			LatLng l = new LatLng(slat, slon);

			View v = getLayoutInflater().inflate(R.layout.l03_01_mapview, null);
			layout.b00_01_linear_content.addView(v);

			GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

			CameraPosition.Builder builder = new CameraPosition.Builder();
			builder.target(l);
			builder.zoom(17f);
			CameraPosition positon = builder.build();

			CameraUpdate update = CameraUpdateFactory.newCameraPosition(positon);
			map.moveCamera(update);

			MarkerOptions marker = new MarkerOptions();
			marker.position(l);
			map.addMarker(marker);
		} catch (Exception e) {
			Log.e("error", e.getMessage() + "");
			StaticClass.alert = new AlertDialog.Builder(context).create();
			StaticClass.alert.setTitle("아이러브골프");
			StaticClass.alert.setMessage("MapView를 실행 할 수 없습니다.\n단말기를 재 실행해 주세요.");
			StaticClass.alert.setButton(AlertDialog.BUTTON_NEUTRAL, "확인", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
			StaticClass.alert.show();
		}
	}
}
