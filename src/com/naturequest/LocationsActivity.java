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
import com.naturequest.radar.RadarActivity;

public class LocationsActivity extends Activity
{
	private TextView latTextView;
	private TextView lonTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locations);

			
		
		
	}
	
	@Override
	protected void onResume() {		
		super.onResume();
		Intent intent = new Intent(this, RadarActivity.class);

		
		
		intent.putExtra("latitude", (float)-41.209066);
        intent.putExtra("longitude", (float)174.800470);
        startActivity(intent);
	}

	
}
