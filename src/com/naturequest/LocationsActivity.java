package com.naturequest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.naturequest.R;

public class LocationsActivity extends Activity
{
	private TextView latTextView;
	private TextView lonTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locations);

		this.latTextView = (TextView)findViewById(R.id.latTextView);
		this.lonTextView = (TextView)findViewById(R.id.LngTextView);		
		
		
	}

	
}
