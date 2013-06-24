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

public class LocationsActivity extends Activity implements UserNotificationListener
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
		
		/*
		for (Question question : Game.getGame().getQuestions())
		{
			this.mapView.getOverlays().add(new BalloonMapMarker(
					getResources().getDrawable(R.drawable.launcher_icon), this.mapView,
					new OverlayItem(question.getLocation(),
							"Question", null), question));
		}

		this.mapView.getController().setZoom(17);
		
		*/
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		//Game.getGame().getUser().addUserNotificationListener(this);
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		//Game.getGame().getUser().removeUserNotificationListener(this);
	}

	@Override
	public void onLocationUpdated(User user)
	{
		Log.d("Location cahanged", "tick");

//		if (Game.getGame().getUser().getLocation() != null)
//		{
//			String latitude = String.valueOf(Game.getGame().getUser().getLocation().getLatitude());
//			String longitude = String.valueOf(Game.getGame().getUser().getLocation().getLongitude());
//					
//			this.latTextView.setText("Latitude: " + latitude);
//			this.lonTextView.setText("Longitude: " + longitude);
//		}
	}
}
