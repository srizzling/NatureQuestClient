package com.naturequest;


import com.naturequest.R;
import com.naturequest.camera.CameraActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainMenuActivity extends TabActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);

		TabHost tabHost = getTabHost();

		TabSpec locationsTab = tabHost.newTabSpec("Locations");
		locationsTab.setIndicator(null, getResources().getDrawable(R.drawable.locations_button));
		Intent locationsIntent = new Intent(this, LocationsActivity.class);
		locationsTab.setContent(locationsIntent);

		TabSpec cameraTab = tabHost.newTabSpec("Camera");
		cameraTab.setIndicator(null, getResources().getDrawable(R.drawable.locations_button));
		Intent cameraIntent = new Intent(this, CameraActivity.class);
		cameraTab.setContent(cameraIntent);

		TabSpec leaderboardTab = tabHost.newTabSpec("Leaderboard");
		leaderboardTab.setIndicator(null, getResources().getDrawable(R.drawable.locations_button));
		Intent leaderboardIntent = new Intent(this, LeaderboardActivity.class);
		leaderboardTab.setContent(leaderboardIntent);

		TabSpec profileTab = tabHost.newTabSpec("Profile");
		profileTab.setIndicator(null, getResources().getDrawable(R.drawable.locations_button));
		Intent profileIntent = new Intent(this, ProfileActivity.class);
		profileTab.setContent(profileIntent);
		
		TabSpec helpTab = tabHost.newTabSpec("Help");
		helpTab.setIndicator(null, getResources().getDrawable(R.drawable.locations_button));
		Intent helpIntent = new Intent(this, HelpActivity.class);
		helpTab.setContent(helpIntent);
		
		tabHost.addTab(locationsTab);
		tabHost.addTab(cameraTab);
		tabHost.addTab(leaderboardTab);
		tabHost.addTab(profileTab);
		tabHost.addTab(helpTab);
		
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++)
		{
			tabHost.getTabWidget().getChildAt(i).setPadding(0,0,0,0);
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if(Game.getGame()==null){
			Intent login = new Intent(this, LoginActivity.class);
			startActivity(login);
		}
	}
}