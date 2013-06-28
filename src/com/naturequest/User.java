package com.naturequest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.naturequest.serverapi.QuestAPI;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class User
{
	private String username;
	private int score;

	private Context context;

	private Location location;
	private LocationListener locationListener;

	private List<UserNotificationListener> userNotificationListeners = new ArrayList<UserNotificationListener>();
	private String token;
	private String picture;

	
	public User(){
		
	}
	
	public User(String username, String authToken)
	{
		this.username = username;		
		this.token = authToken;
	}
	
	
	

	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public int getScore()
	{
		return this.score;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public void addUserNotificationListener(UserNotificationListener userNotificationListener) 
	{
		if (!this.userNotificationListeners.contains(userNotificationListener))
		{
			this.userNotificationListeners.add(userNotificationListener);
		}

		if (!this.userNotificationListeners.isEmpty())
		{
			registerForLocationUpdates();
		}
	}

	public void removeUserNotificationListener(UserNotificationListener userNotificationListener) 
	{
		this.userNotificationListeners.remove(userNotificationListener);

		if (this.userNotificationListeners.isEmpty())
		{
			unregisterForLocationUpdates();
		}
	}

	private void registerForLocationUpdates()
	{
		Log.d("REGISTER", "tick");
		
		LocationManager locationManager = (LocationManager)this.context.getSystemService(Context.LOCATION_SERVICE);

		setLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));

		if (this.location == null)
		{
			setLocation(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
		}

		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this.locationListener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this.locationListener);
	}

	private void unregisterForLocationUpdates()
	{
		LocationManager locationManager = (LocationManager)this.context.getSystemService(Context.LOCATION_SERVICE);
		locationManager.removeUpdates(this.locationListener);
	}

	public Location getLocation()
	{
		return this.location;
	}
	
	public void setLocation(Location location)
	{
		this.location = location;
		
		for (UserNotificationListener listener : this.userNotificationListeners)
		{
			listener.onLocationUpdated(this);
		}
	}

	public GeoPoint getGeoPointLocation()
	{      
		if (this.location == null)
		{
			return null;
		}
		else
		{
			int lat = (int)(this.location.getLatitude() * 1E6);
			int lng = (int)(this.location.getLongitude() * 1E6);
			
			return new GeoPoint(lat, lng);
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
		
	}
	
	public String getInfo()
	{
		return  "Name - " + this.username + "\n" +
				"Score - " + this.score + "\n";
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getPicture(){
		return this.picture;
	}
}
